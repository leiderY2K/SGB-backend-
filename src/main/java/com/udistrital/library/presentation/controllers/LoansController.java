package com.udistrital.library.presentation.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.udistrital.library.models.requests.loans.RegisterLoanRequest;
import com.udistrital.library.persistence.entities.BookLoan;
import com.udistrital.library.services.BookLoanService;

@RestController
@RequestMapping("/loans")
public class LoansController {
	@Autowired
	private BookLoanService bookLoanService;

	@PostMapping
	public ResponseEntity<List<BookLoan>> registerLoan(@RequestBody RegisterLoanRequest request) {
		var responseBody = bookLoanService.registerLoan(request);
		return ResponseEntity.ok(responseBody);
	}

}
