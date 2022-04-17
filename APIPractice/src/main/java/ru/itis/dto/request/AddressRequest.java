package ru.itis.dto.request;

import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class AddressRequest {

    private String country;

    private String city;

    private String street;

    private String houseNumber;
}
