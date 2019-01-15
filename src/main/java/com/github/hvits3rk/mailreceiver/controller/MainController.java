package com.github.hvits3rk.mailreceiver.controller;

import com.github.hvits3rk.mailreceiver.entity.PriceItem;
import com.github.hvits3rk.mailreceiver.repository.PriceItemRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.math.BigDecimal;

@Controller
public class MainController {

    private final PriceItemRepository priceItemRepository;

    public MainController(PriceItemRepository priceItemRepository) {
        this.priceItemRepository = priceItemRepository;
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
}
