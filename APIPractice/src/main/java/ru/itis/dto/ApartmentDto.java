package ru.itis.dto;

import lombok.*;
import lombok.experimental.SuperBuilder;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class ApartmentDto extends ApartmentResponse {
    private Long price;
    private AddressResponse address;
    private UserResponse owner;
}
