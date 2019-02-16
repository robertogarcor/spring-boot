package com.example.roberto.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.roberto.beans.Camiseta;
import com.example.roberto.dao.CamisetaDAO;
import com.example.roberto.dao.ModelDAO;
import com.example.roberto.service.ServiceCamiseta;
import com.example.roberto.service.ServiceModel;

@Service
public class ServiceCamisetaImpl implements ServiceModel<Camiseta> {

	@Autowired
	private ModelDAO<Camiseta> camisetaDAO;

	
	@Override
	public List<Camiseta> findAll() throws Exception {
		try {	
			return camisetaDAO.findAll();
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	public void insert(Camiseta entity) throws Exception {
		try {	
			camisetaDAO.insert(entity);
		} catch (Exception e) {
			throw e;
		}	
	}

	@Override
	public void delete(int id) throws Exception {
		try {	
			camisetaDAO.delete(id);
		} catch (Exception e) {
			throw e;
		}	
	}

	@Override
	public void update(Camiseta entity) throws Exception {
		try {
			camisetaDAO.update(entity);
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	public Camiseta findOne(int id) throws Exception {
		try {	
			return camisetaDAO.findOne(id);
		} catch (Exception e) {
			throw e;
		}
	}

	

}
