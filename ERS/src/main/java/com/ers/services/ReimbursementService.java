package com.ers.services;

import java.util.List;

import com.ers.dao.ReimbursementDao;
import com.ers.enums.RType;
import com.ers.enums.Status;
import com.ers.models.Reimbursement;
import com.ers.models.ReimbursementStatus;
import com.ers.models.ReimbursementType;
import com.ers.models.User;

public class ReimbursementService {

	private ReimbursementDao rDao;
	
	public ReimbursementService(ReimbursementDao r) {
		this.rDao = r;
	}
	
	public void addReimbursement(ReimbursementType type, double amount, String submitteddate, /*String resolveddate,*/ String description, ReimbursementStatus status, User employeeid) {
		Reimbursement r = new Reimbursement(type, amount, submitteddate, /*resolveddate,*/ description, status, employeeid);
		rDao.createReimbursement(r);
	}
	
	public List<Reimbursement> getAllReimbursements(){
		return rDao.getAllReimbursments();
	}
	
	public void updateReimbursement(int id, ReimbursementType type, double amount, String submitteddate, String resolveddate, String description, ReimbursementStatus status, User employeeid, User manager) {
		//Reimbursement r = new Reimbursement(id, type, amount, resolveddate, description, status);
		rDao.updateReimbursementByArgs(id, type, amount, submitteddate, resolveddate, description, status, employeeid, manager);
	}
	
	public void deleteReimbursement(int id){
		rDao.deleteReimbursementById(id);
	}
	
	public List<Reimbursement> getAllPendingReimbursements(){
		return rDao.getAllPendingReimbursments();
	}
	
	public List<Reimbursement> getAllAcceptedReimbursements(){
		return rDao.getAllAcceptedReimbursments();
	}
	
	public List<Reimbursement> getAllDeniedReimbursements(){
		return rDao.getAllDeniedReimbursments();
	}
	
	
	//public Reimbursement getReimbursementById(int id) {
	//	rDao.getReimbursementById(id);
	//}
	
}
