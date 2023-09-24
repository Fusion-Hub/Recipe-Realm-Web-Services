package com.fusionhub.reciperealm.webservices.dto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ShoppingListDto {
    private Long id;
    private Long userId;
    private Long ingredientId;
}
