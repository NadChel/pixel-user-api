package com.example.pixel_user_api.controller;

import com.example.pixel_user_api.data.dto.request.FindUserRequestDto;
import com.example.pixel_user_api.data.dto.request.UpdateUserRequestDto;
import com.example.pixel_user_api.data.dto.response.UserResponseDto;
import com.example.pixel_user_api.mapper.UserMapper;
import com.example.pixel_user_api.service.UserService;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
@SecurityScheme(type = SecuritySchemeType.HTTP,
        name = "bearer-key",
        scheme = "bearer", bearerFormat = "JWT")
public class UserController {

    private final UserService userService;
    private final UserMapper userMapper;

    @PutMapping("/{id}")
    @PreAuthorize("@userPermissionService.matchesCurrentUserId(#id)")
    public ResponseEntity<UserResponseDto> updateUser(@PathVariable Long id,
                                                      @RequestBody UpdateUserRequestDto userRequestDto) {
        UserResponseDto userResponseDto = userService.update(id, userRequestDto);
        return ResponseEntity.ok(userResponseDto);
    }

    @GetMapping("/search")
    public ResponseEntity<List<UserResponseDto>> findUsers(@ParameterObject FindUserRequestDto userRequestDto,
                                                           @ParameterObject Pageable pageable) {
        List<UserResponseDto> userResponseDtos = userService.find(userRequestDto, pageable);
        return ResponseEntity.ok(userResponseDtos);
    }
}
