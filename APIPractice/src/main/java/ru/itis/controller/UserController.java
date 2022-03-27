package ru.itis.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.itis.dto.UserDto;
import ru.itis.service.UserServiceImpl;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {
    private final UserServiceImpl userServiceImpl;

    @PostMapping("/add")
    private UserDto create(@RequestBody UserDto userDto) {
        return userServiceImpl.save(userDto);
    }

    @GetMapping("/{id}")
    private UserDto get(@PathVariable Long id) {
        return userServiceImpl.get(id);
    }

    @GetMapping("/all")
    private List<UserDto> getAll() {
        return userServiceImpl.getAll();
    }

}
