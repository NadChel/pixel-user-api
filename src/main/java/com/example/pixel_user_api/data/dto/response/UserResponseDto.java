package com.example.pixel_user_api.data.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class UserResponseDto {

    private Long id;
    private String name;
    private LocalDate dateOfBirth;
    private List<String> emailData;
    private List<String> phoneData;
}
