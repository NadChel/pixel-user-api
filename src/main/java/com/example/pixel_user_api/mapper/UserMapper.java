package com.example.pixel_user_api.mapper;

import com.example.pixel_user_api.data.dto.request.UpdateUserRequestDto;
import com.example.pixel_user_api.data.dto.response.UserResponseDto;
import com.example.pixel_user_api.data.entity.EmailData;
import com.example.pixel_user_api.data.entity.PhoneData;
import com.example.pixel_user_api.data.entity.User;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(uses = {EmailMapper.class, PhoneMapper.class},
        componentModel = "spring",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface UserMapper {

    default void updateUser(@MappingTarget User user, UpdateUserRequestDto dto) {
        setEmailData(user, dto);
        setPhoneData(user, dto);
    }

    private static void setEmailData(User user, UpdateUserRequestDto dto) {
        List<String> emails = dto.getEmailData();
        if (emails == null) return;
        List<EmailData> emailData = emails.stream().map(e -> EmailData.of(e, user)).toList();
        user.setEmailData(emailData);
    }

    private static void setPhoneData(User user, UpdateUserRequestDto dto) {
        List<String> phones = dto.getPhoneData();
        if (phones == null) return;
        List<PhoneData> phoneData = phones.stream().map(p -> PhoneData.of(p, user)).toList();
        user.setPhoneData(phoneData);
    }

    UserResponseDto toResponseDto(User user);
}
