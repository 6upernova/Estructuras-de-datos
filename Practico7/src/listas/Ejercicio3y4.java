package listas;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Ejercicio3y4 {

	public static void main(String args[]){
		
	
	}
	
	public static <E> boolean contains(PositionList<E> l, E elem) {
		boolean encontre = false;
		try {
			Iterator<E> it = l.iterator();
			while(it.hasNext() && !encontre)
				encontre = it.next().equals(elem);
		}
		catch(NoSuchElementException e) {
			System.out.println(e.getMessage());
		}
		return encontre;
	}
	
	public static <E> PositionList<E> NuevaElemDuped(PositionList<E> l){
		PositionList<E> l2 = new ListDE<E>();
		for(E elem:l) {
			l2.addLast(elem);
			l2.addLast(elem);
		}
		return l2;
	}
}


