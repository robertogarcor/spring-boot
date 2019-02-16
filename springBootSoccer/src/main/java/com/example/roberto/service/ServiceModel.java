package com.example.roberto.service;

import java.util.List;

/**
 *  
 * @author Roberto
 * 
 * Interface Service ServiceModel Generic 
 * @param <T> entity Type Object
 */
public interface ServiceModel<T> {
	
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
	 * @param entity Type to update
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
