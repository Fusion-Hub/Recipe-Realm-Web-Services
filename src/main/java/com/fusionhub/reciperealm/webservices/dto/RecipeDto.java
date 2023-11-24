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
public class RecipeDto {
    private Long id;
    private String name;
    private String description;
    private String cookTime;
    private String ingredients;
    private List<String> steps;
    private String videoLink;
    private String imageLink;
    private UserDto user;
    private RecipeNoteDto note;
    private List<RecipeRatingDto> ratings;
}
