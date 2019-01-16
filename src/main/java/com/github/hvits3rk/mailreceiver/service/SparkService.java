package com.github.hvits3rk.mailreceiver.service;

import com.github.hvits3rk.mailreceiver.component.SparkComponent;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SaveMode;
import org.apache.spark.sql.SparkSession;
import org.springframework.stereotype.Service;

import java.util.Properties;

import static org.apache.spark.sql.functions.*;

@Service
public class SparkService {

    private final SparkComponent sparkComponent;

    public SparkService(SparkComponent sparkComponent) {
        this.sparkComponent = sparkComponent;
    }

    public void runCsvDataFrame(String pathToCsv) {

        SparkSession spark = sparkComponent.getSparkSession();

        Dataset<Row> initDataset = spark.read()
                .option("sep", ";")
                .option("inferSchema", "true")
                .option("header", "true")
                .csv(pathToCsv);

        initDataset.show(5);

        initDataset.createOrReplaceTempView("init_dataset");

        Dataset<Row> dataset = initDataset
                .select(col("Бренд").as("Vendor"),
                        col("Каталожный номер").as("Number"),
                        rpad(col("Описание"), 512, "").as("Description"),
                        regexp_replace(col("`Цена, руб.`"), ",", ".").as("Price"),
                        regexp_extract(col("Наличие"), "(\\d+)(?!.*\\d)", 1).as("Count"),
                        regexp_replace(upper(col("Бренд")), "[^A-Z0-9_]", "").as("SearchVendor"),
                        regexp_replace(upper(col("Каталожный номер")), "[^A-Z0-9_]", "").as("SearchNumber")
                );


        dataset.show(5);

        Properties connectionProperties = new Properties();
        connectionProperties.put("user", "sa");
        connectionProperties.put("password", "");

        dataset.write().mode(SaveMode.Overwrite)
                .option("createTableColumnTypes", "Vendor VARCHAR(64), Number VARCHAR(64), SearchVendor VARCHAR(64), SearchNumber VARCHAR(64), Description VARCHAR(512), Price DECIMAL(18, 2), Count INT")
                .jdbc("jdbc:h2:mem:bootapp", "test", connectionProperties);
    }
}
