package com.udistrital.library.presentation.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.udistrital.library.persistence.entities.User;
import com.udistrital.library.services.UserService;

@RestController
@RequestMapping("/users")
public class UsersController {
	@Autowired
	private UserService usersService;

	@GetMapping
	public ResponseEntity<List<User>> findAll() {
		var users = usersService.findAll();
		return ResponseEntity.ok(users);
	}
}