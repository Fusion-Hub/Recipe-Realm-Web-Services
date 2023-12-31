package com.fusionhub.reciperealm.webservices.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateRecipeDto {
    private Long id;
    private String name;
    private String description;
    private String ingredients;
    private String cookTime;
    private List<RecipeStepsDto> steps;
    private String videoLink;
    private String imageLink;
}