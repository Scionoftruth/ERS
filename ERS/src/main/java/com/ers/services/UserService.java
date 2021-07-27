package com.ers.services;

import java.sql.SQLException;

import com.ers.dao.UserDao;
import com.ers.exceptions.InvalidCredentialsException;
import com.ers.exceptions.UserDoesNotExistException;
import com.ers.exceptions.UsernameAlreadyExistsException;
import com.ers.models.User;
import com.ers.models.UserRole;
import com.ers.logging.Logging;

public class UserService {
	
	private UserDao uDao;
	
	public UserService(UserDao u) {
		this.uDao = u;
	}
	
	public User signUp(String username, String password, String firstname, String lastname, String email, UserRole role) {
		User u = new User(username, password, firstname, lastname, email, role);
		
		try {
			uDao.createUser(u);
			Logging.logger.info("New User Has Registered");
		}catch(SQLException e){
			e.printStackTrace();
		}
		
		u = uDao.getUserByUsername(u.getUsername());
		
		if(u == null) {
			Logging.logger.warn("Username created that already exists in the database");
			throw new UsernameAlreadyExistsException();
		}
		
		return u;
	}
	
	public User signIn(String username, String password) throws UserDoesNotExistException, InvalidCredentialsException{
		
		User u = uDao.getUserByUsername(username);
		
		if(u.getId() == 0) {
			throw new UserDoesNotExistException();
		}else if(!u.getPassword().equals(password)) {
			throw new InvalidCredentialsException();
		}else {
			return u;
		}
	}
	
}
