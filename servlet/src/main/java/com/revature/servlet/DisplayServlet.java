package com.revature.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.revature.interfaces.User;

@WebServlet("/display")
public class DisplayServlet extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		HttpSession sess = req.getSession();
		
		User myGuy = (User) sess.getAttribute("currentUser");
		ArrayList<String> buttons = null;
		try {
			buttons = myGuy.display();
		} catch (NullPointerException ex) {
			RequestDispatcher rd = req.getRequestDispatcher("/");
			rd.forward(req, res);
		}
		
		res.setContentType("text/html");
		
		PrintWriter pw = res.getWriter();
		pw.println("<html>");
		pw.println("<head>");
		pw.println("<title>");
		pw.println("the game");
		pw.println("</title>");
		pw.println("</head>");
	
		pw.println("<body>");
		pw.println("<form action=\"chomp-form\" method=\"post\">");
		for (String button : buttons) {
			pw.println("<button type=\"submit\" name=\"button\" value=\""+button+"\">");
			pw.println(button);
			pw.println("</button><br></br>");
		}
		pw.println("</form>");
		pw.println("</body>");
		pw.println("</html>");
		
	}
}
