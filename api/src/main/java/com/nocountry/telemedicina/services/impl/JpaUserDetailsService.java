package com.nocountry.telemedicina.services.impl;

import com.nocountry.telemedicina.dto.request.RegisterRequestDTO;

import com.nocountry.telemedicina.models.Role;
import com.nocountry.telemedicina.models.User;
import com.nocountry.telemedicina.repository.IRoleRepo;
import com.nocountry.telemedicina.repository.IUserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class JpaUserDetailsService implements UserDetailsService {

    @Autowired
    private IUserRepo repository;

    @Autowired
    private IRoleRepo roleRepo;


    @Transactional(readOnly = true)
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<User> userOptional = repository.findByUsername(username);
        System.out.println(userOptional);


        if (userOptional.isEmpty()) {
            throw new UsernameNotFoundException(String.format("Username %s no existe en el sistema!", username));
        }

        User user = userOptional.orElseThrow();


        List<GrantedAuthority> authorities = user.getRoles().stream()
                .map(role -> new SimpleGrantedAuthority(role.getRoleName()))
                .collect(Collectors.toList());


        return new org.springframework.security.core.userdetails.User(user.getUsername(),
                user.getPassword(),
                user.getActive(),
                true,
                true,
                true,
                authorities);
    }

    public Optional<User> findUserByUsername(String username){
        return repository.findByUsername(username);
    }

    public User register(RegisterRequestDTO userDto) {
        // Validar que el usuario no exista previamente
        if (repository.findByUsername(userDto.getUsername()).isPresent()) {
            throw new RuntimeException("Username already exists");
        }
        User user = new User();
        // Encriptar la contraseña del usuario
        String encryptedPassword = encryptPassword(userDto.getPassword());
        // Set information in User entity
        user.setUsername(userDto.getUsername());
        user.setActive(true);
        user.setCreatedAt(LocalDateTime.now());
        user.setPassword(encryptedPassword);

        // Guardar el usuario en la base de datos
         return registerUser(user,userDto.getRolesId());
    }

    private String encryptPassword(String password) {
        // Implementar la lógica de encriptación de contraseña aquí
        // Por ejemplo, utilizando BCrypt
        String salt = BCrypt.gensalt();
        return BCrypt.hashpw(password, salt);
    }

    public User registerUser(User user, List<Long> roleIds) {
        // Registra el usuario
        User createdUser = repository.save(user);
        // Asigna los roles al usuario
        //roleIds.forEach(roleId -> repository.assignRoleToUser(createdUser.getUserId(), roleId));
        createdUser.setCreateBy(createdUser.getUserId());
        // Asigna los roles al usuario
        List<Role> roles = roleIds.stream()
                .map(roleId -> roleRepo.findById(roleId).orElseThrow())
                .collect(Collectors.toList());
        createdUser.setRoles(roles);
        return repository.save(createdUser);
    }
}
