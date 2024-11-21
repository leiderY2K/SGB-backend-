package com.udistrital.library.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.udistrital.library.persistence.entities.User;
import com.udistrital.library.persistence.repositories.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	public List<User> findAll() { return userRepository.findAll(); }

}
