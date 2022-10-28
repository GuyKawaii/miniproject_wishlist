package com.example.miniproject_wishlist.Repositories;

import com.example.miniproject_wishlist.model.Wishlist;
import com.example.miniproject_wishlist.model.User;
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
        Wishlist expectedGiftList  = new Wishlist(testListID, testEmail, testListName);


        // ### reset database ###
        wishlistRepository.createUser(expected);
        wishlistRepository.deleteWishlist(testListID);

        // # add giftList #
        wishlistRepository.createWishlist(expectedGiftList);
        Wishlist actualGiftList = wishlistRepository.getWishlist(testListID);

        // recall of giftList
        assertEquals(expectedGiftList.getListID(), actualGiftList.getListID());
        assertEquals(expectedGiftList.getEmail(), actualGiftList.getEmail());
        assertEquals(expectedGiftList.getListName(), actualGiftList.getListName());


        // # delete giftList #
        wishlistRepository.deleteWishlist(testListID);

        // recall of removed giftList
        Wishlist actualRemoved =  wishlistRepository.getWishlist(testListID);
        assertNull(actualRemoved);
    }


    @Test
    public void testReturnAllGiftListsFromEmail() {
        // parameters used
        String testEmail = "testEmail";
        String testName = "testName";
        User expected = new User(testEmail, testName);

        String testListName = "testGiftList";
        int testListID = 1;
        Wishlist expectedGiftList  = new Wishlist(testListID, testEmail, testListName);






    }




}