package com.revature.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.revature.model.EmployeeUser;
import com.revature.model.ManagerUser;
import com.revature.model.UserRoles;
import com.revature.repository.EmployeeUserDao;

@WebServlet("/create")
public class CreateEmployeeServlet extends HttpServlet {
	protected void doPost (HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		EmployeeUser eu;
		EmployeeUserDao eud = new EmployeeUserDao();
		if (req.getParameter("role") == null) {
			RequestDispatcher rd = req.getRequestDispatcher("/");	
			rd.forward(req, resp);
		}
		if (req.getParameter("role").equals("Manager")) {
			eu = new ManagerUser();
			UserRoles ur = new UserRoles(1, "Manager");
			eu.setRole(ur);
		}
		else {
			eu = new EmployeeUser();
			UserRoles ur = new UserRoles(2, "Employee");
			eu.setRole(ur);
		}
		if (req.getParameter("username") == null) {
			RequestDispatcher rd = req.getRequestDispatcher("/");	
			rd.forward(req, resp);
		}
		eu.setUsername(req.getParameter("username"));
		if (req.getParameter("password") == null) {
			RequestDispatcher rd = req.getRequestDispatcher("/");	
			rd.forward(req, resp);
		}
		eu.setPassword(req.getParameter("password"));
		if (req.getParameter("fname") != null) {
			eu.setFirstname(req.getParameter("fname"));
		}
		if (req.getParameter("lname") != null) {
			eu.setLastname(req.getParameter("lname"));
		}
		if (req.getParameter("email") != null) {
			eu.setEmail(req.getParameter("email"));
		}
		
		eud.saveEmployee(eu);
		RequestDispatcher rd = req.getRequestDispatcher("/");
		rd.forward(req, resp);
	}
}
