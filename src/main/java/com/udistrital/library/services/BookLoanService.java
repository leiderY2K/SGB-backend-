package com.udistrital.library.services;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.udistrital.library.models.requests.loans.RegisterLoanRequest;
import com.udistrital.library.persistence.entities.Book;
import com.udistrital.library.persistence.entities.BookLoan;
import com.udistrital.library.persistence.entities.Loan;
import com.udistrital.library.persistence.repositories.BookLoanRepository;
import com.udistrital.library.persistence.repositories.BookRepository;
import com.udistrital.library.persistence.repositories.LoanRepository;
import com.udistrital.library.persistence.repositories.UserRepository;

import jakarta.transaction.Transactional;

@Service
public class BookLoanService {

	@Autowired
	private BookLoanRepository bookLoanRepository;

	@Autowired
	private BookRepository bookRepository;

	@Autowired
	private LoanRepository loanRepository;

	@Autowired
	private UserRepository userRepository;

	private static final String USER_NOT_FOUND = "El usuario al que se le quiere prestar el libro no existe";

	@Transactional
	public List<BookLoan> registerLoan(RegisterLoanRequest request) {
		var user = userRepository.findById(request.user()).orElseThrow(() -> new NoSuchElementException(USER_NOT_FOUND));
		var startDate = new Date(request.startDate().getTime());
		var endDate = new Date(request.endDate().getTime());
		var loan = loanRepository.save(Loan.create(user, startDate, endDate));
		var iterableBooksPk = Arrays.asList(request.books());
		var bookLoans = new ArrayList<BookLoan>();

		for (Book book : bookRepository.findAllById(iterableBooksPk)) bookLoans.add(BookLoan.create(book, loan));

		return bookLoanRepository.saveAll(bookLoans);
	}

}
