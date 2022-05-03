package ru.itis.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.itis.dto.request.BookingRequest;
import ru.itis.dto.response.BookingResponse;
import ru.itis.exceptions.ApartmentNotFoundException;
import ru.itis.exceptions.BookingNotFoundException;
import ru.itis.exceptions.UserNotFoundException;
import ru.itis.mapper.BookingMapper;
import ru.itis.model.ApartmentEntity;
import ru.itis.model.BookingEntity;
import ru.itis.model.UserEntity;
import ru.itis.repository.ApartmentRepository;
import ru.itis.repository.BookingRepository;
import ru.itis.repository.UserRepository;
import ru.itis.service.BookingService;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookingServiceImpl implements BookingService {

    private final Validator validator;

    private final BookingRepository bookingRepository;
    private final BookingMapper bookingMapper;

    private final ApartmentRepository apartmentRepository;

    private final UserRepository userRepository;

    @Override
    public Long save(BookingRequest bookingRequest) {
        Set<ConstraintViolation<BookingRequest>> violations = validator.validate(bookingRequest);
        ApartmentEntity apartment = apartmentRepository
                .findById(bookingRequest.getApartmentId()).orElseThrow(ApartmentNotFoundException::new);

        UserEntity user = userRepository
                .findById(bookingRequest.getCustomerId()).orElseThrow(UserNotFoundException::new);

        BookingEntity booking = bookingMapper.toApartment(bookingRequest);
        booking.setCustomer(user);
        booking.setApartment(apartment);
        return bookingRepository.save(booking).getId();
    }

    @Override
    public BookingResponse get(Long id) {
        BookingEntity booking = bookingRepository.findById(id).orElseThrow(BookingNotFoundException::new);
        return bookingMapper.toResponse(booking);
    }

    @Override
    public List<BookingResponse> getAll() {
        return bookingRepository.findAll()
                .stream()
                .map(bookingMapper::toResponse)
                .collect(Collectors.toList());
    }
}