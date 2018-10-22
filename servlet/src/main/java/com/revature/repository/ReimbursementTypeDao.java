package com.revature.repository;

import java.util.List;

import org.hibernate.Session;

import com.revature.model.ReimbursementType;
import com.revature.resources.HibernateUtil;

public class ReimbursementTypeDao {
	public ReimbursementType type(Integer typeNum) {
		Session session = HibernateUtil.getSession();
		return (ReimbursementType) session.createQuery("from ReimbursementType rt where rt.rtId=:num").setInteger("num", typeNum).uniqueResult();
	}
	
	public ReimbursementType type(String typeName) {
		Session session = HibernateUtil.getSession();
		return (ReimbursementType) session.createQuery("from ReimbursementType rt where rt.rtType=:name").setString("name", typeName).uniqueResult();
	}
	
	public List<ReimbursementType> type() {
		Session session = HibernateUtil.getSession();
		return session.createQuery("from ReimbursementType").list();
	}
}
