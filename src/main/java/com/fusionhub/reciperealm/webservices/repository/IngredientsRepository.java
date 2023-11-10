package com.fusionhub.reciperealm.webservices.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fusionhub.reciperealm.webservices.models.Ingredients;


public interface IngredientsRepository extends JpaRepository<Ingredients, Long>{
    
    List<Ingredients> findByNameContainingIgnoreCase(String name);

    List<Ingredients> findByNameIn(List<String> names);

    
}
