package com.fusionhub.reciperealm.webservices.validation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fusionhub.reciperealm.webservices.dto.RegistrationRequest;
import com.fusionhub.reciperealm.webservices.exception.ValidationException;
import com.fusionhub.reciperealm.webservices.models.User;
import com.fusionhub.reciperealm.webservices.repository.UserRepository;

@Component
public class UserValidation {

    @Autowired
    private UserRepository userRepository;
    
    public void validateUser(RegistrationRequest request) {
         if (request.getFirstName() == null || request.getFirstName().isEmpty()) {
            throw new ValidationException("Name is required");
        }
        if (request.getLastName() == null || request.getLastName().isEmpty()) {
            throw new ValidationException("Last Name is required");
        }
        if (request.getEmail() == null || request.getEmail().isEmpty()) {
            throw new ValidationException("Email is required");
        }
        User existingUser = userRepository.findByEmail(request.getEmail());
        if (existingUser != null) {
            throw new ValidationException("Email already in use");
        }
        if (request.getPassword() == null || request.getPassword().isEmpty()) {
            throw new ValidationException("Password is required");
        }
    }
}
