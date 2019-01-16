package com.github.hvits3rk.mailreceiver.component;

import org.apache.spark.sql.SparkSession;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.stereotype.Component;

@Component
public class SparkComponent implements DisposableBean {

    private SparkSession sparkSession;

    public SparkComponent() {
        sparkSession = SparkSession
                .builder()
                .master("local[2]")
                .appName("Spark Session")
                .getOrCreate();
    }

    public SparkSession getSparkSession() {
        return sparkSession;
    }

    @Override
    public void destroy() throws Exception {
        sparkSession.stop();
    }
}
