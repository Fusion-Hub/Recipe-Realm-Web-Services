package com.fusionhub.reciperealm.webservices.services;

import com.fusionhub.reciperealm.webservices.dto.AuthenticationRequest;
import com.fusionhub.reciperealm.webservices.dto.AuthenticationResponse;
import com.fusionhub.reciperealm.webservices.dto.RegistrationRequest;

import java.io.IOException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public interface AuthenticationService {

    public abstract AuthenticationResponse register(RegistrationRequest request);

    public abstract AuthenticationResponse login(AuthenticationRequest authenticationRequest);

    public void refreshToken(
            HttpServletRequest request,
            HttpServletResponse response) throws IOException;
}
