package ru.itis.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.itis.dto.request.AddressRequest;
import ru.itis.dto.response.AddressResponse;
import ru.itis.exceptions.AddressNotFoundException;
import ru.itis.mapper.AddressMapper;
import ru.itis.model.AddressEntity;
import ru.itis.repository.AddressRepository;
import ru.itis.service.AddressService;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AddressServiceImpl implements AddressService {

    private final Validator validator;
    private final AddressRepository addressRepository;
    private final AddressMapper addressMapper;

    @Override
    public Long save(AddressRequest addressRequest) {
        Set<ConstraintViolation<AddressRequest>> violations = validator.validate(addressRequest);
        AddressEntity address = addressMapper.toAddress(addressRequest);
        return addressRepository.save(address).getId();
    }

    @Override
    public AddressResponse get(Long id) {
        AddressEntity address = addressRepository.findById(id).orElseThrow(AddressNotFoundException::new);
        return addressMapper.toResponse(address);
    }

    @Override
    public List<AddressResponse> getAll() {
        return addressRepository.findAll()
                .stream()
                .map(addressMapper::toResponse)
                .collect(Collectors.toList());
    }
}