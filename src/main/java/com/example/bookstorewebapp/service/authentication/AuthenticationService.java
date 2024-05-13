package com.example.bookstorewebapp.service.authentication;

import com.example.bookstorewebapp.dto.user.CreateUserRequestDto;
import com.example.bookstorewebapp.dto.user.UserLoginRequestDto;
import com.example.bookstorewebapp.dto.user.UserLoginResponseDto;
import com.example.bookstorewebapp.dto.user.UserResponseDto;

public interface AuthenticationService {
    UserResponseDto create(CreateUserRequestDto requestDto);

    UserLoginResponseDto authenticate(UserLoginRequestDto requestDto);
}
