package ru.itis.services;


import ru.itis.dto.UserDto;

import java.util.List;

public interface FollowService {
    List<UserDto> following(Long profile_id);
    List<UserDto> followers(Long profile_id);

    void follow(Long profile_id, Long following_id);
    void unfollow(Long profile_id,Long following_id);

    int getFollowingCount(Long id);
    int getFollowersCount(Long id);

    List<UserDto> recommendations(Long profile_id);

}
