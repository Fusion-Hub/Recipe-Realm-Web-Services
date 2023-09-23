package com.fusionhub.reciperealm.webservices.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fusionhub.reciperealm.webservices.dto.AuthenticationRequestDto;
import com.fusionhub.reciperealm.webservices.dto.AuthenticationResponseDto;
import com.fusionhub.reciperealm.webservices.dto.RegistrationRequestDto;
import com.fusionhub.reciperealm.webservices.services.AuthenticationService;
import com.fusionhub.reciperealm.webservices.validation.UserValidation;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/api/v1/account")
@CrossOrigin(origins = "http://localhost:4200")
public class AuthenticationController {
    
    @Autowired
    private AuthenticationService authenticationService;

    @Autowired
    private UserValidation userValidation;

    @Transactional
    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponseDto> register(@RequestBody RegistrationRequestDto request) {
        userValidation.validateUser(request);
        AuthenticationResponseDto registeredUser = authenticationService.register(request);
        return new ResponseEntity<AuthenticationResponseDto>(registeredUser, HttpStatus.CREATED);
    }

    @Transactional
    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponseDto> login(@RequestBody AuthenticationRequestDto request) {
        AuthenticationResponseDto loggedStudent = authenticationService.login(request);
        return new ResponseEntity<AuthenticationResponseDto>(loggedStudent, HttpStatus.OK);
    }

    @PostMapping("/refresh-token")
    public void refreshToken(
            HttpServletRequest request,
            HttpServletResponse response) throws IOException {
        authenticationService.refreshToken(request, response);
    }

}
