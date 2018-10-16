package com.revature.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.revature.interfaces.Reimbursement;
import com.revature.interfaces.User;

@Entity
@Table(name = "ERS_USERS")
public class EmployeeUser implements User {

	@Id
	@Column(name = "U_ID")
	private Integer id;
	@Column(name = "U_USERNAME")
	private String username;
	@Column(name = "U_PASSWORD")
	private String password;
	@Column(name = "U_FIRSTNAME")
	private String firstname;
	@Column(name = "U_LASTNAME")
	private String lastname;
	@Column(name = "U_EMAIL")
	private String email;

	@ManyToOne(fetch=FetchType.EAGER, cascade=CascadeType.ALL)
	@JoinColumn(name = "UR_ID")
	private UserRoles role;
	
	public EmployeeUser() {
		super();
		// TODO Auto-generated constructor stub
	}

	public EmployeeUser(Integer id, String username, String password, String firstname, String lastname, String email,
			UserRoles role) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.firstname = firstname;
		this.lastname = lastname;
		this.email = email;
		this.role = role;
	}

	@Override
	public String toString() {
		return "EmployeeUser [id=" + id + ", username=" + username + ", password=" + password + ", firstname="
				+ firstname + ", lastname=" + lastname + ", email=" + email + ", role=" + role.toString() + "]";
	}

	@Override
	public User login() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void logout() {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Reimbursement> load() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void display() {
		// TODO Auto-generated method stub

	}
	public String getUsername() {
		return this.username;
	}

}
