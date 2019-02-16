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

import com.example.roberto.beans.Camiseta;
import com.example.roberto.service.ServiceModel;
import com.google.gson.JsonObject;


@RestController
public class CamisetaRestController implements ModelRestController<Camiseta, String> {

	
	@Autowired
	private ServiceModel<Camiseta> sc;
	
	
	@Override
	@RequestMapping(value="/camisetas", method=RequestMethod.GET, produces = {"application/json"})
	public ResponseEntity<List<Camiseta>> findAll() throws Exception {
		try {
			List<Camiseta> camisetas = sc.findAll();
			if (camisetas.size() <= 0) {
				return new ResponseEntity<List<Camiseta>>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<List<Camiseta>>(camisetas, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}			
	}
	
	@Override
	@RequestMapping(value="/camisetas/camiseta/{id}", method=RequestMethod.GET, produces = "application/json")
	public ResponseEntity<Camiseta> findId(@PathVariable("id") int id) throws Exception {
		try {
			Camiseta camiseta = sc.findOne(id);
			if (camiseta.getId() <= 0) {
				return new ResponseEntity<Camiseta>(HttpStatus.NOT_FOUND);
			}
			return new ResponseEntity<Camiseta>(sc.findOne(id), HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Override
	@RequestMapping(value="/camisetas/camiseta/add", method=RequestMethod.POST, produces = "application/json")
	public ResponseEntity<String> insert(@RequestBody Camiseta entity) {
		try {
			sc.insert(entity);
			JsonObject json = new JsonObject();
			json.addProperty("message","Insert camiseta OK");
			return new ResponseEntity<String>(json.toString(), HttpStatus.CREATED);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
		}	
	}

	@Override
	@RequestMapping(value="/camisetas/camiseta/{id}/update", method=RequestMethod.PUT, produces = "application/json")
	public ResponseEntity<String> update(@RequestBody Camiseta entity, @PathVariable("id") int id) throws Exception {
		try {
			Camiseta camiseta = sc.findOne(id);
			if (camiseta.getId() <= 0 | camiseta.getId() != entity.getId()) {
				return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
			}
			sc.update(entity);
			JsonObject json = new JsonObject();
			json.addProperty("message", "Update camiseta OK");
			return new ResponseEntity<String>(json.toString(), HttpStatus.CREATED);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
		}	
	}

	@Override
	@RequestMapping(value="/camisetas/camiseta/{id}/delete", method=RequestMethod.DELETE, produces = "application/json")
	public ResponseEntity<String> delete(@PathVariable("id") int id) throws Exception {
		try {
			Camiseta camiseta = sc.findOne(id);
			if (camiseta.getId() <= 0) {
				return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
			}
			sc.delete(id);
			JsonObject json = new JsonObject();
			json.addProperty("message","Delete camiseta OK");
			return new ResponseEntity<String>(json.toString(), HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}


}
