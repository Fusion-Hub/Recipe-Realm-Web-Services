package com.fusionhub.reciperealm.webservices.services.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fusionhub.reciperealm.webservices.dto.IngredientsDto;
import com.fusionhub.reciperealm.webservices.mapping.IngredientsMapper;
import com.fusionhub.reciperealm.webservices.repository.IngredientsRepository;
import com.fusionhub.reciperealm.webservices.services.IngredientsService;
import com.fusionhub.reciperealm.webservices.validation.IngredientsValidation;

@Service
public class IngredientsServiceImp implements IngredientsService{

    @Autowired
    private IngredientsRepository ingredientsRepository;

    @Autowired
    private IngredientsMapper ingredientsMapper;

    @Autowired
    private IngredientsValidation ingredientsValidation;
    
    @Override
    public IngredientsDto findByName(String name) {
       return ingredientsMapper.convertToIngredientDto(ingredientsRepository.findByName(name));
    }

    @Override
    public Optional<IngredientsDto> findById(Long id) {
        return Optional.of(ingredientsMapper.convertToIngredientDto(ingredientsRepository.findById(id).get()));
    }

    @Override
    public IngredientsDto save(IngredientsDto ingredientsDto) {
        ingredientsValidation.validateIngredients(ingredientsDto.getName());
        return ingredientsMapper.convertToIngredientDto(ingredientsRepository.save(ingredientsMapper.convertToIngredient(ingredientsDto)));
    }

    @Override
    public void deleteById(Long id) {
        ingredientsRepository.deleteById(id);
    }
    
}
