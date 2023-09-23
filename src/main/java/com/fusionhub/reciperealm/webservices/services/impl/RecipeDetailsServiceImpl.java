package com.fusionhub.reciperealm.webservices.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.fusionhub.reciperealm.webservices.dto.RecipeDetailsDto;
import com.fusionhub.reciperealm.webservices.mapping.RecipeDetailsMapper;
import com.fusionhub.reciperealm.webservices.repository.RecipeDetailsRepository;
import com.fusionhub.reciperealm.webservices.services.RecipeDetailsService;

public class RecipeDetailsServiceImpl implements RecipeDetailsService{

    @Autowired
    private RecipeDetailsRepository recipeDetailsRepository;

    @Autowired
    private RecipeDetailsMapper recipeDetailsMapper;

    @Override
    public RecipeDetailsDto save(RecipeDetailsDto details) {
        return recipeDetailsMapper.convertToRecipeDetailsDto(recipeDetailsRepository.save(recipeDetailsMapper.convertToRecipeDetails(details)));
    }

    @Override
    public Optional<RecipeDetailsDto> findById(Long id) {
        return Optional.of(recipeDetailsMapper.convertToRecipeDetailsDto(recipeDetailsRepository.findById(id).get()));
    }

    @Override
    public List<RecipeDetailsDto> findAll() {
        return recipeDetailsMapper.convertToRecipeDetailsDtoList(recipeDetailsRepository.findAll());
    }

    @Override
    public void deleteById(Long id) {
        recipeDetailsRepository.deleteById(id);
    }

}
