package com.example.roberto.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.roberto.beans.Jugador;
import com.example.roberto.service.ServiceModel;
import com.google.gson.JsonObject;


@RestController
public class JugadorRestController implements ModelRestController<Jugador, String> {

	
	@Autowired
	private ServiceModel<Jugador> sj;
	
	
	@Override
	@RequestMapping(value="/jugadores", method=RequestMethod.GET, produces = {"application/json"})
	public ResponseEntity<List<Jugador>> findAll() throws Exception {
		try {
			List<Jugador> jugadores = sj.findAll();
			if (jugadores.size() <= 0) {
				return new ResponseEntity<List<Jugador>>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<List<Jugador>>(jugadores, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}			
	}
	
	@Override
	@RequestMapping(value="/jugadores/jugador/{id}", method=RequestMethod.GET, produces = "application/json")
	public ResponseEntity<Jugador> findId(@PathVariable("id") int id) throws Exception {
		try {
			Jugador jugador = sj.findOne(id);
			if (jugador.getId() <= 0) {
				return new ResponseEntity<Jugador>(HttpStatus.NOT_FOUND);
			}
			return new ResponseEntity<Jugador>(sj.findOne(id), HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Override
	@RequestMapping(value="/jugadores/jugador/add", method=RequestMethod.POST, produces = "application/json")
	public ResponseEntity<String> insert(@RequestBody Jugador entity) {
		try {
			sj.insert(entity);
			JsonObject json = new JsonObject();
			json.addProperty("message","Insert jugador OK");
			return new ResponseEntity<String>(json.toString(), HttpStatus.CREATED);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
			
		}	
	}

	@Override
	@RequestMapping(value="/jugadores/jugador/{id}/update", method=RequestMethod.PUT, produces = "application/json")
	public ResponseEntity<String> update(@RequestBody Jugador entity, @PathVariable("id") int id) throws Exception {
		try {
			Jugador jugador = sj.findOne(id);
			if (jugador.getId() <= 0 | jugador.getId() != entity.getId()) {
				return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
			}
			sj.update(entity);
			JsonObject json = new JsonObject();
			json.addProperty("message", "Update jugador OK");
			return new ResponseEntity<String>(json.toString(), HttpStatus.CREATED);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
		}	
	}

	@Override
	@RequestMapping(value="/jugadores/jugador/{id}/delete", method=RequestMethod.DELETE, produces = "application/json")
	public ResponseEntity<String> delete(@PathVariable("id") int id) throws Exception {
		try {
			Jugador jugador = sj.findOne(id);
			if (jugador.getId() <= 0) {
				return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
			}
			sj.delete(id);
			JsonObject json = new JsonObject();
			json.addProperty("message","Delete jugador OK");
			return new ResponseEntity<String>(json.toString(), HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}


}
