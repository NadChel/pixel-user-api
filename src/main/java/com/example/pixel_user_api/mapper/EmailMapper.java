package com.example.pixel_user_api.mapper;

import com.example.pixel_user_api.data.entity.EmailData;
import com.example.pixel_user_api.data.entity.User;
import org.mapstruct.AnnotateWith;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

@Mapper
@AnnotateWith(Component.class)
public interface EmailMapper {

    default String toString(EmailData emailData) {
        String email = emailData == null ? null : emailData.getEmail();
        return email;
    }

    @Mapping(source = "email", target = "email")
    @Mapping(source = "user", target = "user")
    @Mapping(target = "id", ignore = true)
    EmailData toEmailData(String email, User user);
}
