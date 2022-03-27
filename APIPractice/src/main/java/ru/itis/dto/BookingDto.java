package ru.itis.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class BookingDto {
    private Long id;
    private Date dateStart;
    private Date dateEnd;
    private ApartmentResponse apartment;
    private UserResponse customer;
}
