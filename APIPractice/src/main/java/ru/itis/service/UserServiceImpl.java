package ru.itis.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.itis.dto.UserDto;
import ru.itis.exceptions.UserNotFoundException;
import ru.itis.mapper.UserMapper;
import ru.itis.model.UserEntity;
import ru.itis.repository.UserRepository;


import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserDto save(UserDto userDto) {
        UserEntity user = userMapper.toUser(userDto);
        return userMapper.toDto(userRepository.save(user));
    }

    public UserDto get(Long id) {
        UserEntity user = userRepository.findById(id).orElseThrow(UserNotFoundException::new);
        return userMapper.toDto(user);
    }

    public List<UserDto> getAll() {
        return userRepository.findAll()
                .stream()
                .map(userMapper::toDto)
                .collect(Collectors.toList());
    }
}
