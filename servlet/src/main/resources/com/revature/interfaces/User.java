package com.revature.interfaces;

public interface User {
	public User login();
	public void logout();
	public List<Reimbursement> load();
	public void display(); 
}
