package Ejercicio6;

import java.util.EmptyStackException;
import java.util.ArrayList;

public class PilaconLista<E> implements Stack<E> {
	
	//Estructura subyacente Arraylist
	protected ArrayList<E> L;
	
	public PilaconLista() {
		L = new ArrayList<E>();
	}

	@Override
	public int size() {
		return L.size();
	}

	@Override
	public boolean isEmpty() {
		return L.isEmpty();
	}

	@Override
	public E top() throws EmptyStackException {
		if(isEmpty())
			throw new EmptyStackException();
		return L.get(size()-1);
	}

	@Override
	public void push(E element) {
		L.add(element);
	}

	@Override
	public E pop() throws EmptyStackException {
		if(isEmpty())
			throw new EmptyStackException();
		return L.remove(size()-1);
	}
	
}
