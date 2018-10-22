package com.revature.servlet;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Paths;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import org.apache.commons.io.IOUtils;

import com.revature.interfaces.User;
import com.revature.model.EmployeeReimbursement;
import com.revature.model.EmployeeUser;
import com.revature.model.ReimbursementType;
import com.revature.repository.EmployeeReimbursementDao;
import com.revature.repository.ReimbursementTypeDao;
import com.revature.resources.ReimbursementFactory;

@WebServlet("/submit-action")
@MultipartConfig
public class SubmitToDB extends HttpServlet {
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		HttpSession sess = req.getSession();
		
		User myGuy = (User) sess.getAttribute("currentUser");
		EmployeeReimbursement er = null;
		ReimbursementTypeDao rtd = new ReimbursementTypeDao();
		ReimbursementType rt = rtd.type(req.getParameter("type"));
		
		if (req.getPart("receipt") != null ) {
			Part filePart = req.getPart("receipt"); 
			String filename = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
			InputStream file = filePart.getInputStream();
			
//			File imgFile = new File(filename);
//			String ext = filename.substring(filename.lastIndexOf("."));
//			
//			BufferedImage img = ImageIO.read(imgFile);
//			ByteArrayOutputStream baos = new ByteArrayOutputStream();
//			ImageIO.write(img, ext, baos);
			byte[] inBytes = IOUtils.toByteArray(file);
			er = ReimbursementFactory.createEmployeeReimbursement(Double.parseDouble(req.getParameter("amount")), req.getParameter("desc"), inBytes, (EmployeeUser) myGuy, rt);
		}
		else {
			er = ReimbursementFactory.createEmployeeReimbursement(Double.parseDouble(req.getParameter("amount")), req.getParameter("desc"), (EmployeeUser) myGuy, rt);
		}
		
		if (er != null) {
			EmployeeReimbursementDao erd = new EmployeeReimbursementDao();
			erd.saveReimbursement(er);
		}
		RequestDispatcher rd = req.getRequestDispatcher("/display");
		rd.forward(req, res);
		
	}
}
