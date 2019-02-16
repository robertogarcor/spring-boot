package com.example.roberto.controllers;

import java.util.List;

/**
 * 
 * @author Roberto
 *
 * Interface Controller ModelController Generic 
 * @param <T> entity Type Object
 * @param <U> entity Type Object ServiceModel<T>
 */
public interface ModelController<T, U> {
	
	/**
	 * Get Context Applicattion Object Service
	 * @return Type Object ServiceModel<T>
	 * @throws Exception
	 */
	public U getApplication() throws Exception; 
	
	/**
	 * Get all objects by entity
	 * @return list<Type> entity
	 * @throws Exception
	 */
	public List<T> findAll() throws Exception;
	
	/**
	 * Get one object by entity
	 * @param id entity Type to search
	 * @return Type entity
	 * @throws Exception
	 */
	public T findOne(int id) throws Exception;

	/**
	 * Insert a Type entity
	 * @param entity Type to insert
	 * @throws Exception
	 */
	public void insert(T entity) throws Exception;
	
	/**
	 * Update a Type entity
	 * @param entity Type to upate
	 * @throws Exception
	 */
	public void update(T entity) throws Exception;
	
	/**
	 * Delete a Type entity
	 * @param id by entity to delete
	 * @throws Exception
	 */
	public void delete(int id) throws Exception;

}
