package com.fusionhub.reciperealm.webservices.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fusionhub.reciperealm.webservices.models.MealPlan;

public interface MealPlanRepository extends JpaRepository<MealPlan, Long>{

    Optional<MealPlan> findByUserIdAndDayOfWeek(Long userId, String dayOfWeek);

    List<MealPlan> findByUserId(Long userId);

}
