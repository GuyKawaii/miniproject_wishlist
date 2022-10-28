package com.example.miniproject_wishlist.Repositories;

import com.example.miniproject_wishlist.model.Gift;
import com.example.miniproject_wishlist.model.Wishlist;
import com.example.miniproject_wishlist.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class WishlistRepository {
    private Connection con = DatabaseManager.getConn();

    private PreparedStatement psts;

    // ### user ###
    public boolean createUser(User user) {
        // return true or false if inserted

        try {
            psts = con.prepareStatement("INSERT INTO users VALUES (?,?)");

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
            psts = con.prepareStatement("DELETE FROM users WHERE email = ?;");
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
            psts = con.prepareStatement("SELECT * FROM users WHERE email = ?;");
            psts.setString(1, email);
            ResultSet resultSet = psts.executeQuery();

            // add parameters
            while (resultSet.next()) {
                user = new User(
                        resultSet.getString("email"),
                        resultSet.getString("userName"));
            }

        } catch (SQLException e) {
            System.out.println(e);
            return null;
        }

        return user;
    }


    // ### wishList ###
    // ONLY ALLOWED to add list to already existing user
    public boolean createWishlist(Wishlist wishList) {
        PreparedStatement psts = null;

        try {
            // with or without predefined listID;
            if (wishList.getListID() == null) {
                psts = con.prepareStatement("INSERT INTO wishlists (email, listName) VALUES (?, ?);");
                psts.setString(1, wishList.getEmail());
                psts.setString(2, wishList.getListName());

            } else {
                psts = con.prepareStatement("INSERT INTO wishlists (listID, email, listName) VALUES (?, ?, ?);");
                psts.setInt(1, wishList.getListID());
                psts.setString(2, wishList.getEmail());
                psts.setString(3, wishList.getListName());
            }

            psts.executeUpdate();

        } catch (SQLException e) {
            return false;
        }

        return true;
    }

    public boolean deleteWishlist(int wishListID) {
        try {
            psts = con.prepareStatement("DELETE FROM wishlists WHERE listID = ?;");
            psts.setInt(1, wishListID);

            psts.executeUpdate();

        } catch (SQLException e) {
            return false;
        }

        return true;
    }

    public Wishlist getWishlist(int wishListID) {
        Wishlist wishList = null;

        try {
            psts = con.prepareStatement("SELECT * FROM wishlists WHERE listID = ?;");
            psts.setInt(1, wishListID);
            ResultSet resultSet = psts.executeQuery();

            // add parameters listID, String email, String listName
            while (resultSet.next()) {
                wishList = new Wishlist(
                        resultSet.getInt("listID"),
                        resultSet.getString("email"),
                        resultSet.getString("listName"));
            }

        } catch (SQLException e) {
            System.out.println(e);
            return null;
        }

        return wishList;
    }

    public List<Gift> returnGiftsFromList(int listID) {
        ArrayList<Gift> gifts = new ArrayList<>();

        try {
            psts = con.prepareStatement("SELECT * FROM gifts WHERE listID = ?;");
            psts.setInt(1, listID);
            ResultSet resultSet = psts.executeQuery();

            while (resultSet.next()) {
                gifts.add(new Gift(
                        resultSet.getInt("giftID"),
                        resultSet.getString("giftName"),
                        resultSet.getDouble("price"),
                        resultSet.getString("url"),
                        resultSet.getBoolean("isReserved")
                ));
            }

        } catch (SQLException e) {
            return null;
        }

        return gifts;
    }


    // ### gift ###
    // ONLY ALLOWED to add gift to already existing giftList
    public boolean createGift(Gift gift, int listID) {

        PreparedStatement psts = null;

        try {
            // with or without predefined giftID
            if (gift.getGiftID() == null) {
                psts = con.prepareStatement("INSERT INTO gifts (listID, giftName, price, url, isReserved) VALUES (?,?,?,?,?);");
                psts.setInt(1, listID);
                psts.setString(2, gift.getGiftName());
                psts.setDouble(3, gift.getPrice());
                psts.setString(4, gift.getUrl());
                psts.setBoolean(5, gift.getReserved());

            } else {
                psts = con.prepareStatement("INSERT INTO gifts (giftID, listID, giftName, price, url, isReserved) VALUES (?,?,?,?,?,?);");
                psts.setInt(1, gift.getGiftID());
                psts.setInt(2, listID);
                psts.setString(3, gift.getGiftName());
                psts.setDouble(4, gift.getPrice());
                psts.setString(5, gift.getUrl());
                psts.setBoolean(6, gift.getReserved());
            }

            psts.executeUpdate();
        } catch (SQLException e) {
            return false;
        }

        return true;
    }

    public boolean setIsReservedForGift(int giftID, boolean isReserved) {
        int isReservedSql;
//        if (isReserved == true) {
//            isReservedSql = 0;
//        }
//        else (isReserved == false) {
//            isReservedSql = 1;
//        }
        try {
            psts = con.prepareStatement(
                    "UPDATE gifts SET isReserved = ? WHERE giftID = ?;");
            psts.setBoolean(1, isReserved);
            psts.setInt(2, giftID);
            psts.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Problem on line 220 in Wishlist Repository");
            return false;
        }

        return true;
    }

    public boolean deleteGift(int giftID) {
        try {
            psts = con.prepareStatement("DELETE FROM gifts WHERE giftID = ?;");
            psts.setInt(1, giftID);
            psts.executeUpdate();

        } catch (SQLException e) {
            return false;
        }

        return true;
    }

    public Gift getGift(int giftID) {
        Gift gift = null;

        try {
            psts = con.prepareStatement("SELECT * FROM gifts WHERE giftID = ?;");
            psts.setInt(1, giftID);
            ResultSet resultSet = psts.executeQuery();

            // add parameters giftID, String giftName, double price, String url
            while (resultSet.next()) {

                gift = new Gift(
                        resultSet.getInt("giftID"),
                        resultSet.getString("giftName"),
                        resultSet.getDouble("price"),
                        resultSet.getString("url"),
                        resultSet.getBoolean("isReserved")
                );
            }

        } catch (SQLException e) {
            System.out.println(e);
            return null;
        }

        return gift;
    }

    // return all giftLists for user
    public List<Wishlist> returnAllWishlistsFromEmail(String email) {
        List<Wishlist> giftLists = new ArrayList<>();
        try {
            psts = con.prepareStatement("SELECT * FROM wishlists WHERE email = ?");
            psts.setString(1, email);
            ResultSet resultset = psts.executeQuery();

            while (resultset.next()) {
                giftLists.add(new Wishlist(
                        resultset.getInt("listID"),
                        resultset.getString("email"),
                        resultset.getString("listName")
                ));
            }

        } catch (Exception e) {
            System.out.print("SQL exception");
        }
        return giftLists;

    }

    // return ALL giftLists
    public List<Wishlist> returnAllWishlists() {
        List<Wishlist> wishLists = new ArrayList<>();
        try {
            psts = con.prepareStatement("SELECT * FROM wishlists");
            ResultSet resultset = psts.executeQuery();

            while (resultset.next()) {
                wishLists.add(new Wishlist(
                        resultset.getInt("listID"),
                        resultset.getString("email"),
                        resultset.getString("listName")
                ));
            }

        } catch (Exception e) {
            System.out.print("SQL exception");
        }
        return wishLists;

    }

}
