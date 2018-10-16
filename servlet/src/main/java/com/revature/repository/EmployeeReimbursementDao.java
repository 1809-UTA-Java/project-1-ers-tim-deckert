package com.revature.repository;

import java.util.List;

import org.hibernate.Session;

import com.revature.model.EmployeeReimbursement;
import com.revature.resources.HibernateUtil;

public class EmployeeReimbursementDao {
	public List<EmployeeReimbursement> getAllReimbursements() {
		Session session = HibernateUtil.getSession();
		return session.createQuery("from EmployeeReimbursement").list();
	}
	
	public List<EmployeeReimbursement> getReimbusementsByStatus(String status) {
		Session session = HibernateUtil.getSession();
		return session.createQuery("from EmployeeReimbursement e where e.status=:status").setString("status", status).list();
	}
	
	public List<EmployeeReimbursement> getReimbusementsByEmployee(Integer authorId) {
		Session session = HibernateUtil.getSession();
		return session.createQuery("from EmployeeReimbursement e where e.author=:author").setInteger("author", authorId).list();
	}
}
