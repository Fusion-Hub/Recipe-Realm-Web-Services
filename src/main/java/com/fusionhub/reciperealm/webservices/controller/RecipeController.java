package com.fusionhub.reciperealm.webservices.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import com.fusionhub.reciperealm.webservices.dto.RecipeDetailsDto;
import com.fusionhub.reciperealm.webservices.dto.RecipeDto;
import com.fusionhub.reciperealm.webservices.services.RecipeDetailsService;
import com.fusionhub.reciperealm.webservices.services.RecipeService;

@RestController
@RequestMapping("/api/recipes")
public class RecipeController {

    @Autowired
    private RecipeService recipeService;

    @Autowired
    private RecipeDetailsService recipeDetailsService;

    @PostMapping
    @Transactional
    public ResponseEntity<RecipeDto> save(@RequestBody RecipeDto recipeDto) {
        RecipeDto savedRecipeDto = recipeService.save(recipeDto);

        for(RecipeDetailsDto detail : recipeDto.getDetails()) {
            detail.setRecipeId(savedRecipeDto.getId());
            recipeDetailsService.save(detail);
        }

        return ResponseEntity.ok(savedRecipeDto);
    }

    @GetMapping("/{id}")
    @Transactional
    public ResponseEntity<RecipeDto> findById(@PathVariable Long id) {
        Optional<RecipeDto> recipe = recipeService.findById(id);
        if(recipe.isPresent()) {
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
}