package com.udistrital.library.persistence.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@Data
public class BookLoanId implements Serializable {

	private static final long serialVersionUID = 4892439025283357614L;

	@Column(name = "loan_id", nullable = false)
	private short loanId;

	@Column(name = "book_id", nullable = false)
	private short bookId;

	public void setLoanId(short loanId) { this.loanId = loanId; }

	public short getLoanId() { return loanId; }

	public void setBookId(short bookId) { this.bookId = bookId; }

	public short getBookId() { return bookId; }

	public static BookLoanId create(short loanId, short bookId) {
		var bookLoanId = new BookLoanId();
		bookLoanId.setLoanId(loanId);
		bookLoanId.setBookId(bookId);
		return bookLoanId;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null || getClass() != obj.getClass()) return false;
		BookLoanId that = (BookLoanId) obj;
		return loanId == that.loanId && bookId == that.bookId;
	}

	@Override
	public int hashCode() { return 31 * loanId + bookId; }
}
