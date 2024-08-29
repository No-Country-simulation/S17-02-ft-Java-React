package com.nocountry.telemedicina.services.impl;

import com.nocountry.telemedicina.config.mapper.UserMapper;
import com.nocountry.telemedicina.dto.request.LoginRequestDTO;
import com.nocountry.telemedicina.dto.request.RegisterRequestDTO;
import com.nocountry.telemedicina.dto.response.AuthResponseDTO;
import com.nocountry.telemedicina.exception.BadRequestException;
import com.nocountry.telemedicina.exception.InvalidCredentialsException;
import com.nocountry.telemedicina.models.Role;
import com.nocountry.telemedicina.models.User;
import com.nocountry.telemedicina.repository.IRoleRepo;
import com.nocountry.telemedicina.repository.IUserRepo;
import com.nocountry.telemedicina.security.filter.JwtUtils;

import jakarta.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AuthServiceImpl {

    @Autowired
    private UserMapper mapper;

    @Autowired
    private IUserRepo repository;

    @Autowired
    private IRoleRepo roleRepo;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public AuthResponseDTO login(LoginRequestDTO loginRequestDTO) {
        try {
            authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(
                            loginRequestDTO.getUsername(), loginRequestDTO.getPassword()));
        } catch (Exception e) {
            throw new InvalidCredentialsException("Invalid username or password");
        }

        User user = repository.findByUsername(loginRequestDTO.getUsername()).orElseThrow(
                () -> new UsernameNotFoundException("User not found with username: " + loginRequestDTO.getUsername()));

        String token = jwtUtils.generateToken(user);
        return new AuthResponseDTO(mapper.toUserDTO(user), token);

    }

    @Transactional
    public AuthResponseDTO register(RegisterRequestDTO registerRequestDTO) {
        Optional<User> userFound = repository.findByUsername(registerRequestDTO.getUsername());
        if (userFound.isPresent()) {
            throw new BadRequestException("Email already registered");
        }
        User user = new User();
        user.setUsername(registerRequestDTO.getUsername());
        user.setPassword(passwordEncoder.encode(registerRequestDTO.getPassword()));
        user.setActive(true);
        user.setCreatedAt(LocalDateTime.now());

        return registerUser(user, registerRequestDTO.getRolesId());
    }

    public AuthResponseDTO getUserByUsername(String username) {
        User user = repository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));

        String token = jwtUtils.generateToken(user);

        return new AuthResponseDTO(mapper.toUserDTO(user), token);
    }

    public AuthResponseDTO registerUser(User user, List<Long> roleIds) {
        // Registra el usuario
        User createdUser = repository.save(user);
        // Asigna los roles al usuario
        // roleIds.forEach(roleId ->
        // repository.assignRoleToUser(createdUser.getUserId(), roleId));
        createdUser.setCreateBy(createdUser.getUserId());
        // Asigna los roles al usuario
        List<Role> roles = roleIds.stream()
                .map(roleId -> roleRepo.findById(roleId).orElseThrow())
                .collect(Collectors.toList());
        createdUser.setRoles(roles);
        repository.save(createdUser);
        String token = jwtUtils.generateToken(createdUser);
        return new AuthResponseDTO(mapper.toUserDTO(createdUser), token);
    }

}
