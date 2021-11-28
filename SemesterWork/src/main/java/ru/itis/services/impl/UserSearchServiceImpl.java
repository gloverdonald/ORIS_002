package ru.itis.services.impl;

import ru.itis.dao.UsersRepository;
import ru.itis.dto.UserDto;
import ru.itis.model.User;
import ru.itis.services.UserSearchService;

import java.util.ArrayList;
import java.util.List;

public class UserSearchServiceImpl implements UserSearchService {
    private final UsersRepository usersRepository;


    public UserSearchServiceImpl(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    @Override
    public List<UserDto> getByFirstOrLastName(String firstOrLastName) {
        return new ArrayList<>(usersRepository.findByFirstOrLastName(firstOrLastName));
    }


    public UserDto getById(Long id) {
        User user = usersRepository.findById(id).get();
        return UserDto.builder()
                .id(user.getId())
                .avatarId(user.getAvatarId())
                .email(user.getEmail())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .build();
    }

    public List<User> findAll() {
        return usersRepository.findAll();
    }
}
