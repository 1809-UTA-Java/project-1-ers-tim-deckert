package com.revature.model;

import java.sql.Timestamp;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.revature.interfaces.Reimbursement;
import com.revature.repository.ReimbursementStatusDao;

@Entity
@Table(name = "ERS_REIMBURSEMENTS")
public class EmployeeReimbursement implements Reimbursement {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "R_Sequence")
	@SequenceGenerator(name = "R_Sequence", sequenceName = "R_Sequence", allocationSize = 1, initialValue = 1)
	@Column(name = "R_ID")
	private Integer r_id;
	
	@Column(name = "R_AMOUNT")
	private Double r_amount;
	
	@Column(name = "R_DESCRIPTION")
	private String r_desc;
	
	@Column(name = "R_RECIEPT")
	private byte[] r_reciept;
	
	@Column(name = "R_SUBMITTED")
	private Timestamp r_submitted;
	
	@Column(name = "R_RESOLVED")
	private Timestamp r_resolved;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name = "U_ID_AUTHOR")
	private EmployeeUser author;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name = "U_ID_RESOLVER")
	private ManagerUser resolver;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name = "RT_TYPE")
	private ReimbursementType type;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name = "RT_STATUS")
	private ReimbursementStatus status;
	//private boolean resolved;
	
	public EmployeeReimbursement() {
		super();
		// TODO Auto-generated constructor stub
	}

	public EmployeeReimbursement(Integer r_id, Double r_amount, String r_desc, byte[] r_reciept,
			Timestamp r_submitted, Timestamp r_resolved, EmployeeUser author, ManagerUser resolver, ReimbursementType type,
			ReimbursementStatus status) {
		super();
		this.r_id = r_id;
		this.r_amount = r_amount;
		this.r_desc = r_desc;
		this.r_reciept = r_reciept;
		this.r_submitted = r_submitted;
		this.r_resolved = r_resolved;
		this.author = author;
		this.resolver = resolver;
		this.type = type;
		this.status = status;
	}

	public EmployeeReimbursement(Double r_amount, String r_desc, EmployeeUser author, ReimbursementType type) {
		super();
		this.r_amount = r_amount;
		this.r_desc = r_desc;
		this.author = author;
		this.type = type;
		this.r_submitted = new Timestamp(System.currentTimeMillis());
		this.status = new ReimbursementStatus();
	}

	
	public EmployeeReimbursement(Double r_amount, String r_desc, byte[] r_reciept, EmployeeUser author,
			ReimbursementType type) {
		super();
		this.r_amount = r_amount;
		this.r_desc = r_desc;
		this.r_reciept = r_reciept;
		this.author = author;
		this.type = type;
		this.r_submitted = new Timestamp(System.currentTimeMillis());
		this.status = new ReimbursementStatus();
	}

	@Override
	public void resolve(ManagerUser resolver, Integer status) {
		this.resolver = resolver;
		ReimbursementStatusDao rsd = new ReimbursementStatusDao();
		this.status = rsd.status(status);
		this.r_resolved = new Timestamp(System.currentTimeMillis());
	/*	this.resolver = resolver;
		resolved = !resolved;
		return resolved;*/
	}

}
