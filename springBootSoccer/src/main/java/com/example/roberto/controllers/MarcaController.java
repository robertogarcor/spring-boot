package com.example.roberto.controllers;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;

import com.example.roberto.beans.Marca;
import com.example.roberto.service.ServiceMarca;
import com.example.roberto.service.ServiceModel;


@Controller
public class MarcaController implements ModelController<Marca, ServiceModel<Marca>> {
	
	private static MarcaController instance = null;
	private ServiceModel<Marca> sm;
	
	
	public static MarcaController getInstance() {
		if (instance == null) {
			instance = new MarcaController();
		} 
		return instance;	
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public ServiceModel<Marca> getApplication() throws Exception {
		ApplicationContext appContext = new ClassPathXmlApplicationContext("com/example/roberto/xml/beans.xml"); 
		sm = (ServiceModel<Marca>) appContext.getBean("serviceMarcaImpl");	
		((ConfigurableApplicationContext) appContext).close();
		return sm;
	}
	
	
	@Override
	public List<Marca> findAll() throws Exception {
		return sm.findAll();
	}
	
	
	@Override
	public void insert(Marca entity) throws Exception {
		sm.insert(entity);	
	}


	@Override
	public void delete(int id) throws Exception {
		sm.delete(id);
		
	}

	@Override
	public Marca findOne(int id) throws Exception {
		return sm.findOne(id);
	}

	@Override
	public void update(Marca entity) throws Exception {
		sm.update(entity);
		
	}

}