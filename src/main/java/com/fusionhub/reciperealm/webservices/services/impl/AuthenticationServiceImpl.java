package com.fusionhub.reciperealm.webservices.services.impl;

import java.util.ArrayList;
import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;

import org.springframework.stereotype.Service;

import com.fusionhub.reciperealm.webservices.dto.AuthenticationRequest;
import com.fusionhub.reciperealm.webservices.dto.AuthenticationResponse;
import com.fusionhub.reciperealm.webservices.dto.RegistrationRequest;
import com.fusionhub.reciperealm.webservices.mapping.UserMapper;
import com.fusionhub.reciperealm.webservices.models.Role;
import com.fusionhub.reciperealm.webservices.models.Token;
import com.fusionhub.reciperealm.webservices.models.TokenType;
import com.fusionhub.reciperealm.webservices.models.User;
import com.fusionhub.reciperealm.webservices.repository.TokenRepository;
import com.fusionhub.reciperealm.webservices.repository.UserRepository;
import com.fusionhub.reciperealm.webservices.services.AuthenticationService;
import com.fusionhub.reciperealm.webservices.services.JwtService;

@Service
public class AuthenticationServiceImpl implements AuthenticationService{

    @Autowired
    private TokenRepository tokenRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private  JwtService jwtService;
    @Autowired
    private  PasswordEncoder passwordEncoder;
    @Autowired
    private  AuthenticationManager authenticationManager;
    @Autowired
    private  UserMapper userMapper;

    @Override
    public AuthenticationResponse register(RegistrationRequest request) {
        User user = userMapper.convertToUser(request);
        user.setRole(Role.User);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        var savedUser = userRepository.save(user);

        var jwtToken = jwtService.generateToken(savedUser.getId(), savedUser);
        var refreshToken = jwtService.generateRefreshToken(savedUser);
        saveUserToken(savedUser, jwtToken);
        return AuthenticationResponse.builder()
                .accessToken(jwtToken)
                .refreshToken(refreshToken)
                .build();
    }

    public AuthenticationResponse login(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()));
        var user = userRepository.findByEmail(request.getEmail());
        var jwtToken = jwtService.generateToken(user.getId(), user);
        var refreshToken = jwtService.generateRefreshToken(user);
        revokeAllUserTokens(user);
        saveUserToken(user, jwtToken);
        return AuthenticationResponse.builder()
                .accessToken(jwtToken)
                .refreshToken(refreshToken)
                .build();
    }

    private void saveUserToken(User user, String jwtToken) {
        var token = Token.builder()
                .user(user)
                .token(jwtToken)
                .tokenType(TokenType.BEARER)
                .expired(false)
                .revoked(false)
                .build();
        tokenRepository.save(token);
    }

    private void revokeAllUserTokens(User user) {
        var validUserTokens = tokenRepository.findAllValidTokenByUser(user.getId());
        if (validUserTokens.isEmpty())
            return;
        validUserTokens.forEach(token -> {
            token.setExpired(true);
            token.setRevoked(true);
        });
        tokenRepository.saveAll(validUserTokens);
    }

    @Override
    public void refreshToken(
            HttpServletRequest request,
            HttpServletResponse response) throws IOException {
        final String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        final String refreshToken;
        final String userEmail;
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return;
        }
        refreshToken = authHeader.substring(7);
        userEmail = jwtService.extractUsername(refreshToken);
        if (userEmail != null) {
            var user = this.userRepository.findByEmail(userEmail);
            if (jwtService.isTokenValid(refreshToken, user)) {
                var userDetails = new org.springframework.security.core.userdetails.User(
                        user.getEmail(),
                        user.getPassword(),
                        new ArrayList<>());
                var accessToken = jwtService.generateToken(user.getId(), userDetails);
                revokeAllUserTokens(user);
                saveUserToken(user, accessToken);
                var authResponse = AuthenticationResponse.builder()
                        .accessToken(accessToken)
                        .refreshToken(refreshToken)
                        .build();
                new ObjectMapper().writeValue(response.getOutputStream(), authResponse);
            }
        }
    }
    
}
