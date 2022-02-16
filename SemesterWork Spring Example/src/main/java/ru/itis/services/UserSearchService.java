package ru.itis.services;

import ru.itis.dto.UserDto;
import ru.itis.model.User;

import java.util.List;

public interface UserSearchService {
    List<UserDto> getByFirstOrLastName(String firstOrLastName);

    UserDto getById(Long id);

    List<User> findAll();
}
