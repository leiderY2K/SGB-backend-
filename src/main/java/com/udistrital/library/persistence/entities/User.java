package com.udistrital.library.persistence.entities;

import java.io.Serializable;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "app_user")
public class User implements Serializable {

	private static final long serialVersionUID = -4314082013877576625L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private short id;

	@Column(name = "name")
	private String name;

	@Column(name = "email")
	private String email;

	@Column(name = "password")
	private String password;

	@Column(name = "phone")
	private String phone;

	@Column(name = "account_status")
	private boolean accountStatus;

	@ManyToOne
	@JoinColumn(name = "role_id", referencedColumnName = "id")
	private Role role;

	public void setId(Short id) { this.id = id; }

	public Short getId() { return id; }

	public void setName(String name) { this.name = name; }

	public String getName() { return name; }

	public void setEmail(String email) { this.email = email; }

	public String getEmail() { return email; }

	public void setPassword(String password) { this.password = password; }

	public String getPassword() { return password; }

	public void setPhone(String phone) { this.phone = phone; }

	public String getPhone() { return phone; }

	public void setAccountStatus(boolean accountStatus) { this.accountStatus = accountStatus; }

	public boolean isAccountActive() { return accountStatus; }

	public void setRole(Role role) { this.role = role; }

	public Role getRole() { return role; }
}
