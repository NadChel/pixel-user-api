package com.example.pixel_user_api.mapper;

import com.example.pixel_user_api.data.entity.PhoneData;
import com.example.pixel_user_api.data.entity.User;
import org.mapstruct.AnnotateWith;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

@Mapper
@AnnotateWith(Component.class)
public interface PhoneMapper {

    default String toString(PhoneData phoneData) {
        String phone = phoneData == null ? null : phoneData.getPhone();
        return phone;
    }

    @Mapping(source = "phone", target = "phone")
    @Mapping(source = "user", target = "user")
    @Mapping(target = "id", ignore = true)
    PhoneData toPhoneData(String phone, User user);
}
