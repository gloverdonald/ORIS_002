package ru.itis.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.itis.dto.AddressDto;
import ru.itis.dto.BookingDto;
import ru.itis.service.AddressServiceImpl;
import ru.itis.service.BookingServiceImpl;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/booking")
public class BookingController {
    private final BookingServiceImpl bookingService;

    @PostMapping("/add")
    private BookingDto create(@RequestBody BookingDto bookingDto) {
        return bookingService.save(bookingDto);
    }

    @GetMapping("/{id}")
    private BookingDto get(@PathVariable Long id) {
        return bookingService.get(id);
    }

    @GetMapping("/all")
    private List<BookingDto> getAll() {
        return bookingService.getAll();
    }
}
