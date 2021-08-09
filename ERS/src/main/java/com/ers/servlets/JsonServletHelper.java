package com.ers.servlets;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ers.controllers.LoginController;
import com.ers.controllers.LogoutController;
import com.ers.controllers.RegistrationController;
import com.ers.controllers.ReimbursementController;
import com.ers.controllers.SessionController;
import com.fasterxml.jackson.core.JsonProcessingException;

public class JsonServletHelper {
	
	public static void process(HttpServletRequest req, HttpServletResponse res) throws IOException, JsonProcessingException{
		System.out.println("In the JSONServlet Helper with URI: "+req.getRequestURI());
		switch(req.getRequestURI()) {
			case "/ERS/api/login":
				LoginController.login(req,res);
				break;
			case "/ERS/api/logout":
				LogoutController.logout(req, res);
				break;
			//case "/ERS/api/register":
			//	RegistrationController.register(req, res);
			//	break;
			case "/ERS/api/newreimbursement":
				ReimbursementController.addReimbursements(req, res);
				break;
			case "/ERS/api/getSession":
				SessionController.getSession(req, res);
				break;
			case "/ERS/api/getAllById":
				ReimbursementController.getAllByUser(req, res);
				break;
			case "/ERS/api/getUser":
				ReimbursementController.getUserById(req, res);
				break;
			case "/ERS/api/getAllPending":
				ReimbursementController.getAllPendingReimbursements(req, res);
				break;
			case "/ERS/api/getAllAccepted":
				ReimbursementController.getAllAcceptedReimbursements(req, res);
				break;
			case "/ERS/api/getAllDenied":
				ReimbursementController.getAllDeniedReimbursements(req, res);
				break;
			case "/ERS/api/approveReimbursement":
				ReimbursementController.acceptReimbursement(req, res);
				break;
			case "/ERS/api/denyReimbursement":
				ReimbursementController.denyReimbursement(req, res);
				break;
			case "/ERS/api/getAllReimbursements":
				ReimbursementController.getAllReimbursements(req, res);
		}
	}
	
}
