package com.ers.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import com.ers.controllers.LoginViewController;
import com.ers.controllers.RegistrationViewController;


public class RequestViewHelper {

	public static String process(HttpServletRequest req) throws IOException, ServletException{
		System.out.println("In the view dispatcher process method, with URI:");
		switch(req.getRequestURI()) {
			case "/ERS/login":
				return LoginViewController.fetchLoginPage(req);
			case "ERS/register":
				return RegistrationViewController.fetchRegistrationPage(req);
			default:
				return "/ERS/login";
		}
	}
	
}
