package com.github.hvits3rk.mailreceiver.service;

import com.github.hvits3rk.mailreceiver.component.SparkComponent;
import com.github.hvits3rk.mailreceiver.model.CompanyInfo;
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
    private final CompanyInfoService companyInfoService;

    public SparkService(SparkComponent sparkComponent, CompanyInfoService companyInfoService) {
        this.sparkComponent = sparkComponent;
        this.companyInfoService = companyInfoService;
    }

    public void runCsvDataFrame(String pathToCsv, String companyName) {

        CompanyInfo companyInfo = companyInfoService.findByName(companyName);

        if (companyInfo == null)
            return;

        SparkSession spark = sparkComponent.getSparkSession();

        Dataset<Row> initDataset = spark.read()
                .option("sep", ";")
                .option("inferSchema", "true")
                .option("header", "true")
                .csv(pathToCsv);

        initDataset.show(5);

        initDataset.createOrReplaceTempView("init_dataset");

        Dataset<Row> dataset = initDataset
                .select(col(companyInfo.getVendor()).as("Vendor"),
                        col(companyInfo.getNumber()).as("Number"),
                        rpad(col(companyInfo.getDescription()), 512, "").as("Description"),
                        regexp_replace(col(companyInfo.getPrice()), ",", ".").as("Price"),
                        regexp_extract(col(companyInfo.getCount()), "(\\d+)(?!.*\\d)", 1).as("Count"),
                        regexp_replace(upper(col(companyInfo.getVendor())), "[^A-Z0-9_]", "").as("SearchVendor"),
                        regexp_replace(upper(col(companyInfo.getNumber())), "[^A-Z0-9_]", "").as("SearchNumber")
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
