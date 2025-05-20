package com.example.pixel_user_api.service;

import com.example.pixel_user_api.data.dto.request.UserRequestDto;
import com.example.pixel_user_api.data.dto.response.UserResponseDto;
import com.example.pixel_user_api.data.entity.User;
import com.example.pixel_user_api.mapper.UserMapper;
import com.example.pixel_user_api.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository repository;
    private final UserMapper mapper;

    @Override
    @Transactional(readOnly = false)
    public UserResponseDto update(UserRequestDto userRequestDto) {
        User user = mapper.toUser(userRequestDto);
        User updatedUser = repository.save(user);
        return mapper.toResponseDto(updatedUser);
    }
}
