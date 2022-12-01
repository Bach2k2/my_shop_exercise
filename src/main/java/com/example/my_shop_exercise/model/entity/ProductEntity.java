package com.example.my_shop_exercise.model.entity;

public class ProductEntity {
    private String pId;
    private String name;
    private String cId;
    private String image;
    private double price;
    private String description;

    public ProductEntity(String pId, String name, String cId, String image, double price, String description) {
        this.pId = pId;
        this.name = name;
        this.cId = cId;
        this.image = image;
        this.price = price;
        this.description = description;
    }

    public ProductEntity() {
    }

    public ProductEntity(String pId, String name, String cId, double price, String description) {
        this.pId = pId;
        this.name = name;
        this.cId = cId;
        this.price = price;
        this.description = description;
    }

    public ProductEntity(String pId, String name, String cId, String image, double price) {
        this.pId = pId;
        this.name = name;
        this.cId = cId;
        this.image = image;
        this.price = price;
    }

    public String getpId() {
        return pId;
    }

    public void setpId(String pId) {
        this.pId = pId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getcId() {
        return cId;
    }

    public void setcId(String cId) {
        this.cId = cId;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
