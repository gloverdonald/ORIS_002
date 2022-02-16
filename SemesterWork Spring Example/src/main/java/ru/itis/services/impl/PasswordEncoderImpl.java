package ru.itis.services.impl;

import org.springframework.stereotype.Service;
import ru.itis.services.PasswordEncoder;
@Service
public class PasswordEncoderImpl implements PasswordEncoder {
    @Override
    public boolean matches(String password, String hashPassword) {
        return password.equals(hashPassword);
    }

    @Override
    public String encode(String password) {
        return password;
    }
}
