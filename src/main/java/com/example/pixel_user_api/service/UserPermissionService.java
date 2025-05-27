package com.example.pixel_user_api.service;

public interface UserPermissionService {
    boolean matchesCurrentUserId(Long userId);
}
