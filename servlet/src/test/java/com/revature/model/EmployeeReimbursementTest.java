package com.revature.model;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class EmployeeReimbursementTest {
	EmployeeReimbursement er;
	ManagerUser mu;
	@Before
	public void setUp() throws Exception {
		er = new EmployeeReimbursement();
		mu = new ManagerUser();
	}

	@Test
	public void testResolve() {
		//assertEquals(er.resolve(mu, 2), true);
	}

}
