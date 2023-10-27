package com.fusionhub.reciperealm.webservices.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fusionhub.reciperealm.webservices.dto.UserDto;
import com.fusionhub.reciperealm.webservices.services.UserService;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/profile")
    public UserDto getProfile(@RequestHeader(value="Authorization") String token) {
        return userService.getLoggedInUserProfile(token.substring(7));
    }

    @PostMapping("/upload-image")
    public ResponseEntity<UserDto> uploadProfileImage(
        @RequestHeader(value="Authorization") String token,
        @RequestParam("image") MultipartFile imageFile
    ) throws Exception {
        if (imageFile.isEmpty()) {
            throw new Exception("The image file is empty");
        }

        byte[] imageData = imageFile.getBytes();
        UserDto updatedUser = userService.uploadProfileImage(token.substring(7), imageData);

        return ResponseEntity.ok(updatedUser);
    }
}
