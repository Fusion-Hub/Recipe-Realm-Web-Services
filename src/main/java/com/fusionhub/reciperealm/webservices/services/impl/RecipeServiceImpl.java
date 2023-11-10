package com.fusionhub.reciperealm.webservices.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fusionhub.reciperealm.webservices.dto.CreateRecipeDto;
import com.fusionhub.reciperealm.webservices.dto.RecipeDto;
import com.fusionhub.reciperealm.webservices.dto.UserDto;
import com.fusionhub.reciperealm.webservices.mapping.CreateRecipeMapper;
import com.fusionhub.reciperealm.webservices.mapping.RecipeMapper;
import com.fusionhub.reciperealm.webservices.mapping.UserMapper;
import com.fusionhub.reciperealm.webservices.models.Recipe;
import com.fusionhub.reciperealm.webservices.models.User;
import com.fusionhub.reciperealm.webservices.repository.RecipeRepository;
import com.fusionhub.reciperealm.webservices.services.RecipeService;
import com.fusionhub.reciperealm.webservices.services.UserService;
import com.fusionhub.reciperealm.webservices.validation.RecipeValidation;

@Service
public class RecipeServiceImpl implements RecipeService {

    @Autowired
    private RecipeRepository recipeRepository;

    @Autowired
    private RecipeMapper recipeMapper;

    @Autowired
    private CreateRecipeMapper createRecipeMapper;

    @Autowired
    private RecipeValidation recipeValidation;

    @Autowired
    private UserService userService;

    @Autowired
    private UserMapper userMapper;

    @Override
    public CreateRecipeDto save(CreateRecipeDto recipeDto, String token) {
        recipeValidation.validateRecipe(recipeDto);

        UserDto userDto = userService.getLoggedInUserProfile(token);
        User user = userMapper.convertToUser(userDto);

        Recipe recipe = createRecipeMapper.convertToRecipe(recipeDto);
        recipe.setUser(user);

        Recipe savedRecipe = recipeRepository.save(recipe);
        CreateRecipeDto savedRecipeDto = createRecipeMapper.convertToCreateRecipeDto(savedRecipe);

        return savedRecipeDto;
    }

    @Override
    public Optional<RecipeDto> findById(Long id) {
        return Optional.of(recipeMapper.convertToRecipeDto(recipeRepository.findById(id).get()));
    }

    @Override
    public List<RecipeDto> findAll() {
        return recipeMapper.convertToRecipeDtoList(recipeRepository.findAll());
    }

    @Override
    public List<RecipeDto> findByName(String name) {
        return recipeMapper.convertToRecipeDtoList(recipeRepository.findByName(name));
    }

    @Override
    public List<RecipeDto> findByIngredientName(String ingredientName) {
        return recipeMapper.convertToRecipeDtoList(recipeRepository.findByDetails_Ingredients_Name(ingredientName));
    }

    @Override
    public void deleteById(Long id) {
        recipeRepository.deleteById(id);
    }

}
