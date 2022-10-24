package com.example.miniproject_wishlist.Repositories;

import com.example.miniproject_wishlist.model.Gift;
import com.example.miniproject_wishlist.model.GiftList;
import com.example.miniproject_wishlist.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class WishlistRepository {
    private Connection con = DatabaseManager.getConn();

//    public List<Gift> getAllWishlists() {
//        List<Gift> wishlist = new ArrayList<>();
//
//        try {
//            PreparedStatement preparedStatement = con.prepareStatement("SELECT * FROM wishlist.giftlists");
//            ResultSet resultSet = preparedStatement.executeQuery();
//            while (resultSet.next()) {
//                wishlist.add(new Gift(
//                        resultSet.getInt("listID"),
//                        resultSet.getString("email"),
//                        resultSet.getString("listName")
//                ));
//            }
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//
//        return wishlist;
//    }


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

    // create gift
    public void createGift(Gift gift) {
        try {
            PreparedStatement psts = con.prepareStatement("INSERT INTO gifts (giftID, listID, giftName, price, url) VALUES (?,?,?,?,?)");
            psts.setInt(1, gift.getGiftID());
            psts.setInt(2, gift.getGiftID());
            psts.setString(3, gift.getGiftName());
            psts.setDouble(4,gift.getPrice());
            psts.setString(5,gift.getUrl());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    // delete giftList
    public boolean deleteGift(Gift gift) {

        return false;
    }

    // delete gift

    // get all giftLists for user

    // add gift to list

}
