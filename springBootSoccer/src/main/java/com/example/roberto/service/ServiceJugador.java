package com.example.roberto.service;

import java.util.List;

import com.example.roberto.beans.Jugador;

public interface ServiceJugador {
	
	public List<Jugador> findAll() throws Exception;
	
	public Jugador findOne(int id) throws Exception;

	public void insert(Jugador jugador) throws Exception;
	
	public void update(Jugador jugador) throws Exception;
	
	public void delete(int id) throws Exception;
}
