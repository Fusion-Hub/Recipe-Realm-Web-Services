package com.fusionhub.reciperealm.webservices.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.fusionhub.reciperealm.webservices.models.Recipe;
import com.fusionhub.reciperealm.webservices.models.RecipeRating;
import com.fusionhub.reciperealm.webservices.models.User;

public interface RecipeRatingRepository extends JpaRepository<RecipeRating, Long>{
    boolean existsByUserAndRecipe(User user, Recipe recipe);

    Optional<RecipeRating> findByUserAndRecipe(User user, Recipe recipe);

    @Query("SELECT AVG(rr.rating) FROM RecipeRating rr WHERE rr.recipe = :recipe")
    Optional<Double> findAverageRatingByRecipe(@Param("recipe") Recipe recipe);

    List<RecipeRating> findAllByRecipe(Recipe recipe);
}
