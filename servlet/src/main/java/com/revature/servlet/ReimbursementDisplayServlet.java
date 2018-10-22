package com.revature.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
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
		
		resp.setContentType("text/xml");
		PrintWriter pw = resp.getWriter();
		
		EmployeeReimbursementDao erd = new EmployeeReimbursementDao();
		ObjectMapper om = new XmlMapper();
		
		if(pathSplits.length < 2 || pathSplits.length > 3) {
			resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
			return;
		}
		
		String statusToSearch = pathSplits[1];
		
		if (pathSplits.length == 2 && myGuy instanceof ManagerUser) {
			if (statusToSearch.equals("emp")) {
				
			}
			List<EmployeeReimbursement>reimbursements = erd.getReimbursements(statusToSearch);
			String obj = om.writeValueAsString(reimbursements);
			pw.println(obj);
		}
		else if (pathSplits.length == 3) {
			int employeeId = Integer.parseInt(pathSplits[2]);
			List <EmployeeReimbursement> found = erd.getReimbursements(statusToSearch, employeeId);
			if(found != null) {
				String obj = om.writeValueAsString(found);
				pw.println(obj);
			}
		}
		
		resp.setContentType("text/html");
		pw.println("<html>");
	
		pw.println("<body>");
		pw.println("<form action=\"display\" method=\"post\">");
		pw.println("<button type=\"submit\" name=\"button\" value=\"home\">");
		pw.println("done");
		pw.println("</button><br></br>");
		pw.println("</form>");
		pw.println("</body>");
		pw.println("</html>");
		
		pw.close();
		
	}
}
