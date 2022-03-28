package com.vyatsu;

public class Product {
    public int id;
    public String title;
    public double price;
    public String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String category) {
        this.name = category;
    }

    public Product() {
    }

    public Product(String title, double price, String name) {
        this.title = title;
        this.price = price;
        this.name = name;
    }

    public Product(int id, String title, double price, String category) {
        this.id = id;
        this.title = title;
        this.price = price;
        this.name = category;
    }

    @Override
    public String toString() {
        return id + " | " + title + " | " + price + " | " + name;
    }
}
