package ru.itis.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.itis.dto.ApartmentSearchDto;
import ru.itis.dto.request.ApartmentRequest;
import ru.itis.dto.response.ApartmentResponse;
import ru.itis.exceptions.AddressNotFoundException;
import ru.itis.exceptions.ApartmentNotFoundException;
import ru.itis.exceptions.UserNotFoundException;
import ru.itis.mapper.ApartmentMapper;
import ru.itis.model.AddressEntity;
import ru.itis.model.ApartmentEntity;
import ru.itis.model.UserEntity;
import ru.itis.repository.AddressRepository;
import ru.itis.repository.ApartmentRepository;
import ru.itis.repository.BookingRepository;
import ru.itis.repository.UserRepository;
import ru.itis.service.ApartmentService;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ApartmentServiceImpl implements ApartmentService {

    private final Validator validator;
    private final ApartmentRepository apartmentRepository;
    private final ApartmentMapper apartmentMapper;

    private final AddressRepository addressRepository;

    private final UserRepository userRepository;

    private final BookingRepository bookingRepository;

    @Override
    public Boolean isAvailable(ApartmentSearchDto searchDto) {
        Set<ConstraintViolation<ApartmentSearchDto>> violations = validator.validate(searchDto);
        return bookingRepository.isAvailable(searchDto.getId(), searchDto.getDateStart(), searchDto.getDateEnd());
    }

    @Override
    public Long save(ApartmentRequest apartmentRequest) {
        AddressEntity address = addressRepository
                .findById(apartmentRequest.getAddressId()).orElseThrow(AddressNotFoundException::new);
        UserEntity user = userRepository
                .findById(apartmentRequest.getOwnerId()).orElseThrow(UserNotFoundException::new);

        ApartmentEntity apartment = apartmentMapper.toApartment(apartmentRequest);
        apartment.setAddress(address);
        apartment.setOwner(user);
        return apartmentRepository.save(apartment).getId();
    }

    @Override
    public ApartmentResponse get(Long id) {
        ApartmentEntity apartment = apartmentRepository.findById(id).orElseThrow(ApartmentNotFoundException::new);
        return apartmentMapper.toResponse(apartment);
    }

    @Override
    public List<ApartmentResponse> getAll() {
        return apartmentRepository.findAll()
                .stream()
                .map(apartmentMapper::toResponse)
                .collect(Collectors.toList());
    }
}