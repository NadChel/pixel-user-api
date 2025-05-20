package com.example.pixel_user_api.mapper;

import com.example.pixel_user_api.data.dto.request.UserRequestDto;
import com.example.pixel_user_api.data.dto.response.UserResponseDto;
import com.example.pixel_user_api.data.entity.User;
import org.mapstruct.Mapper;

@Mapper(uses = {EmailMapper.class, PhoneMapper.class}, componentModel = "spring")
public interface UserMapper {
    User toUser(UserRequestDto userRequestDto);
    UserResponseDto toResponseDto(User user);
}
