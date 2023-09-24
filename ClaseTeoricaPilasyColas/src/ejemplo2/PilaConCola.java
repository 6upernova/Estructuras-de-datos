package ejemplo2;

import java.util.Queue;

import java.util.LinkedList;

public class PilaConCola<E> implements Stack<E> {
	
	protected Queue<E> cola; //Estructura de datos subyacente
	
	public PilaConCola() {
		cola = new LinkedList<E>();
	}
	
	public int size() {
		return cola.size();
	}
	
	public boolean isEmpty() {
		return cola.isEmpty();
	}
	
	public void push(E elem) {
		cola.add(elem);
	}
	
	public E pop() throws EmptyStackException {
		if(cola.isEmpty())
			throw new EmptyStackException("No se puede desapilar una pila vacia");
		Queue<E> aux = new LinkedList<E>();
		E toReturn;
		while(cola.size()!=1)
			aux.add(cola.poll());
		toReturn = cola.poll();
		while(!aux.isEmpty())
			cola.add(aux.poll());
		return  toReturn;
	}
	
	public  E top() throws  EmptyStackException {
		if(cola.isEmpty())
			throw new EmptyStackException("No se puede desapilar una pila vacia");
		Queue<E> aux= new LinkedList<E>();
		E toReturn;
		while(cola.size()!=1)
			aux.add(cola.poll());
		toReturn = cola.peek();
		aux.add(cola.poll());
		while(!aux.isEmpty())
			cola.add(aux.poll());
		return toReturn;
	}
}
