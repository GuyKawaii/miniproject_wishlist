package com.example.miniproject_wishlist.service;

import com.example.miniproject_wishlist.Repositories.WishlistRepository;
import com.example.miniproject_wishlist.model.User;
import org.springframework.web.context.request.WebRequest;

public class wishlistService {

    WishlistRepository repo = new WishlistRepository();

    public void createUser(WebRequest request) {

        request.getParameter("email");
        request.getParameter("name");

        User user = new User(request.getParameter("email"),request.getParameter("name"));

        repo.createUser(user);
    }

}
