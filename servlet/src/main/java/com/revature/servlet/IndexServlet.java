package com.revature.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;

import com.revature.resources.HibernateUtil;

@WebServlet("/home")
public class IndexServlet extends HttpServlet {
	
	@Override
	protected void doPost (HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		RequestDispatcher rd = req.getRequestDispatcher("index.html");
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		
		Session sess = HibernateUtil.getSession();
		
		rd.forward(req, resp);
	}
}
