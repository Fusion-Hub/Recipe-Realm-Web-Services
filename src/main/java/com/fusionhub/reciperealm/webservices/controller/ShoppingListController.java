package com.fusionhub.reciperealm.webservices.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fusionhub.reciperealm.webservices.dto.ShoppingListDto;
import com.fusionhub.reciperealm.webservices.services.ShoppingListService;

@RestController
@RequestMapping("/api/v1/shoppinglist")
public class ShoppingListController {

    @Autowired
    private ShoppingListService shoppingListService;

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<ShoppingListDto>> getShoppingListByUserId(@PathVariable Long userId) {
        List<ShoppingListDto> items = shoppingListService.getShoppingListByUserId(userId);
        return new ResponseEntity<>(items, HttpStatus.OK);
    }

    @PostMapping("/user/{userId}/ingredient/{ingredientId}")
    public ResponseEntity<Void> addIngredientToShoppingList(@PathVariable Long userId, @PathVariable Long ingredientId) {
        shoppingListService.addIngredientToShoppingList(userId, ingredientId);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping("/user/{userId}/ingredient/{ingredientId}")
    public ResponseEntity<Void> removeIngredientFromShoppingList(@PathVariable Long userId, @PathVariable Long ingredientId) {
        shoppingListService.removeIngredientFromShoppingList(userId, ingredientId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/user/{userId}/ingredient/{ingredientId}/exists")
    public ResponseEntity<Boolean> isIngredientInShoppingList(@PathVariable Long userId, @PathVariable Long ingredientId) {
        boolean exists = shoppingListService.isIngredientInShoppingList(userId, ingredientId);
        return new ResponseEntity<>(exists, HttpStatus.OK);
    }
}