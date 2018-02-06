package com.example.demo.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Login;
import com.example.demo.model.SessionKey;
import com.example.demo.model.User;

@RestController
public class LoginController {
	@PersistenceContext
    private EntityManager entityManager;

	@RequestMapping(value="/logon", method = RequestMethod.POST, consumes = "application/json")
    @ResponseBody
	public Map<String, Object> login(@RequestBody Login requestStr, HttpServletRequest request) {
		Map<String, Object> response = new HashMap<String, Object>();
		String username = requestStr.getMail_id();
		String password = requestStr.getPassword();
		TypedQuery<User> query = entityManager.createNamedQuery("User.findByMailId", User.class).setParameter("mail_id", username);
		try{
			User user = query.getSingleResult();
			if(user.getPassword().equals(password)) {
				HttpSession session = request.getSession();
				response.put("SESSIONKEY", session.getId());
				response.put("success", true);
				response.put("data", user);
			} else {
				response.put("message", "Invalid Username or Password!");
			}
		}catch (NoResultException nre){
			//Ignore this because as per your logic this is ok!
			response.put("message", "Invalid Username or Password!");
		}
		return response;		
	}
	
	@RequestMapping(value="/logout", method = RequestMethod.POST, consumes = "application/json")
    @ResponseBody
	public Map<String, Object> logout(@RequestBody SessionKey requestStr, HttpServletRequest request) {
		HttpSession session = request.getSession();
		session.invalidate();
		Map<String, Object> response = new HashMap<String, Object>();
		String a = "success", b="true";
		response.put(a, b);
		
		return response;		
	}
}
