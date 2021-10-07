package ru.itis.Models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class User {
    private Long id;
    private String name;
    private String password;
    private Integer avatarId;
    public User(String name, String password) {
        this.name = name;
        this.password = password;
    }
}
