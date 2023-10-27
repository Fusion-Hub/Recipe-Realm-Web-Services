package com.fusionhub.reciperealm.webservices.services;

import com.fusionhub.reciperealm.webservices.models.User;

public interface UserService {
    public User getLoggedInUserProfile(String token);
}
