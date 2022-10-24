package com.example.miniproject_wishlist.Repositories;

import com.example.miniproject_wishlist.model.Gift;
import com.example.miniproject_wishlist.model.GiftList;
import com.example.miniproject_wishlist.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class WishlistRepository {
    private Connection con = DatabaseManager.getConn();

    // ### user ###
    public boolean createUser(User user) {
        // return true or false if inserted

        try {
            PreparedStatement psts = con.prepareStatement("INSERT INTO users VALUES (?,?)");

            // user parameters
            psts.setString(1, user.getEmail());
            psts.setString(2, user.getUserName());

            psts.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e);
            return false;
        }

        return true;
    }

    public boolean deleteUser(String testEmail) {
        try {
            PreparedStatement psts = con.prepareStatement("DELETE FROM users WHERE email = ?;");
            psts.setString(1, testEmail);

            psts.executeUpdate();

        } catch (SQLException e) {
            return false;
        }

        return true;
    }

    public User getUser(String email) {
        User user = null;

        try {
            PreparedStatement psts = con.prepareStatement("SELECT * FROM users WHERE email = ?;");
            psts.setString(1, email);
            ResultSet resultSet = psts.executeQuery();

            // add parameters
            while(resultSet.next()) {
                user =  new User(
                        resultSet.getString("email"),
                        resultSet.getString("userName"));
            }

        } catch (SQLException e) {
            System.out.println(e);
            return null;
        }

        return user;
    }


    // ### giftList ###
    // ONLY ALLOWED to add list to already existing user
    public boolean createGiftList(GiftList giftList) {
        PreparedStatement psts = null;

        try {
            // with or without predefined listID;
            if (giftList.getListID() == null) {
                psts = con.prepareStatement("INSERT INTO giftlists (email, listName) VALUES (?, ?);");
                psts.setString(1, giftList.getEmail());
                psts.setString(2, giftList.getListName());

            } else {
                psts = con.prepareStatement("INSERT INTO giftlists (listID, email, listName) VALUES (?, ?, ?);");
                psts.setInt(1, giftList.getListID());
                psts.setString(2, giftList.getEmail());
                psts.setString(3, giftList.getListName());
            }

            psts.executeUpdate();

        } catch (SQLException e) {
            return false;
        }

        return true;
    }

    public boolean deleteGiftList(int giftListID) {
        try {
            PreparedStatement psts = con.prepareStatement("DELETE FROM giftlists WHERE listID = ?;");
            psts.setInt(1, giftListID);

            psts.executeUpdate();

        } catch (SQLException e) {
            return false;
        }

        return true;
    }

    public GiftList getGiftList(int giftListID) {
        GiftList giftList = null;

        try {
            PreparedStatement psts = con.prepareStatement("SELECT * FROM giftlists WHERE listID = ?;");
            psts.setInt(1, giftListID);
            ResultSet resultSet = psts.executeQuery();

            // add parameters listID, String email, String listName
            while(resultSet.next()) {
                giftList =  new GiftList(
                        resultSet.getInt("listID"),
                        resultSet.getString("email"),
                        resultSet.getString("listName"));
            }

        } catch (SQLException e) {
            System.out.println(e);
            return null;
        }

        return giftList;
    }

    public List<Gift> returnGiftsFromList(int listID) {
        ArrayList<Gift> gifts = new ArrayList<>();

        try {
            PreparedStatement psts = con.prepareStatement("SELECT * FROM gifts WHERE listID = ?;");
            psts.setInt(1, listID);
            ResultSet resultSet = psts.executeQuery();

            while (resultSet.next()) {
                gifts.add(new Gift(
                        resultSet.getInt("giftID"),
                        resultSet.getString("giftName"),
                        resultSet.getDouble("price"),
                        resultSet.getString("url")
                ));
            }

        } catch (SQLException e) {
            return null;
        }

        return gifts;
    }



    // ### gift ###
    // ONLY ALLOWED to add gift to already existing giftList
    public boolean createGift(Gift gift, GiftList giftList) {

        // TODO Tweak and recheck
        PreparedStatement psts = null;

        try {
            // with or without predefined giftID
            if (gift.getGiftID() == null) {
                psts = con.prepareStatement("INSERT INTO gifts (giftName, price, url) VALUES (?,?,?)");
                psts.setString(1, gift.getGiftName());
                psts.setDouble(2, gift.getPrice());
                psts.setString(3, gift.getUrl());

            } else {
                psts = con.prepareStatement("INSERT INTO gifts (listID, giftName, price, url) VALUES (?,?,?,?)");
                psts.setInt(1, giftList.getListID());
                psts.setString(2, gift.getGiftName());
                psts.setDouble(3, gift.getPrice());
                psts.setString(4, gift.getUrl());
            }

            psts.executeUpdate();
        } catch (SQLException e) {
            return false;
        }

        return true;
    }
    public boolean deleteGift(String giftID) {
        try {
            PreparedStatement psts = con.prepareStatement("DELETE FROM gifts WHERE giftID = ?;");
            psts.setString(1, giftID);

            psts.executeUpdate();

        } catch (SQLException e) {
            return false;
        }

        return true;
    }

    public Gift getGift(int giftID) {
        Gift gift = null;

        try {
            PreparedStatement psts = con.prepareStatement("SELECT * FROM gifts WHERE giftID = ?;");
            psts.setInt(1, giftID);
            ResultSet resultSet = psts.executeQuery();

            // add parameters giftID, String giftName, double price, String url
            while(resultSet.next()) {

                gift =  new Gift(
                        resultSet.getInt("giftID"),
                        resultSet.getString("giftName"),
                        resultSet.getDouble("price"),
                        resultSet.getString("url"));
            }

        } catch (SQLException e) {
            System.out.println(e);
            return null;
        }

        return gift;
    }

    // todo create method to get all giftLists for user
//    // return all giftLists for user
//    public List<GiftList> returnAllGiftListsFromEmail(String email) {
//        List<GiftList> giftLists = new ArrayList<>();
//        try {
//            PreparedStatement psts = con.prepareStatement("SELECT * FROM giftlists WHERE email = ?");
//            psts.setString(1, email);
//            ResultSet resultset = psts.executeQuery();
//
//            while (resultset.next()) {
//                giftLists.add(new GiftList(
//                        resultset.getInt("email"),
//                        resultset.getString("listName"),
//                ));
//            }
//
//        } catch (Exception e) {
//            System.out.print("SQL exception");
//        }
//        return giftLists;
//
//    }

}
