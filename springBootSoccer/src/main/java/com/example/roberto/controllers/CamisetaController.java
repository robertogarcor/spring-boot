package com.example.roberto.controllers;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.example.roberto.beans.Camiseta;
import com.example.roberto.service.ServiceCamiseta;
import com.example.roberto.service.ServiceModel;

public class CamisetaController implements ModelController<Camiseta, ServiceModel<Camiseta>> {

	private static CamisetaController instance = null;
	private ServiceModel<Camiseta> sc;
	
	public static CamisetaController getInstance() {
		if (instance == null) {
			instance = new CamisetaController();
		} 
		return instance;	
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public ServiceModel<Camiseta> getApplication() throws Exception {
		ApplicationContext appContext = new ClassPathXmlApplicationContext("com/example/roberto/xml/beans.xml"); 
		sc = (ServiceModel<Camiseta>) appContext.getBean("serviceCamisetaImpl");
		((ConfigurableApplicationContext) appContext).close();
		return sc;
	}

	@Override
	public List<Camiseta> findAll() throws Exception {
		return sc.findAll();
	}

	@Override
	public void insert(Camiseta entity) throws Exception {
		sc.insert(entity);	
	}

	@Override
	public void delete(int id) throws Exception {
		sc.delete(id);	
	}

	@Override
	public Camiseta findOne(int id) throws Exception {
		return sc.findOne(id);
	}

	@Override
	public void update(Camiseta entity) throws Exception {
		sc.update(entity);
		
	}

}
