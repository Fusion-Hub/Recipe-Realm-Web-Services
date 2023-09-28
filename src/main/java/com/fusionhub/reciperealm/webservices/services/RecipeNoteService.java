package com.fusionhub.reciperealm.webservices.services;

import com.fusionhub.reciperealm.webservices.dto.RecipeNoteDto;

public interface RecipeNoteService {
    RecipeNoteDto addNoteToRecipe(Long recipeId, RecipeNoteDto noteDto);

    RecipeNoteDto getNoteByRecipe(Long recipeId);

    void deleteNoteFromRecipe(Long recipeId);
}
