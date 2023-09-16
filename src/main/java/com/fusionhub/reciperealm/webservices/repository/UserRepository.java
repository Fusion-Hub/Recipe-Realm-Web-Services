package com.fusionhub.reciperealm.webservices.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fusionhub.reciperealm.webservices.models.User;


public interface UserRepository extends JpaRepository<User, Long>{
    
    User findByEmail(String email);

}
