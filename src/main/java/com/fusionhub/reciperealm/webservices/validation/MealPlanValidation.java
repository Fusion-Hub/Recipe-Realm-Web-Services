package com.fusionhub.reciperealm.webservices.validation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fusionhub.reciperealm.webservices.exception.ValidationException;
import com.fusionhub.reciperealm.webservices.repository.MealPlanRepository;

@Component
public class MealPlanValidation {

    @Autowired
    private MealPlanRepository mealPlanRepository;

    public void validateMealAssignment(Long userId, String day) {
        boolean exists = mealPlanRepository.findByUserIdAndDayOfWeek(userId, day).isPresent();
        if (exists) {
            throw new ValidationException("Ya hay una receta asignada para ese día. Por favor, elige otro día o elimina la receta existente.");
        }
    }
}