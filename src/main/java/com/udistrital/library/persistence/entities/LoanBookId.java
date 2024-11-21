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
public class LoanBookId implements Serializable {

	private static final long serialVersionUID = 4892439025283357614L;

	@Column(name = "loan_id", nullable = false)
	private short loanId;

	@Column(name = "book_id", nullable = false)
	private short bookId;

	public short getLoanId() { return loanId; }

	public void setLoanId(short loanId) { this.loanId = loanId; }

	public short getBookId() { return bookId; }

	public void setBookId(short bookId) { this.bookId = bookId; }

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null || getClass() != obj.getClass())
			return false;
		LoanBookId that = (LoanBookId) obj;
		return loanId == that.loanId && bookId == that.bookId;
	}

	@Override
	public int hashCode() { return 31 * loanId + bookId; }
}
