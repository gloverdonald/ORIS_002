package ru.itis.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class Profile {
    private Long id;
    private String name;
    private String password;
    private Long avatarId;

    public Profile(String name, String password) {
        this.name = name;
        this.password = password;
    }
    public Profile(Long id, String name, Long avatarId) {
        this.avatarId = avatarId;
        this.id = id;
        this.name = name;
    }
    public static Profile from(Profile profile) {
        return Profile.builder()
                .id(profile.getId())
                .name(profile.getName())
                .avatarId(profile.getAvatarId())
                .build();
    }
}