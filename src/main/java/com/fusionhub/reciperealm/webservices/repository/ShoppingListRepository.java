package com.fusionhub.reciperealm.webservices.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fusionhub.reciperealm.webservices.models.Ingredients;
import com.fusionhub.reciperealm.webservices.models.ShoppingList;
import com.fusionhub.reciperealm.webservices.models.User;

public interface ShoppingListRepository extends JpaRepository<ShoppingList, Long>{
    List<ShoppingList> findByUser(User user);
    Optional<ShoppingList> findByUserAndIngredient(User user, Ingredients ingredient);
    
}
