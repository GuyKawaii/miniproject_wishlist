package com.example.miniproject_wishlist.controller;

import com.example.miniproject_wishlist.Repositories.WishlistRepository;
import com.example.miniproject_wishlist.model.GiftList;
import com.example.miniproject_wishlist.model.User;
import com.sun.tools.javac.Main;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
    private WishlistRepository wishlistRepository = new WishlistRepository();


    @GetMapping("/")
    public String index() {
        return "index";
    }


    public void run() {
        User user = new User("email", "name");
        GiftList giftList1 = new GiftList(99,"email", "with id");
        GiftList giftList2 = new GiftList("email", "without id");

        // user
//        boolean added =  wishlistRepository.createUser(user);

        // list
        wishlistRepository.createGiftList(giftList1);
        wishlistRepository.createGiftList(giftList2);

        // gifts


    }

    public static void main(String[] args) {
        MainController mainController = new MainController();

        mainController.run();
    }

}
