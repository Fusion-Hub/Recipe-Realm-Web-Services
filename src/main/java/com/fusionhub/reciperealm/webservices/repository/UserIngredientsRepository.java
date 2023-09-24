package com.fusionhub.reciperealm.webservices.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fusionhub.reciperealm.webservices.models.UserIngredients;

public interface UserIngredientsRepository extends JpaRepository<UserIngredients, Long>{
    List<UserIngredients> findByUser_Id(Long userId);
    Optional<UserIngredients> findByUser_IdAndIngredient_Id(Long userId, Long ingredientId);
    void deleteByUser_Id(Long userId);
    void deleteByUser_IdAndIngredient_Id(Long userId, Long ingredientId);
}
