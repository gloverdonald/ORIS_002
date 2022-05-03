package ru.itis.exceptions;

import static ru.itis.exceptions.MessageConstants.USER_NOT_FOUND;

public class UserNotFoundException extends ModelNotFoundException {

    public UserNotFoundException() {
        super(USER_NOT_FOUND);
    }
}
