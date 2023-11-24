package com.fusionhub.reciperealm.webservices.mapping;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fusionhub.reciperealm.webservices.dto.CreateRecipeDto;
import com.fusionhub.reciperealm.webservices.models.Recipe;

@Component
public class CreateRecipeMapper {

    @Autowired
    private ModelMapper modelMapper;

    public Recipe convertToRecipe(CreateRecipeDto request) {
        Recipe recipe = modelMapper.map(request, Recipe.class);
        
        if (recipe.getSteps() != null) {
            recipe.getSteps().forEach(step -> step.setRecipe(recipe));
        }

        return recipe;
    }
    
    public CreateRecipeDto convertToCreateRecipeDto(Recipe request) {
        return modelMapper.map(request, CreateRecipeDto.class);
    }
}
