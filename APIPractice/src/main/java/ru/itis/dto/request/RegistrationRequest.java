package ru.itis.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import ru.itis.dto.enums.Role;
import ru.itis.validation.custom.CustomPassword;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@EqualsAndHashCode(callSuper = false)
@SuperBuilder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegistrationRequest {

    @NotBlank(message = "Имя не может быть пустым")
    @Size(min = 2, max = 20, message = "минимальный размер {min}, максимальный размер {max}")
    private String firstName;

    @NotBlank(message = "Фамилия не может быть пустой")
    @Size(min = 2, max = 20, message = "минимальный размер {min}, максимальный размер {max}")
    private String lastName;

    private String email;

    @CustomPassword
    private String password;

    private Role role;
}
