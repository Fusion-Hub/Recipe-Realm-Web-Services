package com.fusionhub.reciperealm.webservices.mapping;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fusionhub.reciperealm.webservices.dto.RecipeDto;
import com.fusionhub.reciperealm.webservices.dto.RecipeNoteDto;
import com.fusionhub.reciperealm.webservices.models.Recipe;
import com.fusionhub.reciperealm.webservices.models.RecipeNote;

@Component
public class RecipeMapper {
    
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private RecipeNoteMapper recipeNoteMapper;

   public Recipe convertToRecipe(RecipeDto request) {
        Recipe recipe = modelMapper.map(request, Recipe.class);

       
        if(request.getNote() != null) {
            RecipeNote note = recipeNoteMapper.convertToRecipeNote(request.getNote());
            recipe.setNote(note);
        }

        return recipe;
    }

    public RecipeDto convertToRecipeDto(Recipe recipe) {
        RecipeDto dto = modelMapper.map(recipe, RecipeDto.class);

        if(recipe.getNote() != null) {
            RecipeNoteDto noteDto = recipeNoteMapper.convertToRecipeNoteDto(recipe.getNote());
            dto.setNote(noteDto);
        }

        return dto;
    }

    public List<RecipeDto> convertToRecipeDtoList(List<Recipe> recipes) {
        return recipes.stream()
                      .map(this::convertToRecipeDto)
                      .collect(Collectors.toList());
    }
}
