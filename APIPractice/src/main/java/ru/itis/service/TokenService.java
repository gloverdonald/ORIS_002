package ru.itis.service;

import ru.itis.dto.request.TokenRefreshRequest;
import ru.itis.dto.response.TokensResponse;
import ru.itis.dto.response.UserResponse;

public interface TokenService {
    TokensResponse generateTokens(UserResponse userResponse);

    TokensResponse refreshTokens(TokenRefreshRequest request);
}
