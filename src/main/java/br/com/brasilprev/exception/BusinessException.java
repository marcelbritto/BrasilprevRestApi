/*
 * Brasilprev - Desafio Java
 * --------------------------------------------------------------
 *			Project: BrasilprevRestApi 
 * 	   Date Created: 21 de ago de 2020
 *			   File: BusinessException.java
 *  		 Author: Marcel Britto
 * --------------------------------------------------------------
 */
package br.com.brasilprev.exception;

import br.com.brasilprev.model.CustomError;

/**
 * Class for errors
 * @author dmadmin
 *
 */
public class BusinessException extends Exception {
private static final long serialVersionUID = 1L;
	private CustomError error;

	public final static String ERROR_INVALID_INPUT = "Cliente de entrada invalido!";
	public final static String ERROR_EXISTS = "Client already exists!";
	public final static String ERROR_NOT_FOUND = "Client not found!";
	public final static String ERROR_INVALID_NOME = "Nome do cliente invalido!";
	public final static String ERROR_INVALID_CPF = "CPF do cliente invalido!";
	public final static String ERROR_INVALID_ENDERECO = "Endereco do cliente invalido!";

	public BusinessException(String message) {
		this.error = new CustomError();
		this.error.setMensagem(message);
	}
	
	

	public CustomError getError() {
		return error;
	}

	public void setError(CustomError error) {
		this.error = error;
	}

	@Override
	public String toString() {
		return "BusinessException [error=" + error + "]";
	}
}
