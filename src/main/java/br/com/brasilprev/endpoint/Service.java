/*
 * Brasilprev - Desafio Java
 * --------------------------------------------------------------
 *			Project: BrasilprevRestApi 
 * 	   Date Created: 21 de ago de 2020
 *			   File: Service.java
 *  		 Author: Marcel Britto
 * --------------------------------------------------------------
 */
package br.com.brasilprev.endpoint;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.brasilprev.controller.ClientController;
import br.com.brasilprev.exception.BusinessException;
import br.com.brasilprev.model.Client;

/**
 * Endpoint of application
 * @author marcelbritto
 *
 */
@RestController
public class Service {

	@Autowired
	private ClientController clientController;
	
	private static final Logger logger = LogManager.getLogger(Service.class);
	
	@Autowired
	public Service() {
		super();
	}

	@GetMapping("/clients")
	public ResponseEntity<?> listAll() {
		logger.debug("listAll called");
		return new ResponseEntity<>(clientController.listAll(), HttpStatus.OK);
	}
	
//	@GetMapping("/clients/{cpf}")
//	public ResponseEntity<?> findClientByCpf(@PathVariable String cpf) {
//		try {
//			System.out.println(cpf);
//			Client client = clientController.findClientByCpf(cpf);
//			
//			return new ResponseEntity<>(client, HttpStatus.OK);
//		} catch (BusinessException e){
//			return new ResponseEntity<>(e.getError(), HttpStatus.NOT_FOUND);
//		}
//	}
	
	@GetMapping("/clients/{id}")
	public ResponseEntity<?> findClientById(@PathVariable long id) {
		logger.debug("findClientById called");
		try {
			Client client = clientController.findClientById(id);
			
			return new ResponseEntity<>(client, HttpStatus.OK);
		} catch (BusinessException e){
			logger.debug("error: ", e);
			return new ResponseEntity<>(e.getError(), HttpStatus.NOT_FOUND);
		}
	}
	@PostMapping("/clients")
	public ResponseEntity<?> save(@RequestBody Client client) {
		logger.debug("save called");
		try {
			Client newClient = clientController.save(client);
			return new ResponseEntity<>(newClient, HttpStatus.OK);
		} catch (BusinessException e){
			logger.debug("error:", e);
			return new ResponseEntity<>(e.getError(), HttpStatus.BAD_REQUEST);
		}
	}
	
	@DeleteMapping("/clients")
	public ResponseEntity<?> delete(@RequestBody Client client) {
		logger.debug("delete called");
		try {
			clientController.delete(client);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (BusinessException e){
			logger.debug("error", e);
			return new ResponseEntity<>(e.getError(), HttpStatus.NOT_FOUND);
		}
	}
	
	@PutMapping("/clients")
	public ResponseEntity<?> update(@RequestBody Client client) {
		logger.debug("update called");
		try {
			Client clientToUpdate = clientController.update(client);
			return new ResponseEntity<>(clientToUpdate, HttpStatus.OK);
		} catch (BusinessException e){
			logger.debug("error", e);
			return new ResponseEntity<>(e.getError(), HttpStatus.NOT_FOUND);
		}
	
	}
	
}
