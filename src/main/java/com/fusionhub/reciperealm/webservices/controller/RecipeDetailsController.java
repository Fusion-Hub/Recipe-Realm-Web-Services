package com.fusionhub.reciperealm.webservices.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.fusionhub.reciperealm.webservices.dto.RecipeDetailsDto;
import com.fusionhub.reciperealm.webservices.services.RecipeDetailsService;

@RestController
@RequestMapping("/api/v1/recipe-details")
public class RecipeDetailsController {

    @Autowired
    private RecipeDetailsService recipeDetailsService;

    @GetMapping("/{id}")
    public ResponseEntity<RecipeDetailsDto> findById(@PathVariable Long id) {
        Optional<RecipeDetailsDto> details = recipeDetailsService.findById(id);
        if(details.isPresent()) {
            return ResponseEntity.ok(details.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<RecipeDetailsDto>> findAll() {
        return ResponseEntity.ok(recipeDetailsService.findAll());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        recipeDetailsService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
