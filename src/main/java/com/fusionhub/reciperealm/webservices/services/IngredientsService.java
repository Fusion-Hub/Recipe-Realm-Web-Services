package com.fusionhub.reciperealm.webservices.services;

import java.util.Optional;

import com.fusionhub.reciperealm.webservices.dto.IngredientsDto;

public interface IngredientsService {
    
    public abstract IngredientsDto findByName(String name);

    public abstract Optional<IngredientsDto> findById(Long id);

    public abstract IngredientsDto save(IngredientsDto ingredientsDto);

    public abstract void deleteById(Long id);
    
}
