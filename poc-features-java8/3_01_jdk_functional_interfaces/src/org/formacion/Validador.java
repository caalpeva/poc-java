package org.formacion;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

/**
 * El objetivo de esta clase es validar que objetos del tipo T cumplen unos determinados requisitos.
 * Estos requisitos son dinamicos (se pueden configurar durante la ejecucion), por lo que, 
 * en lugar de tener una comprobacion fija, tendremos una lista de validadores individuales.
 * El usuario de esta clase podra sumar validadores mediante un metodo add ( que teneis que implementar ).
 * El metodo valida debera devolver true si el objeto que se pasa como parametro cumple con todos los 
 * validadores de la lista y false si no.
 * 
 * Para completar esta clase falta:
 *  - indicar el tipo de los elementos de la List validadores: pensad, debe ser un tipo que nos diga
 *       si un objeto del tipo T cumple con una condicion o no.
 *  - implementar el metodo add para aceptar un nuevo validador individual y acumularlo a la lista validadores
 *  - implementar el metodo valida para que realice la accion indicada mas arriba. Sera necesario cambiar el 
 *       tipo del parametro de Object al tipo adecuado
 */
public class Validador <T> {

	List<Predicate<T>> validadores = new ArrayList<Predicate<T>>();
	
	public boolean valida(T valor) {
		for(Predicate<T> predicate: validadores) {
			if (!predicate.test(valor)) {
				return false;
			}
		}
		return true;
	}
	
	public void add(Predicate<T> predicate) {
		validadores.add(predicate);
	}
}