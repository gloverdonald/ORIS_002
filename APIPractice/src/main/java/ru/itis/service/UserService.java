package ru.itis.service;

import ru.itis.dto.request.UserRequest;
import ru.itis.dto.response.UserResponse;

import java.util.List;

public interface UserService {

    Long save(UserRequest userRequest);

    UserResponse get(Long id);

    List<UserResponse> getAll();
}
