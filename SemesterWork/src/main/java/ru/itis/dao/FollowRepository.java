package ru.itis.dao;

import ru.itis.dto.UserDto;

import java.util.List;

public interface FollowRepository {
    List<UserDto> followers(Long id);

    List<UserDto> following(Long id);

    void follow(Long profile_id, Long following_id);

    void unfollow(Long profile_id, Long following_id);

    List<UserDto> recommendations(Long id);
}
