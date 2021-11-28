package ru.itis.dao;

import ru.itis.model.Post;
import ru.itis.dao.base.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface PostsRepository extends CrudRepository<Post, Long> {
    List<Post> findByAuthorId(Long authorId);

    List<Post> findFeed(Long profile_id);

    Optional<Post> findById(Long id);

    List<Post> findAll();

    Post save(Post item);

    void delete(Long id);
}
