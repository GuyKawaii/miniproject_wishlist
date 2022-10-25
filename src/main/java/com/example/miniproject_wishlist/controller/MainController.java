package com.example.miniproject_wishlist.controller;

import com.example.miniproject_wishlist.model.Gift;
import com.example.miniproject_wishlist.Repositories.WishlistRepository;
import com.example.miniproject_wishlist.model.GiftList;
import com.example.miniproject_wishlist.model.User;
import com.example.miniproject_wishlist.service.wishlistService;
import com.sun.tools.javac.Main;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.WebRequest;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
public class MainController {
    private WishlistRepository wishlistRepository = new WishlistRepository();
    private wishlistService wishlistService = new wishlistService();


    @GetMapping("/")
    public String index() {
        return "index";
    }

    @PostMapping("/create")
    public String createUser(WebRequest dataFromForm, Model model) {
        // Create user i database with name and email
        //todo læg koden ind i service
        String email = dataFromForm.getParameter("email");
        String userName = dataFromForm.getParameter("name");
        wishlistRepository.createUser(new User(email, userName));



        return "redirect:/myWishlists";
    }

    @GetMapping("/myWishlists")
    public String showAllWishlists(Model model) {

        List<GiftList> wishLists = wishlistRepository.returnAllGiftLists();
        //wishLists = new ArrayList<>();
        model.addAttribute("giftLists", wishLists);

        return "myWishlists";
    }

    @PostMapping("/createWishlist")
    public String createWishlist(WebRequest dataFromForm, @RequestParam String userEmail) {
        //Create list in database under the user
        String wishlistName = dataFromForm.getParameter("name");
        //wishlistRepository.createGiftList(userEmail);


        // display from db all user lists 'email'


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
    public String findWishlistAsUser(Model model) {
        //Find the list that the user has clicked on


        //test
        List<Gift> gifts = wishlistRepository.returnGiftsFromList(2);
        model.addAttribute("newGift", gifts);
        return "myListOfGifts";
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
