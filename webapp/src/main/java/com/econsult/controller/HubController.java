package com.econsult.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.econsult.model.Query;
import com.econsult.model.User;
import com.econsult.service.QueryService;

@RestController
@RequestMapping("/econsult")
public class HubController {
	
	@Autowired
	private QueryService hubService;
	
	@RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
	public User getUser(@PathVariable("id") int userId){
		return null;
	}
	
	@RequestMapping(value = "/Query/{id}", method = RequestMethod.GET)
	public Query getQuery(@PathVariable("id") long Id){
		return hubService.getQuery(Id);
	}
}
