package com.revature.repository;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.revature.model.EmployeeUser;
import com.revature.resources.HibernateUtil;

public class EmployeeUserDao {
	public List<EmployeeUser> getEmployees() {
		Session session = HibernateUtil.getSession();
		//look at adding managers to this list
		return session.createQuery("from EmployeeUser").list();
	}
	
	public EmployeeUser getEmployee(Integer employeeId) {
		EmployeeUser found = null;
		List<EmployeeUser> employees = new ArrayList<>();
		
		Session session = HibernateUtil.getSession();
		
		employees = session.createQuery("from EmployeeUser e where e.id=:eId").setInteger("eId", employeeId).list();
		if (employees.isEmpty()) {
			return null;
		}
		
		return employees.get(0);
	}
	
	public void saveEmployee(EmployeeUser e) {
		Session session = HibernateUtil.getSession();
		Transaction tx = session.beginTransaction();
		session.save(e);
		tx.commit();
	}

}
