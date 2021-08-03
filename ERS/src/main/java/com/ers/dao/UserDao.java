package com.ers.dao;

import java.util.List;

import com.ers.models.User;

public interface UserDao {
	
	List<User> getAllUsers();
	
	User getUserByUsername(String username);
	
	User getUserById(int id);
	
	void createUser(User u) /*throws SQLException*/;
	
	void updateUser(User u);
	
	void deleteUser(User u);
}
