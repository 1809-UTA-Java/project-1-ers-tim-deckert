package com.revature.model;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.util.ArrayList;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Entity
@Table(name = "ERS_USERS")
@DiscriminatorValue("1")
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
	
	public ManagerUser(Integer id, String username, String firstname, String lastname, String email,
			UserRoles role) {
		super(id, username, firstname, lastname, email, role);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void service (String toCall, HttpServletRequest req, HttpServletResponse res) throws MalformedURLException, ProtocolException, IOException, ServletException {
		// TODO Auto-generated method stub
		
		if (toCall.equals("Submit a reimbursement")) {
			this.submit(req, res);
		} else if (toCall.equals("View your pending reimburements")) {
			this.viewPending(req, res);
		} else if (toCall.equals("View your resolved reimburements")) {
			this.viewResolved(req, res);
		} else if (toCall.equals("View your information")) {
			this.viewInfo(req, res);
		} else if (toCall.equals("Update your information")) {
			this.update(req, res);
		} else if (toCall.equals("Logout")) {
			this.logout(req,res);
		} else if (toCall.equals("Approve/Deny reimbursements")) {
			this.decide(req, res);
		} else if (toCall.equals("View all pending reimburements")) {
			this.viewAllPending(req, res);
		} else if (toCall.equals("View all resolved reimburements")) {
			this.viewAllResolved(req, res);
		} else if (toCall.equals("View all employee information")) {
			this.viewAllInfo(req, res);
		} else if (toCall.equals("View all reimbursement requests from an employee")) {
			this.viewEmployeeReimbursements(req, res);
		}
		
	}

	@Override
	public ArrayList<String> display() {
		// TODO Auto-generated method stub
		ArrayList<String> options = new ArrayList<>();
		
		options.add("Approve/Deny reimbursements");
		options.add("View all pending reimburements");
		options.add("View all resolved reimburements");
		options.add("View all employee information");
		options.add("View all reimbursement requests from an employee");
		options.addAll(super.display());
		
		return options;
	}
	
	public void decide(HttpServletRequest req, HttpServletResponse res) throws MalformedURLException, ProtocolException, IOException, ServletException {
		//RequestDispatcher rd = req.getRequestDispatcher("descision");
		res.sendRedirect("descision"); //.forward(req, res);
	}
	
	public void viewAllPending(HttpServletRequest req, HttpServletResponse res) throws MalformedURLException, ProtocolException, IOException, ServletException {
		//RequestDispatcher rd = req.getRequestDispatcher("reimbursements/pending");
		res.sendRedirect("reimbursements/Pending");
	}

	public void viewAllResolved(HttpServletRequest req, HttpServletResponse res) throws MalformedURLException, ProtocolException, IOException, ServletException {
		//RequestDispatcher rd = req.getRequestDispatcher("reimburesements/resolved");
		res.sendRedirect("reimbursements/Resolved");
	}
	
	public void viewEmployeeReimbursements(HttpServletRequest req, HttpServletResponse res) throws MalformedURLException, ProtocolException, IOException, ServletException {
		//RequestDispatcher rd = req.getRequestDispatcher("reimburesements/emp");
		res.sendRedirect("emp-rei");
	}
	
	public void viewAllInfo(HttpServletRequest req, HttpServletResponse res) throws MalformedURLException, ProtocolException, IOException, ServletException {
		//RequestDispatcher rd = req.getRequestDispatcher("employees/");
		res.sendRedirect("employees");
	}

}
