package ru.itis.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.model.AddressEntity;

public interface AddressRepository extends JpaRepository<AddressEntity, Long> {
}