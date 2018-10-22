package com.revature.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ERS_REIMBURSEMENT_STATUS")
public class ReimbursementStatus {
	@Id
	@Column(name = "RS_ID")
	private Integer rsId;
	
	@Column(name = "RS_STATUS")
	private String rsStatus;
	
	public ReimbursementStatus() {
		rsId = 1;
		rsStatus = "Pending";
	}
}