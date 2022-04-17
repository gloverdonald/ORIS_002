package ru.itis.model;

import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;

@Entity
@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "address")
public class AddressEntity extends AbstractEntity {

    private String country;

    private String city;

    private String street;

    @Column(name = "house_number")
    private String houseNumber;
}
