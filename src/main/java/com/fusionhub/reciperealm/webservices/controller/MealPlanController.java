package com.fusionhub.reciperealm.webservices.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fusionhub.reciperealm.webservices.dto.MealPlanDto;
import com.fusionhub.reciperealm.webservices.services.MealPlanService;

@RestController
@RequestMapping("/api/v1/meal-plans")
public class MealPlanController {
    
    @Autowired
    private MealPlanService mealPlanService;

    @PostMapping("/assign")
    public ResponseEntity<MealPlanDto> assignMealToDay(
            @RequestParam Long userId, 
            @RequestParam String day, 
            @RequestParam Long recipeId) {
        
        MealPlanDto mealPlanDto = mealPlanService.assignMealToDay(userId, day, recipeId);
        return new ResponseEntity<>(mealPlanDto, HttpStatus.CREATED);
    }

    @DeleteMapping("/remove/{userId}/{day}")
    public ResponseEntity<Void> removeMealFromDay(@PathVariable Long userId, @PathVariable String day) {
        mealPlanService.removeMealFromDay(userId, day);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<MealPlanDto>> getMealPlansByUserId(@PathVariable Long userId) {
        List<MealPlanDto> mealPlanDtos = mealPlanService.getMealPlansByUserId(userId);
        return new ResponseEntity<>(mealPlanDtos, HttpStatus.OK);
    }
}
