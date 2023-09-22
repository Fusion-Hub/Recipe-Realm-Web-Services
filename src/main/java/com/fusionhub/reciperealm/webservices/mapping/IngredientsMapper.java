package com.fusionhub.reciperealm.webservices.mapping;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fusionhub.reciperealm.webservices.dto.IngredientsDto;
import com.fusionhub.reciperealm.webservices.models.Ingredients;

@Component
public class IngredientsMapper {
    @Autowired
    private ModelMapper modelMapper;

    public Ingredients convertToIngredient(IngredientsDto request) {
        return modelMapper.map(request, Ingredients.class);
    }

    public IngredientsDto convertToIngredientDto(Ingredients request) {
        return modelMapper.map(request, IngredientsDto.class);
    }
}
