package com.revature.interfaces;

import java.util.List;

public interface User {
	public User login();
	public void logout();
	public List<Reimbursement> load();
	public void display(); 
}
