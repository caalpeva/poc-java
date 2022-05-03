package org.formacion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Para las soluciones de estos ejercicios utiliza streams, las 
 * operaciones intermedias que necesites y una operación terminal
 * con una estructura mutante.
 */
public class ReduccionesUtil {

	/**
	 * Devuelve una coleccion con los Strings de la lista original con:
	 *  - los nulls eliminados del resultado
	 *  - el resto pasado a mayúsculas.
	 *  
	 *  Es decir, si pasamos
	 *  {"a",null,"b",null} debe devolver {"A","B"}
	 *  
	 */
	public Collection<String> obtenSinNulosYMayusculas(List<String> original) {
		Collection<String> result = new ArrayList<>();
		original.stream()
				.filter(p -> p != null)
				.map(p -> p.toUpperCase())
				.forEach(p -> result.add(p));
		
		return result;
		
	}

	/**
	 * Devolver un array de dos enteros. En la posicion 0 con 
	 * el numero de pares encontrado en la lista de numeros y en 
	 * la posicion 1 el numero de impares
	 * 
	 * Por ejemplo, 
	 * si numeros es { 0, 1, 2, 4 } debe devolver [3,1]
	 */
	public int[] paresImpares (List<Integer> numeros) {
		
		/* Ir acumulando en la posicion 0 el recuento de pares
		 * y en la posicion 1 el recuento de impares
		 */
		int[] acumular = {0,0};
		
		
		numeros.stream().forEach(p -> acumular[p % 2]++);
		
		return acumular;
		
	}
	
	public static void main(String[] args) {
		System.out.println("Lista con stream (suma)");
		List<Integer> lista = Arrays.asList(1,2,3,4,5,6,7,8,9,10);
		Integer value = lista.stream()
		 .peek(System.out::println)
		 .reduce(0, (a,b)-> a+b);
		System.out.println("Suma = " + value);
		
		System.out.println("Lista con parallel stream (suma)");
		value = lista.parallelStream()
		 .peek(System.out::println)
		 .reduce(0, (a,b)-> a+b);
		System.out.println("Suma = " + value);
			
		System.out.println("Lista con stream (concatenacion)");
		String text = lista.stream()
		.map(String::valueOf)
		.peek(System.out::println)
		.collect(Collectors.joining(","));
		System.out.println(text);
		
		System.out.println("Lista con parallel stream (concatenacion)");
		text = lista.parallelStream()
		.map(String::valueOf)
		.peek(System.out::println)
		.collect(Collectors.joining(","));
		System.out.println(text);
	}
	
}