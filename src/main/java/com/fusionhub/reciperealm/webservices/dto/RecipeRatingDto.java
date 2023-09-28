package com.fusionhub.reciperealm.webservices.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RecipeRatingDto {
    private Long id;
    private int rating;
    private String comment;
    private UserDto user;
}
