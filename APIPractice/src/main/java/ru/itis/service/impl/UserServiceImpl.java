package ru.itis.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import ru.itis.dto.request.LoginRequest;
import ru.itis.dto.request.RegistrationRequest;
import ru.itis.dto.response.UserResponse;
import ru.itis.exceptions.UnauthorizedException;
import ru.itis.exceptions.UserNotFoundException;
import ru.itis.mapper.UserMapper;
import ru.itis.model.UserEntity;
import ru.itis.repository.UserRepository;
import ru.itis.service.UserService;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Component
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Long create(RegistrationRequest userRequest) {
        UserEntity user = userMapper.toEntity(userRequest);
        user.setHashPassword(passwordEncoder.encode(userRequest.getPassword()));
        user.setRole(userRequest.getRole());
        System.out.println("userRequest.getRole(): " + userRequest.getRole());

        return userRepository.save(user).getId();
    }

    @Override
    public UserResponse login(LoginRequest request) {
        return userRepository.findByEmail(request.getEmail())
                .filter(user -> passwordEncoder.matches(request.getPassword(), user.getHashPassword()))
                .map(userMapper::toResponse)
                .orElseThrow(() -> new UnauthorizedException("Can't login in: " + request.getEmail() + ". Wrong email or password."));
    }

    @Override
    public UserResponse getById(Long id) {
        UserEntity user = userRepository.findById(id).orElseThrow(UserNotFoundException::new);
        return userMapper.toResponse(user);
    }

    @Override
    public List<UserResponse> getAll() {
        return userRepository.findAll()
                .stream()
                .map(userMapper::toResponse)
                .collect(Collectors.toList());
    }
}
