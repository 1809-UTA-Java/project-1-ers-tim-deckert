package com.revature.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.revature.interfaces.User;
import com.revature.model.EmployeeUser;
import com.revature.repository.EmployeeUserDao;
import com.revature.resources.HibernateUtil;

@WebServlet("/login")
public class LoggingInServlet extends HttpServlet {
	protected void doPost (HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		
		HttpSession sess = req.getSession();
		
		User user = null;
		EmployeeUserDao eud = new EmployeeUserDao();
		//NULL POINTER IF NO USER ALSO CASE SENSITIVE USERNAMES
		EmployeeUser eu = eud.getEmployee(username);
		
		try {
			user = eu.login(password);
		} catch (NullPointerException ex) {
			RequestDispatcher rd = req.getRequestDispatcher("/");
			rd.forward(req, res);
		}
		if (user == null) {
			RequestDispatcher rd = req.getRequestDispatcher("/");
			rd.forward(req, res);
		}
		
		sess.setAttribute("currentUser", user);
		RequestDispatcher rd = req.getRequestDispatcher("/display");
		rd.forward(req, res);
		
	}
}
