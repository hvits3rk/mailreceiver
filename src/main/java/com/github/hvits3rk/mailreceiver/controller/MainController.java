package com.github.hvits3rk.mailreceiver.controller;

import com.github.hvits3rk.mailreceiver.entity.PriceItem;
import com.github.hvits3rk.mailreceiver.repository.PriceItemRepository;
import com.github.hvits3rk.mailreceiver.service.SparkService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.math.BigDecimal;

@Controller
public class MainController {

    private final PriceItemRepository priceItemRepository;
    private final SparkService sparkService;

    @Value("${com.github.hvits3rk.debug.csvpath}")
    private String csvPath;

    public MainController(PriceItemRepository priceItemRepository, SparkService sparkService) {
        this.priceItemRepository = priceItemRepository;
        this.sparkService = sparkService;
    }

    @GetMapping("/")
    public String indexPage() {
        PriceItem priceItem = new PriceItem();
        priceItem.setCount(2);
        priceItem.setDescription("Description");
        priceItem.setNumber("number9");
        priceItem.setPrice(BigDecimal.valueOf(134.25d));
        priceItem.setSearchNumber("searchNumber9");
        priceItem.setSearchVendor("searchVendor17");
        priceItem.setVendor("vendor17");
        priceItemRepository.save(priceItem);
        return "index";
    }

    @GetMapping("/mail")
    public String mailPage(Model model) {
        return "mail";
    }

    @RequestMapping("/debug/launchSpark")
    public void launchSpark() {
        sparkService.runCsvDataFrame(csvPath);
    }
}
