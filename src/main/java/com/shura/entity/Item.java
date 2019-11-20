package com.shura.entity;

public class Item {
    private Integer id;
    private String title;
    private Float price;
    private String image;
    private String category;
    private String brand;

    public Item() {
    }

    public Item(Integer id, String title, Float price, String brand) {
        this.id = id;
        this.title = title;
        this.price = price;
        this.brand = brand;
    }

    public Item(Integer id, String title, Float price, String image, String category, String brand) {
        this.id = id;
        this.title = title;
        this.price = price;
        this.image = image;
        this.category = category;
        this.brand = brand;
    }

    @Override
    public String toString() {
        return "Item{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", price=" + price +
                ", image='" + image + '\'' +
                ", category='" + category + '\'' +
                ", brand='" + brand + '\'' +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }
}
