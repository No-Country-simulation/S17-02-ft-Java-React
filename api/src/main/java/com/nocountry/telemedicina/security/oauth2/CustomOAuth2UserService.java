package com.nocountry.telemedicina.security.oauth2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import com.nocountry.telemedicina.exception.NotFoundException;
import com.nocountry.telemedicina.models.Role;
import com.nocountry.telemedicina.models.User;
import com.nocountry.telemedicina.repository.IRoleRepo;
import com.nocountry.telemedicina.repository.IUserRepo;
import com.nocountry.telemedicina.security.oauth2.exception.OAuth2AuthenticationProcessingException;
import com.nocountry.telemedicina.security.oauth2.user.UserInfoOAuth;
import com.nocountry.telemedicina.security.oauth2.user.UserPrincipal;

import io.micrometer.common.util.StringUtils;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import java.time.LocalDateTime;

@Slf4j
@Service
@RequiredArgsConstructor
public class CustomOAuth2UserService extends DefaultOAuth2UserService {

    private final IUserRepo userRepository;
    private final IRoleRepo roleRepo;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User oAuth2User = super.loadUser(userRequest);

        try {
            return processOAuth2User(userRequest, oAuth2User);
        } catch (AuthenticationException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new InternalAuthenticationServiceException(ex.getMessage(), ex.getCause());
        }

    }

    @Transactional
    private OAuth2User processOAuth2User(OAuth2UserRequest oAuth2UserRequest, OAuth2User oAuth2User) {
        UserInfoOAuth userInfoOAuth = new UserInfoOAuth(oAuth2User.getAttributes());

        // en caso de no tener el email es un problema en la cofig de oauth en google u
        // otro problema asi que lanzamos excepcion.
        if (StringUtils.isEmpty(userInfoOAuth.getEmail())) {
            log.error("Email not found from OAuth2 provider");
            throw new OAuth2AuthenticationProcessingException("Email not found from OAuth2 provider");
        }

        // busco el usuario en la base de datos
        Optional<User> userOptional = userRepository.findByUsername(userInfoOAuth.getEmail());

        User user;
        // en caso de existir lo asigno a la variable y luego lo devuelvo en la funcion.
        // de lo contrario creo un nuevo usuario con los datos para seguir con el
        // proceso
        if (userOptional.isPresent()) {
            user = userOptional.get();
        } else {

            user = new User();
            user.setUsername(userInfoOAuth.getEmail());
            user.setActive(true);
            user.setCreatedAt(LocalDateTime.now());
            user.setRoles(new ArrayList<>());
            Role role = roleRepo.findById(1L).orElseThrow(() -> new NotFoundException("Role not found"));
            user.getRoles().add(role);
            userRepository.save(user);
            userRepository.updateCreateByWithUserId(user.getUserId());
        }

        return UserPrincipal.create(user, oAuth2User.getAttributes());
    }
}
