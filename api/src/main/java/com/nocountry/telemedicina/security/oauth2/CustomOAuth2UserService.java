package com.nocountry.telemedicina.security.oauth2;

import com.nocountry.telemedicina.exception.NotFoundException;
import com.nocountry.telemedicina.models.Role;
import com.nocountry.telemedicina.models.User;
import com.nocountry.telemedicina.repository.IRoleRepo;
import com.nocountry.telemedicina.repository.IUserRepo;
import com.nocountry.telemedicina.security.oauth2.user.UserPrincipal;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class CustomOAuth2UserService extends DefaultOAuth2UserService {
    private  final IUserRepo userRepository;
    private  final IRoleRepo roleRepo;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User oAuth2User =  super.loadUser(userRequest);
        try {
            return proccessOAuth2User(oAuth2User);
        }catch (Exception ex) {
            throw  new InternalAuthenticationServiceException(ex.getMessage(),ex.getCause());
        }
    }

    private OAuth2User proccessOAuth2User(OAuth2User oAuth2User) {
        String email = oAuth2User.getAttribute("email");


        Optional<User> userOptional = userRepository.findByUsername(email);
        User user;
        if(userOptional.isPresent()) {
            user = userOptional.get();
        }else {
            user = new User();
            user.setUsername(email);
            user.setActive(true);
            user.setCreatedAt(LocalDateTime.now());
            Role role = roleRepo.findById(1L).orElseThrow(() -> new NotFoundException("Role not found"));
            user.getRoles().add(role);
            userRepository.save(user);
            user.setCreateBy(user.getUserId());
            userRepository.save(user);
        }
        return UserPrincipal.create(user);
    }
}
