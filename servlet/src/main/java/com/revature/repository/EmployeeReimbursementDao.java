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
		if (status.equals("Pending")) {
			return session.createQuery("from EmployeeReimbursement e where e.status=1").list();
		}
		return session.createQuery("from EmployeeReimbursement e where e.status=2 or e.status=3").list();
	}
	
	public List<EmployeeReimbursement> getReimbursements(Integer authorId) {
		Session session = HibernateUtil.getSession();
		return session.createQuery("from EmployeeReimbursement e where e.author=:author").setInteger("author", authorId).list();
	}
	
	public List<EmployeeReimbursement> getReimbursements(String status, Integer authorId) {
		Session session = HibernateUtil.getSession();
		if (status.equals("Pending")) {
			return session.createQuery("from EmployeeReimbursement e where e.status=1 and e.author=:author").setInteger("author", authorId).list();
		}
		return session.createQuery("from EmployeeReimbursement e where (e.status=2 or e.status=3) and e.author=:author").setInteger("author", authorId).list();
	}
	
	public EmployeeReimbursement getReimbursement(Integer reId) {
		Session session = HibernateUtil.getSession();
		return (EmployeeReimbursement) session.createQuery("from EmployeeReimbursement e where e.r_id=:id")
				.setInteger("id", reId).uniqueResult();
	}
	
	public void saveReimbursement(EmployeeReimbursement er) {
		Session session = HibernateUtil.getSession();
		Transaction tx = session.beginTransaction();
		session.saveOrUpdate(er);
		tx.commit();
	}
}
