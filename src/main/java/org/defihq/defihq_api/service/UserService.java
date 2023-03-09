package org.defihq.defihq_api.service;

import java.util.stream.Collectors;
import org.defihq.defihq_api.domain.User;
import org.defihq.defihq_api.model.SimplePage;
import org.defihq.defihq_api.model.UserDTO;
import org.defihq.defihq_api.repos.UserRepository;
import org.defihq.defihq_api.util.NotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(final UserRepository userRepository, final PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public SimplePage<UserDTO> findAll(final String filter, final Pageable pageable) {
        Page<User> page;
        if (filter != null) {
            page = userRepository.findAllByUserNameRegex(".*" + filter + ".*", pageable);
        } else {
            page = userRepository.findAll(pageable);
        }
        return new SimplePage<>(page.getContent()
                .stream()
                .map((user) -> mapToDTO(user, new UserDTO()))
                .collect(Collectors.toList()),
                page.getTotalElements(), pageable);
    }

    public UserDTO get(final String userName) {
        return userRepository.findById(userName)
                .map(user -> mapToDTO(user, new UserDTO()))
                .orElseThrow(NotFoundException::new);
    }

    public String create(final UserDTO userDTO) {
        final User user = new User();
        mapToEntity(userDTO, user);
        user.setUserName(userDTO.getUserName());
        return userRepository.save(user).getUserName();
    }

    public void update(final String userName, final UserDTO userDTO) {
        final User user = userRepository.findById(userName)
                .orElseThrow(NotFoundException::new);
        mapToEntity(userDTO, user);
        userRepository.save(user);
    }

    public void delete(final String userName) {
        userRepository.deleteById(userName);
    }

    private UserDTO mapToDTO(final User user, final UserDTO userDTO) {
        userDTO.setUserName(user.getUserName());
        userDTO.setCreated(user.getCreated());
        return userDTO;
    }

    private User mapToEntity(final UserDTO userDTO, final User user) {
        user.setUserPassword(passwordEncoder.encode(userDTO.getUserPassword()));
        user.setCreated(userDTO.getCreated());
        return user;
    }

    public boolean userNameExists(final String userName) {
        return userRepository.existsByUserNameIgnoreCase(userName);
    }

}
