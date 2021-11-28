package ru.itis.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import ru.itis.model.User;

@Data
@AllArgsConstructor
@Builder
public class UserDto {
    private Long id;
    private String email;
    private String firstName;
    private String lastName;
    private Long avatarId;

    public UserDto(Long id, String firstName, String lastName, Long avatarId) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.avatarId = avatarId;
    }

    public static UserDto from(User user) {
        return UserDto.builder()
                .id(user.getId())
                .email(user.getEmail())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .avatarId(user.getAvatarId())
                .build();
    }
}
