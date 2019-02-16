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

import com.example.roberto.beans.Equipo;
import com.example.roberto.service.ServiceModel;
import com.google.gson.JsonObject;


@RestController
public class EquipoRestController implements ModelRestController<Equipo, String> {

	
	@Autowired
	private ServiceModel<Equipo> se;
	
	
	@Override
	@RequestMapping(value="/equipos", method=RequestMethod.GET, produces = {"application/json"})
	public ResponseEntity<List<Equipo>> findAll() throws Exception {
		try {
			List<Equipo> equipos = se.findAll();
			if (equipos.size() <= 0) {
				return new ResponseEntity<List<Equipo>>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<List<Equipo>>(equipos, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}			
	}
	
	@Override
	@RequestMapping(value="/equipos/equipo/{id}", method=RequestMethod.GET, produces = "application/json")
	public ResponseEntity<Equipo> findId(@PathVariable("id") int id) throws Exception {
		try {
			Equipo equipo = se.findOne(id);
			if (equipo.getId() <= 0) {
				return new ResponseEntity<Equipo>(HttpStatus.NOT_FOUND);
			}
			return new ResponseEntity<Equipo>(se.findOne(id), HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}	
	}

	@Override
	@RequestMapping(value="/equipos/equipo/add", method=RequestMethod.POST, produces = "application/json")
	public ResponseEntity<String> insert(@RequestBody Equipo entity) {
		try {
			se.insert(entity);
			JsonObject json = new JsonObject();
			json.addProperty("message","Insert equipo OK");
			return new ResponseEntity<String>(json.toString(), HttpStatus.CREATED);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);	
		}	
	}

	@Override
	@RequestMapping(value="/equipos/equipo/{id}/update", method=RequestMethod.PUT, produces = "application/json")
	public ResponseEntity<String> update(@RequestBody Equipo entity, @PathVariable("id") int id) throws Exception {
		try {
			Equipo equipo = se.findOne(id);
			if (equipo.getId() <= 0 | equipo.getId() != entity.getId()) {
				return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
			}
			se.update(entity);
			JsonObject json = new JsonObject();
			json.addProperty("message", "Update equipo OK");
			return new ResponseEntity<String>(json.toString(), HttpStatus.CREATED);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
		}	
	}

	@Override
	@RequestMapping(value="/equipos/equipo/{id}/delete", method=RequestMethod.DELETE, produces = "application/json")
	public ResponseEntity<String> delete(@PathVariable("id") int id) throws Exception {
		try {
			Equipo equipo = se.findOne(id);
			if (equipo.getId() <= 0) {
				return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
			}
			se.delete(id);
			JsonObject json = new JsonObject();
			json.addProperty("message","Delete equipo OK");
			return new ResponseEntity<String>(json.toString(), HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}


}
