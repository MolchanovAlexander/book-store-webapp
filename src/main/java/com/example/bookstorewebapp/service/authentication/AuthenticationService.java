package com.example.bookstorewebapp.service.authentication;

import com.example.bookstorewebapp.dto.user.CreateUserRequestDto;
import com.example.bookstorewebapp.dto.user.UserResponseDto;

public interface AuthenticationService {
    UserResponseDto create(CreateUserRequestDto requestDto);
}
