package ru.itis.service;

import ru.itis.dto.request.AddressRequest;
import ru.itis.dto.response.AddressResponse;

import java.util.List;

public interface AddressService {

    Long save(AddressRequest addressRequest);

    AddressResponse get(Long id);

    List<AddressResponse> getAll();
}