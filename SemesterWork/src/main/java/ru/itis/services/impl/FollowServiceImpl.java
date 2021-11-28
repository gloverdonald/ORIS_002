package ru.itis.services.impl;


import ru.itis.dao.FollowRepository;
import ru.itis.dto.UserDto;
import ru.itis.services.FollowService;

import java.util.List;

public class FollowServiceImpl implements FollowService {
    private final FollowRepository followRepository;

    public FollowServiceImpl(FollowRepository followerRepository) {
        this.followRepository = followerRepository;
    }

    @Override
    public void follow(Long profile_id, Long following_id) {
        followRepository.follow(profile_id, following_id);
    }

    @Override
    public void unfollow(Long profile_id, Long following_id) {
        followRepository.unfollow(profile_id, following_id);
    }

    @Override
    public List<UserDto> following(Long profile_id) {
        return followRepository.following(profile_id);
    }

    @Override
    public List<UserDto> followers(Long profile_id) {
        return followRepository.followers(profile_id);
    }

    @Override
    public int getFollowingCount(Long id) {
        return followRepository.following(id).size();
    }

    @Override
    public int getFollowersCount(Long id) {
        return followRepository.followers(id).size();
    }

    @Override
    public List<UserDto> recommendations(Long profile_id) {
        return followRepository.recommendations(profile_id);
    }


}
