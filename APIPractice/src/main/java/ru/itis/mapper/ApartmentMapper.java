package ru.itis.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.itis.dto.request.ApartmentRequest;
import ru.itis.dto.response.ApartmentResponse;
import ru.itis.model.ApartmentEntity;

@Mapper(componentModel = "spring")
public interface ApartmentMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createDate", ignore = true)
    @Mapping(target = "updateDate", ignore = true)
    ApartmentEntity toApartment(ApartmentResponse apartmentResponse);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createDate", ignore = true)
    @Mapping(target = "updateDate", ignore = true)
    ApartmentEntity toApartment(ApartmentRequest apartmentRequest);

    @Mapping(target = "addressId", source = "address.id")
    @Mapping(target = "ownerId", source = "owner.id")
    ApartmentResponse toResponse(ApartmentEntity apartmentEntity);
}
