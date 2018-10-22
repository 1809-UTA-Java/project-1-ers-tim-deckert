package com.revature.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.revature.interfaces.User;

@WebServlet("/chomp-form")
public class ChompFormServlet extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String button = req.getParameter("button");
		HttpSession sess = req.getSession();
		User myGuy = (User) sess.getAttribute("currentUser");
		
		res.setContentType("text");
		
		//PrintWriter pw = res.getWriter();
		//pw.println(button);
		try {
			myGuy.service(button, req, res);
		} catch (NullPointerException ex) {
			RequestDispatcher rd = req.getRequestDispatcher("/");
			rd.forward(req, res);
		}
	}
}
