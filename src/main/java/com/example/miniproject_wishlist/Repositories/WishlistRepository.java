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

    // create user
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

    // todo add ternary operator for empty id
    // create list - ONLY ALLOWED add list to user
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

    // create gift - ONLY ALLOWED TO CREATE TO A GIFT_LIST
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


    // delete gift
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

    // delete giftList
    public boolean deleteGiftList(String giftListID) {
        try {
            PreparedStatement psts = con.prepareStatement("DELETE FROM giftlists WHERE listID = ?;");
            psts.setString(1, giftListID);

            psts.executeUpdate();

        } catch (SQLException e) {
            return false;
        }

        return true;
    }

    // return all gifts for giftList
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
