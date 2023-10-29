package arboles;

import java.util.Iterator;

import Excepciones.BoundaryViolationException;
import Excepciones.EmptyListException;
import Excepciones.EmptyTreeException;
import Excepciones.InvalidOperationException;
import Excepciones.InvalidPositionException;
import listas.*;

public class Arbol<E> implements Tree<E> {
	
	protected Tnodo<E> raiz;
	protected int size;
	

	public Arbol() {
		raiz = null;
		size = 0;
	}

	public int size() {
		return size;
	}

	public boolean isEmpty() {
		return size == 0;
	}

	public Iterator<E> iterator() {
		PositionList<E> toret = new ListDE<E>();
		if(raiz != null)
			preorden(raiz, toret);
		return toret.iterator();
	}
	
	private void preorden(Tnodo<E> p, PositionList<E> l) {
		//visita
		l.addLast(p.element());
		//Recorrer recursivamente todos los hijos
		for ( Tnodo<E> hijo : p.getHijos() )
			preorden(hijo, l);		
	}
	
	private void pospreorden(Tnodo<E> p, PositionList<Position<E>> l) {
		//visita
		l.addLast(p);
		//Recorrer recursivamente todos los hijos
		for ( Tnodo<E> hijo : p.getHijos() )
			pospreorden(hijo, l);		

		
	}
	
	private Tnodo<E> checkposition(Position<E> p) throws InvalidPositionException	{
		Tnodo<E> n;
		if(p == null)
			throw new InvalidPositionException("Malisimo bro no mandes posiciones nulas bobo");
		if(p.element() == null)
			throw new InvalidPositionException("No se puede crear un nodo con elemento nulos");
		if(size == 0)
			throw new InvalidPositionException("La lista esta vacia");
		try {
			n = (Tnodo<E>) p;
		}
		catch (ClassCastException e ){
			throw new InvalidPositionException("La posicion es invalida"); 
		}
		
		return n;
		
	}

	@Override
	public Iterable<Position<E>> positions() {
		PositionList<Position<E>> toret = new ListDE<Position<E>>();
		if(raiz != null)
			pospreorden(raiz, toret);
		return toret;
	}

	@Override
	public E replace(Position<E> v, E e) throws InvalidPositionException {
		Tnodo<E> n = checkposition(v);
		E toret = n.element();
		n.setElem(e);
		return toret;
	}

	@Override
	public Position<E> root() throws EmptyTreeException {
		if(isEmpty())
			throw new EmptyTreeException("Un arbol vacio no tiene raiz");
		return raiz;
	}

	@Override
	public Position<E> parent(Position<E> v) throws InvalidPositionException, BoundaryViolationException {
		Tnodo<E> n = checkposition(v);
		if(n.equals(raiz))
			throw new BoundaryViolationException("El nodo raiz no tiene padre");
		return n.getPadre();
	}

	@Override
	public Iterable<Position<E>> children(Position<E> v) throws InvalidPositionException {
		Tnodo<E> n = checkposition(v);
		PositionList<Position<E>> toret = new ListDE<Position<E>>();
		for(Position<E> p : n.getHijos()) {
			toret.addLast(p);
		}
		return toret;
	}

	@Override
	public boolean isInternal(Position<E> v) throws InvalidPositionException {
		return !checkposition(v).getHijos().isEmpty();
	}

	@Override
	public boolean isExternal(Position<E> v) throws InvalidPositionException {
		return !isInternal(v);
	}

	@Override
	public boolean isRoot(Position<E> v) throws InvalidPositionException {
		return checkposition(v) == (raiz);
	}

	@Override
	public void createRoot(E e) throws InvalidOperationException {
		if(raiz != null)
			throw new InvalidOperationException("Ya existe una raiz");
		raiz = new Tnodo<E> (e, null);
		size++;
		
	}

	@Override
	public Position<E> addFirstChild(Position<E> p, E e) throws InvalidPositionException {
		Tnodo<E> n = checkposition(p);
		Tnodo<E> toret = new Tnodo<E>(e, n);
		n.getHijos().addFirst(toret);
		size++;
		return toret;
		
	}

	@Override
	public Position<E> addLastChild(Position<E> p, E e) throws InvalidPositionException {
		Tnodo<E> n = checkposition(p);
		Tnodo<E> toret = new Tnodo<E>(e, n);
		n.getHijos().addLast(toret);
		size++;
		return toret;
	}

