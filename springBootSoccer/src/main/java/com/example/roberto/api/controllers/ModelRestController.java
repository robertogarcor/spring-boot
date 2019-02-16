package com.example.roberto.api.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;

/**
 * 
 * @author Roberto
 *
 * Interface API RestController ModelRestController Generic 
 * @param <T>
 * @param <S>
 */
public interface ModelRestController<T, S> {
	
	/**
	 * Get all objects by entity
	 * @return response list<Type> entity 
	 * @throws Exception
	 */
	public ResponseEntity<List<T>> findAll() throws Exception;
	
	/**
	 * Get one object by entity
	 * @param id entity Type to search
	 * @return response Type entity
	 * @throws Exception
	 */
	public ResponseEntity<T> findId(int id) throws Exception;
	
	/**
	 * Insert a Type entity
	 * @param entity Type to insert
	 * @return reponse String message
	 * @throws Exception
	 */
	public ResponseEntity<S> insert(T entity) throws Exception;
	
	/**
	 * Update a Type entity
	 * @param entity Type to update
	 * @param id by entity to update
	 * @return reponse String message
	 * @throws Exception
	 */
	public ResponseEntity<S> update(T entity, int id) throws Exception;
	
	/**
	 * Delete a Type entity
	 * @param id by entity to delete
	 * @return reponse String message
	 * @throws Exception
	 */
	public ResponseEntity<S> delete(int id) throws Exception;

}
