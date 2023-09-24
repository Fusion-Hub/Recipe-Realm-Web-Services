package com.fusionhub.reciperealm.webservices.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RecipeNoteDto {
    private Long id;
    private String note;
    private Long userId;
}
