package ru.itis.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.itis.dto.AddressDto;
import ru.itis.exceptions.AddressNotFoundException;
import ru.itis.exceptions.UserNotFoundException;
import ru.itis.mapper.AddressMapper;
import ru.itis.model.AddressEntity;
import ru.itis.repository.AddressRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AddressServiceImpl {
    private final AddressRepository addressRepository;
    private final AddressMapper addressMapper;

    public AddressDto save(AddressDto addressDto) {
        AddressEntity address = addressMapper.toAddress(addressDto);
        return addressMapper.toDto(addressRepository.save(address));
    }

    public AddressDto get(Long id) {
        AddressEntity address = addressRepository.findById(id).orElseThrow(AddressNotFoundException::new);
        return addressMapper.toDto(address);
    }

    public List<AddressDto> getAll() {
        return addressRepository.findAll()
                .stream()
                .map(addressMapper::toDto)
                .collect(Collectors.toList());
    }
}
