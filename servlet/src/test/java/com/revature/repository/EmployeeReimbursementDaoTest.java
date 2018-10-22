package com.revature.repository;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class EmployeeReimbursementDaoTest {
	public EmployeeReimbursementDao erd;
	@Before
	public void setUp() throws Exception {
		erd = new EmployeeReimbursementDao();
	}

	@Test
	public void testGetAllReimbursements() {
		erd.getAllReimbursements();
		fail("Not yet implemented");
	}

	@Test
	public void testGetReimbusementsByStatus() {
		erd.getReimbursements("approved");
		erd.getReimbursements("pending");
		erd.getReimbursements("denied");
		fail("Not yet implemented");
	}

}
