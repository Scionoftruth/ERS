package com.ers.controllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

public class RegistrationViewController {

	public static String fetchRegistrationPage(HttpServletRequest req) throws IOException, ServletException{
		System.out.println("This Should Return The View registration.html");
		return "resources/html/registration.html";
	}
	
}
