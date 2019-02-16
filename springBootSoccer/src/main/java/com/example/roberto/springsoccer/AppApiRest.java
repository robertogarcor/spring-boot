package com.example.roberto.springsoccer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan(basePackages = "com.example.roberto")
//@ComponentScan(basePackages = "com.example.roberto.api.controllers, com.example.roberto.service, com.example.roberto.dao")
public class AppApiRest {
	
	/**
	 * Run Aplication Spring Boot soccer
	 * @param args request
	 */
	public static void main(String[] args) {
		SpringApplication.run(AppApiRest.class, args);	
	}

}
