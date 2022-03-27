package ru.itis.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.itis.dto.ApartmentDto;
import ru.itis.model.ApartmentEntity;

@Mapper(componentModel = "spring")
public interface ApartmentMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createDate", ignore = true)
    @Mapping(target = "updateDate", ignore = true)
    ApartmentEntity toApartment(ApartmentDto apartmentDto);
    ApartmentDto toDto(ApartmentEntity apartmentEntity);
}
