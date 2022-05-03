package ru.itis.exceptions;

import static ru.itis.exceptions.MessageConstants.ROLE_NOT_FOUND;

public class RoleNotFoundException extends ModelNotFoundException {

    public RoleNotFoundException() {
        super(ROLE_NOT_FOUND);
    }
}
