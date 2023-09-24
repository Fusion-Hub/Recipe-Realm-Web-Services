package com.fusionhub.reciperealm.webservices.mapping;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fusionhub.reciperealm.webservices.dto.UserIngredientsDto;
import com.fusionhub.reciperealm.webservices.models.UserIngredients;

@Component
public class UserIngredientsMapper {

    @Autowired
    private ModelMapper modelMapper;

    public UserIngredientsDto convertToDto(UserIngredients userIngredients) {
        return modelMapper.map(userIngredients, UserIngredientsDto.class);
    }

    public UserIngredients convertToEntity(UserIngredientsDto userIngredientsDto) {
        return modelMapper.map(userIngredientsDto, UserIngredients.class);
    }
}
