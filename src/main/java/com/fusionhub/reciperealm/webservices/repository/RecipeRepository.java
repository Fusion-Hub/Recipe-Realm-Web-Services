package com.fusionhub.reciperealm.webservices.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fusionhub.reciperealm.webservices.models.Recipe;
import java.util.List;


public interface RecipeRepository extends JpaRepository<Recipe, Long>{
    List<Recipe> findByName(String name);

    List<Recipe> findByDetails_Ingredients_Name(String ingredientName);

    List<Recipe> findByUserId(Long userId); 
}
