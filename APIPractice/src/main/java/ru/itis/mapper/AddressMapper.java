package ru.itis.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.itis.dto.AddressDto;
import ru.itis.model.AddressEntity;

@Mapper(componentModel = "spring")
public interface AddressMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createDate", ignore = true)
    @Mapping(target = "updateDate", ignore = true)
    AddressEntity toAddress(AddressDto addressDto);
    AddressDto toDto(AddressEntity addressEntity);
}