	@Override
	public Position<E> addBefore(Position<E> p, Position<E> rb, E e) throws InvalidPositionException {
		Position<Tnodo<E>> cursor = null;
		Tnodo<E> Pn = checkposition(p);
		Tnodo<E> Bn = checkposition(rb);
		Tnodo<E> toret = new Tnodo<E>(e, Pn);
		PositionList<Tnodo<E>> hijos = Pn.getHijos();
		try {
			cursor = hijos.first();
		} catch (EmptyListException e1) {
			e1.printStackTrace();
		}
		boolean esta = false;
		while(cursor != null && !esta) {
			if(cursor.element() == Bn) {
				esta = true;
				hijos.addBefore(cursor, toret);
				size++;
			} 
			else
			try {
				if(cursor == hijos.last())
					cursor = null;
				else
					cursor = hijos.next(cursor);
			} 
			catch (EmptyListException | InvalidPositionException | BoundaryViolationException e1) {	
				e1.printStackTrace();
			}
		}
		if(!esta)
			throw new InvalidPositionException("El primer nodo no es el padre del segundo");
		return toret;
		
	}

	@Override
	public Position<E> addAfter(Position<E> p, Position<E> lb, E e) throws InvalidPositionException {
		Position<Tnodo<E>> cursor = null;
		Tnodo<E> Pn = checkposition(p);
		Tnodo<E> Ln = checkposition(lb);
		Tnodo<E> toret = new Tnodo<E>(e, Pn);
		PositionList<Tnodo<E>> hijos = Pn.getHijos();
		try {
			cursor = hijos.first();
		} catch (EmptyListException e1) {
			e1.printStackTrace();
		}
		boolean esta = false;
		while(cursor != null && !esta) {
			if(cursor.element() == Ln) {
				esta = true;
				hijos.addAfter(cursor, toret);
				size++;
			} 
			else
			try {
				if(cursor == hijos.last())
					cursor = null;
				else
					cursor = hijos.next(cursor);
			} 
			catch (EmptyListException | InvalidPositionException | BoundaryViolationException e1) {	
				e1.printStackTrace();
			}
		}
		if(!esta)
			throw new InvalidPositionException("El primer nodo no es el padre del segundo");
			
		return toret;
	}

	@Override
	public void removeExternalNode(Position<E> p) throws InvalidPositionException {
		Tnodo<E> n = checkposition(p);
		if(isInternal(n))
			throw new InvalidPositionException("El nodo es Interno utilize otro metodo");
		if(isRoot(n)) { //Si llego hasta este punto es por que la raiz no tiene hijos
			raiz = null;
			size--;
		}
		else {	
			PositionList<Tnodo<E>> hijos = n.getPadre().getHijos();
			Position<Tnodo<E>> cursor = null;
			try {
				cursor = hijos.first();
			} catch (EmptyListException e) {
				e.printStackTrace();
			}
			boolean esta = false;
			while(!esta && cursor != null) {
				if(cursor.element() == n ) {
					esta = true;
					hijos.remove(cursor);
					size--;
				} 
				else
				try {
					if(cursor == hijos.last())
						cursor = null;
					else 
						cursor = hijos.next(cursor);
				} 
				catch (EmptyListException | InvalidPositionException | BoundaryViolationException e) {
						e.printStackTrace();
				}		
			}
		}
	}
	
	/**
	 * Elimina el nodo referenciado por una posici�n dada, si se trata de un nodo interno.
	 * Los hijos del nodo eliminado lo reemplazan en el mismo orden en el que aparecen. 
	 * Si el nodo a eliminar es la ra�z del �rbol, �nicamente podr� ser eliminado si tiene un solo hijo, 
	 * el cual lo reemplazar�.
	 */
	@Override
	public void removeInternalNode(Position<E> p) throws InvalidPositionException {
		Tnodo<E> n = checkposition(p);
		if(isExternal(n) || (n == raiz && raiz.getHijos().size()>1))
			throw new InvalidPositionException("Ingrese una posicion valida PORFAVOOOR");
		if(isRoot(n) && raiz.getHijos().size() == 1) {
			Position<Tnodo<E>> hijo = null;
			try {
				hijo = n.getHijos().first();
			} 
			catch (EmptyListException e) {
				e.printStackTrace();
			}
			raiz = hijo.element();
			raiz.setPadre(null);
			size--;
		}
		else {
			PositionList<Tnodo<E>> hijos = n.getPadre().getHijos();
			Position<Tnodo<E>> cursor = null;
			try {
				cursor = hijos.first();
			} catch (EmptyListException e) {
				e.printStackTrace();
			}
			boolean esta = false;
			while(!esta && cursor != null) {
				if(cursor.element() == n) {
					esta = true;
					for(Tnodo<E> nd : cursor.element().getHijos()) {
						hijos.addBefore(cursor, nd);
						nd.setPadre(cursor.element().getPadre());
					}
					hijos.remove(cursor);
					size--;	
				} 
				else
				try {
					if(cursor == hijos.last())
						cursor = null;
					else
						cursor = hijos.next(cursor);
				} 
				catch (EmptyListException | InvalidPositionException | BoundaryViolationException e) {
					e.printStackTrace();
				}
			}
		}
		
	}

	@Override
	public void removeNode(Position<E> p) throws InvalidPositionException {
		Tnodo<E> n = checkposition(p);
		if(isInternal(n))
			removeInternalNode(n);
		else
			removeExternalNode(n);
		
	}

}
