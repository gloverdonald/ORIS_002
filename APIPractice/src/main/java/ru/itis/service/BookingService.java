package ru.itis.service;

import ru.itis.dto.request.BookingRequest;
import ru.itis.dto.response.BookingResponse;

import java.util.List;

public interface BookingService {

    Long save(BookingRequest bookingRequest);

    BookingResponse get(Long id);

    List<BookingResponse> getAll();
}