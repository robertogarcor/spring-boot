package com.example.roberto.dao;

import java.util.List;

import com.example.roberto.beans.Equipo;

public interface EquipoDAO {
	
	public List<Equipo> findAll() throws Exception;
	
	public Equipo findOne(int id) throws Exception;

	public void insert(Equipo equipo) throws Exception;
	
	public void update(Equipo equipo) throws Exception;
	
	public void delete(int id) throws Exception;
	
}
