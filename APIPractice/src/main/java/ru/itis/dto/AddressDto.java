package ru.itis.dto;

import lombok.*;
import lombok.experimental.SuperBuilder;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder

public class AddressDto extends AddressResponse {
    private Long id;
    private String country;
    private String city;
    private String street;
    private String houseNumber;

}
