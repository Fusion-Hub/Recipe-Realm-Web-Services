package com.fusionhub.reciperealm.webservices.services;

import com.fusionhub.reciperealm.webservices.dto.AuthenticationRequestDto;
import com.fusionhub.reciperealm.webservices.dto.AuthenticationResponseDto;
import com.fusionhub.reciperealm.webservices.dto.RegistrationRequestDto;

import java.io.IOException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public interface AuthenticationService {

    public abstract AuthenticationResponseDto register(RegistrationRequestDto request);

    public abstract AuthenticationResponseDto login(AuthenticationRequestDto authenticationRequest);

    public void refreshToken(
            HttpServletRequest request,
            HttpServletResponse response) throws IOException;
}
