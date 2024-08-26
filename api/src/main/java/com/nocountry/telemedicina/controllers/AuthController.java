package com.nocountry.telemedicina.controllers;

import com.nocountry.telemedicina.config.mapper.UserMapper;
import com.nocountry.telemedicina.dto.request.RegisterRequestDTO;
import com.nocountry.telemedicina.dto.response.UserResponseDTO;
import com.nocountry.telemedicina.models.User;
import com.nocountry.telemedicina.services.IUserService;
import com.nocountry.telemedicina.services.impl.JpaUserDetailsService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Tag(name = "Autentificación y Registro", description = "Registro de usuario según roles y Autentificación")
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private JpaUserDetailsService userService;

    @Autowired
    private UserMapper mapper;

    @PostMapping("/register")
    public ResponseEntity<User> registerUser(@RequestBody RegisterRequestDTO user) {
        try {
            User registeredUser = userService.register(user);
            return new ResponseEntity<>(registeredUser, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/user/{username}")
    public ResponseEntity<UserResponseDTO> findUser(@PathVariable("username") String username) {
        Optional<User> userOptional = userService.findUserByUsername(username);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            UserResponseDTO userDTO = mapper.toUserDTO(user);
            return new ResponseEntity<>(userDTO, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
