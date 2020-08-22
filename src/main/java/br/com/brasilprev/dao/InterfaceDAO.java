/*
 * Brasilprev - Desafio Java
 * --------------------------------------------------------------
 *			Project: BrasilprevRestApi 
 * 	   Date Created: 21 de ago de 2020
 *			   File: InterfaceDAO.java
 *  		 Author: Marcel Britto
 * --------------------------------------------------------------
 */
package br.com.brasilprev.dao;
 

import java.util.List;

import org.springframework.validation.annotation.Validated;



/**
 * Interface for applicationDAOs.
 * 
 * @author Marcel Britto
 *
 */
public interface InterfaceDAO<T, I> {

   /**
    * Save object	
    * @param object
    * @return
    */
   public T save(@Validated T object);
   
   /**
    * Change object
    * @param object
    * @return
    */
   public T update(@Validated T object);
   
   /**
    * Delete object
    * @param id
    */
   public void delete(I id);

   /**
    * Get all objects
    * @return
    */
   public List<T> getList();
   
   /**
    * Find object by id
    * @param id
    * @return
    */
   public T find(I id);
}
