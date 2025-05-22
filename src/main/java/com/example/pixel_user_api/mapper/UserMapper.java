package com.example.pixel_user_api.mapper;

import com.example.pixel_user_api.data.dto.response.UserResponseDto;
import com.example.pixel_user_api.data.entity.User;
import org.mapstruct.AnnotateWith;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Mapper(uses = {EmailMapper.class, PhoneMapper.class})
@AnnotateWith(Component.class)
public interface UserMapper {

    UserResponseDto toResponseDto(User user);
}
