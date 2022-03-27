package ru.itis.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.itis.dto.AddressDto;
import ru.itis.service.AddressServiceImpl;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/address")
public class AddressController {
    private final AddressServiceImpl addressService;

    @PostMapping("/add")
    private AddressDto create(@RequestBody AddressDto addressDto) {
        return addressService.save(addressDto);
    }

    @GetMapping("/{id}")
    private AddressDto get(@PathVariable Long id) {
        return addressService.get(id);
    }

    @GetMapping("/all")
    private List<AddressDto> getAll() {
        return addressService.getAll();
    }
}
