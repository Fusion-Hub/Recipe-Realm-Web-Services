package com.fusionhub.reciperealm.webservices.mapping;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fusionhub.reciperealm.webservices.dto.RecipeRatingDto;
import com.fusionhub.reciperealm.webservices.models.RecipeRating;

@Component
public class RecipeRatingMapper {

    @Autowired
    private ModelMapper modelMapper;

    public RecipeRatingDto convertToRecipeRatingDto(RecipeRating recipeRating) {
        return modelMapper.map(recipeRating, RecipeRatingDto.class);
    }

    public RecipeRating convertToRecipeRating(RecipeRatingDto recipeRatingDto) {
        return modelMapper.map(recipeRatingDto, RecipeRating.class);
    }

    public List<RecipeRatingDto> convertToRecipeRatingDtoList(List<RecipeRating> recipeRatings) {
        return recipeRatings.stream()
            .map(this::convertToRecipeRatingDto)
            .collect(Collectors.toList());
    }
}