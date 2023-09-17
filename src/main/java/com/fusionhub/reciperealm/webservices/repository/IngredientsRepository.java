package com.fusionhub.reciperealm.webservices.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fusionhub.reciperealm.webservices.models.Ingredients;


public interface IngredientsRepository extends JpaRepository<Ingredients, Long>{
    Ingredients findByName(String name);
}
