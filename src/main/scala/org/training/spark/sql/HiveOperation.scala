package org.training.spark.sql

/**
  * Created by liulebin on 03/09/2019.
  */

import java.io.File
import org.apache.spark.sql.Row
import org.apache.spark.sql.SparkSession

case class Record(key: Int, value: String)

object HiveOperation {

  def main(args: Array[String]) {

    val warehouseLocation = new File("spark-warehouse").getAbsolutePath

    val spark = SparkSession
      .builder()
      .appName("Spark Hive Example")
      .config("spark.sql.warehouse.dir", warehouseLocation)
      .enableHiveSupport()
      .getOrCreate()

    //import spark.implicits._

    spark.sql("CREATE TABLE IF NOT EXISTS aaaaa (key INT, value STRING)")
    spark.sql("LOAD DATA LOCAL INPATH 'examples/src/main/resources/kv1.txt' INTO TABLE src")

    // Queries are expressed in HiveQL
    val df = spark.sql("SELECT * FROM aaaaa")
    df.show()

    df.write.format("json").save("hdfs://master01:9000/ssss.json")

    spark.stop()
  }


}



