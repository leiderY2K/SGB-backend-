package com.udistrital.library.persistence.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;

@Data
@Entity
@Table(name = "loan_book")
public class LoanBook implements Serializable {

	private static final long serialVersionUID = 1044021581691209057L;

	@EmbeddedId
	private LoanBookId id;

	@ManyToOne
	@MapsId("book_id")
	@JoinColumn(name = "book_id", referencedColumnName = "id")
	private Book book;

	@ManyToOne
	@MapsId("loan_id")
	@JoinColumn(name = "loan_id", referencedColumnName = "id")
	private Loan loan;

	public Loan getLoan() { return loan; }

	public Book getBook() { return book; }

}
