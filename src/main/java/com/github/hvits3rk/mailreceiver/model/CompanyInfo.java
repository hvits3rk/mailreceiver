package com.github.hvits3rk.mailreceiver.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class CompanyInfo {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long id;

    @Column(nullable = false, unique = true)
    private String name;

    private String vendor;

    private String number;

    private String description;

    private String price;

    private String count;

    public CompanyInfo() {
    }

    public CompanyInfo(String name) {
        this.name = name;
    }

    public CompanyInfo(String name, String vendor, String number, String description, String price, String count) {
        this.name = name;
        this.vendor = vendor;
        this.number = number;
        this.description = description;
        this.price = price;
        this.count = count;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CompanyInfo that = (CompanyInfo) o;
        return id == that.id &&
                name.equals(that.name) &&
                vendor.equals(that.vendor) &&
                number.equals(that.number) &&
                description.equals(that.description) &&
                price.equals(that.price) &&
                count.equals(that.count);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, vendor, number, description, price, count);
    }

    @Override
    public String toString() {
        return "CompanyInfo{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", vendor='" + vendor + '\'' +
                ", number='" + number + '\'' +
                ", description='" + description + '\'' +
                ", price='" + price + '\'' +
                ", count='" + count + '\'' +
                '}';
    }
}
