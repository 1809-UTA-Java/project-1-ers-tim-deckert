package com.revature.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.revature.interfaces.Reimbursement;
import com.revature.interfaces.User;

@Entity
@Table(name = "ERS_USERS")
public class ManagerUser extends EmployeeUser {

	public ManagerUser() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public ManagerUser(Integer id, String username, String password, String firstname, String lastname, String email,
			UserRoles role) {
		super(id, username, password, firstname, lastname, email, role);
		// TODO Auto-generated constructor stub
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

}
