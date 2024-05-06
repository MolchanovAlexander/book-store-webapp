package com.example.bookstorewebapp.service.authentication.impl;

import com.example.bookstorewebapp.dto.user.CreateUserRequestDto;
import com.example.bookstorewebapp.dto.user.UserResponseDto;
import com.example.bookstorewebapp.exception.RegistrationException;
import com.example.bookstorewebapp.mapper.UserMapper;
import com.example.bookstorewebapp.model.User;
import com.example.bookstorewebapp.repository.user.UserRepository;
import com.example.bookstorewebapp.service.authentication.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AuthenticationServiceImpl implements AuthenticationService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public UserResponseDto create(CreateUserRequestDto requestDto) {
        if (userRepository.getByEmail(requestDto.getEmail()) != null) {
            throw new RegistrationException("User already exists with this email: "
                    + requestDto.getEmail());
        }
        User user = userMapper.toModel(requestDto);
        return userMapper.toDto(userRepository.save(user));
    }
}
