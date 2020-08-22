/*
 * Brasilprev - Desafio Java
 * --------------------------------------------------------------
 *			Project: BrasilprevRestApi 
 * 	   Date Created: 21 de ago de 2020
 *			   File: ClientDAO.java
 *  		 Author: Marcel Britto
 * --------------------------------------------------------------
 */
package br.com.brasilprev.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.brasilprev.model.Client;

/**
 * DAO class to Client
 * @author dmadmin
 *
 */
@Repository
public interface ClientDAO extends JpaRepository<Client, Long>  {
	
	List<Client> findByCpf(String cpf);
}
