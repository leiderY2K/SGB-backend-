package com.udistrital.library.persistence.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.udistrital.library.persistence.entities.BookLoan;
import com.udistrital.library.persistence.entities.BookLoanId;

@Repository
public interface BookLoanRepository extends JpaRepository<BookLoan, BookLoanId> {}
