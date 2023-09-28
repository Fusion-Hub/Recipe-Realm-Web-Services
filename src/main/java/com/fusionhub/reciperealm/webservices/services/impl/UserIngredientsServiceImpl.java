package com.fusionhub.reciperealm.webservices.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fusionhub.reciperealm.webservices.dto.UserIngredientsDto;
import com.fusionhub.reciperealm.webservices.mapping.UserIngredientsMapper;
import com.fusionhub.reciperealm.webservices.models.UserIngredients;
import com.fusionhub.reciperealm.webservices.repository.IngredientsRepository;
import com.fusionhub.reciperealm.webservices.repository.UserIngredientsRepository;
import com.fusionhub.reciperealm.webservices.repository.UserRepository;
import com.fusionhub.reciperealm.webservices.services.UserIngredientsService;
import com.fusionhub.reciperealm.webservices.validation.UserIngredientsValidation;

@Service
public class UserIngredientsServiceImpl implements UserIngredientsService{
    @Autowired
    private UserIngredientsRepository userIngredientsRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private IngredientsRepository ingredientRepository;

    @Autowired
    private UserIngredientsValidation userIngredientsValidation;

    @Autowired
    private UserIngredientsMapper userIngredientsMapper;

    @Override
    public void addUserIngredient(UserIngredientsDto request) {
        userIngredientsValidation.validateAddition(request);

        UserIngredients userIngredient = userIngredientsMapper.convertToEntity(request);

        userIngredient.setUser(userRepository.findById(request.getUserId()).orElse(null));
        userIngredient.setIngredient(ingredientRepository.findById(request.getIngredientId()).orElse(null));

        userIngredientsRepository.save(userIngredient);
    }

    @Override
    public void updateUserIngredient(UserIngredientsDto request) {
        userIngredientsValidation.validateUpdate(request);

        UserIngredients existingUserIngredient = userIngredientsRepository
            .findByUser_IdAndIngredient_Id(request.getUserId(), request.getIngredientId())
            .orElseThrow(() -> new RuntimeException("Ingredient not found in user's pantry"));

        UserIngredients updatedUserIngredient = userIngredientsMapper.convertToEntity(request);
        existingUserIngredient.setQuantity(updatedUserIngredient.getQuantity());
        existingUserIngredient.setUnit(updatedUserIngredient.getUnit());

        userIngredientsRepository.save(existingUserIngredient);
    }

    @Override
    public void removeUserIngredient(Long userId, Long ingredientId) {
        userIngredientsValidation.validateRemoval(userId, ingredientId);
        userIngredientsRepository.deleteByUser_IdAndIngredient_Id(userId, ingredientId);
    }

    @Override
    public void removeAllIngredientsForUser(Long userId) {
    userIngredientsRepository.deleteByUser_Id(userId);
    }
}

