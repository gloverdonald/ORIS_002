package ru.itis.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.model.ApartmentEntity;

public interface ApartmentRepository extends JpaRepository<ApartmentEntity, Long> {
}
