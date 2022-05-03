package ru.itis.service;

import ru.itis.dto.response.UserResponse;

public interface TokenAuthorizationService {
    UserResponse getUserInfoByToken(String token);
}
