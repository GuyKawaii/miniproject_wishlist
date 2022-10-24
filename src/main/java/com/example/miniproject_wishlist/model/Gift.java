package com.example.miniproject_wishlist.model;

public class Gift {
    private int giftID;
    private String giftName;
    private double price;
    private String url;

    Gift(int giftID, String giftName, double price){
        setGiftID(giftID);
        setGiftName(giftName);
        setPrice(price);
    }

    public int getGiftID() {
        return giftID;
    }

    public void setGiftID(int giftID) {
        this.giftID = giftID;
    }

    public String getGiftName() {
        return giftName;
    }

    public void setGiftName(String giftName) {
        this.giftName = giftName;
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
}
