ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "2.13.10"

lazy val root = (project in file("."))
  .settings(
    name := "integration"
  )

libraryDependencies += "org.apache.spark" %% "spark-core" % "3.5.5"

libraryDependencies += "org.apache.spark" %% "spark-sql" % "3.5.5"

libraryDependencies += "org.mongodb.spark" %% "mongo-spark-connector" % "10.4.1"

libraryDependencies += "org.postgresql" % "postgresql" % "42.7.5"

libraryDependencies += "com.typesafe" % "config" % "1.4.3"
