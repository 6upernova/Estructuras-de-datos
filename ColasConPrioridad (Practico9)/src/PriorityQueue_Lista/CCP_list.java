package PriorityQueue_Lista;

import java.security.InvalidKeyException;
import java.util.Comparator;

import Excepciones.BoundaryViolationException;
import Excepciones.EmptyListException;
import Excepciones.EmptyPriorityQueueException;
import Excepciones.InvalidPositionException;
import listas.*;

public class CCP_list<K,V> implements PriorityQueue<K,V> {
	
	private PositionList<Entry<K,V>> pl;
	private Comparator<K> c;
	
	public CCP_list(Comparator<K> c) {
		pl = new ListDE<Entry<K,V>> ();
		this.c = c;
	}

	@Override
	public int size() {
		return pl.size();
	}

	@Override
	public boolean isEmpty() {
		return pl.isEmpty();
	}

	@Override
	public Entry<K, V> min() throws EmptyPriorityQueueException {
		if(this.isEmpty())
			throw new EmptyPriorityQueueException("La cola esta vacia");
		Entry<K, V> toret = null;
		try {
			toret = pl.first().element();
		} catch (EmptyListException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return toret;
	}
	
	private void checkKey(K key) throws InvalidKeyException {
		if (key == null )
			throw new InvalidKeyException ("La clave pasada por parametro es invalida");
	}

	@Override
	public Entry<K, V> insert(K key, V value) throws InvalidKeyException {
		checkKey(key);
		Entry<K,V> toret = new Entrada<K,V> (key, value);
		insertEntry(toret);
		return toret;
		
		
	}
	
	private void insertEntry(Entry<K,V> e) {
		try {
			if (pl.isEmpty()) {
				pl.addFirst(e);
			} 
			else if(c.compare(e.getKey(), pl.last().element().getKey()) > 0) {
					pl.addLast(e);
			}
			else {
				Position<Entry<K,V>> actual = pl.first();
				while(c.compare(e.getKey(), actual.element().getKey()) > 0)
					actual = pl.next(actual);
				pl.addBefore(actual, e);
			}
		} 
		catch (EmptyListException | InvalidPositionException | BoundaryViolationException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
		}		
	}

	@Override
	public Entry<K, V> removeMin() throws EmptyPriorityQueueException {
		if(this.isEmpty())
			throw new EmptyPriorityQueueException("La cola esta vacia");
		Entry<K, V> toret = null;
		try {
			toret = pl.remove(pl.first());
		} catch (InvalidPositionException | EmptyListException e) {
			e.printStackTrace();
		}
		return toret;
	}

}
