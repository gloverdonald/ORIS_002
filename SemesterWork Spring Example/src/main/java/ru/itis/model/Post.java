package ru.itis.model;

import lombok.*;
import ru.itis.dto.PostDto;
import ru.itis.dto.Profile;

import java.sql.Timestamp;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class Post {
    Long id;
    User author;
    String content;
    Timestamp createdAt;

    public static Post from(PostDto postDto) {
        return Post.builder()
                .id(postDto.getId())
                .author(User.builder()
                        .id(postDto.getAuthor().getId())
                        .avatarId(postDto.getAuthor().getAvatarId())
                        .email(postDto.getAuthor().getEmail())
                        .firstName(postDto.getAuthor().getFirstName())
                        .lastName(postDto.getAuthor().getLastName())
                        .build())
                .content(postDto.getContent())
                .createdAt(postDto.getCreatedAt())
                .build();
    }
}
