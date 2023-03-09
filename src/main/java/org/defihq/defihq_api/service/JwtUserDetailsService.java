package org.defihq.defihq_api.service;

import java.util.Collections;
import lombok.extern.slf4j.Slf4j;
import org.defihq.defihq_api.repos.UserRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
@Slf4j
public class JwtUserDetailsService implements UserDetailsService {

    public static final String USER = "USER";
    public static final String ROLE_USER = "ROLE_" + USER;

    private final UserRepository userRepository;

    public JwtUserDetailsService(final UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User loadUserByUsername(final String username) {
        final org.defihq.defihq_api.domain.User user = userRepository.findByUserNameIgnoreCase(username);
        if (user == null) {
            log.warn("user not found: {}", username);
            throw new UsernameNotFoundException("User " + username + " not found");
        }
        return new User(username, user.getUserPassword(), Collections.singletonList(new SimpleGrantedAuthority(ROLE_USER)));
    }

}
