package com.fusionhub.reciperealm.webservices.mapping;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fusionhub.reciperealm.webservices.dto.ShoppingListDto;
import com.fusionhub.reciperealm.webservices.models.ShoppingList;

@Component
public class ShoppingListMapper {
     @Autowired
    private ModelMapper modelMapper;

    public ShoppingListDto convertToDto(ShoppingList shoppingList) {
        return modelMapper.map(shoppingList, ShoppingListDto.class);
    }

    public ShoppingList convertToEntity(ShoppingListDto dto) {
        return modelMapper.map(dto, ShoppingList.class);
    }
}
