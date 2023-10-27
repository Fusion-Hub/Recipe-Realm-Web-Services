package com.fusionhub.reciperealm.webservices.services;

import com.fusionhub.reciperealm.webservices.dto.UserDto;

public interface UserService {
    public UserDto getLoggedInUserProfile(String token);
}
