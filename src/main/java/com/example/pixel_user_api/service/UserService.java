package com.example.pixel_user_api.service;

import com.example.pixel_user_api.data.dto.request.FindUserRequestDto;
import com.example.pixel_user_api.data.dto.request.UpdateUserRequestDto;
import com.example.pixel_user_api.data.dto.response.UserResponseDto;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UserService {

    UserResponseDto update(long id, UpdateUserRequestDto userRequestDto);

    List<UserResponseDto> find(FindUserRequestDto userRequestDto, Pageable pageable);
}
