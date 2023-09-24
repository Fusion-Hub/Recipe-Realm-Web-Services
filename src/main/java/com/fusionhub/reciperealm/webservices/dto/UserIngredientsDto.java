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
public class UserIngredientsDto {
    private Long userId;   
    private Long ingredientId;
    private String ingredientName;
    private Double quantity;
    private Unit unit;
}
