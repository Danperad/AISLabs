package com.vyatsu;

public class Product {
    public int id;
    public String title;
    public double price;
    public String category;

    public Product(String title, double price, String category) {
        this.title = title;
        this.price = price;
        this.category = category;
    }

    public Product(int id, String title, double price, String category) {
        this.id = id;
        this.title = title;
        this.price = price;
        this.category = category;
    }

    @Override
    public String toString() {
        return id + " | " + title + " | " + price + " | " + category;
    }
}
