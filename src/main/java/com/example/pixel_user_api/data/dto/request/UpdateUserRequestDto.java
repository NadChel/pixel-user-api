package com.example.pixel_user_api.data.dto.request;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UpdateUserRequestDto {

    private Long id;
    private List<String> emailData;
    private List<String> phoneData;
}
