/*
 * Brasilprev - Desafio Java
 * --------------------------------------------------------------
 *			Project: BrasilprevRestApi 
 * 	   Date Created: 21 de ago de 2020
 *			   File: CustomError.java
 *  		 Author: Marcel Britto
 * --------------------------------------------------------------
 */
package br.com.brasilprev.model;

/**
 * Custom Error Class
 * @author marcelbritto
 *
 */
public class CustomError {

	private String mensagem;

	public CustomError() {
		super();
	}

	public CustomError(String mensagem) {
		super();
		this.mensagem = mensagem;
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

	@Override
	public String toString() {
		return "Error [mensagem=" + mensagem + "]";
	}

}