package com.ers.services;

import com.ers.dao.UserDao;
import com.ers.enums.UserRole;
import com.ers.exceptions.InvalidCredentialsException;
import com.ers.exceptions.UserDoesNotExistException;
import com.ers.exceptions.UsernameAlreadyExistsException;
import com.ers.models.User;
import com.ers.models.UserRoles;
import com.ers.logging.Logging;

public class UserService {
	
	private UserDao uDao;
	
	public UserService(UserDao u) {
		this.uDao = u;
	}
	
	/*
	public User signUp(String firstname, String lastname, String username, String email, String password, UserRoles role) {
		User u = new User(username, password, firstname, lastname, email, role);
		uDao.createUser(u);
		Logging.logger.info("New User Has Registered");
		
		u = uDao.getUserByUsername(u.getUsername());
		
		if(u == null) {
			Logging.logger.warn("Username created that already exists in the database");
			throw new UsernameAlreadyExistsException();
		}
		
		return u;
	}*/
	
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
	
	public User getUserByUsername(String username) {
		return uDao.getUserByUsername(username);
	}
	
	public User getUserById(int id) {
		return uDao.getUserById(id);
	}
}
