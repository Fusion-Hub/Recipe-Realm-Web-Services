package com.fusionhub.reciperealm.webservices.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.fusionhub.reciperealm.webservices.models.Recipe;
import java.util.List;


public interface RecipeRepository extends JpaRepository<Recipe, Long>{
    List<Recipe> findByName(String name);

    @Query("SELECT r FROM Recipe r JOIN r.details d WHERE d.ingredient.name IN :ingredients")
    List<Recipe> findByIngredients(@Param("ingredients") List<String> ingredientNames);
}
