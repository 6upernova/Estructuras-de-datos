package Ejercicios;

import ArbolBi.*;
import Excepciones.BoundaryViolationException;
import Excepciones.EmptyTreeException;
import Excepciones.InvalidPositionException;
import listas.*;

public class EjerciciosArbolBi {


	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	/**
	Ejercicio 3:
	Un árbol binario de expresión es un árbol binario donde cada uno de sus nodos
	internos están rotulados con operadores y sus hojas con operandos (tal como el que se
	muestra en la figura). 1
	Dado un árbol binario A que representa una expresión aritmética, escriba un método
	recursivo (es decir, no puede usar los iteradores) que retorne un iterable de caracteres
	con la notación infija de la expresión que el árbol representa
	 * 
	 */
	
	
	public static Iterable<Character> NotacionInfija(BinaryTree<Character> a) {
		PositionList<Character> toret = new ListDE<Character> ();
		try {
			posOrdenOperaciones(a.root(), toret, a);
		} catch (EmptyTreeException e) {
			e.printStackTrace();
		}
		return toret;
		
		
	}
	
	
	private static void posOrdenOperaciones(Position<Character> n, PositionList<Character> p, BinaryTree<Character> a) {
		//Recorrer recursivamente todos los hijos
		try {
			if(a.left(n) != null  )	
				posOrdenOperaciones(a.left(n), p, a);
		
			if(a.right(n) != null)
				posOrdenOperaciones(a.left(n), p, a);
		}
		catch (InvalidPositionException | BoundaryViolationException e) {
			
			e.printStackTrace();
		}
		//Visita
		p.addLast(n.element());
		
		
				
	}
}
