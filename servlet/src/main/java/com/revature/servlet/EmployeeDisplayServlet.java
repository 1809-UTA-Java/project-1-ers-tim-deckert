package com.revature.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.revature.interfaces.User;
import com.revature.model.EmployeeUser;
import com.revature.model.ManagerUser;
import com.revature.repository.EmployeeUserDao;

@WebServlet("/employees/*")
public class EmployeeDisplayServlet extends HttpServlet {
	protected void doGet (HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession sess = req.getSession();
		User myGuy = (User) sess.getAttribute("currentUser");
		
		String path = req.getPathInfo();
		
		resp.setContentType("text/html");
		PrintWriter pw = resp.getWriter();
		pw.println("<html>");
		pw.println("<head>");
		pw.println("<title>");
		pw.println("employees");
		pw.println("</title>");
		pw.println("</head>");
		pw.println("<body>");
		
		EmployeeUserDao eud = new EmployeeUserDao();
		
		if((path == null || path.equals("/")) && myGuy instanceof ManagerUser) {
			List<EmployeeUser>employees = eud.getEmployees();
			for (EmployeeUser employee : employees) {
				pw.println("<div>");
				pw.println("<p>");
				pw.println("Username :"+employee.getUsername());
				pw.println("</p>");
				if (employee.getFirstname() != null) {
					pw.println("<p>");
					pw.println("First Name :"+employee.getFirstname());
					pw.println("</p>");
				}
				if (employee.getLastname() != null) {
					pw.println("<p>");
					pw.println("Last Name :"+employee.getLastname());
					pw.println("</p>");
				}
				if (employee.getEmail() != null) {
					pw.println("<p>");
					pw.println("E-mail :"+employee.getEmail());
					pw.println("</p>");
				}
				pw.println("<p>");
				pw.println("Role :"+employee.getRole().role());
				pw.println("</p>");
				pw.println("</div>");
			}
			
		} else if (path == null || path.equals("/")) {
			RequestDispatcher rd = req.getRequestDispatcher("/");
			rd.forward(req, resp);
		} else if(path.split("/").length != 2) {
			resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
			return;
		} else {
			String[] pathSplits = path.split("/");
			int employeeId = Integer.parseInt(pathSplits[1]);
			EmployeeUser found = eud.getEmployee(employeeId);
			
			if(found != null) {
				pw.println("<div>");
				pw.println("<p>");
				pw.println("Username :"+found.getUsername());
				pw.println("</p>");
				if (found.getFirstname() != null) {
					pw.println("<p>");
					pw.println("First Name :"+found.getFirstname());
					pw.println("</p>");
				}
				if (found.getLastname() != null) {
					pw.println("<p>");
					pw.println("Last Name :"+found.getLastname());
					pw.println("</p>");
				}
				if (found.getEmail() != null) {
					pw.println("<p>");
					pw.println("E-mail :"+found.getEmail());
					pw.println("</p>");
				}
				pw.println("<p>");
				pw.println("Role :"+found.getRole().role());
				pw.println("</p>");
				pw.println("</div>");
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
}
