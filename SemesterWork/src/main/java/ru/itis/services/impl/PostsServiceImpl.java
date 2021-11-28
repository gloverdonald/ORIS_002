package ru.itis.services.impl;

import ru.itis.model.Post;
import ru.itis.dao.PostsRepository;
import ru.itis.dto.PostDto;
import ru.itis.model.User;
import ru.itis.services.PostsService;

import java.util.List;
import java.util.stream.Collectors;

public class PostsServiceImpl implements PostsService {
    private final PostsRepository postsRepository;

    public PostsServiceImpl(PostsRepository postsRepository) {
        this.postsRepository = postsRepository;
    }

    @Override
    public List<PostDto> getByAuthorId(Long authorId) {
        return postsRepository.findByAuthorId(authorId).stream()
                .map(PostDto::from)
                .collect(Collectors.toList());
    }

    @Override
    public PostDto addPost(PostDto postDto) {
        Post post = Post.from(postDto);
        Post savedPost = postsRepository.save(post);
        return PostDto.from(savedPost);
    }

    @Override
    public List<Post> allPosts() {
        return postsRepository.findAll();
    }

    @Override
    public List<Post> getFeed(Long profile_id) {
        return postsRepository.findFeed(profile_id);
    }

    @Override
    public void delete(Long id) {
        postsRepository.delete(id);
    }
}
