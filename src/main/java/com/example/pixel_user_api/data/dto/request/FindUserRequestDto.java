package com.example.pixel_user_api.data.dto.request;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class FindUserRequestDto {

    private LocalDate dateOfBirth;
    private String phone;
    private String name;
    private String email;
}
