package ru.itis.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.itis.dto.ApartmentDto;
import ru.itis.dto.ApartmentSearchDto;
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

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ApartmentServiceImpl {
    private final ApartmentRepository apartmentRepository;
    private final ApartmentMapper apartmentMapper;

    private final AddressRepository addressRepository;
    private final UserRepository userRepository;
    private final BookingRepository bookingRepository;

    public Boolean isAvailable(ApartmentSearchDto searchDto) {
       return bookingRepository.isAvailable(searchDto.getId(), searchDto.getDateStart(), searchDto.getDateEnd());
    }


    public ApartmentDto save(ApartmentDto apartmentDto) {
        AddressEntity address = addressRepository
                .findById(apartmentDto.getAddress().getId()).orElseThrow(AddressNotFoundException::new);
        UserEntity user = userRepository
                .findById(apartmentDto.getOwner().getId()).orElseThrow(UserNotFoundException::new);
        ApartmentEntity apartment = apartmentMapper.toApartment(apartmentDto);
        apartment.setAddress(address);
        apartment.setOwner(user);
        return apartmentMapper.toDto(apartmentRepository.save(apartment));
    }

    public ApartmentDto get(Long id) {
        ApartmentEntity apartment = apartmentRepository.findById(id).orElseThrow(ApartmentNotFoundException::new);
        return apartmentMapper.toDto(apartment);
    }

    public List<ApartmentDto> getAll() {
        return apartmentRepository.findAll()
                .stream()
                .map(apartmentMapper::toDto)
                .collect(Collectors.toList());
    }


}
