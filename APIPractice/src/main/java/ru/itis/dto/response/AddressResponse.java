package ru.itis.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder

public class AddressResponse {

    private Long id;

    private String country;

    private String city;

    private String street;

    private String houseNumber;
}
