package com.ers.controllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

public class LoginViewController {
	
	public static String fetchLoginPage(HttpServletRequest req) throws IOException, ServletException{
		System.out.println("This Should Return The View login.html");
		return "resources/html/login.html";
	}
	
}
