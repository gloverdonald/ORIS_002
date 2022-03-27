package ru.itis.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.model.UserEntity;

import java.util.List;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

}
