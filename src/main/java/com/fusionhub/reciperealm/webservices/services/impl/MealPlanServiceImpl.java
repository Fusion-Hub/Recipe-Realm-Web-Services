package com.fusionhub.reciperealm.webservices.services.impl;


import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fusionhub.reciperealm.webservices.dto.MealPlanDto;
import com.fusionhub.reciperealm.webservices.mapping.MealPlanMapper;
import com.fusionhub.reciperealm.webservices.models.DayOfWeek;
import com.fusionhub.reciperealm.webservices.models.MealPlan;
import com.fusionhub.reciperealm.webservices.models.Recipe;
import com.fusionhub.reciperealm.webservices.models.User;
import com.fusionhub.reciperealm.webservices.repository.MealPlanRepository;
import com.fusionhub.reciperealm.webservices.repository.RecipeRepository;
import com.fusionhub.reciperealm.webservices.repository.UserRepository;
import com.fusionhub.reciperealm.webservices.services.MealPlanService;
import com.fusionhub.reciperealm.webservices.validation.MealPlanValidation;

@Service
public class MealPlanServiceImpl implements MealPlanService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RecipeRepository recipeRepository;

    @Autowired
    private MealPlanRepository mealPlanRepository;

    @Autowired
    private MealPlanMapper mealPlanMapper;

    @Autowired
    private MealPlanValidation mealPlanValidation;

    @Override
    public MealPlanDto assignMealToDay(Long userId, String day, Long recipeId) {
        mealPlanValidation.validateMealAssignment(userId, day);

        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        Recipe recipe = recipeRepository.findById(recipeId).orElseThrow(() -> new RuntimeException("Recipe not found"));

        MealPlan mealPlan = new MealPlan();
        mealPlan.setUser(user);
        mealPlan.setDayOfWeek(DayOfWeek.valueOf(day.toUpperCase()));
        mealPlan.setRecipe(recipe);
        
        mealPlan = mealPlanRepository.save(mealPlan);

        return mealPlanMapper.convertToDto(mealPlan);
    }

    @Override
    public void removeMealFromDay(Long userId, String day) {
        MealPlan mealPlan = mealPlanRepository.findByUserIdAndDayOfWeek(userId, day)
            .orElseThrow(() -> new RuntimeException("Meal plan not found for user and day"));

        mealPlanRepository.delete(mealPlan);
    }

    @Override
    public List<MealPlanDto> getMealPlansByUserId(Long userId) {
        List<MealPlan> mealPlans = mealPlanRepository.findByUserId(userId);
        return mealPlans.stream()
            .map(mealPlanMapper::convertToDto)
            .collect(Collectors.toList());
    }
}