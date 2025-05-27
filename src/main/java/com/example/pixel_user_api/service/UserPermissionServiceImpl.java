package com.example.pixel_user_api.service;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;

@Service("userPermissionService")
public class UserPermissionServiceImpl implements UserPermissionService {

    @Override
    public boolean isCurrentUserIdEqualTo(String userId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) return false;
        Jwt jwt = (Jwt) authentication.getPrincipal();
        Long currentUserId = jwt.getClaim("userid");
        return currentUserId != null && currentUserId.toString().equals(userId);
    }
}