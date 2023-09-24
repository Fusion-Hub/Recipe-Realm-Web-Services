package com.fusionhub.reciperealm.webservices.mapping;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fusionhub.reciperealm.webservices.dto.FavoriteDto;
import com.fusionhub.reciperealm.webservices.models.Favorite;

@Component
public class FavoriteMapper {
    @Autowired
    private ModelMapper modelMapper;

    public FavoriteDto convertToDto(Favorite favorite) {
        return modelMapper.map(favorite, FavoriteDto.class);
    }

    public Favorite convertToEntity(FavoriteDto favoriteDto) {
        return modelMapper.map(favoriteDto, Favorite.class);
    }
}
