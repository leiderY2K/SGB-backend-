package org.example.sgb.persistence.repository;

import org.example.sgb.persistence.entity.Prestamo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PrestamoRepository extends JpaRepository<Prestamo, Short> {
}
