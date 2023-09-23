package com.fusionhub.reciperealm.webservices.mapping;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fusionhub.reciperealm.webservices.dto.RecipeDto;
import com.fusionhub.reciperealm.webservices.models.Recipe;

@Component
public class RecipeMapper {
    
    @Autowired
    private ModelMapper modelMapper;

    public Recipe convertToRecipe(RecipeDto request) {
        return modelMapper.map(request, Recipe.class);
    }

    public RecipeDto convertToRecipeDto(Recipe request) {
        return modelMapper.map(request, RecipeDto.class);
    }

     public List<RecipeDto> convertToRecipeDtoList(List<Recipe> recipes) {
        return recipes.stream()
                      .map(this::convertToRecipeDto)
                      .collect(Collectors.toList());
    }
}
