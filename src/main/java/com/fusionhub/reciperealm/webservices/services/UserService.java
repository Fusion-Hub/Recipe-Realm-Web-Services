package com.fusionhub.reciperealm.webservices.services;

import com.fusionhub.reciperealm.webservices.dto.UserDto;

public interface UserService {
    
    public abstract UserDto getLoggedInUserProfile(String token);

    public abstract UserDto uploadProfileImage(String token, byte[] imageData);

    public abstract UserDto uploadBannerImage(String token, byte[] imageData);
}
