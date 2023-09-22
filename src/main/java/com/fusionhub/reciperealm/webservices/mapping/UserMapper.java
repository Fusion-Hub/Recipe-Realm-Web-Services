package com.fusionhub.reciperealm.webservices.mapping;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fusionhub.reciperealm.webservices.dto.RegistrationRequest;
import com.fusionhub.reciperealm.webservices.models.User;

@Component
public class UserMapper {
    @Autowired
    private ModelMapper modelMapper;


    public User convertToUser(RegistrationRequest request) {
        return modelMapper.map(request, User.class);
    }

}
