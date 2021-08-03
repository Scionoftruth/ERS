package com.ers.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.ers.models.ReimbursementStatus;
import com.ers.util.HibernateUtil;

public class StatusDao {
	public void insert(ReimbursementStatus s) {
		Session ses = HibernateUtil.getSession();
		Transaction tx = ses.beginTransaction();
		ses.save(s);
		tx.commit();
	}
	
	public ReimbursementStatus getStatusById(int id) {
		Session ses = HibernateUtil.getSession();
		ReimbursementStatus s = ses.createQuery("from ReimbursementStatus where id ="+id,ReimbursementStatus.class).uniqueResult();
		return s;
	}
}
