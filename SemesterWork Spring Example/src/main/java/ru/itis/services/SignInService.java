package ru.itis.services;

import ru.itis.dto.UserForm;
import ru.itis.dto.UserDto;

public interface SignInService {
    UserDto signIn(UserForm userForm);
}
