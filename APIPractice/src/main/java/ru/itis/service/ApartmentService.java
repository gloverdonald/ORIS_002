package ru.itis.service;

import ru.itis.dto.request.ApartmentRequest;
import ru.itis.dto.response.ApartmentResponse;
import ru.itis.dto.ApartmentSearchDto;

import java.util.List;

public interface ApartmentService {

    Boolean isAvailable(ApartmentSearchDto searchDto);

    Long save(ApartmentRequest apartmentDto);

    ApartmentResponse get(Long id);

    List<ApartmentResponse> getAll();
}
