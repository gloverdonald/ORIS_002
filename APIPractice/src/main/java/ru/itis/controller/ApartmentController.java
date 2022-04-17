package ru.itis.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.itis.dto.request.ApartmentRequest;
import ru.itis.dto.response.ApartmentResponse;
import ru.itis.dto.ApartmentSearchDto;
import ru.itis.service.ApartmentService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/apartment")
public class ApartmentController {

    private final ApartmentService apartmentService;

    @PostMapping("/add")
    private Long create(@Valid @RequestBody ApartmentRequest apartment) {
        return apartmentService.save(apartment);
    }

    @GetMapping("/{id}")
    private ApartmentResponse get(@PathVariable Long id) {
        return apartmentService.get(id);
    }

    @GetMapping("/availability")
    private Boolean check(@RequestBody ApartmentSearchDto searchDto) {
        return apartmentService.isAvailable(searchDto);
    }

    @GetMapping("/all")
    private List<ApartmentResponse> getAll() {
        return apartmentService.getAll();
    }
}
