package com.fusionhub.reciperealm.webservices.services;

import java.util.List;

import com.fusionhub.reciperealm.webservices.dto.MealPlanDto;

public interface MealPlanService {
    
    public abstract MealPlanDto assignMealToDay(Long userId, String day, Long recipeId);

    public abstract void removeMealFromDay(Long userId, String day);

    public abstract List<MealPlanDto> getMealPlansByUserId(Long userId);
}
