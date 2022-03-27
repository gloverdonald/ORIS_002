package ru.itis.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.itis.dto.ApartmentDto;
import ru.itis.dto.ApartmentSearchDto;
import ru.itis.service.ApartmentServiceImpl;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/apartment")
public class ApartmentController {
    private final ApartmentServiceImpl apartmentService;

    @PostMapping("/add")
    private ApartmentDto create(@RequestBody ApartmentDto apartmentDto) {
        return apartmentService.save(apartmentDto);
    }

    @GetMapping("/{id}")
    private ApartmentDto get(@PathVariable Long id) {
        return apartmentService.get(id);
    }

    @GetMapping("/availability")
    private Boolean check(@RequestBody ApartmentSearchDto searchDto) {
        return apartmentService.isAvailable(searchDto);
    }

    @GetMapping("/all")
    private List<ApartmentDto> getAll() {
        return apartmentService.getAll();
    }
}
