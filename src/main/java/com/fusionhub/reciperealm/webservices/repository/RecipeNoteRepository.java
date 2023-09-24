package com.fusionhub.reciperealm.webservices.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fusionhub.reciperealm.webservices.models.Recipe;
import com.fusionhub.reciperealm.webservices.models.RecipeNote;

public interface RecipeNoteRepository extends JpaRepository<RecipeNote, Long>{
    Optional<RecipeNote> findByRecipe(Recipe recipe);
}
