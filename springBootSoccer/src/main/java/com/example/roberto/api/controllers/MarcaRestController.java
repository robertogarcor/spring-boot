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

import com.example.roberto.beans.Marca;
import com.example.roberto.service.ServiceModel;
import com.google.gson.JsonObject;


@RestController
public class MarcaRestController implements ModelRestController<Marca, String> {

	
	@Autowired
	private ServiceModel<Marca> sm;
	
	
	@Override
	@RequestMapping(value="/marcas", method=RequestMethod.GET, produces = {"application/json"})
	public ResponseEntity<List<Marca>> findAll() throws Exception {
		try {
			List<Marca> marcas = sm.findAll();
			if (marcas.size() <= 0) {
				return new ResponseEntity<List<Marca>>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<List<Marca>>(marcas, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}			
	}
	
	
	@Override
	@RequestMapping(value="/marcas/marca/{id}", method=RequestMethod.GET, produces = "application/json")
	public ResponseEntity<Marca> findId(@PathVariable("id") int id) throws Exception {
		try {
			Marca marca = sm.findOne(id);
			if (marca.getId() <= 0) {
				return new ResponseEntity<Marca>(HttpStatus.NOT_FOUND);
			}
			return new ResponseEntity<Marca>(sm.findOne(id), HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}	
	}
	

	@Override
	@RequestMapping(value="/marcas/marca/add", method=RequestMethod.POST, produces = "application/json")
	public ResponseEntity<String> insert(@RequestBody Marca entity) {
		try {
			sm.insert(entity);
			JsonObject json = new JsonObject();
			json.addProperty("message","Insert marca OK");
			return new ResponseEntity<String>(json.toString(), HttpStatus.CREATED);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
		}	
	}

	@Override
	@RequestMapping(value="/marcas/marca/{id}/update", method=RequestMethod.PUT, produces = "application/json")
	public ResponseEntity<String> update(@RequestBody Marca entity, @PathVariable("id") int id) throws Exception {
		try {
			Marca marca = sm.findOne(id);
			if (marca.getId() <= 0 | marca.getId() != entity.getId()) {
				return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
			}
			sm.update(entity);
			JsonObject json = new JsonObject();
			json.addProperty("message", "Update marca OK");
			return new ResponseEntity<String>(json.toString(), HttpStatus.CREATED);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
		}	
	}

	@Override
	@RequestMapping(value="/marcas/marca/{id}/delete", method=RequestMethod.DELETE, produces = "application/json")
	public ResponseEntity<String> delete(@PathVariable("id") int id) throws Exception {
		try {
			Marca marca = sm.findOne(id);
			if (marca.getId() <= 0) {
				return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
			}
			sm.delete(id);
			JsonObject json = new JsonObject();
			json.addProperty("message","Delete marca OK");
			return new ResponseEntity<String>(json.toString(), HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}


}
