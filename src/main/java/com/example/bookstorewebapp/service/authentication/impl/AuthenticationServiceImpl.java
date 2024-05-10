package com.example.bookstorewebapp.service.authentication.impl;

import com.example.bookstorewebapp.dto.user.CreateUserRequestDto;
import com.example.bookstorewebapp.dto.user.UserResponseDto;
import com.example.bookstorewebapp.exception.RegistrationException;
import com.example.bookstorewebapp.mapper.UserMapper;
import com.example.bookstorewebapp.model.User;
import com.example.bookstorewebapp.model.role.Role;
import com.example.bookstorewebapp.repository.role.RoleRepository;
import com.example.bookstorewebapp.repository.user.UserRepository;
import com.example.bookstorewebapp.service.authentication.AuthenticationService;
import java.util.Collections;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AuthenticationServiceImpl implements AuthenticationService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;

    @Override
    public UserResponseDto create(CreateUserRequestDto requestDto) {
        if (userRepository.findByEmail(requestDto.getEmail()).isPresent()) {
            throw new RegistrationException("User already exists with this email: "
                    + requestDto.getEmail());
        }
        User user = userMapper.toModel(requestDto);
        user.setPassword(passwordEncoder.encode(requestDto.getPassword()));
        Role defaultRole = roleRepository.findByRole(Role.RoleName.USER);
        user.setRoles(Collections.singleton(defaultRole));
        return userMapper.toDto(userRepository.save(user));
    }
}
