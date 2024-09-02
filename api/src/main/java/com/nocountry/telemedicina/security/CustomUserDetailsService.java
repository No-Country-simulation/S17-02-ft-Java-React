package com.nocountry.telemedicina.security;

import com.nocountry.telemedicina.models.User;
import com.nocountry.telemedicina.repository.IUserRepo;
import com.nocountry.telemedicina.security.oauth2.user.UserPrincipal;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * The type Custom user details service.
 */
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

    /**
     * Load user by id user details.
     *
     * @param id the id
     * @return the user details
     */
    public UserDetails loadUserById(UUID id) {
        User user = userRepository
                .findById(id)
                .orElseThrow(() -> new UsernameNotFoundException(String.format("User not found with ID: %s.", id)));

        return UserPrincipal.create(user);
    }

}
