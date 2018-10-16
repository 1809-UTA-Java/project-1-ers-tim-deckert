package com.revature.repository;

import org.hibernate.Session;

import com.revature.model.ReimbursementType;
import com.revature.resources.HibernateUtil;

public class ReimbursementTypeDao {
	public ReimbursementType type(Integer typeNum) {
		Session session = HibernateUtil.getSession();
		return (ReimbursementType) session.createQuery("from ReimbursementType rt where rt.rtId=:num").setInteger("num", typeNum).list().get(0);
	}
}
