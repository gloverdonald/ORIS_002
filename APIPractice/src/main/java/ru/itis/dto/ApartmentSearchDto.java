package ru.itis.dto;

import lombok.AllArgsConstructor;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class ApartmentSearchDto extends ApartmentResponse {
    private Date dateStart;
    private Date dateEnd;
}
