package com.ers.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.ers.models.User;
import com.ers.util.HibernateUtil;

public class UserDaoDB implements UserDao{

	@Override
	public List<User> getAllUsers() {
		Session ses = HibernateUtil.getSession();
		List<User> uList = ses.createQuery("from User", User.class).list();
		return uList;
	}

	@Override
	public User getUserByUsername(String username) {
		Session ses = HibernateUtil.getSession();
		List<User> uList = ses.createQuery("from User where username='"+username+"'", User.class).list();
		if(uList.size() == 0) {
			return null;
		}
		return uList.get(0);
	}
	
	@Override
	public User getUserById(int id) {
		Session ses = HibernateUtil.getSession();
		User u = ses.createQuery("from User where id ="+id,User.class).uniqueResult();
		return u;
	}

	@Override
	public void createUser(User u) {
		Session ses = HibernateUtil.getSession();
		Transaction tx = ses.beginTransaction();
		ses.save(u);
		tx.commit();
	}

	@Override
	public void updateUser(User u) {
		Session ses = HibernateUtil.getSession();
		Transaction tx = ses.beginTransaction();
		ses.merge(u);
		tx.commit();
	}

	@Override
	public void deleteUser(User u) {
		Session ses = HibernateUtil.getSession();
		Transaction tx = ses.beginTransaction();
		ses.delete(u);
		tx.commit();
	}
	
	

}
