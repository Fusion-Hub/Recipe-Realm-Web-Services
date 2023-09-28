package com.fusionhub.reciperealm.webservices.services;

import java.util.List;
import java.util.Optional;

import com.fusionhub.reciperealm.webservices.dto.RecipeDetailsDto;

public interface RecipeDetailsService {

    RecipeDetailsDto save(RecipeDetailsDto details);

    Optional<RecipeDetailsDto> findById(Long id);

    List<RecipeDetailsDto> findAll();

    void deleteById(Long id);
}
