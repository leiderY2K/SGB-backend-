package com.udistrital.library.persistence.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.udistrital.library.persistence.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, Short> {

	Optional<User> findByEmail(String email);

}
