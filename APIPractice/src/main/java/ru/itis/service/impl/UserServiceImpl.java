package ru.itis.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.itis.dto.request.UserRequest;
import ru.itis.dto.response.UserResponse;
import ru.itis.exceptions.UserNotFoundException;
import ru.itis.mapper.UserMapper;
import ru.itis.model.UserEntity;
import ru.itis.repository.UserRepository;
import ru.itis.service.UserService;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final Validator validator;

    private final UserRepository userRepository;

    private final UserMapper userMapper;

    @Override
    public Long save(UserRequest userRequest) {
        Set<ConstraintViolation<UserRequest>> violations = validator.validate(userRequest);
        UserEntity user = userMapper.toUser(userRequest);
        return userRepository.save(user).getId();
    }

    @Override
    public UserResponse get(Long id) {
        UserEntity user = userRepository.findById(id).orElseThrow(UserNotFoundException::new);
        return userMapper.toResponse(user);
    }

    @Override
    public List<UserResponse> getAll() {
        return userRepository.findAll()
                .stream()
                .map(userMapper::toResponse)
                .collect(Collectors.toList());
    }
}
