package ru.itis.dao;

import ru.itis.dao.base.CrudRepository;
import ru.itis.dto.UserDto;
import ru.itis.model.User;

import java.util.List;
import java.util.Optional;

public interface UsersRepository extends CrudRepository<User, Long> {
    Optional<User> findByEmail(String email);

    void updateAvatarForUser(Long userId, Long fileId);

    List<UserDto> findByFirstOrLastName(String name);
}
