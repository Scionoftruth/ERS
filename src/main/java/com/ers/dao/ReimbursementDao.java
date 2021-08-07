package com.ers.dao;

import java.util.List;

import com.ers.enums.RType;
import com.ers.enums.Status;
import com.ers.models.Reimbursement;
import com.ers.models.ReimbursementStatus;
import com.ers.models.ReimbursementType;
import com.ers.models.User;

public interface ReimbursementDao {
	
	List<Reimbursement> getAllReimbursments();
	
	Reimbursement getReimbursementById(int id);
	
	void createReimbursement(Reimbursement r);

	void updateReimbursement(Reimbursement r);
	
	void deleteReimbursement(Reimbursement r);
	
	void deleteReimbursementById(int id);
	
	void updateReimbursementByArgs(int id, ReimbursementType type, double amount, String submitteddate, String resolveddate, String description, ReimbursementStatus status, User employeeId, User Manager);
	
	List<Reimbursement> getAllPendingReimbursments();
	
	List<Reimbursement> getAllAcceptedReimbursments();
	
	List<Reimbursement> getAllDeniedReimbursments();
	
	List<Reimbursement> getAllPendingReimbursmentsForUser(User u);
	
	List<Reimbursement> getAllAcceptedReimbursmentsForUser(User u);
	
	List<Reimbursement> getAllDeniedReimbursmentsForUser(User u);
}
