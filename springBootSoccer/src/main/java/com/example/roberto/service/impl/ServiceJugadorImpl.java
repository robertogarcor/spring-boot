package com.example.roberto.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.roberto.beans.Jugador;
import com.example.roberto.dao.JugadorDAO;
import com.example.roberto.dao.ModelDAO;
import com.example.roberto.service.ServiceJugador;
import com.example.roberto.service.ServiceModel;


@Service
public class ServiceJugadorImpl implements ServiceModel<Jugador> {

	@Autowired
	private ModelDAO<Jugador> jugadorDAO;
	

	@Override
	public List<Jugador> findAll() throws Exception {
		try {
			return jugadorDAO.findAll();
		} catch (Exception e) {
			throw e;
		}
	}
	
	@Override
	public void insert(Jugador entity) throws Exception {
		try {
			jugadorDAO.insert(entity);
		} catch (Exception e) {
			throw e;
		}	
	}
	
	@Override
	public void delete(int id) throws Exception {
		try {
			jugadorDAO.delete(id);
		} catch (Exception e) {
			throw e;
		}	
	}
	
	@Override
	public void update(Jugador entity) throws Exception {
		try {
			jugadorDAO.update(entity);
		} catch (Exception e) {
			throw e;
		}		
	}
	
	@Override
	public Jugador findOne(int id) throws Exception {
		try {	
			return jugadorDAO.findOne(id);
		} catch (Exception e) {
			throw e;
		}
	}
	
	
	

	

}
