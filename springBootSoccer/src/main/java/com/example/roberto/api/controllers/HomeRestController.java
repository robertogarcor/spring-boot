package com.example.roberto.api.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.roberto.beans.Equipo;
import com.example.roberto.service.ServiceModel;
import com.google.gson.JsonObject;


@RestController
public class HomeRestController {

	
	@Autowired
	private ServiceModel<Equipo> se;
	
	
	@RequestMapping("/")
	public ResponseEntity<String> welcome() throws Exception {
		JsonObject json = new JsonObject();
		json.addProperty("message","Hello!, Welcome to Spring Boot Soccer.");
		return new ResponseEntity<String>(json.toString(), HttpStatus.OK);
	}
	
	
	

	

	
	
}
