package pilas_Nodos;

import ejemplo2.EmptyStackException;

public class PilaEnlazada<E> implements Stack<E>{
	
	protected Nodo<E> tope;
	protected int cantidad;

	public PilaEnlazada() {
		tope=null;
		cantidad=0;
	}
	
	
	public int size() {
		return cantidad;
	}
	
	public boolean isEmpty() {
		return cantidad==0;
	}
	
	public E top() throws EmptyStackException {
		if(cantidad==0)
			throw new EmptyStackException("La pila esta vacia");
		return tope.getElement();
	}
	
	public void push(E elem) {
		Nodo<E> nuevo = new Nodo<E> (elem, tope);
		tope=nuevo;
		cantidad++;
	}
	
	public E pop() throws EmptyStackException {
		if(cantidad==0)
			throw new EmptyStackException("La  pila esta vacia no puede devolver ningun elemento");
		E toreturn = tope.getElement();
		tope = tope.getSiguiente();
		cantidad--;
		return toreturn;
	}
}
