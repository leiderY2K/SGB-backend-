package com.udistrital.library.persistence.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.udistrital.library.persistence.entities.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Short> {}
