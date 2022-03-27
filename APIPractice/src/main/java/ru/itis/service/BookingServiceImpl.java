package ru.itis.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.itis.dto.BookingDto;
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

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookingServiceImpl {
    private final BookingRepository bookingRepository;
    private final BookingMapper bookingMapper;

    private final ApartmentRepository apartmentRepository;
    private final UserRepository userRepository;

    public BookingDto save(BookingDto bookingDto) {
        ApartmentEntity apartment = apartmentRepository
                .findById(bookingDto.getApartment().getId()).orElseThrow(ApartmentNotFoundException::new);

        UserEntity user = userRepository
                .findById(bookingDto.getCustomer().getId()).orElseThrow(UserNotFoundException::new);

        BookingEntity booking = bookingMapper.toApartment(bookingDto);
        booking.setCustomer(user);
        booking.setApartment(apartment);
        return bookingMapper.toDto(bookingRepository.save(booking));
    }

    public BookingDto get(Long id) {
        BookingEntity booking = bookingRepository.findById(id).orElseThrow(BookingNotFoundException::new);
        return bookingMapper.toDto(booking);
    }

    public List<BookingDto> getAll() {
        return bookingRepository.findAll()
                .stream()
                .map(bookingMapper::toDto)
                .collect(Collectors.toList());
    }
}
