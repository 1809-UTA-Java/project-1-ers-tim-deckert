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
import com.revature.model.EmployeeUser;
import com.revature.repository.EmployeeUserDao;

@WebServlet("/update")
public class UpdateEmployeeServlet extends HttpServlet {
	protected void doGet (HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession sess = req.getSession();
		User myGuy = (User) sess.getAttribute("currentUser");
		
		String path = req.getPathInfo();
		try {
			String[] pathSplits = path.split("/");
		} catch (NullPointerException ex) {
			RequestDispatcher rd = req.getRequestDispatcher("/display");
			rd.forward(req, resp);
		}
		
		resp.setContentType("text/html");
		PrintWriter pw = resp.getWriter();
		pw.println("<html>");
		pw.println("<head>");
		pw.println("<title>");
		pw.println("Update");
		pw.println("</title>");
		pw.println("</head>");
	
		pw.println("<body>");
		pw.println("<h1>Update your information</h1>");
		pw.println("<form action=\"/ers-servlet/update\" method=\"post\">");
		
		pw.println("<label for=\"pass\">Password: </label>\n" + 
				   "<input type=\"password\" id=\"pass\" name=\"password\"><br></br>");
		pw.println("<label for=\"fname\">First Name: </label>\n" + 
				   "<input type=\"text\" id=\"fname\" name=\"fname\"><br></br>");
		pw.println("<label for=\"lname\">Last Name: </label>\n" + 
				   "<input type=\"text\" id=\"lname\" name=\"lname\"><br></br>");
		pw.println("<label for=\"email\">E-mail: </label>\n" + 
				   "<input type=\"text\" id=\"email\" name=\"email\"><br></br>");
		
		pw.println("<button type=\"submit\">Submit</button>");
		pw.println("</form>");
		pw.println("</body>");
		pw.println("</html>");
	}
	
	protected void doPost (HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession sess = req.getSession();
		EmployeeUser myGuy = (EmployeeUser) sess.getAttribute("currentUser");
		EmployeeUserDao eud = new EmployeeUserDao();
		if (req.getParameter("password") != null) {
			myGuy.setPassword(req.getParameter("password"));
		}
		if (req.getParameter("fname") != null) {
			myGuy.setFirstname(req.getParameter("fname"));
		}
		if (req.getParameter("lname") != null) {
			myGuy.setFirstname(req.getParameter("lname"));
		}
		if (req.getParameter("email") != null) {
			myGuy.setFirstname(req.getParameter("email"));
		}
		
		eud.saveEmployee(myGuy);
	}
}
