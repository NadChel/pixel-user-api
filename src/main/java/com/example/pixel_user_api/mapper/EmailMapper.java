package com.example.pixel_user_api.mapper;

import com.example.pixel_user_api.data.entity.EmailData;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface EmailMapper {
    @Mapping(source = "email", target = ".")
    String toString(EmailData emailData);
    @Mapping(source = ".", target = "email")
    EmailData toEmailData(String email);
}
