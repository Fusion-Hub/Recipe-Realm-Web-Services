package com.fusionhub.reciperealm.webservices.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fusionhub.reciperealm.webservices.dto.RecipeNoteDto;
import com.fusionhub.reciperealm.webservices.services.RecipeNoteService;

@RestController
@RequestMapping("/api/v1/recipes/{recipeId}/note")
public class RecipeNoteController {

    @Autowired
    private RecipeNoteService recipeNoteService;

    @PostMapping
    public ResponseEntity<RecipeNoteDto> addNoteToRecipe(@PathVariable Long recipeId, @RequestBody RecipeNoteDto noteDto) {
        RecipeNoteDto savedNote = recipeNoteService.addNoteToRecipe(recipeId, noteDto);
        return new ResponseEntity<>(savedNote, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<RecipeNoteDto> getNoteByRecipe(@PathVariable Long recipeId) {
        RecipeNoteDto noteDto = recipeNoteService.getNoteByRecipe(recipeId);
        if (noteDto == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(noteDto, HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteNoteFromRecipe(@PathVariable Long recipeId) {
        recipeNoteService.deleteNoteFromRecipe(recipeId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}