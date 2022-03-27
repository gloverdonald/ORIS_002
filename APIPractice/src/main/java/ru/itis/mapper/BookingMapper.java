package ru.itis.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.itis.dto.BookingDto;
import ru.itis.model.BookingEntity;

@Mapper(componentModel = "spring")
public interface BookingMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createDate", ignore = true)
    @Mapping(target = "updateDate", ignore = true)
    BookingEntity toApartment(BookingDto bookingDto);
    BookingDto toDto(BookingEntity bookingEntity);
}
