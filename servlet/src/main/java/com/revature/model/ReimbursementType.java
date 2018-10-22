package com.revature.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ERS_REIMBURSEMENT_TYPE")
public class ReimbursementType {
	@Id
	@Column(name = "RT_ID")
	private Integer rtId;
	
	@Column(name = "RT_TYPE")
	private String rtType;
	
	public String getType() {
		return rtType;
	}
}
