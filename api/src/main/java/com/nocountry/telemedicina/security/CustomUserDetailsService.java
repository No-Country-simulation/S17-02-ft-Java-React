package com.nocountry.telemedicina.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.nocountry.telemedicina.models.User;
import com.nocountry.telemedicina.repository.IUserRepo;
import com.nocountry.telemedicina.security.oauth2.user.UserPrincipal;
import java.util.UUID;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    private final IUserRepo userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository
                .findByUsername(email)
                .orElseThrow(
                        () -> new UsernameNotFoundException(String.format("User not found with email: %s.", email)));

        return UserPrincipal.create(user);
    }

    public UserDetails loadUserById(UUID id) {
        User user = userRepository
                .findById(id)
                .orElseThrow(() -> new UsernameNotFoundException(String.format("User not found with ID: %s.", id)));

        return UserPrincipal.create(user);
    }

}
