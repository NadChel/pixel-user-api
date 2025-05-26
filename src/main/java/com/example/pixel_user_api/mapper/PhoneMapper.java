package com.example.pixel_user_api.mapper;

import com.example.pixel_user_api.data.entity.PhoneData;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface PhoneMapper {

    default String toString(PhoneData phoneData) {
        String phone = phoneData == null ? null : phoneData.getPhone();
        return phone;
    }
}
