package Ejercicios;

import java.security.InvalidKeyException;

import java.util.Iterator;

import Diccionario.Dictionary;
import Diccionario.HashDictionary;
import Mapeo.*;
import listas.ListDE;
import listas.PositionList;

public class Ejercio1 {


	public static void main(String[] args) {
		Dictionary<Integer, Integer> d3 = new  HashDictionary<Integer, Integer> ();
		Map<Integer, Integer> m1 = new MapeoConLista<Integer, Integer>();
		Map<Integer, Integer> m2 = new MapeoConLista<Integer, Integer>();
		try {
			m1.put(1, 10);
	        m1.put(2, 20);
	        m1.put(3, 30);
	        
	        m2.put(1, 25);
	        m2.put(2, 30);
	        m2.put(3, 40);
	        
	        d3.insert(1, 10);
	        d3.insert(2, 20);
	        d3.insert(3, 2);
	        d3.insert(3, 2);
	        d3.insert(3, 2);
	        d3.insert(2, 33);
		}
		catch(InvalidKeyException e) {
			System.out.println(e.getMessage());
		}
		
		System.out.println("---------------------------------------------------------------------------------------------");
		
		System.out.println("Pasar en limpio(1-a)");
		System.out.println();
		
		PositionList<Entry<Integer,Integer>> p = PasarEnLimpio(m1,m2);
		for(Entry<Integer, Integer> e : p )
			System.out.println(e.toString());
		
		System.out.println("---------------------------------------------------------------------------------------------");
		
		System.out.println("Esta Contenido(1-b)");
		System.out.println();
		
		System.out.println(estaContenido(m1,m2));
		
		System.out.println("---------------------------------------------------------------------------------------------");
		
		System.out.println("Acomodar (1-c)");
		System.out.println();
		
		for(Diccionario.Entry<Integer, Integer> e : acomodar(d3).entries() )
			System.out.println(e.toString());
		
		System.out.println("---------------------------------------------------------------------------------------------");
		
		System.out.println("Eliminar Todas(5-b)");
		System.out.println();
		
		try {
			for(Diccionario.Entry<Integer, Integer> e : d3.eliminarTodas(3,2) )
				System.out.println(e.toString());
		} catch (InvalidKeyException e1) {
			
			e1.printStackTrace();
		}
		
		System.out.println("");
		
		for(Diccionario.Entry<Integer, Integer> e : d3.entries() )
			System.out.println(e.toString());
		
		System.out.println("---------------------------------------------------------------------------------------------");
		

	}
	
	/**
	Escriba un método que reciba dos mapeos M1 y M2 de enteros en enteros (nros de libreta - nota materia)
	que devuelva una PositionList<Entry<Integer,Integer>> L con aquellos elementos E1 de M1 y E2 de M2 que
	coincidan en la clave, pero tengan un valor diferente. Por ejemplo, si E1= (LU: 29303, Nota: 8) pertenece a
	M1 y E2= (LU:29303, Nota: 7) pertenece a M2, entonces E1 y E2 deben estar en L. 
	*/
	
	
	public static <K, V> PositionList<Entry<Integer, Integer>> PasarEnLimpio(Map<Integer,Integer> M1,Map<Integer,Integer> M2 ){
		PositionList<Entry<Integer,Integer>> toret = new ListDE<Entry<Integer,Integer>>();
		for(Entry<Integer, Integer> e1 : M1.entries())
			for(Entry<Integer,Integer> e2 : M2.entries()) {
				if(e1.getKey().equals(e2.getKey()) && !e1.getValue().equals(e2.getValue())) {
					toret.addLast(e1);
					toret.addLast(e2);
				}
			}
				
		return toret;
	}
	
	/**
	Escriba un metodo que dados dos mapeos M1 y M2, determine si todas las claves de M1 están contenidas en
	M2.
	*/
	
	public static <K, V> boolean estaContenido(Map<K, V> M1, Map<K,V> M2) {
		boolean toret = true;
		Iterator<K> it = M1.keys().iterator();
		while(it.hasNext() && toret) {
			K k = it.next();
			if(!containskey(k, M2))
				toret = false;
		}
		return toret;	
	}
	private static <K,V> boolean containskey(K key, Map<K,V> M1) {
		boolean toret = false;
		Iterator<K> it = M1.keys().iterator();
		while(it.hasNext() && !toret) {
			K k = it.next();
			if(key.equals(k))
				toret = true;
		}
		return toret;
			
	}
	
	/**
	Escriba un método cuya signatura sea: public Dictionary<K,V> acomodar (Dictionary<K,V> d) que reciba un
	diccionario d, y que retorne un nuevo diccionario igual a d pero sin claves repetidas. De esta manera, el
	diccionario resultante de este procedimiento no tendrá entradas con claves iguales. Utilice un mapeo auxiliar
	para resolver este ejercicio.
	Por ejemplo: Si d = {(1,a), (2,b), (3,a), (2,c), (1,d), (4,b)}, entonces el diccionario resultante es dRes = {(1,d),
	(2,c), (3,a) (4,b)}.
	 */
	
	public static <K,V> Dictionary<K,V> acomodar (Dictionary<K,V> d) {
		Dictionary<K,V> aux = new HashDictionary<K,V> ();
		for(Diccionario.Entry<K, V> e : d.entries()) {
			try {
				if(aux.find(e.getKey()) == null)
					aux.insert(e.getKey(), e.getValue());
			} catch (InvalidKeyException e1) {
				e1.printStackTrace();
			}
			
		}
		return aux;
		
	}

	

	

}
