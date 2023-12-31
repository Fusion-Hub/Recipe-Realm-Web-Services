package com.fusionhub.reciperealm.webservices.dto;

import com.fusionhub.reciperealm.webservices.models.Unit;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RecipeDetailsDto {
    private Long id;
    private Long recipeId;
    private IngredientsDto ingredients;
    private String quantity;
    private Unit unit;
}
