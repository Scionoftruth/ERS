package com.ers.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.ers.models.UserRoles;
import com.ers.util.HibernateUtil;

public class RoleDao {
	public void insert(UserRoles r) {
		Session ses = HibernateUtil.getSession();
		Transaction tx = ses.beginTransaction();
		ses.save(r);
		tx.commit();
	}
	
	public UserRoles getUserRoleById(int id) {
		Session ses = HibernateUtil.getSession();
		UserRoles u = ses.createQuery("from UserRoles where id ="+id,UserRoles.class).uniqueResult();
		return u;
	}
}
