package com.example.roberto.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.roberto.beans.Marca;
import com.example.roberto.dao.MarcaDAO;
import com.example.roberto.dao.ModelDAO;
import com.example.roberto.service.ServiceMarca;
import com.example.roberto.service.ServiceModel;

@Service
public class ServiceMarcaImpl implements ServiceModel<Marca> {

	@Autowired
	private ModelDAO<Marca> marcaDAO;
	
	
	@Override
	public List<Marca> findAll() throws Exception {
		try {
			return marcaDAO.findAll();
		} catch (Exception e) {
			throw e;
		}	
	}
	
	@Override
	public void insert(Marca entity) throws Exception {
		try {
			marcaDAO.insert(entity);			
		} catch (Exception e) {
			throw e;
		}	
	}

	@Override
	public void delete(int id) throws Exception {
		try {
			marcaDAO.delete(id);
		} catch (Exception e) {
			throw e;
		}	
	}

	@Override
	public void update(Marca entity) throws Exception {
		try {
			marcaDAO.update(entity);
		} catch (Exception e) {
			throw e;
		}	
	}

	@Override
	public Marca findOne(int id) throws Exception {
		try {
			return marcaDAO.findOne(id);
		} catch (Exception e) {
			throw e;
		}
	}
	

}
