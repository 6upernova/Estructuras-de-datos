package PriorityQueue_Heap;

import java.security.InvalidKeyException;
import java.util.Comparator;

import Excepciones.EmptyPriorityQueueException;

public class Heap<K,V> implements PriorityQueue<K, V> {
	
	protected Entry<K,V> [] entradas;
	protected int size;
	protected Comparator comp;

	public Heap(int maxElm, Comparator c) {
		entradas = new Entry[maxElm];
		comp = c;
		size = 0;
	}
	
	@Override
	public int size() {
		return size;
	}

	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	@Override
	public Entry<K, V> min() throws EmptyPriorityQueueException {
		if(isEmpty())
			throw new EmptyPriorityQueueException("La cola esta vacia");
		return entradas[1];
	}

	@Override
	public Entry<K, V> insert(K key, V value) throws InvalidKeyException {
		checkKey(key);
		
		Entry<K,V> toret = new Entrada<K,V>(key, value);
		
		insertEntry(toret);
		return toret;
		
	}
	
	private void insertEntry(Entry<K,V> e ) {
		if(isEmpty()) {
			entradas[1] = e;
			size++;
		}
		else {
			entradas[size+1] = e;
			heapUp(size+1);
			size++;
		}
		
	}
		
	private void heapUp(int pos) {
	    if (pos > 1) {
	        int padre = pos / 2;
	        if (comp.compare(entradas[pos].getKey(), entradas[padre].getKey()) < 0) {
	            cambiar(pos, padre);
	            heapUp(padre);
	        }
	    }
	}
	
	private void cambiar(int pos, int pos2) {
		Entry<K,V> aux = entradas[pos];
		entradas[pos] = entradas[pos2];
		entradas[pos2] = aux;
	}
			
			 
	
	
	private void checkKey(K key) throws InvalidKeyException {
		if (key == null )
			throw new InvalidKeyException ("La clave pasada por parametro es invalida");
	}

	@Override
	public Entry<K, V> removeMin() throws EmptyPriorityQueueException {
		if(isEmpty())
			throw new EmptyPriorityQueueException("La cola esta vacia");
		Entry<K,V> toret = entradas[1];
		entradas[1] = entradas[size];
		entradas[size] = null;
		size--;
		if(size > 1)
			heapDown(1);
		
		return toret;
	}
	
	private void heapDown(int pos) {
		int hijoizq = 2*pos;
		int hijoder = 2*pos+1;
		int hijomenor = pos;
		
		if( hijoizq <= size && comp.compare(entradas[hijomenor].getKey(), entradas[hijoizq].getKey()) > 0 )
			hijomenor = hijoizq;
		if( hijoder <= size && comp.compare(entradas[hijomenor].getKey(), entradas[hijoder].getKey()) > 0 )
			hijomenor = hijoder;
		
		if(hijomenor != pos) {
			cambiar(pos, hijomenor);
			heapDown(hijomenor);
		}
	}

}
