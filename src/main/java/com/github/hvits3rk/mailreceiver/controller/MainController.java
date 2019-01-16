package com.github.hvits3rk.mailreceiver.controller;

import com.github.hvits3rk.mailreceiver.service.SparkService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {

    private final SparkService sparkService;

    @Value("${com.github.hvits3rk.debug.csvpath}")
    private String csvPath;

    public MainController(SparkService sparkService) {
        this.sparkService = sparkService;
    }

    @GetMapping("/")
    public String indexPage() {

        return "index";
    }

    @RequestMapping("/debug/launchSpark")
    public void launchSpark() {
        sparkService.runCsvDataFrame(csvPath);
    }
}
