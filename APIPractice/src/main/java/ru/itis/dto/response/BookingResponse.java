package ru.itis.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import ru.itis.dto.response.ApartmentResponse;
import ru.itis.dto.response.UserResponse;

import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class BookingResponse {

    private Long id;

    private Date dateStart;

    private Date dateEnd;

    private Long apartmentId;

    private Long customerId;
}
