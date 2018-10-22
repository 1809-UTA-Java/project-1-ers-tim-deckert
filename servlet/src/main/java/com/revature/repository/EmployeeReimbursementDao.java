package com.revature.repository;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.revature.model.EmployeeReimbursement;
import com.revature.model.EmployeeUser;
import com.revature.resources.HibernateUtil;

public class EmployeeReimbursementDao {
	public List<EmployeeReimbursement> getAllReimbursements() {
		Session session = HibernateUtil.getSession();
		return session.createQuery("from EmployeeReimbursement").list();
	}
	
	public List<EmployeeReimbursement> getReimbursements(String status) {
		Session session = HibernateUtil.getSession();
		return session.createQuery("from EmployeeReimbursement e where e.status=:status").setString("status", status).list();
	}
	
	public List<EmployeeReimbursement> getReimbursements(Integer authorId) {
		Session session = HibernateUtil.getSession();
		return session.createQuery("from EmployeeReimbursement e where e.author=:author").setInteger("author", authorId).list();
	}
	
	public List<EmployeeReimbursement> getReimbursements(String status, Integer authorId) {
		Session session = HibernateUtil.getSession();
		return session.createQuery("from EmployeeReimbursement e where e.status=:status and e.author=:author")
				.setString("status", status).setInteger("author", authorId).list();
	}
	
	public void saveReimbursement(EmployeeReimbursement er) {
		Session session = HibernateUtil.getSession();
		Transaction tx = session.beginTransaction();
		session.save(er);
		tx.commit();
	}
}
