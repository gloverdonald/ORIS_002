package ru.itis.service;

import ru.itis.dto.request.LoginRequest;
import ru.itis.dto.request.RegistrationRequest;
import ru.itis.dto.response.UserResponse;

import java.util.List;

public interface UserService {

    Long create(RegistrationRequest userRequest);

    UserResponse login(LoginRequest loginRequest);

    UserResponse getById(Long id);

    public List<UserResponse> getAll();
}
