package com.example.roberto.dao;

import java.util.List;

import com.example.roberto.beans.Camiseta;


public interface CamisetaDAO {
	
	public List<Camiseta> findAll() throws Exception;
	
	public Camiseta findOne(int id) throws Exception;

	public void insert(Camiseta camiseta) throws Exception;
	
	public void update(Camiseta camiseta) throws Exception;
	
	public void delete(int id) throws Exception;
	
}
