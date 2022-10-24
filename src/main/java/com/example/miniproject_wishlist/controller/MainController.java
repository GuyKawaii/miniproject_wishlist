package com.example.miniproject_wishlist.controller;

import com.example.miniproject_wishlist.model.Gift;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.context.request.WebRequest;

import java.util.ArrayList;
import java.util.Map;

@Controller
public class MainController {

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @PostMapping("/create")
    public String createUser(WebRequest dataFromForm, Model model) {
        //Create user i database with name and email


        //test
        model.addAttribute("list1", "MyWishlist");

        return "myWishlists";
    }

    @PostMapping("/createWishlist")
    public String createWishlist(WebRequest dataFromForm) {
        //Create list in database under the user

        return "myListOfGifts";
    }

    @PostMapping("/createGift")
    public String createGift(WebRequest dataFromForm, Model model) {
        //Create wish/gift in database under the correct wishlist



        return "myListOfGifts";
    }

    @PostMapping("/find")
    public String findWishlistAsGuest(WebRequest dataFromForm) {
        //Find list by id

        return "";
    }

    @PostMapping("/findMyWishlist")
    public String findWishlistAsUser() {
        //Find the list that the user has clicked on


        //test
        ArrayList<Gift> gifts = new ArrayList<>();
        Gift gift = new Gift("Ford Focus", 200000, "ford.dk");
        gifts.add(gift);
        model.addAttribute("newGift", gifts);
        return "myListOfGifts";
    }
}
