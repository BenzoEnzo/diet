package pl.bartus.jakub.diet.data.integration.script

import org.apache.spark.sql.{DataFrame, SparkSession}

object DataManager {
  def readFromMongo(spark: SparkSession, mongoUri: String, collection: String): DataFrame = {
    spark.read
      .format("mongodb")
      .option("uri", mongoUri)
      .option("collection", collection)
      .load()
  }

  def readFromPostgres(spark: SparkSession, postgresUrl: String, dbTable: String, postgresUser: String, postgresPass: String): DataFrame = {
    spark.read
      .format("jdbc")
      .option("url", postgresUrl)
      .option("dbtable", dbTable)
      .option("user", postgresUser)
      .option("password", postgresPass)
      .load()
  }
  def saveToPostgres(df: DataFrame,postgresUrl: String, dbTable: String, postgresUser: String, postgresPass: String): Unit = {
    df.write
      .format("jdbc")
      .option("url", postgresUrl)
      .option("dbtable", dbTable)
      .option("user", postgresUser)
      .option("password", postgresPass)
      .mode("append")
      .save()
  }
}
