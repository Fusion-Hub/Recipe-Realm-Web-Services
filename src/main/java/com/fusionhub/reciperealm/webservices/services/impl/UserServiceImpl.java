package com.fusionhub.reciperealm.webservices.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fusionhub.reciperealm.webservices.dto.UserDto;
import com.fusionhub.reciperealm.webservices.mapping.UserMapper;
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

    @Autowired
    private UserMapper userMapper;  

    @Override
    public UserDto getLoggedInUserProfile(String token) {
        String userEmail = jwtService.extractUsername(token);
        User user = userRepository.findByEmail(userEmail);
        return userMapper.convertToUserDto(user); 
    }

    @Override
    public UserDto uploadProfileImage(String token, byte[] imageData) {
        String userEmail = jwtService.extractUsername(token);
        User user = userRepository.findByEmail(userEmail);
        user.setProfileImage(imageData);
        userRepository.save(user);
        return userMapper.convertToUserDto(user);
    }
}