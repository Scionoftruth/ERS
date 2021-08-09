package com.ers.controllers;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ers.dao.ReimbursementDao;
import com.ers.dao.ReimbursementDaoDB;
import com.ers.dao.StatusDao;
import com.ers.dao.TypeDao;
import com.ers.dao.UserDao;
import com.ers.dao.UserDaoDB;
import com.ers.enums.RType;
import com.ers.enums.Status;
import com.ers.models.Reimbursement;
import com.ers.models.ReimbursementStatus;
import com.ers.models.ReimbursementType;
import com.ers.models.User;
import com.ers.services.ReimbursementService;
import com.ers.services.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class ReimbursementController {

	private static ReimbursementDao rDao = new ReimbursementDaoDB();
	private static ReimbursementService rServ = new ReimbursementService(rDao);
	private static UserDao uDao = new UserDaoDB();
	private static UserService uServ = new UserService(uDao);
	private static StatusDao sDao = new StatusDao();
	private static TypeDao tDao = new TypeDao();
	
	public static void getAllByUser(HttpServletRequest req, HttpServletResponse res) throws JsonProcessingException, IOException{
		
		int id = Integer.parseInt(req.getSession().getAttribute("id").toString());
		User u = uServ.getUserById(id);
		List<Reimbursement> re = rServ.getAllReimbursementsForUser(u);
		System.out.println(re);
		res.addHeader("Access-Control-Allow-Origin", "*");
		res.setHeader("Access-Control-Allow-Methods", "GET");
		res.getWriter().write(new ObjectMapper().writeValueAsString(re));
	
	}
	
	public static void getUserById(HttpServletRequest req, HttpServletResponse res) throws JsonProcessingException, IOException{
		
		int id = Integer.parseInt(req.getSession().getAttribute("id").toString());
		User u = uServ.getUserById(id);
		System.out.println(u);
		res.addHeader("Access-Control-Allow-Origin", "*");
		res.setHeader("Access-Control-Allow-Methods", "GET");
		res.getWriter().write(new ObjectMapper().writeValueAsString(u));
		
	}
	
	public static void addReimbursements(HttpServletRequest req, HttpServletResponse res) throws JsonProcessingException, IOException{
		
		StringBuilder buffer = new StringBuilder();
		BufferedReader reader = req.getReader();
		
		String line;
		while((line = reader.readLine()) != null) {
			buffer.append(line);
			buffer.append(System.lineSeparator());
		}
		String data = buffer.toString();
		System.out.println(data);
		ObjectMapper mapper = new ObjectMapper();
		JsonNode parsedObj = mapper.readTree(data);
		
		String retype = parsedObj.get("type").asText();
		ReimbursementType type = new ReimbursementType();
		switch(retype) {
			case "LODGING":
				type = tDao.getTypeById(6);
				break;
			case "TRAVEL":
				type = tDao.getTypeById(7);
				break;
			case "FOOD":
				type = tDao.getTypeById(8);
				break;
			case "OTHER":
				type = tDao.getTypeById(9);
				break;
		}
		double amount = Double.parseDouble(parsedObj.get("amount").asText());
		String date = parsedObj.get("date").asText();
		//String resolveddate = parsedObj.get("resolveddate").asText();
		String description = parsedObj.get("desc").asText();
		//Status status;
		int userName = Integer.parseInt(parsedObj.get("author").asText());
		User creator = uServ.getUserById(userName);
		ReimbursementStatus status = sDao.getStatusById(3);
		
		rServ.addReimbursement(type, amount, date, description, status, creator);
		
		ObjectNode ret = mapper.createObjectNode();
		ret.put("message", "Successfully Sumbmitted A New Reimbursement Request");
		
		res.getWriter().write(new ObjectMapper().writeValueAsString(ret));
	}
	
	public static void getAllPendingReimbursements(HttpServletRequest req, HttpServletResponse res) throws JsonProcessingException, IOException{
		List<Reimbursement> re = rServ.getAllPendingReimbursements();
		res.addHeader("Access-Control-Allow-Origin", "*");
		res.setHeader("Access-Control-Allow-Methods", "GET");
		res.getWriter().write(new ObjectMapper().writeValueAsString(re));
	}
	
	public static void getAllAcceptedReimbursements(HttpServletRequest req, HttpServletResponse res) throws JsonProcessingException, IOException{
		List<Reimbursement> re = rServ.getAllAcceptedReimbursements();
		res.addHeader("Access-Control-Allow-Origin", "*");
		res.setHeader("Access-Control-Allow-Methods", "GET");
		res.getWriter().write(new ObjectMapper().writeValueAsString(re));
	}
	
	public static void getAllDeniedReimbursements(HttpServletRequest req, HttpServletResponse res) throws JsonProcessingException, IOException{
		List<Reimbursement> re = rServ.getAllDeniedReimbursements();
		res.addHeader("Access-Control-Allow-Origin", "*");
		res.setHeader("Access-Control-Allow-Methods", "GET");
		res.getWriter().write(new ObjectMapper().writeValueAsString(re));
	}
	
	public static void getAllReimbursements(HttpServletRequest req, HttpServletResponse res) throws JsonProcessingException, IOException{
		List<Reimbursement> re = rServ.getAllReimbursements();
		res.addHeader("Access-Control-Allow-Origin", "*");
		res.setHeader("Access-Control-Allow-Methods", "GET");
		res.getWriter().write(new ObjectMapper().writeValueAsString(re));
	}
	
	public static void acceptReimbursement(HttpServletRequest req, HttpServletResponse res) throws JsonProcessingException, IOException {
		StringBuilder buffer = new StringBuilder();
		BufferedReader reader = req.getReader();
		
		String line;
		while((line = reader.readLine()) != null) {
			buffer.append(line);
			buffer.append(System.lineSeparator());
		}
		String data = buffer.toString();
		System.out.println(data);
		ObjectMapper mapper = new ObjectMapper();
		JsonNode parsedObj = mapper.readTree(data);
		
		String today = parsedObj.get("date").asText();
		int rint = Integer.parseInt(parsedObj.get("reid").asText());
		Reimbursement r = rDao.getReimbursementById(rint);
		int managerId = Integer.parseInt(parsedObj.get("userid").asText());
		User manager = uDao.getUserById(managerId);
		ReimbursementStatus rs = sDao.getStatusById(4);
				
		rServ.updateReimbursement(r.getId(), r.getType(), r.getAmount(), r.getSubmitteddate(), today, r.getDescription(), rs, r.getUserConnection(), manager);
	}
	
	public static void denyReimbursement(HttpServletRequest req, HttpServletResponse res) throws JsonProcessingException, IOException {
		StringBuilder buffer = new StringBuilder();
		BufferedReader reader = req.getReader();
		
		String line;
		while((line = reader.readLine()) != null) {
			buffer.append(line);
			buffer.append(System.lineSeparator());
		}
		String data = buffer.toString();
		System.out.println(data);
		ObjectMapper mapper = new ObjectMapper();
		JsonNode parsedObj = mapper.readTree(data);
		
		String today = parsedObj.get("date").asText();
		int rint = Integer.parseInt(parsedObj.get("reid").asText());
		Reimbursement r = rDao.getReimbursementById(rint);
		int managerId = Integer.parseInt(parsedObj.get("userid").asText());
		User manager = uDao.getUserById(managerId);
		ReimbursementStatus status = sDao.getStatusById(5);
				
		rServ.updateReimbursement(r.getId(), r.getType(), r.getAmount(), r.getSubmitteddate(), today, r.getDescription(), status, r.getUserConnection(), manager);
	}
}
