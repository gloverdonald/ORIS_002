package ru.itis.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.itis.dto.request.LoginRequest;
import ru.itis.dto.request.RegistrationRequest;
import ru.itis.dto.response.TokensResponse;
import ru.itis.dto.response.UserResponse;
import ru.itis.service.TokenService;
import ru.itis.service.UserService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserService userService;
    private final TokenService tokenService;

    @PostMapping("/sign-up")
    public Long create(@Valid @RequestBody RegistrationRequest registrationRequest) {
        return userService.create(registrationRequest);
    }

    @PostMapping(value = "/sign-in")
    public TokensResponse login(@RequestBody LoginRequest userRequest) {
        return tokenService.generateTokens(userService.login(userRequest));
    }

    @GetMapping("/{id}")
    private UserResponse get(@PathVariable Long id) {
        return userService.getById(id);
    }

    @GetMapping("/all")
    private List<UserResponse> getAll() {
        return userService.getAll();
    }

}
