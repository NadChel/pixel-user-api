package com.example.pixel_user_api.mapper;

import com.example.pixel_user_api.data.entity.PhoneData;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PhoneMapper {
    @Mapping(source = "phone", target = ".")
    String toString(PhoneData phoneData);
    @Mapping(source = ".", target = "phone")
    PhoneData toPhoneData(String phone);
}
