package com.revature.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.revature.interfaces.User;

@WebServlet("/update")
public class UpdateEmployeeServlet extends HttpServlet {
	protected void doGet (HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession sess = req.getSession();
		User myGuy = (User) sess.getAttribute("currentUser");
		
		String path = req.getPathInfo();
		String[] pathSplits = path.split("/");
		
		resp.setContentType("text/xml");
		PrintWriter pw = resp.getWriter();
		
	}
}
