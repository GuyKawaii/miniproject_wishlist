package com.example.miniproject_wishlist.Repositories;

import com.example.miniproject_wishlist.model.GiftList;
import com.example.miniproject_wishlist.model.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WishlistRepositoryTest {
    private WishlistRepository wishlistRepository = new WishlistRepository();

    @Test
    public void testCreateAndDeleteUser() {
        // parameters used
        String testEmail = "testEmail";
        String testName = "testName";
        User expected = new User(testEmail, testName);

        // ### reset database ###
        wishlistRepository.deleteUser(testEmail);

        // # add user #
        wishlistRepository.createUser(expected);
        User actual =  wishlistRepository.getUser(testEmail);

        // recall of user
        assertEquals(expected.getEmail(), actual.getEmail());
        assertEquals(expected.getUserName(), actual.getUserName());


        // # delete user #
        wishlistRepository.deleteUser(testEmail);

        // recall of removed user
        User actualRemoved =  wishlistRepository.getUser(testEmail);
        assertNull(actualRemoved);
    }

    @Test
    public void testCreateAndDeleteGiftList() {
        // parameters used
        String testEmail = "testEmail";
        String testName = "testName";
        User expected = new User(testEmail, testName);

        String testListName = "testGiftList";
        int testListID = 1;
        GiftList expectedGiftList  = new GiftList(testListID, testEmail, testListName);


        // ### reset database ###
        wishlistRepository.createUser(expected);
        wishlistRepository.deleteGiftList(testListID);

        // # add giftList #
        wishlistRepository.createGiftList(expectedGiftList);
        GiftList actualGiftList = wishlistRepository.getGiftList(testListID);

        // recall of giftList
        assertEquals(expectedGiftList.getListID(), actualGiftList.getListID());
        assertEquals(expectedGiftList.getEmail(), actualGiftList.getEmail());
        assertEquals(expectedGiftList.getListName(), actualGiftList.getListName());


        // # delete giftList #
        wishlistRepository.deleteGiftList(testListID);

        // recall of removed giftList
        GiftList actualRemoved =  wishlistRepository.getGiftList(testListID);
        assertNull(actualRemoved);
    }




}