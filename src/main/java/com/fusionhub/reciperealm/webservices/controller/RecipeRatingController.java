package com.fusionhub.reciperealm.webservices.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fusionhub.reciperealm.webservices.dto.RecipeRatingDto;
import com.fusionhub.reciperealm.webservices.models.Recipe;
import com.fusionhub.reciperealm.webservices.models.User;
import com.fusionhub.reciperealm.webservices.services.RecipeRatingService;

@RestController
@RequestMapping("/api/v1/ratings")
public class RecipeRatingController {

    @Autowired
    private RecipeRatingService recipeRatingService;

    @PostMapping("/rate/{recipeId}")
    public ResponseEntity<RecipeRatingDto> rateRecipe(
            @PathVariable Long recipeId,
            @RequestBody RecipeRatingDto ratingDto,
            @AuthenticationPrincipal User user) {

        Recipe recipe = new Recipe();
        recipe.setId(recipeId);

        RecipeRatingDto ratedRecipe = recipeRatingService.rateRecipe(user, recipe, ratingDto);
        return new ResponseEntity<>(ratedRecipe, HttpStatus.CREATED);
    }

    @GetMapping("/recipe/{recipeId}")
    public ResponseEntity<?> getRatingsForRecipe(@PathVariable Long recipeId) {
        Recipe recipe = new Recipe();
        recipe.setId(recipeId);

        return ResponseEntity.ok(recipeRatingService.getRatingsForRecipe(recipe));
    }

    @GetMapping("/average/{recipeId}")
    public ResponseEntity<?> getAverageRatingForRecipe(@PathVariable Long recipeId) {
        Recipe recipe = new Recipe();
        recipe.setId(recipeId); 

        return ResponseEntity.ok(recipeRatingService.getAverageRatingForRecipe(recipe));
    }

    @DeleteMapping("/delete/{recipeId}")
    public ResponseEntity<?> deleteRating(
            @PathVariable Long recipeId,
            @AuthenticationPrincipal User user) {

        Recipe recipe = new Recipe();
        recipe.setId(recipeId);

        recipeRatingService.deleteRating(user, recipe);
        return ResponseEntity.noContent().build();
    }
}