package ru.itis.services.validation;

import ru.itis.dto.UserForm;

import java.util.Optional;

public interface Validator {
    Optional<ErrorEntity> validateRegistration(UserForm form);
}
