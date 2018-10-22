package com.revature.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Base64;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.revature.interfaces.User;
import com.revature.model.EmployeeReimbursement;
import com.revature.model.ManagerUser;
import com.revature.repository.EmployeeReimbursementDao;

@WebServlet("/descision/*")
public class ReimbursementDecisionServlet extends HttpServlet {
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		HttpSession sess = req.getSession();
		User myGuy = (User) sess.getAttribute("currentUser");
		
		String path = req.getPathInfo();

		res.setContentType("text/html");
		PrintWriter pw = res.getWriter();
		pw.println("<html>");
		pw.println("<head>");
		pw.println("<title>");
		pw.println("descision");
		pw.println("</title>");
		pw.println("</head>");
		pw.println("<body>");
		
		if((path == null || path.equals("/")) && myGuy instanceof ManagerUser) {
			EmployeeReimbursementDao erd = new EmployeeReimbursementDao();
			List<EmployeeReimbursement>reimbursements = erd.getReimbursements("Pending");
			for (EmployeeReimbursement reimbursement : reimbursements) {
				htmlReimbursement(pw, reimbursement);
				pw.println("<form action = \"descision\" method=\"post\" >");
				pw.println("<input type=\"radio\" id=\"Approved\" name=\"status\" value=\"2\">");
				pw.println("<label for=\"Approved\">Approve</label>");
				pw.println("<br></br>");
				
				pw.println("<input type=\"radio\" id=\"Denied\" name=\"status\" value=\"3\">");
				pw.println("<label for=\"Denied\">Deny</label>");
				pw.println("<br></br>");
				
				pw.println("<button type=\"submit\" name=\"button\" value=\""+reimbursement.getId()+"\">Submit</button>");
				pw.println("</form>");
			}
		} else if(path == null || path.equals("/")) {
			RequestDispatcher rd = req.getRequestDispatcher("/");
			rd.forward(req, res);
		} else {
			String[] pathSplits = path.split("/");
		}
		
		pw.println("</body>");
		pw.println("</html>");
		pw.close();
		
	}
	
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		EmployeeReimbursementDao erd = new EmployeeReimbursementDao();
		EmployeeReimbursement er= erd.getReimbursement(Integer.parseInt(req.getParameter("button")));
		HttpSession sess = req.getSession();
		User myGuy = (User) sess.getAttribute("currentUser");
		
		er.resolve((ManagerUser) myGuy, Integer.parseInt(req.getParameter("status")));
		erd.saveReimbursement(er);
		RequestDispatcher rd = req.getRequestDispatcher("/display");
		rd.forward(req, res);
		
	}
	
	protected void htmlReimbursement(PrintWriter pw, EmployeeReimbursement reimbursement) {
		pw.println("<div>");
		pw.println("<p>");
		pw.println("Reimbursement ID :"+reimbursement.getId());
		pw.println("</p>");
		pw.println("<p>");
		pw.println("Amount :"+reimbursement.getAmount());
		pw.println("</p>");
		if (reimbursement.getDesc() != null) {
			pw.println("<p>");
			pw.println("Description :"+reimbursement.getDesc());
			pw.println("</p>");
		}
		pw.println("<p>");
		pw.println("Submitted :"+reimbursement.getSubmitted());
		pw.println("</p>");
		if (reimbursement.getResolved() != null) {
			pw.println("<p>");
			pw.println("Resolved :"+reimbursement.getResolved());
			pw.println("</p>");
		}
		pw.println("<p>");
		pw.println("Author :"+reimbursement.getAuthor().getFirstname()+" "+reimbursement.getAuthor().getLastname());
		pw.println("</p>");
		if (reimbursement.getResolver() != null) {
			pw.println("<p>");
			pw.println("Resolver :"+reimbursement.getResolver().getFirstname()+" "+reimbursement.getResolver().getLastname());
			pw.println("</p>");
		}
		pw.println("<p>");
		pw.println("Type :"+reimbursement.getType().getType());
		pw.println("</p>");
		pw.println("<p>");
		pw.println("Status :"+reimbursement.getStatus().getStatus());
		pw.println("</p>");
		if (reimbursement.getReceipt() != null) {
			pw.println("<p>");
			pw.println("<img src=\"data:image/*;base64,"+Base64.getEncoder().encodeToString(reimbursement.getReceipt())+"\" alt=\"nada\">");
			pw.println("</p>");
		}
		pw.println("</div>");
	}
}
