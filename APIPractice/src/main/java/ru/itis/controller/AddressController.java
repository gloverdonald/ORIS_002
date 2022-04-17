package ru.itis.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.itis.dto.request.AddressRequest;
import ru.itis.dto.response.AddressResponse;
import ru.itis.service.AddressService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/address")
public class AddressController {

    private final AddressService addressService;

    @PostMapping("/add")
    private Long create(@RequestBody AddressRequest addressRequest) {
        return addressService.save(addressRequest);
    }

    @GetMapping("/{id}")
    private AddressResponse get(@PathVariable Long id) {
        return addressService.get(id);
    }

    @GetMapping("/all")
    private List<AddressResponse> getAll() {
        return addressService.getAll();
    }
}
