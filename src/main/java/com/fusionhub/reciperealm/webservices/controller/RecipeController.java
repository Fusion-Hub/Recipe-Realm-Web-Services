package com.fusionhub.reciperealm.webservices.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import com.fusionhub.reciperealm.webservices.dto.CreateRecipeDto;
import com.fusionhub.reciperealm.webservices.dto.RecipeDto;
import com.fusionhub.reciperealm.webservices.services.RecipeService;

@RestController
@RequestMapping("/api/v1/recipes")
public class RecipeController {

    @Autowired
    private RecipeService recipeService;

    @PostMapping
    @Transactional
    public ResponseEntity<CreateRecipeDto> save(@RequestBody CreateRecipeDto recipeDto,
            @RequestHeader("Authorization") String token) {
        token = token.replace("Bearer ", "");
        CreateRecipeDto savedRecipeDto = recipeService.save(recipeDto, token);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedRecipeDto);
    }

    @GetMapping("/{id}")
    @Transactional
    public ResponseEntity<RecipeDto> findById(@PathVariable Long id) {
        Optional<RecipeDto> recipe = recipeService.findById(id);
        if (recipe.isPresent()) {
            return ResponseEntity.ok(recipe.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    @Transactional
    public ResponseEntity<List<RecipeDto>> findAll() {
        return ResponseEntity.ok(recipeService.findAll());
    }

    @GetMapping("/by-name/{name}")
    @Transactional
    public ResponseEntity<List<RecipeDto>> findByName(@PathVariable String name) {
        return ResponseEntity.ok(recipeService.findByName(name));
    }

    @GetMapping("/by-ingredient/{ingredientName}")
    @Transactional
    public ResponseEntity<List<RecipeDto>> findByIngredientName(@PathVariable String ingredientName) {
        return ResponseEntity.ok(recipeService.findByIngredientName(ingredientName));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        recipeService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/my-recipes")
    public ResponseEntity<List<RecipeDto>> findRecipesByAuthenticatedUser(@RequestHeader("Authorization") String token) {
        token = token.replace("Bearer ", "");
        List<RecipeDto> recipes = recipeService.findRecipesByAuthenticatedUser(token);
        return ResponseEntity.ok(recipes);
    }
}