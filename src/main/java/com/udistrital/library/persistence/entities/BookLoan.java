package com.udistrital.library.persistence.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;

@Data
@Entity
@Table(name = "loan_book")
public class BookLoan implements Serializable {

	private static final long serialVersionUID = 1044021581691209057L;

	@EmbeddedId
	private BookLoanId id;

	@ManyToOne
	@MapsId("book_id")
	@JoinColumn(name = "book_id", referencedColumnName = "id")
	private Book book;

	@ManyToOne
	@MapsId("loan_id")
	@JoinColumn(name = "loan_id", referencedColumnName = "id")
	private Loan loan;

	public void setId(BookLoanId id) { this.id = id; }

	public BookLoanId getId() { return id; }

	public void setLoan(Loan loan) { this.loan = loan; }

	public Loan getLoan() { return loan; }

	public void setBook(Book book) { this.book = book; }

	public Book getBook() { return book; }

	public static BookLoan create(Book book, Loan loan) {
		BookLoan bookLoan = new BookLoan();
		bookLoan.setId(BookLoanId.create(loan.getId(), book.getId()));
		bookLoan.setBook(book);
		bookLoan.setLoan(loan);
		return bookLoan;
	}

}
