package com.example.roberto.controllers;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;

import com.example.roberto.beans.Jugador;
import com.example.roberto.service.ServiceJugador;
import com.example.roberto.service.ServiceModel;

@Controller
public class JugadorController implements ModelController<Jugador, ServiceModel<Jugador>> {

	private static JugadorController instance = null;
	private ServiceModel<Jugador> sj;
		
	public static JugadorController getInstance() {
		if (instance == null) {
			instance = new JugadorController();
		} 
		return instance;	
	}
	
	
	@SuppressWarnings("unchecked")
	@Override
	public ServiceModel<Jugador> getApplication() throws Exception {
		ApplicationContext appContext = new ClassPathXmlApplicationContext("com/example/roberto/xml/beans.xml"); 
		sj = (ServiceModel<Jugador>) appContext.getBean("serviceJugadorImpl");
		((ConfigurableApplicationContext) appContext).close();
		return sj;
	}


	@Override
	public List<Jugador> findAll() throws Exception {
		return sj.findAll();
	}


	@Override
	public void insert(Jugador entity) throws Exception {
		sj.insert(entity);
		
	}


	@Override
	public void delete(int id) throws Exception {
		sj.delete(id);
		
	}


	@Override
	public Jugador findOne(int id) throws Exception {
		return sj.findOne(id);
	}


	@Override
	public void update(Jugador entity) throws Exception {
		sj.update(entity);
		
	}
		
}



