package com.example.miniproject_wishlist.controller;

import com.example.miniproject_wishlist.Repositories.WishlistRepository;
import com.example.miniproject_wishlist.model.Gift;
import com.example.miniproject_wishlist.model.GiftList;
import com.example.miniproject_wishlist.model.User;
import com.example.miniproject_wishlist.service.wishlistService;
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
    private wishlistService wishlistService = new wishlistService();


    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/myWishlists")
    public String myWishlists(@RequestParam String email, Model model) {

        List<GiftList> wishLists = wishlistRepository.returnAllGiftListsFromEmail(email);

        model.addAttribute("giftLists", wishLists);
        model.addAttribute("email", email);

        return "myWishlists";
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

    @PostMapping("/myGifts/newGift")
    public String newGift(WebRequest dataFromForm, Model model) {

        String giftName = dataFromForm.getParameter("name");
        double giftPrice = Double.parseDouble(Objects.requireNonNull(dataFromForm.getParameter("price")));
        String link = dataFromForm.getParameter("link");

        wishlistRepository.createGift(new Gift(giftName, giftPrice, link), 12);

        int linkID = Integer.parseInt(dataFromForm.getParameter("oldListID"));

        System.out.println(linkID);


        return "redirect:/myGifts?listID";
    }


    @PostMapping("/create")
    public String createUser(WebRequest dataFromForm, Model model) {
        // Create user i database with name and email
        //todo læg koden ind i service
        String email = dataFromForm.getParameter("email");
        String userName = dataFromForm.getParameter("name");
        wishlistRepository.createUser(new User(email, userName));

        model.addAttribute("email", email);

        return "redirect:/myWishlists?email=" + email;
    }


    @PostMapping("/createWishlist")
    public String createWishlist(WebRequest dataFromForm, @RequestParam String userEmail) {
        //Create list in database under the user
        String wishlistName = dataFromForm.getParameter("name");
        //wishlistRepository.createGiftList(userEmail);


        // display from db all user lists 'email'
        return "myGifts";
    }

    @PostMapping("/createGift")
    public String createGift(WebRequest dataFromForm, Model model) {
        //Create wish/gift in database under the correct wishlist

        return "myGifts";
    }

    @PostMapping("/find")
    public String findWishlistAsGuest(WebRequest dataFromForm) {
        //Find list by id

        return "";
    }

    @PostMapping("/findMyWishlist")
    public String findGilistAsUser(@RequestParam String listID, Model model) {
        //Find the list that the user has clicked on


        //test shows gifts in myLists
        /*
        List<Gift> gifts = wishlistRepository.returnGiftsFromList(2);
        model.addAttribute("newGift", gifts);
         */
        return "redirect:/myGifts?listID=" + listID;
    }


}
