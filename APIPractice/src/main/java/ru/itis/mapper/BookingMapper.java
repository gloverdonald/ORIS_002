package ru.itis.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.itis.dto.request.BookingRequest;
import ru.itis.dto.response.BookingResponse;
import ru.itis.model.BookingEntity;

@Mapper(componentModel = "spring")
public interface BookingMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createDate", ignore = true)
    @Mapping(target = "updateDate", ignore = true)
    BookingEntity toApartment(BookingResponse bookingResponse);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createDate", ignore = true)
    @Mapping(target = "updateDate", ignore = true)
    BookingEntity toApartment(BookingRequest bookingRequest);

    /*@Mapping(target = "apartmentId", source = "apartment.id")
    @Mapping(target = "customerId", source = "customer.id")*/
    BookingResponse toResponse(BookingEntity bookingEntity);
}
