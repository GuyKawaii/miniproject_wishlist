package com.example.miniproject_wishlist.model;

// todo refator to wishlist all places
public class Wishlist {
    private Integer listID;
    private String email;
    private String listName;

    // id provided
    public Wishlist(int listID, String email, String listName) {
        setListID(listID);
        setEmail(email);
        setListName(listName);
    }

    // no id provided
    public Wishlist(String email, String listName) {
        listID = null;
        setEmail(email);
        setListName(listName);
    }

    public Wishlist(String listName) {
        listID = null;
        setListName(listName);
    }

    public Integer getListID() {
        return listID;
    }

    public void setListID(int listID) {
        this.listID = listID;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getListName() {
        return listName;
    }

    public void setListName(String listName) {
        this.listName = listName;
    }

    @Override
    public String toString() {
        return "GiftList{" +
                "listID=" + listID +
                ", email='" + email + '\'' +
                ", listName='" + listName + '\'' +
                '}';
    }
}
