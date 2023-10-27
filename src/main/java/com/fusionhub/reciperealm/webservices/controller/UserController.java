package com.fusionhub.reciperealm.webservices.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.fusionhub.reciperealm.webservices.models.User;
import com.fusionhub.reciperealm.webservices.services.UserService;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/api/v1/profile")
    public User getProfile(@RequestHeader(value="Authorization") String token) {
        return userService.getLoggedInUserProfile(token.substring(7));
    }
}
