package com.fusionhub.reciperealm.webservices.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fusionhub.reciperealm.webservices.dto.RecipeNoteDto;
import com.fusionhub.reciperealm.webservices.exception.ValidationException;
import com.fusionhub.reciperealm.webservices.mapping.RecipeNoteMapper;
import com.fusionhub.reciperealm.webservices.models.Recipe;
import com.fusionhub.reciperealm.webservices.models.RecipeNote;
import com.fusionhub.reciperealm.webservices.repository.RecipeNoteRepository;
import com.fusionhub.reciperealm.webservices.repository.RecipeRepository;
import com.fusionhub.reciperealm.webservices.services.RecipeNoteService;
import com.fusionhub.reciperealm.webservices.validation.RecipeNoteValidation;

@Service
public class RecipeNoteServiceImpl implements RecipeNoteService{
    @Autowired
    private RecipeNoteRepository recipeNoteRepository;

    @Autowired
    private RecipeRepository recipeRepository;

    @Autowired
    private RecipeNoteValidation recipeNoteValidation;

    @Autowired
    private RecipeNoteMapper recipeNoteMapper;

    @Override
    public RecipeNoteDto addNoteToRecipe(Long recipeId, RecipeNoteDto noteDto) {
        recipeNoteValidation.validateRecipeNoteCreation(recipeId, noteDto);
        Recipe recipe = recipeRepository.findById(recipeId).orElseThrow(() -> new ValidationException("Recipe not found!"));

        RecipeNote recipeNote = new RecipeNote();
        recipeNote.setRecipe(recipe);
        recipeNote.setNote(noteDto.getNote());

        recipeNoteRepository.save(recipeNote);

        return recipeNoteMapper.convertToRecipeNoteDto(recipeNote);
    }

    @Override
    public RecipeNoteDto getNoteByRecipe(Long recipeId) {
        Recipe recipe = recipeRepository.findById(recipeId).orElseThrow(() -> new ValidationException("Recipe not found!"));
        RecipeNote note = recipeNoteRepository.findByRecipe(recipe).orElse(null);
        if (note == null) {
            return null;
        }
        return recipeNoteMapper.convertToRecipeNoteDto(note);
    }

    @Override
    public void deleteNoteFromRecipe(Long recipeId) {
        Recipe recipe = recipeRepository.findById(recipeId).orElseThrow(() -> new ValidationException("Recipe not found!"));
        RecipeNote note = recipeNoteRepository.findByRecipe(recipe).orElse(null);
        if (note != null) {
            recipeNoteRepository.delete(note);
        }
    }
}

