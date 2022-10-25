package com.example.miniproject_wishlist.model;

public class GiftList {
    private Integer listID;
    private String email;
    private String listName;

    // id provided
    public GiftList(int listID, String email, String listName) {
        setListID(listID);
        setEmail(email);
        setListName(listName);
    }

    // no id provided
    public GiftList(String email, String listName) {
        listID = null;
        setEmail(email);
        setListName(listName);
    }

    public GiftList(String listName) {
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
}
