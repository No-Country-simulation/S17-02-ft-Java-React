package com.nocountry.telemedicina.controllers;

import com.nocountry.telemedicina.dto.request.LoginRequestDTO;
import com.nocountry.telemedicina.dto.request.RegisterRequestDTO;
import com.nocountry.telemedicina.dto.response.AuthResponseDTO;

import com.nocountry.telemedicina.security.oauth2.user.CurrentUser;
import com.nocountry.telemedicina.security.oauth2.user.UserPrincipal;
import com.nocountry.telemedicina.services.impl.AuthServiceImpl;

import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Autentificación y Registro", description = "Registro de usuario según roles y Autentificación")
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    AuthServiceImpl authService;

    @PostMapping("/login")
    public ResponseEntity<AuthResponseDTO> login(@RequestBody LoginRequestDTO loginRequestDTO) {

        AuthResponseDTO response = authService.login(loginRequestDTO);
        return ResponseEntity.status(200).body(response);
    }

    @PostMapping("/register")
    public ResponseEntity<AuthResponseDTO> register(@RequestBody RegisterRequestDTO registerRequestDTO) {
        AuthResponseDTO response = authService.register(registerRequestDTO);
        return ResponseEntity.status(201).body(response);
    }

    @GetMapping("/check-login")
    public ResponseEntity<?> checkLogin(@CurrentUser UserPrincipal userPrincipal) {
        if (userPrincipal == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("User is not authenticated");
        }
        return ResponseEntity.status(200).body(authService.getUserByUsername(userPrincipal.getUsername()));
    }
}
