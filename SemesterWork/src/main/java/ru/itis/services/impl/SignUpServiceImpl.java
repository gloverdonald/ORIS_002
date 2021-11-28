package ru.itis.services.impl;

import ru.itis.services.SignUpService;
import ru.itis.dao.UsersRepository;
import ru.itis.dto.UserForm;
import ru.itis.exceptions.ValidationException;
import ru.itis.model.User;
import ru.itis.services.PasswordEncoder;
import ru.itis.services.validation.ErrorEntity;
import ru.itis.services.validation.Validator;

import java.util.Optional;

public class SignUpServiceImpl implements SignUpService {
    private final UsersRepository usersRepository;
    private final PasswordEncoder passwordEncoder;
    private final Validator validator;

    public SignUpServiceImpl(UsersRepository usersRepository, PasswordEncoder passwordEncoder, Validator validator) {
        this.usersRepository = usersRepository;
        this.passwordEncoder = passwordEncoder;
        this.validator = validator;
    }

    @Override
    public void signUp(UserForm form) {
        // passwordEncoder.matches("123123", "HASH");
        Optional<ErrorEntity> optionalError = validator.validateRegistration(form);
        if(optionalError.isPresent()) {
            throw new ValidationException(optionalError.get());
        }
        User user = User.builder()
                .email(form.getEmail())
                .firstName(form.getFirstName())
                .lastName(form.getLastName())
                .age(form.getAge())
                .hashPassword(passwordEncoder.encode(form.getPassword()))
                .build();
        usersRepository.save(user);
    }
}
