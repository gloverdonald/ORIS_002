package ru.itis.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.itis.dto.request.BookingRequest;
import ru.itis.dto.response.BookingResponse;
import ru.itis.service.BookingService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/booking")
public class BookingController {

    private final BookingService bookingService;

    @PostMapping("/add")
    private Long create(@RequestBody BookingRequest booking) {
        return bookingService.save(booking);
    }

    @GetMapping("/{id}")
    private BookingResponse get(@PathVariable Long id) {
        return bookingService.get(id);
    }

    @GetMapping("/all")
    private List<BookingResponse> getAll() {
        return bookingService.getAll();
    }
}
