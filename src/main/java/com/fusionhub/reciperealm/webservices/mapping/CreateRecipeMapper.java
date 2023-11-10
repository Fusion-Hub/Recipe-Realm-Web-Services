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
        return modelMapper.map(request, Recipe.class);
    }
    
    public CreateRecipeDto convertToCreateRecipeDto(Recipe request) {
        return modelMapper.map(request, CreateRecipeDto.class);
    }
}
