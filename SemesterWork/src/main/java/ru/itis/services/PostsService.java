package ru.itis.services;

import ru.itis.dto.PostDto;
import ru.itis.model.Post;


import java.util.List;

public interface PostsService {
    List<PostDto> getByAuthorId(Long authorId);

    PostDto addPost(PostDto postDto);

    List<Post> allPosts();

    List<Post> getFeed(Long profile_id);

    void delete(Long id);
}
