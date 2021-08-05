package com.ers.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.ers.enums.RType;

@Entity
@Table(name="rtype")
public class ReimbursementType {
	@Id
	@Column(name = "re_type_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "re_type")
	private RType reimbursement_type;
	
	public ReimbursementType() {
		
	}
	
	public ReimbursementType(int id, RType reimbursement_type) {
		super();
		this.id=id;
		this.reimbursement_type=reimbursement_type;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public RType getReimbursement_type() {
		return reimbursement_type;
	}

	public void setReimbursement_type(RType reimbursement_type) {
		this.reimbursement_type = reimbursement_type;
	}

	@Override
	public String toString() {
		return "" + reimbursement_type;
	}
	
}
