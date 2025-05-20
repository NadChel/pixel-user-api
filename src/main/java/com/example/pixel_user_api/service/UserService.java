package com.example.pixel_user_api.service;

import com.example.pixel_user_api.data.dto.request.UserRequestDto;
import com.example.pixel_user_api.data.dto.response.UserResponseDto;

public interface UserService {
    UserResponseDto update(UserRequestDto userRequestDto);
}
