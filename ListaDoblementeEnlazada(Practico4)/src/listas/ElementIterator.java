package listas;

import java.util.Iterator;
import java.util.NoSuchElementException;
import Excepciones.BoundaryViolationException;
import Excepciones.EmptyListException;
import Excepciones.InvalidPositionException;

//Esta clase es mi implementacion de la interface iterator que va a actuar sobre cualquier objeto que implemente la interfaze iterable
public class ElementIterator<E> implements Iterator<E> {
	
	protected ListDE<E> l;
	protected Position<E> cursor;

	public ElementIterator(ListDE<E> lista)  {
		l = lista;
		if(l.isEmpty())
			cursor = null;
		else
			try {
				cursor = l.first();
			} catch (EmptyListException e) {
				e.printStackTrace();
			}
	
	}
	
	public boolean hasNext() {
		return cursor != null;
	}
	
	public E next()	throws NoSuchElementException{
		if(cursor == null)
			throw new NoSuchElementException("No hay elemento siguiente");
		E ret = cursor.element();
		try {
			if(cursor==l.last())
				cursor = null;
			else
				cursor = l.next(cursor);
		} catch (EmptyListException | InvalidPositionException | BoundaryViolationException e) {
			e.printStackTrace();
		}
		return ret;
		
	}

	

	
	 

}
