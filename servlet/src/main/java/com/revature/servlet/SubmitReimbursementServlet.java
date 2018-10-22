package com.revature.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.revature.interfaces.User;
import com.revature.model.ReimbursementType;
import com.revature.repository.ReimbursementTypeDao;

@WebServlet("/submit")
public class SubmitReimbursementServlet extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		HttpSession sess = req.getSession();
		
		User myGuy = (User) sess.getAttribute("currentUser");
		
		ReimbursementTypeDao rtd = new ReimbursementTypeDao();
		List<ReimbursementType> types = rtd.type();
		
		res.setContentType("text/html");
		PrintWriter pw = res.getWriter();
		
		pw.println("<html>");
		pw.println("<head>");
		pw.println("<title>");
		pw.println("Reimbursement Submission");
		pw.println("</title>");
		pw.println("</head>");
	
		pw.println("<body>");
		pw.println("<h1>Welcome to the reimbursement submission page</h1>");
		pw.println("<form action=\"submit-action\" method=\"post\" enctype=\"multipart/form-data\">");
		
		pw.println("<label for=\"amount\">Amount: $</label>\n" + 
				   "<input type=\"number\" id=\"amount\" name=\"amount\" step=\"0.01\"><br></br>");
		
		pw.println("<label for=\"desc\">Description: </label>\n" + 
				   "<input type=\"text\" id=\"desc\" name=\"desc\"><br></br>");
		
		pw.println("<label for=\"receipt\">Receipt: </label>\n" + 
				   "<input type=\"file\" id=\"receipt\" name=\"receipt\" accept=\"image/*\"><br></br>");
		
		for (ReimbursementType type : types) {
			pw.println("<input type=\"radio\" id=\""+type.getType()+"\" name=\"type\" value=\""+type.getType()+"\">");
			pw.println("<label for=\""+type.getType()+"\">"+type.getType()+"</label>");
			pw.println("<br></br>");
		}
		pw.println("<button type=\"submit\">Submit</button>");
		pw.println("</form>");
		pw.println("</body>");
		pw.println("</html>");
		pw.close();
	}
}
