package com.revature.repository;

import static org.junit.Assert.fail;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.revature.model.EmployeeUser;

public class EmployeeUserDaoTest {
	EmployeeUserDao eud;

	@Before
	public void setUp() throws Exception {
		eud = new EmployeeUserDao();
	}

	@Test
	public void testGetEmployees() {
		List<EmployeeUser> eu = eud.getEmployees();
		System.out.println(eu.get(0).toString());
		fail("Not yet implemented");
	}
	
	@Test
	public void testGetEmployee() {
		EmployeeUser eu = eud.getEmployee(1);
		System.out.println(eu.toString());
		assert(eu.getUsername().equals("TIM"));
	}

}
