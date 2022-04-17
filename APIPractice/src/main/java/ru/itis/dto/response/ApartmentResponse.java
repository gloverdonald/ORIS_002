package ru.itis.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class ApartmentResponse {

    private Long id;

    private Long price;

    private Long addressId;

    private Long ownerId;
}
