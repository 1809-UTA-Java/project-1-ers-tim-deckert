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
import com.revature.model.EmployeeUser;
import com.revature.model.ManagerUser;
import com.revature.repository.EmployeeUserDao;

@WebServlet("/employees/*")
public class EmployeeDisplayServlet extends HttpServlet {
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
		pw.println("employees");
		pw.println("</title>");
		pw.println("</head>");
		pw.println("<body>");
		
		EmployeeUserDao eud = new EmployeeUserDao();
		ObjectMapper om = new XmlMapper();
		
		if((path == null || path.equals("/")) && myGuy instanceof ManagerUser) {
			List<EmployeeUser>employees = eud.getEmployees();
			for (EmployeeUser employee : employees) {
				pw.println("<div>");
				pw.println("<p>");
				pw.println("Username :"+employee.getUsername());
				pw.println("</p>");
				pw.println("<p>");
				pw.println("First Name :"+employee.getFirstname());
				pw.println("</p>");
				pw.println("<p>");
				pw.println("Last Name :"+employee.getLastname());
				pw.println("</p>");
				pw.println("<p>");
				pw.println("E-mail :"+employee.getEmail());
				pw.println("</p>");
				pw.println("<p>");
				pw.println("Role :"+employee.getRole().role());
				pw.println("</p>");
				pw.println("</div>");
			}
			
		} else if (path == null || path.equals("/")) {
			
		} else if(pathSplits.length != 2) {
			resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
			return;
		}
		
		int employeeId = Integer.parseInt(pathSplits[1]);
		EmployeeUser found = eud.getEmployee(employeeId);
		
		if(found != null) {
			String obj = om.writeValueAsString(found);
			pw.println(obj);
		}
	
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
