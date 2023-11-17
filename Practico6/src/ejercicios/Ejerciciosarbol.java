package ejercicios;

import arboles.Arbol;

import arboles.Tnodo;
import arboles.Tree;
import listas.ListDE;
import listas.Position;
import listas.PositionList;
import listas.Nodo;

import java.security.InvalidKeyException;
import java.util.Iterator;

import Excepciones.EmptyTreeException;
import Excepciones.InvalidOperationException;
import Excepciones.InvalidPositionException;
import Mapeo.*;

public class Ejerciciosarbol {


	private static char[] h;

	public static void main(String[] args) {
		Tree <Character> t1 = new Arbol<Character> ();
		Tree <String> t2 = new Arbol<String> ();
		Tree <Integer> t3 = new Arbol<Integer> ();
		try {
			t1.createRoot('a');
		
			t1.addFirstChild(t1.root(), 'a');
			t1.addFirstChild(t1.root(), 'a');
			t1.addFirstChild(t1.root(), 'a');
			t1.addFirstChild(t1.root(), 'a');
			Iterator<Position<Character>> it = t1.children(t1.root()).iterator();
			t1.addLastChild(it.next(), 'b');
			t1.addLastChild(it.next(), 'c');
			t1.addLastChild(it.next(), 'b');
			t1.addLastChild(it.next(), 'b');
			
			t2.createRoot("skibiditoilet");
			
			t2.addFirstChild(t2.root(), "skibidi");
			t2.addFirstChild(t2.root(), "skibidi");
			t2.addFirstChild(t2.root(), "skibidi");
			t2.addFirstChild(t2.root(), "skibiditoilt");
			
			t3.createRoot(5);
			
			t3.addFirstChild(t3.root(), 3);
			t3.addFirstChild(t3.root(), 4);
			t3.addFirstChild(t3.root(), 6);
			t3.addFirstChild(t3.root(), 7);
			
			
			
		}
		catch (InvalidOperationException | InvalidPositionException | EmptyTreeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("---------------------------------------------------------------------------------------------");
		
		
		for(Entry<Character, Integer> e : cantidadRepeticiones(t1).entries())
			System.out.println(e);
		
		System.out.println("---------------------------------------------------------------------------------------------");
		
		for(Position<String> p : posQueContienenS("skibidi",t2))
			System.out.println(p);
		
		System.out.println("---------------------------------------------------------------------------------------------");
		
		System.out.println(eliminarAparicionesDeE(t2, "skibidi"));
		for(String s : t2)
			System.out.println(s);
		
		System.out.println("---------------------------------------------------------------------------------------------");
		
		System.out.println(PerteneceN(t3, 9));
		System.out.println(PerteneceN(t3, 5));
		
		System.out.println("---------------------------------------------------------------------------------------------");
		
		
	}
	
	/**
	 * 	Ejercicio 3: Programe un método con la siguiente signatura: public Map<Character, Integer>
	 *	cantidadRepeticiones(Tree<Character> t). Este método deberá retornar un mapeo con cada uno de los
	 * 	caracteres del árbol y la cantidad de veces que aparece cada carácter en el árbol. Resuelva este problema utilizando
	 *	un recorrido en preorden.
	 *
	 */
	public static Map<Character, Integer> cantidadRepeticiones(Tree<Character> t){
		Map<Character,Integer> toret = new HashMap<Character, Integer> ();
		try {
			preordenCantidadRepeticiones((Tnodo<Character>)t.root(), toret, 0);
		} catch (EmptyTreeException e) {
			e.printStackTrace();
		}
		return toret;
	}
	
	private static void preordenCantidadRepeticiones(Tnodo<Character> p, Map<Character,Integer> l, int i) {
		//visita
		try {
			if(l.get(p.element()) == null)
				i = 1;
			else {
				i = l.get(p.element());
				i++;
			}
			l.put(p.element(), i );
		}
		catch (InvalidKeyException e) {
			e.printStackTrace();
		}
			
		//Recorrer recursivamente todos los hijos
		for ( Tnodo<Character> hijo : p.getHijos() )
			preordenCantidadRepeticiones(hijo, l, i );	
		
		
	}
	
	/**
	Ejercicio 4: Dado un árbol a de Strings y un String s, programe un método tal que retorne un Iterable con las
	posiciones del árbol en las que aparece el String s. Para resolver este problema implemente un recorrido en
	postorden.
	*/
	public static Iterable<Position<String>> posQueContienenS(String s, Tree<String> t) {
		PositionList<Position<String>> toret = new ListDE<Position<String>> ();
		try {
			posOrdenposQueContienenS((Tnodo<String>)t.root(), s, toret);
		} catch (EmptyTreeException e) {
			e.printStackTrace();
		}
		return toret;
		
		
	}
	
	private static void posOrdenposQueContienenS(Tnodo<String> p, String s, PositionList<Position<String>> pl) {
		//Recorrer recursivamente todos los hijos
		for ( Tnodo<String> hijo : p.getHijos())
			posOrdenposQueContienenS(hijo, s , pl);
		
		//Vista
		if(p.element().equals(s))
			pl.addLast(p);
	}
	
	/**
	Ejercicio 5:
	a. Escriba un método tal que dado un árbol genérico a y un elemento e, elimine de a todas las apariciones de e.
	Compare los elementos por equivalencia. El método debe retornar la cantidad de eliminaciones realizadas
	 */
	
	public static<E> int eliminarAparicionesDeE(Tree<E> a, E elem) {
		int Eliminados = 0;
		for(Position<E> e: a.positions())
			if(e.element().equals(elem)) {
				try {
					a.removeNode(e);
					Eliminados++;
				} 
				catch (InvalidPositionException e1) {
					e1.printStackTrace();
				}
			}
		return Eliminados;
	}
	
	/**
	Ejercicio 6:
	a. Dado un árbol de enteros a y un entero n, escriba un método que determine si n pertenece al árbol a. Para
	esolver este método utilice el iterador del árbol
	 */
	
	public static<E> boolean PerteneceN(Tree<Integer> a, int n) {
		boolean encontre = false;
		Iterator<Integer> it = a.iterator();
		while(it.hasNext() && !encontre) {
			Integer e = it.next();
			if(e.equals(n))
				encontre = true;
		}
		return encontre;
	}

	
	

}
