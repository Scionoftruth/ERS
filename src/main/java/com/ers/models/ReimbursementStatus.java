package com.ers.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.ers.enums.Status;

@Entity
@Table(name="rstatus")
public class ReimbursementStatus {
	@Id
	@Column(name = "re_status_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "re_status")
	private Status reimbursement_status;
	
	public ReimbursementStatus() {
		
	}
	
	public ReimbursementStatus(int id, Status reimbursement_status) {
		super();
		this.id=id;
		this.reimbursement_status=reimbursement_status;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Status getReimbursement_status() {
		return reimbursement_status;
	}

	public void setReimbursement_status(Status reimbursement_status) {
		this.reimbursement_status = reimbursement_status;
	}

	@Override
	public String toString() {
		return ""+reimbursement_status;
	}
	
	
}
