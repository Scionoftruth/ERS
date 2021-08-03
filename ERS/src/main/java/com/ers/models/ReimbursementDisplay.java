package com.ers.models;

import com.ers.enums.RType;
import com.ers.enums.Status;

public class ReimbursementDisplay {
	
	private String username;
	private int id;
	private RType type;
	private double amount;
	private String resolveddate;
	private String description;
	private Status status;
	
	public ReimbursementDisplay() {
		super();
	}
	
	public ReimbursementDisplay(String username, int id, RType type, double amount, String resolveddate, String description, Status status) {
		super();
		this.username = username;
		this.id = id;
		this.type = type;
		this.amount = amount;
		this.resolveddate = resolveddate;
		this.description = description;
		this.status = status;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
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

	public String getResolveddate() {
		return resolveddate;
	}

	public void setResolveddate(String resolveddate) {
		this.resolveddate = resolveddate;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "ReimbursementDisplay [username=" + username + ", id=" + id + ", type=" + type + ", amount=" + amount
				+ ", resolveddate=" + resolveddate + ", description=" + description + ", status=" + status + "]";
	}

}
