package com.revature.resources;

import java.sql.Timestamp;

import com.revature.model.EmployeeReimbursement;
import com.revature.model.EmployeeUser;
import com.revature.model.ManagerUser;
import com.revature.model.ReimbursementStatus;
import com.revature.model.ReimbursementType;

public class ReimbursementFactory {
	public static EmployeeReimbursement createEmployeeReimbursement (Double r_amount, String r_desc, EmployeeUser author, ReimbursementType type) {
		return new EmployeeReimbursement(r_amount, r_desc, author, type);
	}

	public static EmployeeReimbursement createEmployeeReimbursement(Double r_amount, String r_desc, byte[] r_reciept, EmployeeUser author, ReimbursementType type) {
		return new EmployeeReimbursement(r_amount, r_desc, r_reciept, author, type);
	}
	
}
