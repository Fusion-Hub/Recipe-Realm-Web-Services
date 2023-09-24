package com.fusionhub.reciperealm.webservices.mapping;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fusionhub.reciperealm.webservices.dto.RecipeNoteDto;
import com.fusionhub.reciperealm.webservices.models.RecipeNote;

@Component
public class RecipeNoteMapper {
    @Autowired
    private ModelMapper modelMapper;

     public RecipeNote convertToRecipeNote(RecipeNoteDto dto) {
        return modelMapper.map(dto, RecipeNote.class);
    }

    public RecipeNoteDto convertToRecipeNoteDto(RecipeNote note) {
        return modelMapper.map(note, RecipeNoteDto.class);
    }
}
