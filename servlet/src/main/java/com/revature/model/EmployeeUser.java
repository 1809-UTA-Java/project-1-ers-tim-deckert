package com.revature.model;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.revature.interfaces.User;

@Entity
@Table(name = "ERS_USERS")
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(discriminatorType = DiscriminatorType.INTEGER,
					 name = "UR_ID")
@DiscriminatorValue("2")
public class EmployeeUser implements User {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "U_Sequence")
	@SequenceGenerator(name = "U_Sequence", sequenceName = "U_Sequence", allocationSize = 1, initialValue = 3)
	@Column(name = "U_ID")
	protected Integer id;
	@Column(name = "U_USERNAME")
	protected String username;
	@Column(name = "U_PASSWORD")
	protected String password;
	@Column(name = "U_FIRSTNAME")
	protected String firstname;
	@Column(name = "U_LASTNAME")
	protected String lastname;
	@Column(name = "U_EMAIL")
	protected String email;

	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name = "UR_ID", insertable=false, updatable=false)
	protected UserRoles role;
	
	public EmployeeUser() {
		super();
		// TODO Auto-generated constructor stub
	}

	public EmployeeUser(Integer id, String username, String password, String firstname, String lastname, String email,
			UserRoles role) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.firstname = firstname;
		this.lastname = lastname;
		this.email = email;
		this.role = role;
	}
	
	public EmployeeUser(Integer id, String username, String firstname, String lastname, String email,
			UserRoles role) {
		super();
		this.id = id;
		this.username = username;
		this.firstname = firstname;
		this.lastname = lastname;
		this.email = email;
		this.role = role;
	}

	@Override
	public String toString() {
		return "EmployeeUser [id=" + id + ", username=" + username + ", password=" + password + ", firstname="
				+ firstname + ", lastname=" + lastname + ", email=" + email + ", role=" + role.toString() + "]";
	}

	@Override
	public User login(String password) {
		if (password.equals(this.password)) {
			if (this.role.role().equals("Manager")) {
				return new ManagerUser(this.id, this.username, this.firstname, this.lastname, this.email, this.role);
			}
			else {
				return new EmployeeUser(this.id, this.username, this.firstname, this.lastname, this.email, this.role);
			}
		}
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void logout(HttpServletRequest req, HttpServletResponse res) throws MalformedURLException, ProtocolException, IOException, ServletException {
		// TODO Auto-generated method stub
		RequestDispatcher rd = req.getRequestDispatcher("logout");
		rd.forward(req, res);
	}

	@Override
	public void service(String toCall, HttpServletRequest req, HttpServletResponse res) throws MalformedURLException, ProtocolException, IOException, ServletException {
		// TODO Auto-generated method stub
		if (toCall.equals("Submit a reimbursement")) {
			this.submit(req, res);
		} else if (toCall.equals("View your pending reimburements")) {
			this.viewPending(req, res);
		} else if (toCall.equals("View your resolved reimburements")) {
			this.viewResolved(req, res);
		} else if (toCall.equals("View your information")) {
			this.viewInfo(req, res);
		} else if (toCall.equals("Update your information")) {
			this.update(req, res);
		} else if (toCall.equals("Logout")) {
			this.logout(req,res);
		} else {
			//this.submit();
		}
	}

	@Override
	public ArrayList<String> display() {
		// TODO Auto-generated method stub
		ArrayList<String> options = new ArrayList<>();
		
		options.add("Submit a reimbursement");
		options.add("View your pending reimburements");
		options.add("View your resolved reimburements");
		options.add("View your information");
		options.add("Update your information");
		options.add("Logout");
		
		return options;

	}
		
	public void submit(HttpServletRequest req, HttpServletResponse res) throws MalformedURLException, ProtocolException, IOException, ServletException {
		RequestDispatcher rd = req.getRequestDispatcher("submit");
		rd.forward(req, res);
	}

	public void viewPending(HttpServletRequest req, HttpServletResponse res) throws MalformedURLException, ProtocolException, IOException, ServletException {
		//RequestDispatcher rd = req.getRequestDispatcher("reimbursements/pending/"+this.id);
		res.sendRedirect("reimbursements/Pending/"+this.id);
	}

	public void viewResolved(HttpServletRequest req, HttpServletResponse res) throws MalformedURLException, ProtocolException, IOException, ServletException {
		//RequestDispatcher rd = req.getRequestDispatcher("reimbursements/resolved/"+this.id);
		res.sendRedirect("reimbursements/Resolved/"+this.id);
	}

	public void viewInfo(HttpServletRequest req, HttpServletResponse res) throws MalformedURLException, ProtocolException, IOException, ServletException {
		//RequestDispatcher rd = req.getRequestDispatcher("employees/"+this.id);
		res.sendRedirect("employees/"+this.id);
	}

	public void update(HttpServletRequest req, HttpServletResponse res) throws MalformedURLException, ProtocolException, IOException, ServletException {
		//RequestDispatcher rd = req.getRequestDispatcher("update/"+this.id);
		res.sendRedirect("update/"+this.id);
	}
	
	public String getUsername() {
		return this.username;
	}

	public Integer getId() {
		return id;
	}

	public String getPassword() {
		return password;
	}

	public String getFirstname() {
		return firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public String getEmail() {
		return email;
	}

	public UserRoles getRole() {
		return role;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}

	public void setRole(UserRoles role) {
		this.role = role;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
