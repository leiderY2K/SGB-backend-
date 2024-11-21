package com.udistrital.library.services;

import java.util.Date;
import java.util.Map;
import java.util.NoSuchElementException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.udistrital.library.models.requests.auth.RegisterRequest;
import com.udistrital.library.models.requests.auth.LoginRequest;
import com.udistrital.library.persistence.entities.User;
import com.udistrital.library.persistence.repositories.RoleRepository;
import com.udistrital.library.persistence.repositories.UserRepository;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Service
public class AuthenticationService {

	@Autowired
	private UserRepository usersRepository;

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Value("${jwt.secret}")
	private String secretKey;

	private static final String USER_ALREADY_EXISTS_MSG = "El usuario ya está registrado";

	private static final String INVALID_USERNAME_MSG = "Usuario inválido";

	private static final String INVALID_PASSWORD_MSG = "Contraseña inválida";

	private static final String NO_ROLE_FOUND_MSG = "No se encontró el rol proporcionado";

	public String generateToken(User user) {
		Map<String, String> claims = Map.ofEntries(Map.entry("name", user.getName()), Map.entry("phone", user.getPhone()), Map.entry("role", user.getRole().getId().toString()));
		var key = Keys.hmacShaKeyFor(secretKey.getBytes());
		return Jwts.builder()
				.setClaims(claims)
				.setSubject(user.getEmail())
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24))
				.signWith(key)
				.compact();
	}

	public Claims extractAllClaims(String token) {
		var key = Keys.hmacShaKeyFor(secretKey.getBytes());
		return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody();
	}

	public Boolean validateToken(String token) {
		try {
			var expirationDate = extractAllClaims(token).getExpiration();
			return new Date().before(expirationDate);
		} catch (Exception e) {
			return false;
		}
	}

	public String encryptPassword(String password) { return passwordEncoder.encode(password); }

	public String authenticate(LoginRequest request) throws BadCredentialsException {
		var user = usersRepository.findByEmail(request.email).orElseThrow(() -> new BadCredentialsException(INVALID_USERNAME_MSG));
		if (!passwordEncoder.matches(request.password, user.getPassword())) throw new BadCredentialsException(INVALID_PASSWORD_MSG);
		return generateToken(user);
	}

	public User register(RegisterRequest request) throws IllegalArgumentException, NoSuchElementException {
		var existingUser = usersRepository.findByEmail(request.email());
		if (existingUser.isPresent()) throw new IllegalArgumentException(USER_ALREADY_EXISTS_MSG);

		var role = roleRepository.findById(request.role());
		if (role.isEmpty()) throw new NoSuchElementException(NO_ROLE_FOUND_MSG);

		User user = new User();
		user.setName(request.name());
		user.setEmail(request.email());
		user.setPassword(encryptPassword(request.password()));
		user.setPhone(request.Phone());
		user.setAccountStatus(true);
		user.setRole(role.get());
		return usersRepository.save(user);
	}
}
