package com.example.pixel_user_api.data.dto.request;

import io.swagger.v3.oas.annotations.Parameter;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class FindUserRequestDto {

    @Parameter(description = "Exclusive lower bound")
    private LocalDate dateOfBirth;
    private String phone;
    @Parameter(description = "User name prefix")
    private String name;
    private String email;
}
