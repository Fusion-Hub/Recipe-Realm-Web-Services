package com.fusionhub.reciperealm.webservices.validation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fusionhub.reciperealm.webservices.exception.ValidationException;
import com.fusionhub.reciperealm.webservices.repository.IngredientsRepository;

@Component
public class IngredientsValidation {
    
    @Autowired
    private IngredientsRepository ingredientsRepository;

    public void validateIngredients(String name) {
        if (name == null || name.isEmpty()) {
            throw new ValidationException("Name is required");
        }
        if (ingredientsRepository.findByName(name) != null) {
            throw new ValidationException("Ingredients already exists");
        }
    }
}
