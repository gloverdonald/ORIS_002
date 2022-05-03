package ru.itis.model;

import lombok.*;
import lombok.experimental.SuperBuilder;
import ru.itis.dto.enums.Role;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Data
@Entity
@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")
public class UserEntity extends AbstractEntity {

    @NotBlank
    @Column(name = "first_name")
    private String firstName;

    @NotBlank
    @Column(name = "last_name")
    private String lastName;

    @NotBlank
    @Column(name = "hash_password")
    private String hashPassword;

    @NotBlank
    private String email;

    private Role role;
}
