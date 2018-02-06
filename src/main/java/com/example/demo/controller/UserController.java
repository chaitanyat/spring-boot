package com.example.demo.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.UserDto;
import com.example.demo.model.SessionKey;
import com.example.demo.model.User;


@RestController
public class UserController {
	@PersistenceContext
    private EntityManager entityManager;
	
	@RequestMapping(value="/users", method = RequestMethod.POST, consumes = "application/json")
    @ResponseBody
	public Map<String, Object> getAll(@RequestBody SessionKey sessionKey, HttpServletRequest request) {
		HttpSession session = request.getSession();
		TypedQuery<User> query = entityManager.createNamedQuery("User.findAll", User.class);
		List<User> results = query.getResultList();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("success", "true");
		map.put("SESSIONKEY", sessionKey.getSESSIONKEY());
		map.put("data", results);
		return map;
	}
	
	@RequestMapping(value="/createUser", method = RequestMethod.POST, consumes = "application/json")
    @ResponseBody
	public Map<String, Object> createUser(@RequestBody UserDto requestData, HttpServletRequest request) {
		HttpSession session = request.getSession();
		JSONParser parser = new JSONParser();
		/*try {
			JSONObject requestJSON = (JSONObject) parser.parse(requestData);
			JSONObject userDataJSON = (JSONObject) requestJSON.get("data");
			User user = (User) requestJSON.get("data");
			String sessionKey = (String) requestJSON.get("SESSIONKEY");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("success", "true");
		map.put("SESSIONKEY", session.getId());
		//map.put("data", results);
		return map;
	}
}
