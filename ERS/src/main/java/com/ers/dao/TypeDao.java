package com.ers.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.ers.models.ReimbursementStatus;
import com.ers.models.ReimbursementType;
import com.ers.util.HibernateUtil;

public class TypeDao {
	public void insert(ReimbursementType t) {
		Session ses = HibernateUtil.getSession();
		Transaction tx = ses.beginTransaction();
		ses.save(t);
		tx.commit();
	}
	
	public ReimbursementType getTypeById(int id) {
		Session ses = HibernateUtil.getSession();
		ReimbursementType t = ses.createQuery("from ReimbursementType where id ="+id,ReimbursementType.class).uniqueResult();
		return t;
	}
}
