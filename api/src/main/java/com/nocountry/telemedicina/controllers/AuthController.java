package com.nocountry.telemedicina.controllers;

import com.nocountry.telemedicina.config.mapper.UserMapper;
import com.nocountry.telemedicina.dto.request.BookingRequestDTO;
import com.nocountry.telemedicina.dto.request.LoginRequestDTO;
import com.nocountry.telemedicina.dto.request.RegisterRequestDTO;
import com.nocountry.telemedicina.dto.response.AuthResponseDTO;

import com.nocountry.telemedicina.dto.response.ErrorResponseDTO;
import com.nocountry.telemedicina.exception.ErrorResponse;
import com.nocountry.telemedicina.security.oauth2.user.CurrentUser;
import com.nocountry.telemedicina.security.oauth2.user.UserPrincipal;
import com.nocountry.telemedicina.services.impl.AuthServiceImpl;

import io.jsonwebtoken.security.SignatureException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

@Tag(name = "Autentificación y Registro", description = "Registro de usuario según roles y Autentificación")
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    AuthServiceImpl authService;

    @Operation(
            summary = "Autenticación del usuario(Login)",
            description = "Autenticación del usuario y entrega de credenciales",
            tags = {})
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = {@Content(schema = @Schema(implementation = AuthResponseDTO.class), mediaType = "application/json")}),
            @ApiResponse(responseCode = "404", content = {@Content(schema = @Schema())}),
            @ApiResponse(responseCode = "500", content = {@Content(schema = @Schema())})})
    @PostMapping("/login")
    public ResponseEntity<AuthResponseDTO> login(@RequestBody LoginRequestDTO loginRequestDTO) {

        AuthResponseDTO response = authService.login(loginRequestDTO);
        return ResponseEntity.status(200).body(response);
    }

    @Operation(
            summary = "Registro del usuario(Login)",
            description = "Registro del usuario y entrega de credenciales",
            tags = {})
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = {@Content(schema = @Schema(implementation = AuthResponseDTO.class), mediaType = "application/json")}),
            @ApiResponse(responseCode = "404", content = {@Content(schema = @Schema())}),
            @ApiResponse(responseCode = "500", content = {@Content(schema = @Schema())})})
    @PostMapping("/register")
    public ResponseEntity<AuthResponseDTO> register(@RequestBody RegisterRequestDTO registerRequestDTO) {
        AuthResponseDTO response = authService.register(registerRequestDTO);
        return ResponseEntity.status(201).body(response);
    }
//Hay un error presente corregir el manejo de excepciones para que devuelva un json en el body explicando el error
    @Operation(
            summary = "Verifica el Token",
            description = "Verifica el Token y devuelve los datos del usuario",
            tags = {})
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = {@Content(schema = @Schema(implementation = AuthResponseDTO.class), mediaType = "application/json")}),
            @ApiResponse(responseCode = "401", content = {@Content(schema = @Schema(implementation = ErrorResponseDTO.class))}),
            @ApiResponse(responseCode = "403", content = {@Content(schema = @Schema(implementation = ErrorResponseDTO.class))}),
            @ApiResponse(responseCode = "500", content = {@Content(schema = @Schema(implementation = ErrorResponseDTO.class))})
    })
    @GetMapping("/check-login")
    public ResponseEntity<?> checkLogin(@CurrentUser UserPrincipal userPrincipal) {
        try {
            if (userPrincipal != null) {
                AuthResponseDTO response = authService.getUserByUsername(userPrincipal.getUsername());
                return ResponseEntity.status(200).body(response);
            } else {
                ErrorResponseDTO error = new ErrorResponseDTO("Forbidden", "Acceso denegado");
                return ResponseEntity.status(403).body(error);
            }
        } catch (SignatureException e) {
            ErrorResponse errorResponse = new ErrorResponse("Invalid token",401 ,"Token is invalid or tampered with");
            return ResponseEntity.status(401).body(errorResponse);
        } catch (Exception e) {
            ErrorResponse errorResponse = new ErrorResponse("Internal Server Error", 500,"Error interno del servidor");
            return ResponseEntity.status(500).body(errorResponse);
        }
    }

}
