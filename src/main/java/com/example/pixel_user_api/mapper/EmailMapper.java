package com.example.pixel_user_api.mapper;

import com.example.pixel_user_api.data.entity.EmailData;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface EmailMapper {

    default String toString(EmailData emailData) {
        String email = emailData == null ? null : emailData.getEmail();
        return email;
    }
}
