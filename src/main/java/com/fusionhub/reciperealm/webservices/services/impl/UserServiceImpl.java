package com.fusionhub.reciperealm.webservices.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fusionhub.reciperealm.webservices.models.User;
import com.fusionhub.reciperealm.webservices.repository.UserRepository;
import com.fusionhub.reciperealm.webservices.services.JwtService;
import com.fusionhub.reciperealm.webservices.services.UserService;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtService jwtService;

    @Override
    public User getLoggedInUserProfile(String token) {
        String userEmail = jwtService.extractUsername(token);
        return userRepository.findByEmail(userEmail);
    }
}