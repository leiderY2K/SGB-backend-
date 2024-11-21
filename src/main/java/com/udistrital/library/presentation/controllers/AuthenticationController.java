package com.udistrital.library.presentation.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.udistrital.library.models.dto.auth.LoginResponse;
import com.udistrital.library.models.requests.auth.LoginRequest;
import com.udistrital.library.models.requests.auth.RegisterRequest;
import com.udistrital.library.models.requests.auth.ValidateTokenRequest;
import com.udistrital.library.persistence.entities.User;
import com.udistrital.library.services.AuthenticationService;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {
	@Autowired
	private AuthenticationService authService;

	@PostMapping("/register")
	public ResponseEntity<User> register(@RequestBody RegisterRequest request) {
		var user = authService.register(request);
		return ResponseEntity.ok(user);
	}

	@PostMapping("/login")
	public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request) {
		var token = authService.authenticate(request);
		var responseBody = new LoginResponse(token);
		return ResponseEntity.ok(responseBody);
	}

	@PostMapping("/token")
	public ResponseEntity<?> validateToken(@RequestBody ValidateTokenRequest request) {
		if (authService.validateToken(request.token)) return ResponseEntity.noContent().build();
		else throw new BadCredentialsException("El token es invalido");
	}

}
