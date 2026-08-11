package com.example.studsystem.controller;


import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.example.studsystem.dto.JwtRequestDTO;
import com.example.studsystem.dto.RegistrationUserDTO;
import com.example.studsystem.service.AuthService;


@RestController
public class AuthController {
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("api/login")
    public ResponseEntity<?> createAuthToken(@RequestBody @Valid JwtRequestDTO authRequest) {
        return authService.createAuthToken(authRequest);
    }

    @PostMapping("api/registration")
    public ResponseEntity<?> createNewUser(@RequestBody @Valid RegistrationUserDTO registrationUserDto) {
    	return authService.createNewUser(registrationUserDto);
    }
}
