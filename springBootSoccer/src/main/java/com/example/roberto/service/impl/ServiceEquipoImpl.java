package com.example.roberto.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.roberto.beans.Equipo;
import com.example.roberto.dao.EquipoDAO;
import com.example.roberto.dao.ModelDAO;
import com.example.roberto.service.ServiceEquipo;
import com.example.roberto.service.ServiceModel;

@Service
public class ServiceEquipoImpl implements ServiceModel<Equipo> {

	@Autowired
	private ModelDAO<Equipo> equipoDAO;
	
	
	@Override
	public List<Equipo> findAll() throws Exception {
		try {
			return equipoDAO.findAll();
		} catch (Exception e) {
			throw e;
		}	
	}
	
	@Override
	public void insert(Equipo entity) throws Exception {
		try {
			equipoDAO.insert(entity);			
		} catch (Exception e) {
			throw e;
		}	
	}

	@Override
	public void delete(int id) throws Exception {
		try {
			equipoDAO.delete(id);
		} catch (Exception e) {
			throw e;
		}	
	}

	@Override
	public void update(Equipo entity) throws Exception {
		try {
			equipoDAO.update(entity);
		} catch (Exception e) {
			throw e;
		}	
	}

	@Override
	public Equipo findOne(int id) throws Exception {
		try {	
			return equipoDAO.findOne(id);
		} catch (Exception e) {
			throw e;
		}
	}
	

}
