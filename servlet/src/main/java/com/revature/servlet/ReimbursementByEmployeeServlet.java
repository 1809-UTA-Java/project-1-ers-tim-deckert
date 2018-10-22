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

@WebServlet("/emp-rei")
public class ReimbursementByEmployeeServlet extends HttpServlet {
	protected void doGet (HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession sess = req.getSession();
		User myGuy = (User) sess.getAttribute("currentUser");
		
		PrintWriter pw = resp.getWriter();
		
		pw.println("<html>");
		pw.println("<head>");
		pw.println("<title>");
		pw.println("Employee ID");
		pw.println("</title>");
		pw.println("</head>");
	
		pw.println("<body>");
		pw.println("<form action=\"/ers-servlet/reimbursements\" method=\"post\">");
		
		pw.println("<label for=\"EID\">Employee ID: </label>\n" + 
				   "<input type=\"number\" id=\"EID\" name=\"EID\"><br></br>");
		pw.println("<button type=\"submit\">Submit</button>");
		pw.println("</form>");
		pw.println("</body>");
		pw.println("</html>");
	}
	
}
