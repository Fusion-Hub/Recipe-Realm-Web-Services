package com.fusionhub.reciperealm.webservices.validation;

import org.springframework.stereotype.Component;

import com.fusionhub.reciperealm.webservices.dto.RecipeDto;
import com.fusionhub.reciperealm.webservices.exception.ValidationException;

@Component
public class RecipeValidation {

    public void validateRecipe(RecipeDto request) {
        if (request.getName() == null || request.getName().isEmpty()) {
            throw new ValidationException("Name is required");
        }
    }

}
