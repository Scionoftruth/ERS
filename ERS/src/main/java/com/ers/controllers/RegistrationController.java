package com.ers.controllers;

import java.io.BufferedReader;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ers.dao.RoleDao;
import com.ers.dao.UserDao;
import com.ers.dao.UserDaoDB;
import com.ers.enums.UserRole;
import com.ers.exceptions.EmailAlreadyRegisteredException;
import com.ers.exceptions.UsernameAlreadyExistsException;
import com.ers.models.User;
import com.ers.models.UserRoles;
import com.ers.services.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class RegistrationController {
	
	/*private static UserDao uDao = new UserDaoDB();
	private static UserService uServ = new UserService(uDao);
	private static RoleDao rDao = new RoleDao();
	
	public static void register(HttpServletRequest req, HttpServletResponse res) throws JsonProcessingException, IOException{
		
		StringBuilder buffer = new StringBuilder();
		BufferedReader reader = req.getReader();
		
		String line;
		while((line = reader.readLine()) != null) {
			buffer.append(line);
			buffer.append(System.lineSeparator());
		}
		
		String data = buffer.toString();
		System.out.println(data);
		
		ObjectMapper mapper= new ObjectMapper();
		JsonNode parsedObj = mapper.readTree(data);
		
		String firstname = parsedObj.get("firstname").asText();
		String lastname = parsedObj.get("lastname").asText();
		String username = parsedObj.get("username").asText();
		String email = parsedObj.get("email").asText();
		String password = parsedObj.get("password").asText();
		UserRoles role = new UserRoles();
		role.setU_role(UserRole.EMPLOYEE);
		
		try {
			System.out.println("In The Registration Attempt");
			User u = uServ.signUp(firstname, lastname, username, email, password, role);
			System.out.println(u);
			
			req.getSession().setAttribute("id", u.getId());
			res.setStatus(HttpServletResponse.SC_OK);
			res.getWriter().write(new ObjectMapper().writeValueAsString(u));
		}catch(UsernameAlreadyExistsException e) {
			res.getWriter().println("That Username Already Exists");
		}catch(EmailAlreadyRegisteredException e) {
			res.getWriter().println("That Email Has Already Been Registered");
		}
		
	}*/
}
