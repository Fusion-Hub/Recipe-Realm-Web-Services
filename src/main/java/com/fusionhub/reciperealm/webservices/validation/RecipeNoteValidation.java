package com.fusionhub.reciperealm.webservices.validation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fusionhub.reciperealm.webservices.dto.RecipeNoteDto;
import com.fusionhub.reciperealm.webservices.exception.ValidationException;
import com.fusionhub.reciperealm.webservices.models.Recipe;
import com.fusionhub.reciperealm.webservices.models.RecipeNote;
import com.fusionhub.reciperealm.webservices.repository.RecipeRepository;

@Component
public class RecipeNoteValidation {

    @Autowired
    private RecipeRepository recipeRepository;

    public void validateRecipeNoteCreation(Long recipeId, RecipeNoteDto noteDto) {
        Recipe recipe = recipeRepository.findById(recipeId).orElseThrow(() -> new ValidationException("Recipe not found!"));

        if (noteDto.getNote() == null || noteDto.getNote().isEmpty()) {
            throw new ValidationException("Note content is required");
        }

        if (noteDto.getNote().length() > 500) {
            throw new ValidationException("Note content should not exceed 500 characters");
        }

        RecipeNote existingNote = recipe.getNote();
        if (existingNote != null) {
            throw new ValidationException("Recipe already has a note attached");
        }
    }
}
