package ru.itis.service.impl;

import io.jsonwebtoken.ClaimJwtException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.itis.dto.response.UserResponse;
import ru.itis.exceptions.UserNotFoundException;
import ru.itis.mapper.UserMapper;
import ru.itis.repository.UserRepository;
import ru.itis.service.TokenAuthorizationService;

@RequiredArgsConstructor
@Service
public class TokenAuthorizationServiceImpl implements TokenAuthorizationService {

    @Value("${secret}")
    private String secretKey;
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public UserResponse getUserInfoByToken(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(token).getBody();
        String email = claims.getSubject();
        return userMapper.toResponse(userRepository.findByEmail(email)
                .orElseThrow(UserNotFoundException::new));
    }
}
