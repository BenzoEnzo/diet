package pl.bartus.jakub.diet.data.integration

import com.typesafe.config.ConfigFactory
import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.functions.{sum, _}

object Main {
  def main(args: Array[String]): Unit = {
    val config = ConfigFactory.load()

    val mongoUri = config.getString("mongodb.uri")
    val mealCollection = config.getString("mongodb.mealCollection")
    val productCollection = config.getString("mongodb.productCollection")
    val postgresUrl = config.getString("postgres.url")
    val postgresUser = config.getString("postgres.user")
    val postgresPass = config.getString("postgres.password")
    val mealTable = config.getString("postgres.mealTable")

    val spark = SparkSession.builder()
      .appName("data-integration")
      .master("local[*]")
      .config("spark.mongodb.read.connection.uri", mongoUri)
      .getOrCreate()

    import spark.implicits._

    val mealDF = spark.read
      .format("mongodb")
      .option("uri", mongoUri)
      .option("collection", mealCollection)
      .load()

    val productDF = spark.read
      .format("mongodb")
      .option("uri", mongoUri)
      .option("collection", productCollection)
      .load()


    val explodedMeals = mealDF
      .withColumn("product", explode($"products"))
      .select(
        $"_id".alias("meal_id"),
        $"name".alias("meal_name"),
        $"mealType",
        $"description".alias("meal_description"),
        $"product.name".alias("prodName"),
        $"product.value".alias("prodValue"),
        $"product.unitType".alias("prodUnit")
      )

    val joinedDF = explodedMeals.join(productDF, explodedMeals("prodName") === productDF("name"), "left")

    val enrichedDF = joinedDF.withColumn("prodValueNumeric", $"prodValue".cast("double"))
      .withColumn("kcalContribution", $"prodValueNumeric" * $"kcal")
      .withColumn("proteinContribution", $"prodValueNumeric" * $"protein")
      .withColumn("fatContribution", $"prodValueNumeric" * $"fat")
      .withColumn("carbohydrateContribution", $"prodValueNumeric" * $"carbohydrate")
      .withColumn("priceContribution", $"prodValueNumeric" * $"value")

    val aggregatedMeals = enrichedDF.groupBy("meal_id", "meal_name", "mealType", "meal_description")
      .agg(
        sum("kcalContribution").alias("totalKcal"),
        sum("proteinContribution").alias("totalProtein"),
        sum("fatContribution").alias("totalFat"),
        sum("carbohydrateContribution").alias("totalCarbohydrate"),
        sum("priceContribution").alias("totalPrice")
      )

    val mappedMeals = aggregatedMeals.select(
      col("meal_id").alias("id"),
      col("meal_name").alias("name"),
      col("mealType").alias("meal_type"),
      col("meal_description").alias("description"),
      col("totalKcal").cast("int").alias("kcal"),
      col("totalProtein").cast("float").alias("protein"),
      col("totalFat").cast("float").alias("fat"),
      col("totalCarbohydrate").cast("float").alias("carbohydrate"),
      col("totalPrice").cast("float").alias("price")
    )

    val existingMeals = spark.read
      .format("jdbc")
      .option("url", postgresUrl)
      .option("dbtable", mealTable)
      .option("user", postgresUser)
      .option("password", postgresPass)
      .load()
      .select("id")

    val newMeals = mappedMeals.join(existingMeals, mappedMeals("id") === existingMeals("id"), "leftanti")

    newMeals.write
      .format("jdbc")
      .option("url", postgresUrl)
      .option("dbtable", mealTable)
      .option("user", postgresUser)
      .option("password", postgresPass)
      .mode("append")
      .save()

    spark.stop()
  }
}

