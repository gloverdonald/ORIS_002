package ru.itis.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.validator.constraints.Length;
import ru.itis.model.AddressEntity;
import ru.itis.model.UserEntity;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class ApartmentRequest {

    @NotNull(message = "Цена не может быть пустой")
    @Min(value = 1, message = "минимальная цена {value}")
    private Long price;

    private Long addressId;

    private Long ownerId;
}
