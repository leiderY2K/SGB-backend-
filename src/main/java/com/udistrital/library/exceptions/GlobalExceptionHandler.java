
package com.udistrital.library.exceptions;

import java.util.Map;
import java.util.NoSuchElementException;

import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ExceptionHandler(NoSuchElementException.class)
	@ResponseBody
	public Map<String, String> handleNotFound(NoSuchElementException ex) { return Map.of("message", ex.getMessage()); }

	@ResponseStatus(HttpStatus.UNAUTHORIZED)
	@ExceptionHandler(BadCredentialsException.class)
	@ResponseBody
	public Map<String, String> handleUnauthorized(BadCredentialsException ex) { return Map.of("message", ex.getMessage()); }

	@ResponseStatus(HttpStatus.FORBIDDEN)
	@ExceptionHandler(AccessDeniedException.class)
	@ResponseBody
	public Map<String, String> handleForbidden(AccessDeniedException ex) { return Map.of("message", ex.getMessage()); }

	@ResponseStatus(HttpStatus.CONFLICT)
	@ExceptionHandler(IllegalArgumentException.class)
	@ResponseBody
	public Map<String, String> handleConflict(IllegalArgumentException ex) { return Map.of("message", ex.getMessage()); }
	
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(Exception.class)
	@ResponseBody
	public Map<String, String> handleInternalServerError(Exception ex) { return Map.of("message", ex.getMessage()); }
}
