package com.example.miniproject_wishlist.controller;

import com.example.miniproject_wishlist.Repositories.WishlistRepository;
import com.example.miniproject_wishlist.model.Gift;
import com.example.miniproject_wishlist.model.GiftList;
import com.example.miniproject_wishlist.model.User;
import com.example.miniproject_wishlist.service.WishlistService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.WebRequest;

import java.util.List;
import java.util.Objects;

@Controller
public class MainController {
    private WishlistRepository wishlistRepository = new WishlistRepository();
    private WishlistService wishlistService = new WishlistService();

    // ### BASE GET MAPPINGS ###
    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/myWishlists")
    public String myWishlists(@RequestParam String email, Model model) {

        if (wishlistRepository.createUser(new User(email, ""))) {
            model.addAttribute("existingEmail", email);
            return "index";
        }
        else {

            List<GiftList> wishLists = wishlistRepository.returnAllGiftListsFromEmail(email);

            model.addAttribute("giftLists", wishLists);
            model.addAttribute("email", email);

            return "myWishlists";
        }
    }

    @GetMapping("/myGifts")
    public String myGifts(@RequestParam int listID, Model model) {

        List<Gift> gifts = wishlistRepository.returnGiftsFromList(listID);
        GiftList giftList = wishlistRepository.getGiftList(listID);

        model.addAttribute("listName", giftList.getListName());
        model.addAttribute("oldListID", listID);
        model.addAttribute("gifts", gifts);

        return "myGifts";
    }

    @GetMapping("/shareGifts")
    public String shareGift(@RequestParam int listID, Model model) {

        List<Gift> gifts = wishlistRepository.returnGiftsFromList(listID);
        GiftList giftList = wishlistRepository.getGiftList(listID);

        model.addAttribute("listName", giftList.getListName());
        model.addAttribute("oldListID", listID);
        model.addAttribute("gifts", gifts);

        return "shareGifts";
    }

    // ### OTHER MAPPINGS ###
    @PostMapping("/myGifts/newGift")
    public String newGift(WebRequest dataFromForm, Model model) {

        String giftName = dataFromForm.getParameter("name");
        double giftPrice = Double.parseDouble(Objects.requireNonNull(dataFromForm.getParameter("price")));
        String link = dataFromForm.getParameter("link");

        int listID = Integer.parseInt(Objects.requireNonNull(dataFromForm.getParameter("oldListID")));
        wishlistRepository.createGift(new Gift(giftName, giftPrice, link, false), listID);
//        String shortUrl todo maybe create a short form of a link to display

        System.out.println(listID);

        return "redirect:/myGifts?listID=" + listID;
    }

    @PostMapping("/myGifts/deleteGift")
    public String deleteGift(WebRequest dataFromForm, Model model) {

        int giftID = Integer.parseInt(Objects.requireNonNull(dataFromForm.getParameter("giftID")));
        int listID = Integer.parseInt(Objects.requireNonNull(dataFromForm.getParameter("oldListID")));

        wishlistRepository.deleteGift(giftID);

        System.out.println(listID);
        System.out.println(giftID);


        return "redirect:/myGifts?listID=" + listID;
    }

    @PostMapping("/myWishlists/editList")
    public String editList(WebRequest dataFromForm, Model model) {

        int listID = Integer.parseInt(Objects.requireNonNull(dataFromForm.getParameter("listID")));

        System.out.println(listID);


        return "redirect:/myGifts?listID=" + listID;
    }

    @PostMapping("/myWishlists/newWishlist")
    public String newWishlist(WebRequest dataFromForm, Model model) {

        String listName = dataFromForm.getParameter("listName");
        String email = dataFromForm.getParameter("email");

        System.out.println(listName);
        System.out.println(email);
        GiftList giftList = new GiftList(email, listName);


        wishlistRepository.createGiftList(giftList);


        return "redirect:/myWishlists?email=" + email;
    }

    @PostMapping("/myWishlists/deleteWishlist")
    public String deleteWishlist(WebRequest dataFromForm, Model model) {

        int listID = Integer.parseInt(Objects.requireNonNull(dataFromForm.getParameter("listID")));
        String email = dataFromForm.getParameter("email");

        wishlistRepository.deleteGiftList(listID);

        return "redirect:/myWishlists?email=" + email;
    }


    @PostMapping("/findWishlistAsGuest")
    public String findWishlistAsGuest(WebRequest dataFromForm, Model model) {
        int listID = Integer.parseInt(Objects.requireNonNull(dataFromForm.getParameter("listID")));
        GiftList giftList = wishlistRepository.getGiftList(listID);

        // check if valid redirect
        if (giftList != null)
            return "redirect:/shareGifts?listID=" + listID;
        else
            return "redirect:/";
    }


    @PostMapping("/createUser")
    public String createUser(WebRequest dataFromForm, Model model) {
        String email = dataFromForm.getParameter("email");
        String userName = dataFromForm.getParameter("name");

        if (!wishlistRepository.createUser(new User(email, userName))) {
            model.addAttribute("existingEmail", email);
            return "index";
        }
        else {
            wishlistRepository.createUser(new User(email, userName));


            // Create user i database with name and email
            //todo læg koden ind i service

            model.addAttribute("email", email);
        }

        return "redirect:/myWishlists?email=" + email;
    }


    @PostMapping("/shareGifts/updateReserved")
    public String updateReserved(WebRequest dataFromForm) {
        int listID = Integer.parseInt(Objects.requireNonNull(dataFromForm.getParameter("oldListID")));

        // parameters for gift -- int giftID, String giftName, double price, String url, boolean isReserved
        int giftID = Integer.parseInt(Objects.requireNonNull(dataFromForm.getParameter("giftID")));
        String strIsReserved = dataFromForm.getParameter("reserved");

        // assign boolean value
        boolean isReserved;
        if (strIsReserved == null)
            isReserved = false;
        else
            isReserved = true;

        String reserved = dataFromForm.getParameter("reserved");
        System.out.println(reserved);

        wishlistRepository.setIsReservedForGift(giftID, isReserved);


        return "redirect:/shareGifts?listID=" + listID;
    }

//    @PostMapping("/createWishlist")
//    public String createWishlist(WebRequest dataFromForm, @RequestParam String userEmail) {
//        //Create list in database under the user
//        String wishlistName = dataFromForm.getParameter("name");
//        //wishlistRepository.createGiftList(userEmail);
//
//
//        // display from db all user lists 'email'
//        return "myGifts";
//    }

//    @PostMapping("/createGift")
//    public String createGift(WebRequest dataFromForm, Model model) {
//        //Create wish/gift in database under the correct wishlist
//
//        return "myGifts";
//    }
}
