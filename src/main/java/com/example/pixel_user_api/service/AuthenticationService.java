package com.example.pixel_user_api.service;

import java.util.Optional;

public interface AuthenticationService {

    Optional<Long> getCurrentUserId();
}
