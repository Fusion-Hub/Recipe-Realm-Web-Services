package com.fusionhub.reciperealm.webservices.mapping;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fusionhub.reciperealm.webservices.dto.RecipeDetailsDto;
import com.fusionhub.reciperealm.webservices.models.RecipeDetails;

@Component
public class RecipeDetailsMapper {
    @Autowired
    private ModelMapper modelMapper;

    public RecipeDetails convertToRecipeDetails(RecipeDetailsDto request) {
        return modelMapper.map(request, RecipeDetails.class);
    }

    public RecipeDetailsDto convertToRecipeDetailsDto(RecipeDetails request) {
        return modelMapper.map(request, RecipeDetailsDto.class);
    }

    public List<RecipeDetailsDto> convertToRecipeDetailsDtoList(List<RecipeDetails> recipes) {
        return recipes.stream()
                      .map(this::convertToRecipeDetailsDto)
                      .collect(java.util.stream.Collectors.toList());
    }
}
