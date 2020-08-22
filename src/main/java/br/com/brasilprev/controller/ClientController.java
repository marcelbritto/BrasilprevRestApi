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
import org.springframework.transaction.annotation.Transactional;

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
	@Transactional(rollbackFor = Exception.class) 
	public Client save(Client clientToInsert) throws BusinessException {
		validateSave(clientToInsert);
		
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
	@Transactional(rollbackFor = Exception.class) 
	public Client update(Client clientToUpdate) throws BusinessException {
		Optional<Client> client = clientDAO.findById(clientToUpdate.getId());
		if (!client.isPresent()) 
			throw new BusinessException(BusinessException.ERROR_NOT_FOUND);
		
		clientToUpdate = clientDAO.save(clientToUpdate);
		return clientToUpdate;
	}
	
	public void validateSave(Client client) throws BusinessException {
		if (client == null) {
			throw new BusinessException(BusinessException.ERROR_INVALID_INPUT);
		}
		if (client.getName() == null) {
			throw new BusinessException(BusinessException.ERROR_INVALID_NAME);
		}
		if (client.getCpf() == null) {
			throw new BusinessException(BusinessException.ERROR_INVALID_CPF);
		}
		if (client.getAddress() == null) {
			throw new BusinessException(BusinessException.ERROR_INVALID_ADDRESS);
		}
		Optional<Client> clientDB = clientDAO.findById(client.getId());
		if (clientDB.isPresent()) 
			throw new BusinessException(BusinessException.ERROR_EXISTS);
	}
}
