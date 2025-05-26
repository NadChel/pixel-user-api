package com.example.pixel_user_api.controller;

import com.example.pixel_user_api.data.dto.request.FindUserRequestDto;
import com.example.pixel_user_api.data.dto.request.UpdateUserRequestDto;
import com.example.pixel_user_api.data.dto.response.UserResponseDto;
import com.example.pixel_user_api.mapper.UserMapper;
import com.example.pixel_user_api.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final UserMapper userMapper;

    @PutMapping("/{id}")
    public ResponseEntity<UserResponseDto> updateUser(@PathVariable long id,
                                                      @RequestBody UpdateUserRequestDto userRequestDto) {
        UserResponseDto userResponseDto = userService.update(id, userRequestDto);
        return ResponseEntity.ok(userResponseDto);
    }

    @GetMapping
    public ResponseEntity<List<UserResponseDto>> findUsers(@ParameterObject FindUserRequestDto userRequestDto,
                                                           @ParameterObject Pageable pageable) {
        List<UserResponseDto> userResponseDtos = userService.find(userRequestDto, pageable);
        return ResponseEntity.ok(userResponseDtos);
    }
}
