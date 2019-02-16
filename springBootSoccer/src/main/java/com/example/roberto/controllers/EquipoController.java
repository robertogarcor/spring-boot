package com.example.roberto.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;

import com.example.roberto.beans.Equipo;
import com.example.roberto.service.ServiceEquipo;
import com.example.roberto.service.ServiceModel;


@Controller
public class EquipoController implements ModelController<Equipo, ServiceModel<Equipo>> {
	
	private static EquipoController instance = null;
	private ServiceModel<Equipo> se;
	
	
	public static EquipoController getInstance() {
		if (instance == null) {
			instance = new EquipoController();
		} 
		return instance;	
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public ServiceModel<Equipo> getApplication() throws Exception {
		ApplicationContext appContext = new ClassPathXmlApplicationContext("com/example/roberto/xml/beans.xml"); 
		se = (ServiceModel<Equipo>) appContext.getBean("serviceEquipoImpl");	
		((ConfigurableApplicationContext) appContext).close();
		return se;
	}
	
	@Override
	public List<Equipo> findAll() throws Exception {
		return se.findAll();
	}
	
	
	@Override
	public void insert(Equipo entity) throws Exception {
		se.insert(entity);	
	}


	@Override
	public void delete(int id) throws Exception {
		se.delete(id);
		
	}

	@Override
	public Equipo findOne(int id) throws Exception {
		return se.findOne(id);
	}

	@Override
	public void update(Equipo entity) throws Exception {
		se.update(entity);
		
	}
	
}
