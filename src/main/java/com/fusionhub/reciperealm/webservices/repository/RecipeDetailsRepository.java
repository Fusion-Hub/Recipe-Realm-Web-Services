package com.fusionhub.reciperealm.webservices.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fusionhub.reciperealm.webservices.models.RecipeDetails;

public interface RecipeDetailsRepository extends JpaRepository<RecipeDetails, Long>{
    
}
