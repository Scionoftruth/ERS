package com.ers.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="reimbursement")
public class Reimbursement {
	
	@Id
	@Column(name="reimbursement_id")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	@Enumerated(EnumType.STRING)
	@Column(length=8)
	private RType type;
	
	@Column(name="amount", nullable=false)
	private double amount;
	
	@Column(name="resolved_date", nullable=false)
	private String resolveddate; 
	
	@Column(name="description", nullable=false)
	private String description;
	
	
	//private String receipt;
	
	@Enumerated(EnumType.STRING)
	@Column(length=8)
	private Status status;
	
	public Reimbursement() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Reimbursement(int id, RType type, double amount, String resolveddate, String description, /*String receipt,*/ Status status) {
		super();
		this.id = id;
		this.type = type;
		this.amount = amount;
		this.resolveddate = resolveddate;
		this.description = description;
		//this.receipt = receipt;
		this.status = status;
	}
	
	public Reimbursement(RType type, double amount, String resolveddate, String description, /*String receipt,*/ Status status) {
		super();
		this.type = type;
		this.amount = amount;
		this.resolveddate = resolveddate;
		this.description = description;
		//this.receipt = receipt;
		this.status = status;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public RType getType() {
		return type;
	}
	
	public void setType(RType type) {
		this.type = type;
	}
	
	public double getAmount() {
		return amount;
	}
	
	public void setAmount(double amount) {
		this.amount = amount;
	}
	
	public Status getStatus() {
		return status;
	}
	
	public void setStatus(Status resolved) {
		this.status = resolved;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	/*
	public String getReceipt() {
		return receipt;
	}
	
	public void setReceipt(String receipt) {
		this.receipt = receipt;
	}
	*/
	public String getResolveDate() {
		return resolveddate;
	}
	
	public void setResolveDate(String resolveddate) {
		this.resolveddate=resolveddate;
	}

	@Override
	public String toString() {
		return "Reimbursment [id=" + id + ", type=" + type + ", amount=" + amount + ", resolveddate=" + resolveddate
				+ ", description=" + description + /*", receipt=" + receipt +*/ ", status=" + status + "]";
	}
	
	
	
	

}
