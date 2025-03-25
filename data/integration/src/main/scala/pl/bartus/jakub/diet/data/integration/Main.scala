package pl.bartus.jakub.diet.data.integration

import com.typesafe.config.ConfigFactory
import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.functions.{sum, _}
import pl.bartus.jakub.diet.data.integration.script.DataManager

object DataIntegrationApp {
  def main(args: Array[String]): Unit = {
    val config = ConfigFactory.load()

    val mongoUri = config.getString("mongodb.uri")

    val mealCollection = config.getString("mongodb.mealCollection")
    val productCollection = config.getString("mongodb.productCollection")
    val productPostgresUrl = config.getString("productDomain.url")
    val mealPostgresUrl = config.getString("mealDomain.url")
    val postgresUser = config.getString("productDomain.user")
    val postgresPass = config.getString("productDomain.password")
    val mealTable = config.getString("mealDomain.mealTable")
    val mealProdTable = config.getString("mealDomain.productTable")
    val mealProductTable = config.getString("mealDomain.mealProductTable")
    val productTable = config.getString("productDomain.productTable")

    val spark = SparkSession.builder()
      .appName("data-integration")
      .master("local[*]")
      .config("spark.mongodb.write.connection.uri", mongoUri)
      .config("spark.mongodb.write.database", "diet")
      .config("spark.mongodb.write.collection", mealCollection)
      .getOrCreate()

    import spark.implicits._

    val productDF = DataManager.readFromPostgres(spark,productPostgresUrl,productTable,postgresUser,postgresPass)
    val mealDF = DataManager.readFromPostgres(spark,mealPostgresUrl,mealTable,postgresUser,postgresPass)
    val mProductDF = DataManager.readFromPostgres(spark,mealPostgresUrl,mealProdTable,postgresUser,postgresPass)
    val mealProductDF = DataManager.readFromPostgres(spark,mealPostgresUrl,mealProductTable,postgresUser,postgresPass)

    val mealAlias = mealDF.alias("meal")
    val mProductAlias = mProductDF.alias("mProd")
    val mealProductAlias = mealProductDF.alias("mealProd")
    val productAlias = productDF.alias("prod")

    val joinedDF = mealAlias
      .join(mealProductAlias, col("meal.id") === col("mealProd.meal_id"), "inner")
      .join(mProductAlias, col("mealProd.product_id") === col("mProd.id"), "inner")
      .join(productAlias, col("mProd.name") === col("prod.name"), "inner")

    val resultDF = joinedDF.select(
      col("meal.id").as("meal_id"),
      col("meal.name").as("meal_name"),
      col("meal.meal_type").as("meal_type"),
      col("meal.description").as("meal_description"),
      col("mealProd.meal_id").as("mp_meal_id"),
      col("mealProd.product_id").as("mp_product_id"),
      col("mProd.id").as("meal_product_id"),
      col("mProd.name").as("meal_product_name"),
      col("mProd.description").as("meal_product_description"),
      col("mProd.value").as("meal_product_value"),
      col("mProd.unit").as("meal_product_unit"),
      col("prod.id").as("product_id"),
      col("prod.name").as("product_name"),
      col("prod.description").as("product_description"),
      col("prod.price").as("product_price"),
      col("prod.currency").as("product_currency"),
      col("prod.value").as("product_value"),
      col("prod.unit").as("product_unit"),
      col("prod.kcal").as("product_kcal"),
      col("prod.protein").as("product_protein"),
      col("prod.fat").as("product_fat"),
      col("prod.carbohydrate").as("product_carbohydrate")
    )

    val enrichedDF = joinedDF
      .withColumn("predict_kcal", col("prod.kcal") / 100 * col("mProd.value") )
      .withColumn("predict_protein", col("prod.protein") / 100 * col("mProd.value"))
      .withColumn("predict_fat", col("prod.fat") / 100 * col("mProd.value"))
      .withColumn("predict_carbohydrate", col("prod.carbohydrate") / 100 * col("mProd.value"))
      .withColumn("predict_price", col("prod.price") / 100 * col("mProd.value"))

    val baseDF = enrichedDF.select(
      col("meal.id").as("meal_id"),
      col("meal.name").as("meal_name"),
      col("meal.meal_type").as("meal_type"),
      col("meal.description").as("meal_description"),

      col("mProd.value").as("usage_amount"),

      struct(
        col("prod.id").as("id"),
        col("prod.name").as("name"),
        col("prod.description").as("description"),
        col("prod.currency").as("currency"),
        col("prod.unit").as("unit"),
        col("usage_amount").as("value"),
        col("predict_kcal").as("kcal"),
        col("predict_protein").as("protein"),
        col("predict_fat").as("fat"),
        col("predict_carbohydrate").as("carbohydrate"),
        col("predict_price").as("price")
      ).as("product_obj")
    )

    val groupedDF = baseDF
      .groupBy(
        "meal_id",
        "meal_name",
        "meal_type",
        "meal_description"
      )
      .agg(
        collect_list(col("product_obj")).as("products"),
        sum(col("product_obj.kcal")).as("total_kcal"),
        sum(col("product_obj.protein")).as("total_protein"),
        sum(col("product_obj.fat")).as("total_fat"),
        sum(col("product_obj.carbohydrate")).as("total_carbohydrate"),
        sum(col("product_obj.price")).as("total_price")
      )

    val finalDF = groupedDF.select(
      col("meal_id").cast("string").as("_id"),
      col("meal_name").as("name"),
      col("meal_type").as("mealType"),
      col("meal_description").as("description"),
      col("total_kcal").as("kcal"),
      col("total_protein").as("protein"),
      col("total_fat").as("fat"),
      col("total_carbohydrate").as("carbohydrate"),
      col("total_price").as("price"),
      col("products")
    )


    finalDF.write
      .format("mongodb")
      .mode("append")
      .save()


    spark.stop()
  }
}