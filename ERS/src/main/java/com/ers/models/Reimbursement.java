package com.ers.models;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.ers.enums.RType;
import com.ers.enums.Status;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@JsonIgnoreProperties(value= {"hibernateLazyInitializer", "handler"})
@Table(name="reimbursement")
public class Reimbursement {
	
	@Id
	@Column(name="reimbursement_id")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="re_type_id")
	private ReimbursementType type;
	
	@Column(name="amount", nullable=false)
	private double amount;
	
	@Column(name="submitted_date", nullable=false)
	private String submitteddate;
	
	@Column(name="resolved_date")
	private String resolveddate; 
	
	@Column(name="description", nullable=false)
	private String description;
	
	//@ManyToOne(cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	//@JoinColumn(name="user_id")
	//@JsonIgnoreProperties("ers_reimb")
	//private User user;
	
	//private String receipt;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="re_status_id")
	private ReimbursementStatus status;
	
	@ManyToOne(cascade=CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="employee_id")
	private User employee;
	
	@ManyToOne(cascade=CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="manager_id")
	private User manager;
	
	public Reimbursement() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Reimbursement(int id, ReimbursementType type, double amount, String submitteddate, String resolveddate, String description, /*String receipt,*/ ReimbursementStatus status) {
		super();
		this.id = id;
		this.type = type;
		this.amount = amount;
		this.submitteddate=submitteddate;
		this.resolveddate = resolveddate;
		this.description = description;
		//this.receipt = receipt;
		this.status = status;
	}
	
	public Reimbursement(ReimbursementType type, double amount, String submitteddate, /*String resolveddate,*/ String description, /*String receipt,*/ ReimbursementStatus status, User employee) {
		super();
		this.type = type;
		this.amount = amount;
		this.submitteddate= submitteddate;
		//this.resolveddate = resolveddate;
		this.description = description;
		//this.receipt = receipt;
		this.status = status;
		this.employee=employee;
		//this.managerConnection=manager;
	}
	
	public Reimbursement(int id, ReimbursementType type, double amount, String submitteddate, String resolveddate, String description, ReimbursementStatus status, User employee, User manager) {
		super();
		this.id = id;
		this.type = type;
		this.amount = amount;
		this.submitteddate=submitteddate;
		this.resolveddate = resolveddate;
		this.description = description;
		this.status = status;
		this.employee = employee;
		this.manager=manager;
	}
	

	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public ReimbursementType getType() {
		return type;
	}
	
	public void setType(ReimbursementType type) {
		this.type = type;
	}
	
	public double getAmount() {
		return amount;
	}
	
	public void setAmount(double amount) {
		this.amount = amount;
	}
	
	public ReimbursementStatus getStatus() {
		return status;
	}
	
	public void setStatus(ReimbursementStatus resolved) {
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

	public User getUserConnection() {
		return employee;
	}

	public void setUserConnection(User userConnection) {
		this.employee = userConnection;
	}
	
	

	public String getSubmitteddate() {
		return submitteddate;
	}

	public void setSubmitteddate(String submitteddate) {
		this.submitteddate = submitteddate;
	}

	public String getResolveddate() {
		return resolveddate;
	}

	public void setResolveddate(String resolveddate) {
		this.resolveddate = resolveddate;
	}

	public User getManager() {
		return manager;
	}

	public void setManager(User manager) {
		this.manager = manager;
	}

	@Override
	public String toString() {
		return  "\n" + "Id: " + id + "\n" + "Type: " + type + "\n" + "Amount: " + amount + "\n" + "Submitted Date: " + submitteddate
				+ "\n" + "Resolved Date: " + resolveddate + "\n" + "Description: " + description + "\n" + "Status: " + status
				+ "\n"+ "\n" + "Employee: " + employee + "\n" + "Manager: " + manager + "\n";
	}
	
	
	
	

}
