package com.example.miniproject_wishlist.model;

public class Gift {
    private int gID;
    private String name;
    private double price;
    private String url;

    public int getgID() {
        return gID;
    }

    public void setgID(int gID) {
        this.gID = gID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "Gift{" +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", url='" + url + '\'' +
                '}';
    }
}
