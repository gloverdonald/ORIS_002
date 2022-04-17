package ru.itis.dto.request;

import lombok.*;
import lombok.experimental.SuperBuilder;
import ru.itis.dto.response.UserResponse;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class UserRequest {

    private String email;

    @NotBlank(message = "Имя не может быть пустым")
    @Size(min = 2, max = 20, message = "минимальный размер {min}, максимальный размер {max}")
    private String firstName;

    @NotBlank(message = "Фамилия не может быть пустой")
    @Size(min = 2, max = 20, message = "минимальный размер {min}, максимальный размер {max}")
    private String lastName;

    @NotBlank(message = "Пароль не может быть пустым")
    @Size(min = 6, max = 100, message = "минимальный размер {min}, максимальный размер {max}")
    private String password;
}
