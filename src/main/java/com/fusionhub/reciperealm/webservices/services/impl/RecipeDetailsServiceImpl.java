package com.fusionhub.reciperealm.webservices.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fusionhub.reciperealm.webservices.dto.RecipeDetailsDto;
import com.fusionhub.reciperealm.webservices.mapping.RecipeDetailsMapper;
import com.fusionhub.reciperealm.webservices.models.Recipe;
import com.fusionhub.reciperealm.webservices.models.RecipeDetails;
import com.fusionhub.reciperealm.webservices.repository.RecipeDetailsRepository;
import com.fusionhub.reciperealm.webservices.repository.RecipeRepository;
import com.fusionhub.reciperealm.webservices.services.RecipeDetailsService;

import jakarta.persistence.EntityNotFoundException;

@Service
public class RecipeDetailsServiceImpl implements RecipeDetailsService {

    @Autowired
    private RecipeDetailsRepository recipeDetailsRepository;

    @Autowired
    private RecipeDetailsMapper recipeDetailsMapper;

    @Autowired
    private RecipeRepository recipeRepository;

    @Override
    public RecipeDetailsDto addRecipeDetail(RecipeDetailsDto recipeDetailsDto) {

        Recipe recipe = recipeRepository.findById(recipeDetailsDto.getRecipeId())
                .orElseThrow(() -> new EntityNotFoundException("Receta no encontrada"));

        RecipeDetails recipeDetail = recipeDetailsMapper.convertToRecipeDetails(recipeDetailsDto);
        recipeDetail.setRecipe(recipe);

        RecipeDetails savedDetail = recipeDetailsRepository.save(recipeDetail);

        return recipeDetailsMapper.convertToRecipeDetailsDto(savedDetail);

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
