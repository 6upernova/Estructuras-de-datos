package TDAPila;
import Exceptions.*;

public class PilaEnlazada<E> implements Stack<E> {

	//atributos
	protected Nodo<E> tope;
	protected int tamanio;
	

	public PilaEnlazada() {
		tope = null;
		tamanio = 0;
	}
	
	public int size() {
		return tamanio;
	}
	
	public boolean isEmpty() {
		return tamanio == 0;
	}
	
	public E top() throws EmptyStackException {
		if(tamanio == 0) {
			throw new EmptyStackException("Pila vacía.");
		}
		return tope.getElemento();
	}
	
	public void push(E element) {
		Nodo<E> aux = new Nodo<E>(element, tope);
		tope = aux;
		tamanio++;
	}

	public E pop() throws EmptyStackException {
		if (isEmpty()) {
			throw new EmptyStackException("Pila vacía.");
		}
		else {
			E aux = tope.getElemento();
			tope = tope.getSiguiente();
			tamanio--;
			return aux;
		}
	}

}