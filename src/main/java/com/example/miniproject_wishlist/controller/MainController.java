package com.example.miniproject_wishlist.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.context.request.WebRequest;

@Controller
public class MainController {

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @PostMapping("/create")
    public String createUser(WebRequest dataFromForm) {
        //Create user i database with name and email

        return "myWishlists";
    }

    @PostMapping("/createWishlist")
    public String createWishlist(WebRequest dataFromForm) {
        //Create list in database under the user

        return "myListOfGifts";
    }

    @PostMapping("/createGift")
    public String createGift(WebRequest dataFromForm) {
        //Create wish/gift in database under the correct wishlist

        return "myListOfGifts";
    }

    @PostMapping("/find")
    public String findWishlist(WebRequest dataFromForm) {
        //Find list by id

        return "";
    }
}
