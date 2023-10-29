package Punto4;
import java.util.Stack;


public class ArrayQueue<E> implements Queue<E>{
	
	protected Stack<E> pila;
	
	public ArrayQueue() {
		pila = new Stack<E>();
	}

	
	public int size() {
		return pila.size();
	}

	
	public boolean isEmpty() {
		return pila.isEmpty();
	}

	
	public E front() throws EmptyQueueException {
		if(pila.isEmpty())
			throw new EmptyQueueException("No se puede Mostrar una cola vacia");
		Stack<E> aux = new Stack<E>();
		E toreturn;
		while(pila.size()!=1)
			aux.push(pila.pop());
		toreturn = pila.peek();
		while(!aux.isEmpty())
			pila.push(aux.pop());
		return toreturn;
	}

	@Override
	public void enqueue(E element) {
		pila.push(element);
		
	}

	@Override
	public E dequeue() throws EmptyQueueException {
		if(pila.isEmpty())
			throw new EmptyQueueException("No se puede desencolar una cola vacia");
		Stack<E> aux = new Stack<E>();
		E toreturn;
		while(pila.size()!=1)
			aux.push(pila.pop());
		toreturn = pila.peek();
		pila.pop();
		while(!aux.isEmpty())
			pila.push(aux.pop());
		return toreturn;
	}
	
	
	
}
