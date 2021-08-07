package com.ers.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Transaction;
import org.hibernate.Session;


import com.ers.models.Reimbursement;
import com.ers.models.ReimbursementStatus;
import com.ers.models.ReimbursementType;
import com.ers.models.User;
import com.ers.util.HibernateUtil;

public class ReimbursementDaoDB implements ReimbursementDao{

	@Override
	public List<Reimbursement> getAllReimbursments() {
		Session ses = HibernateUtil.getSession();
		List<Reimbursement> rList = ses.createQuery("from Reimbursement", Reimbursement.class).list();
		return rList;
	}

	@Override
	public Reimbursement getReimbursementById(int id) {
		Session ses = HibernateUtil.getSession();
		Reimbursement r = ses.get(Reimbursement.class, id);
		return r;
	}

	@Override
	public void createReimbursement(Reimbursement r) {
		Session ses = HibernateUtil.getSession();
		Transaction tx = ses.beginTransaction();
		ses.save(r);
		tx.commit();
	}

	@Override
	public void updateReimbursement(Reimbursement r) {
		Session ses = HibernateUtil.getSession();
		Transaction tx = ses.beginTransaction();
		ses.merge(r);
		tx.commit();
	}

	@Override
	public void deleteReimbursement(Reimbursement r) {
		Session ses = HibernateUtil.getSession();
		Transaction tx = ses.beginTransaction();
		ses.delete(r);
		tx.commit();
	}

	@Override
	public void deleteReimbursementById(int id) {
		Session ses = HibernateUtil.getSession();
		Reimbursement r = ses.get(Reimbursement.class, id);
		Transaction tx = ses.beginTransaction();
		ses.delete(r);
		tx.commit();
	}

	@Override
	public void updateReimbursementByArgs(int id, ReimbursementType type, double amount, String submitteddate, String resolveddate, String description, ReimbursementStatus status, User employeeId, User manager) {
		Session ses = HibernateUtil.getSession();
		Reimbursement r = new Reimbursement(id, type, amount, submitteddate, resolveddate, description, status, employeeId, manager);
		Transaction tx = ses.beginTransaction();
		ses.merge(r);
		tx.commit();
	}
	
	@Override
	public List<Reimbursement> getAllPendingReimbursments() {
		Session ses = HibernateUtil.getSession();
		List<Reimbursement> rList = ses.createQuery("from Reimbursement where re_status_id = 3", Reimbursement.class).list();
		return rList;
	}

	@Override
	public List<Reimbursement> getAllAcceptedReimbursments() {
		Session ses = HibernateUtil.getSession();
		List<Reimbursement> rList = ses.createQuery("from Reimbursement where re_status_id = 4", Reimbursement.class).list();
		return rList;
	}
	
	@Override
	public List<Reimbursement> getAllDeniedReimbursments() {
		Session ses = HibernateUtil.getSession();
		List<Reimbursement> rList = ses.createQuery("from Reimbursement where re_status_id = 5", Reimbursement.class).list();
		return rList;
	}
	
	@Override
	public List<Reimbursement> getAllPendingReimbursmentsForUser(User u) {
		Session ses = HibernateUtil.getSession();
		List<Reimbursement> rList = ses.createQuery("from Reimbursement where re_status_id = 3", Reimbursement.class).list();
		List<Reimbursement> result = new ArrayList<Reimbursement>();
		for(Reimbursement curr:rList) {
			if(curr.getUserConnection()==u) {
				result.add(curr);
			}
		}
		return result;
	}
	
	@Override
	public List<Reimbursement> getAllAcceptedReimbursmentsForUser(User u) {
		Session ses = HibernateUtil.getSession();
		List<Reimbursement> rList = ses.createQuery("from Reimbursement where re_status_id = 4", Reimbursement.class).list();
		List<Reimbursement> result = new ArrayList<Reimbursement>();
		for(Reimbursement curr:rList) {
			if(curr.getUserConnection()==u) {
				result.add(curr);
			}
		}
		return result;
	}
	
	@Override
	public List<Reimbursement> getAllDeniedReimbursmentsForUser(User u) {
		Session ses = HibernateUtil.getSession();
		List<Reimbursement> rList = ses.createQuery("from Reimbursement where re_status_id = 5", Reimbursement.class).list();
		List<Reimbursement> result = new ArrayList<Reimbursement>();
		for(Reimbursement curr:rList) {
			if(curr.getUserConnection()==u) {
				result.add(curr);
			}
		}
		return result;
	}
}
