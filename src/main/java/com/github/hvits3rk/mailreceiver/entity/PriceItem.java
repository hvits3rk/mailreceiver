package com.github.hvits3rk.mailreceiver.entity;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.Objects;

@Entity
public class PriceItem {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(nullable = false)
    @Size(max = 64)
    private String vendor;

    @Size(max = 64)
    @Column(nullable = false)
    private String number;

    @Size(max = 64)
    @Column(nullable = false)
    private String searchVendor;

    @Size(max = 64)
    @Column(nullable = false)
    private String searchNumber;

    @Size(max = 512)
    @Column(nullable = false)
    private String description;

    @Column(nullable = false, precision=18, scale=2)
    private BigDecimal price;

    @Column(nullable = false)
    private int count;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getVendor() {
        return vendor;
    }

    public void setVendor(String vendor) {
        this.vendor = vendor;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getSearchVendor() {
        return searchVendor;
    }

    public void setSearchVendor(String searchVendor) {
        this.searchVendor = searchVendor;
    }

    public String getSearchNumber() {
        return searchNumber;
    }

    public void setSearchNumber(String searchNumber) {
        this.searchNumber = searchNumber;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PriceItem priceItem = (PriceItem) o;
        return id == priceItem.id &&
                count == priceItem.count &&
                vendor.equals(priceItem.vendor) &&
                number.equals(priceItem.number) &&
                searchVendor.equals(priceItem.searchVendor) &&
                searchNumber.equals(priceItem.searchNumber) &&
                description.equals(priceItem.description) &&
                price.equals(priceItem.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, vendor, number, searchVendor, searchNumber, description, price, count);
    }

    @Override
    public String toString() {
        return "PriceItem{" +
                "id=" + id +
                ", vendor='" + vendor + '\'' +
                ", number='" + number + '\'' +
                ", searchVendor='" + searchVendor + '\'' +
                ", searchNumber='" + searchNumber + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", count=" + count +
                '}';
    }
}
