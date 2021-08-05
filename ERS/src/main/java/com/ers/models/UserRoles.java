package com.ers.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.ers.enums.UserRole;

@Entity
@Table(name="urole")
public class UserRoles {
	@Id
	@Column(name = "u_roles_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "u_roles")
	private UserRole u_role;
	
	public UserRoles() {
		
	}
	
	public UserRoles(int id, UserRole u_role) {
		super();
		this.id=id;
		this.u_role=u_role;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public UserRole getU_role() {
		return u_role;
	}

	public void setU_role(UserRole u_role) {
		this.u_role = u_role;
	}

	@Override
	public String toString() {
		return "" + u_role;
	}
	
	
}
