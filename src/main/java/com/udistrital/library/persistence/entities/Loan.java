package com.udistrital.library.persistence.entities;

import java.io.Serializable;
import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

@Entity
@Table(name = "loan")
public class Loan implements Serializable {

	private static final long serialVersionUID = -8677629507895820956L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private short id;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;

	@Column(name = "start_date", insertable = true, updatable = false)
	private Date startDate;

	@Column(name = "end_date")
	private Date endDate;

	public void setId(Short id) { this.id = id; }

	public Short getId() { return id; }

	public void setUser(User user) { this.user = user; }

	public User getUser() { return user; }

	public void setStartDate(Date startDate) { this.startDate = startDate; }

	public Date getStartDate() { return startDate; }

	public void setEndDate(Date endDate) { this.endDate = endDate; }

	public Date getEndDate() { return endDate; }

}
