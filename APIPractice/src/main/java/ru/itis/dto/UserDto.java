package ru.itis.dto;

import lombok.*;
import lombok.experimental.SuperBuilder;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class UserDto extends UserResponse{
    private String firstName;
    private String lastName;
    private String password;
}
