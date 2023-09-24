package com.fusionhub.reciperealm.webservices.mapping;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fusionhub.reciperealm.webservices.dto.MealPlanDto;
import com.fusionhub.reciperealm.webservices.models.MealPlan;

@Component
public class MealPlanMapper {

    @Autowired
    private ModelMapper modelMapper;

    public MealPlanDto convertToDto(MealPlan mealPlan) {
        return modelMapper.map(mealPlan, MealPlanDto.class);
    }

    public MealPlan convertToEntity(MealPlanDto mealPlanDto) {
        return modelMapper.map(mealPlanDto, MealPlan.class);
    }
}