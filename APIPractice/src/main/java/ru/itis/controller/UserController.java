package ru.itis.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.itis.dto.request.UserRequest;
import ru.itis.dto.response.UserResponse;
import ru.itis.service.UserService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserService userServiceImpl;

    @PostMapping("/add")
    private Long create(@Valid @RequestBody UserRequest userRequest) {
        return userServiceImpl.save(userRequest);
    }

    @GetMapping("/{id}")
    private UserResponse get(@PathVariable Long id) {
        return userServiceImpl.get(id);
    }

    @GetMapping("/all")
    private List<UserResponse> getAll() {
        return userServiceImpl.getAll();
    }

}
