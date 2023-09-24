package com.fusionhub.reciperealm.webservices.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fusionhub.reciperealm.webservices.dto.UserIngredientsDto;
import com.fusionhub.reciperealm.webservices.services.UserIngredientsService;

@RestController
@RequestMapping("/api/v1/user-ingredients")
public class UserIngredientsController {
     @Autowired
    private UserIngredientsService userIngredientsService;

    @PostMapping
    public ResponseEntity<String> addUserIngredient(@RequestBody UserIngredientsDto request) {
        userIngredientsService.addUserIngredient(request);
        return ResponseEntity.ok("Ingredient added successfully!");
    }

    @PutMapping
    public ResponseEntity<String> updateUserIngredient(@RequestBody UserIngredientsDto request) {
        userIngredientsService.updateUserIngredient(request);
        return ResponseEntity.ok("Ingredient updated successfully!");
    }

    @DeleteMapping("/{userId}/{ingredientId}")
    public ResponseEntity<String> removeUserIngredient(@PathVariable Long userId, @PathVariable Long ingredientId) {
        userIngredientsService.removeUserIngredient(userId, ingredientId);
        return ResponseEntity.ok("Ingredient removed successfully!");
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<String> removeAllIngredientsForUser(@PathVariable Long userId) {
        userIngredientsService.removeAllIngredientsForUser(userId);
        return ResponseEntity.ok("All ingredients for user removed successfully!");
    }
}
