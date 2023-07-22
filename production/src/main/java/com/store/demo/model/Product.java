package com.store.demo.model;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @Column(name = "name")
    @Size(min = 2,message = "It's not valid name")
    private String name;

    @Column(name = "brand")
    @Size(min = 2,message = "It's not valid brand")
    private String brand;
    @Column(name = "madein")
    @NotNull(message ="Requered made in." )
    private String madein;

    @Min(value=0,message = "Price is mandatory > 0")
    private double price;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotNull(message = "Expired date is mandatory")
    private Date expiredDate;

    @NotNull(message = "Description is mandatory")
    private String description;

    public Product() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getMadein() {
        return madein;
    }

    public void setMadein(String madein) {
        this.madein = madein;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Date getExpiredDate() {
        return expiredDate;
    }

    public void setExpiredDate(Date expiredDate) {
        this.expiredDate = expiredDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", brand='" + brand + '\'' +
                ", madein='" + madein + '\'' +
                ", price=" + price +
                ", expiredDate=" + expiredDate +
                ", description='" + description + '\'' +
                '}';
    }
}
