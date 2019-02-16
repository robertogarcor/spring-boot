package com.example.roberto.service;

import java.util.List;

import com.example.roberto.beans.Marca;

public interface ServiceMarca {
	
	public List<Marca> findAll() throws Exception;
	
	public Marca findOne(int id) throws Exception;
	
	public void insert(Marca marca) throws Exception;
	
	public void update(Marca marca) throws Exception;
	
	public void delete(int id) throws Exception;
	
}
