package ru.itis.services.impl;

import org.springframework.stereotype.Service;
import ru.itis.dto.UserForm;
import ru.itis.dao.UsersRepository;
import ru.itis.dto.UserDto;
import ru.itis.exceptions.ValidationException;
import ru.itis.model.User;
import ru.itis.services.PasswordEncoder;
import ru.itis.services.SignInService;
import ru.itis.services.validation.ErrorEntity;
@Service
public class SignInServiceImpl implements SignInService {
    private final UsersRepository usersRepository;
    private final PasswordEncoder passwordEncoder;

    public SignInServiceImpl(UsersRepository usersRepository, PasswordEncoder passwordEncoder) {
        this.usersRepository = usersRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDto signIn(UserForm userForm) {
        User user = usersRepository.findByEmail(userForm.getEmail())
                .orElseThrow(() -> new ValidationException(ErrorEntity.NOT_FOUND));
        if (!passwordEncoder.matches(userForm.getPassword(), user.getHashPassword())) {
            throw new ValidationException(ErrorEntity.INCORRECT_PASSWORD);
        }
        return UserDto.from(user);
    }
}
