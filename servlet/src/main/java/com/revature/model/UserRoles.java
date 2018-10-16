package com.revature.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ERS_USER_ROLES")
public class UserRoles {
	@Id
	@Column(name = "UR_ID")
	private Integer urId;
	@Column(name = "UR_ROLE")
	private String role;
	
	public UserRoles() {
		super();
		// TODO Auto-generated constructor stub
	}

	public UserRoles(Integer urId, String role) {
		super();
		this.urId = urId;
		this.role = role;
	}

	@Override
	public String toString() {
		return "UserRoles [urId=" + urId + ", role=" + role + "]";
	}
	
}
