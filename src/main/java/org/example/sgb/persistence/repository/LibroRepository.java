package org.example.sgb.persistence.repository;

import org.example.sgb.persistence.entity.libro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LibroRepository extends JpaRepository<libro, Short > {
}
