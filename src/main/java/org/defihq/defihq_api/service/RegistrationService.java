package org.defihq.defihq_api.service;

import lombok.extern.slf4j.Slf4j;
import org.defihq.defihq_api.domain.User;
import org.defihq.defihq_api.model.RegistrationRequest;
import org.defihq.defihq_api.repos.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
@Slf4j
public class RegistrationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public RegistrationService(final UserRepository userRepository,
            final PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public boolean userNameExists(final RegistrationRequest registrationRequest) {
        return userRepository.existsByUserNameIgnoreCase(registrationRequest.getUserName());
    }

    public void register(final RegistrationRequest registrationRequest) {
        log.info("registering new user: {}", registrationRequest.getUserName());

        final User user = new User();
        user.setUserName(registrationRequest.getUserName());
        user.setUserPassword(passwordEncoder.encode(registrationRequest.getPassword()));
        user.setCreated(registrationRequest.getCreated());
        userRepository.save(user);
    }

}
