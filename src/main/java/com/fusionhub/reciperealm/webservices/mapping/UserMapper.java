package com.fusionhub.reciperealm.webservices.mapping;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fusionhub.reciperealm.webservices.dto.RegistrationRequestDto;
import com.fusionhub.reciperealm.webservices.dto.UserDto;
import com.fusionhub.reciperealm.webservices.models.User;

@Component
public class UserMapper {
    @Autowired
    private ModelMapper modelMapper;


    public User convertToUser(RegistrationRequestDto request) {
        return modelMapper.map(request, User.class);
    }

    public UserDto convertToUserDto(User user) {
        return modelMapper.map(user, UserDto.class);
    }

    public User convertToUser(UserDto userDto) {
        return modelMapper.map(userDto, User.class);
    }

}
