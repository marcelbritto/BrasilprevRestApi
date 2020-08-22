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
 * Class for errors of Business logic
 * @author marcelbritto
 *
 */
public class BusinessException extends Exception {
private static final long serialVersionUID = 1L;
	private CustomError error;

	public final static String ERROR_INVALID_INPUT = "Invalid Client input!";
	public final static String ERROR_EXISTS = "Client already exists!";
	public final static String ERROR_NOT_FOUND = "Client not found!";
	public final static String ERROR_INVALID_NAME = "Invalid name!";
	public final static String ERROR_INVALID_CPF = "Invalid CPF!";
	public final static String ERROR_INVALID_ADDRESS = "Invalid address!";

	public BusinessException(String message) {
		this.error = new CustomError();
		this.error.setMessage(message);
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
