package com.fusionhub.reciperealm.webservices.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fusionhub.reciperealm.webservices.models.Favorite;
import com.fusionhub.reciperealm.webservices.models.Recipe;
import com.fusionhub.reciperealm.webservices.models.User;

public interface FavoriteRepository extends JpaRepository<Favorite, Long>{
    List<Favorite> findByUserId(Long userId);
     Optional<Favorite> findByUserAndRecipe(User user, Recipe recipe);
}
