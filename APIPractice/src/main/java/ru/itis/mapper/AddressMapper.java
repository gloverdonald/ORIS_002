package ru.itis.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.itis.dto.request.AddressRequest;
import ru.itis.dto.response.AddressResponse;
import ru.itis.model.AddressEntity;

@Mapper(componentModel = "spring")
public interface AddressMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createDate", ignore = true)
    @Mapping(target = "updateDate", ignore = true)
    AddressEntity toAddress(AddressRequest addressRequest);
    AddressRequest toRequest(AddressEntity addressEntity);

    AddressResponse toResponse(AddressEntity addressEntity);
}
