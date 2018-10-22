package com.revature.repository;

import org.hibernate.Session;

import com.revature.model.ReimbursementStatus;
import com.revature.resources.HibernateUtil;

public class ReimbursementStatusDao {
	public ReimbursementStatus status(Integer statusNum) {
		Session session = HibernateUtil.getSession();
		return (ReimbursementStatus) session.createQuery("from ReimbursementStatus rs where rs.rsId=:num").setInteger("num", statusNum).uniqueResult();
	}
	public ReimbursementStatus status(String statusName) {
		Session session = HibernateUtil.getSession();
		return (ReimbursementStatus) session.createQuery("from ReimbursementStatus rs where rs.rsStatus=:name").setString("name", statusName).uniqueResult();
	}
}
