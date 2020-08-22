/*
 * Brasilprev - Desafio Java
 * --------------------------------------------------------------
 *			Project: BrasilprevRestApi 
 * 	   Date Created: 21 de ago de 2020
 *			   File: ClientController.java
 *  		 Author: Marcel Britto
 * --------------------------------------------------------------
 */
package br.com.brasilprev.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.brasilprev.dao.ClientDAO;
import br.com.brasilprev.exception.BusinessException;
import br.com.brasilprev.model.Client;

/**
 * Controller for Client Objects.
 * This class contains Business Logic
 * @author marcelbritto
 *
 */
@Component
public class ClientController {

	@Autowired
	private ClientDAO clientDAO;

	/**
	 * Retrieve all Clients
	 * @return
	 */
	public List<Client> listAll() {
		return clientDAO.findAll();
	}
	
	
	/**
	 * Find client by cpf
	 * @param cpf
	 * @return Client or null if not exist
	 */
//	public Client findClientByCpf(String cpf) throws BusinessException{
//		Client clientToFind = clientDAO.find(cpf);
//		if (clientToFind == null) 
//			throw new BusinessException(BusinessException.ERROR_NOT_FOUND);
//		
//		return clientToFind;
//	}
	
	/**
	 * Retrieve Client by Id
	 * @param id
	 * @return
	 * @throws BusinessException
	 */
	public Client findClientById(long id) throws BusinessException{
		Optional<Client> client = clientDAO.findById(id);
		if (!client.isPresent()) 
			throw new BusinessException(BusinessException.ERROR_NOT_FOUND);
		
		return client.get();
	}
	
	/**
	 * Save a new Client
	 * @param clientToInsert
	 * @return
	 * @throws BusinessException
	 */
	public Client save(Client clientToInsert) throws BusinessException {
		Optional<Client> client = clientDAO.findById(clientToInsert.getId());
		if (client.isPresent()) 
			throw new BusinessException(BusinessException.ERROR_EXISTS);
		
		return clientDAO.save(clientToInsert);
		
	}
	
	/**
	 * Delete one Client
	 * @param client
	 * @throws BusinessException
	 */
	public void delete(Client clientToDelete) throws BusinessException {
		Optional<Client> client = clientDAO.findById(clientToDelete.getId());
		if (!client.isPresent()) 
			throw new BusinessException(BusinessException.ERROR_NOT_FOUND);
		
		clientDAO.deleteById(clientToDelete.getId());
	}
	
	/**
	 * Update a Client
	 * @param clientToUpdate
	 * @return
	 * @throws BusinessException
	 */
	public Client update(Client clientToUpdate) throws BusinessException {
		Optional<Client> client = clientDAO.findById(clientToUpdate.getId());
		if (!client.isPresent()) 
			throw new BusinessException(BusinessException.ERROR_NOT_FOUND);
		
		clientToUpdate = clientDAO.save(clientToUpdate);
		return clientToUpdate;
	}
}
