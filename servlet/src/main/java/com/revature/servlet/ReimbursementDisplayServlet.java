package com.revature.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Base64;
import java.util.List;

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

@WebServlet("/reimbursements/*")
public class ReimbursementDisplayServlet extends HttpServlet {
	protected void doGet (HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession sess = req.getSession();
		User myGuy = (User) sess.getAttribute("currentUser");
		
		String path = req.getPathInfo();
		String[] pathSplits = path.split("/");
		
		resp.setContentType("text/html");
		PrintWriter pw = resp.getWriter();
		pw.println("<html>");
		pw.println("<head>");
		pw.println("<title>");
		pw.println("reimbursements");
		pw.println("</title>");
		pw.println("</head>");
		pw.println("<body>");
		
		EmployeeReimbursementDao erd = new EmployeeReimbursementDao();
		
		if(pathSplits.length < 2 || pathSplits.length > 3) {
			resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
			return;
		}
		
		String statusToSearch = pathSplits[1];
		
		if (pathSplits.length == 2 && myGuy instanceof ManagerUser) {
			List<EmployeeReimbursement>reimbursements = erd.getReimbursements(statusToSearch);
			for (EmployeeReimbursement reimbursement : reimbursements) {
				htmlReimbursement(pw, reimbursement);
			}
		}
		else if (pathSplits.length == 3) {
			int employeeId = Integer.parseInt(pathSplits[2]);
			if (statusToSearch.equals("emp")) {
				List <EmployeeReimbursement> reimbursements = erd.getReimbursements(employeeId); 
				for (EmployeeReimbursement reimbursement : reimbursements) {
					htmlReimbursement(pw, reimbursement);
				}
			}
			else {
				List <EmployeeReimbursement> reimbursements = erd.getReimbursements(statusToSearch, employeeId); 
				for (EmployeeReimbursement reimbursement : reimbursements) {
					htmlReimbursement(pw, reimbursement);
				}
			}
		}
	
		pw.println("<form action=\"/ers-servlet/display\" method=\"post\">");
		pw.println("<button type=\"submit\" name=\"button\" value=\"home\">");
		pw.println("done");
		pw.println("</button><br></br>");
		pw.println("</form>");
		pw.println("</body>");
		pw.println("</html>");
		
		pw.close();
		
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
	
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		res.sendRedirect("reimbursements/emp/"+req.getParameter("EID"));
	}
}
