package Ejercicios;

import Diccionario.*;
import Diccionario.Entry;
import PriorityQueue_Heap.*;
import PriorityQueue_Lista.DefaultComparator;
public class ej1 {
	public static void main(String[] args) {
	
	}
	
	/**
	 * 	que reciba un diccionario d,
		cuyas claves sean caracteres y sus valores números enteros, y retorne un arreglo con los valores contenidos
		en el diccionario ordenados en forma ascendente. Asuma que cuenta con el TDA diccionario totalmente
		implementado. Resuelva el ejercicio utilizando una cola con prioridades, asuma que dicho TDA se encuentra
		implementado.
	 */
	
	public int[] valOrdenados(Dictionary<Character,Integer> d) {
		int [] toret = new int[d.size()];
		PriorityQueue<Character, Integer> cola = new Heap<Character, Integer> (d.size(), new DefaultComparator<INTEGER>() );
		for(Entry<Character, Integer> e : d.entries())
			cola.insert(e.getKey(), e.getValue());
		for(int i = 0; i<d.size()-1; i++ )
			toret[i]=cola.removeMin().getValue();
			
		return toret;	
	}
	
}
