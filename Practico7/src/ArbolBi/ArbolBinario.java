package ArbolBi;

import java.util.Iterator;


import Excepciones.BoundaryViolationException;
import Excepciones.EmptyTreeException;
import Excepciones.InvalidOperationException;
import Excepciones.InvalidPositionException;
import listas.ListDE;
import listas.Position;
import listas.PositionList;

public class ArbolBinario<E> implements BinaryTree<E>  {
	
	protected Bnode<E> raiz;
	protected int size;
	

	public ArbolBinario() {
		raiz = null;
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
	
	private Bnode<E> checkposition(Position<E> p) throws InvalidPositionException	{
		Bnode<E> n;
		if(p == null)
			throw new InvalidPositionException("Malisimo bro no mandes posiciones nulas bobo");
		if(p.element() == null)
			throw new InvalidPositionException("No se puede crear un nodo con elemento nulos");
		if(size == 0)
			throw new InvalidPositionException("La lista esta vacia");
		try {
			n = (Bnode<E>) p;
		}
		catch (ClassCastException e ){
			throw new InvalidPositionException("La posicion es invalida"); 
		}
		
		return n;
		
	}

	
	private void preorden(Bnode<E> p, PositionList<E> l) {
		//visita
		l.addLast(p.element());
		//Recorrer recursivamente todos los hijos
		if(p.getHizq() != null)	
			preorden(p.getHizq(), l);
		if(p.getHder() != null)
			preorden(p.getHder(), l);
	}
	
	private void pospreorden(Bnode<E> p, PositionList<Position<E>> l) {
		//visita
		l.addLast(p);
		//Recorrer recursivamente todos los hijos
		if(p.getHizq() != null)	
			pospreorden(p.getHizq(), l);
		if(p.getHder() != null)
			pospreorden(p.getHder(), l);
	}
	
	@Override
	public Iterator<E> iterator() {
		PositionList<E> toret = new ListDE<E>();
		if(raiz != null)
			preorden(raiz, toret);
		return toret.iterator();
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
		Bnode<E> n = checkposition(v);
		E toret = n.element();
		n.setElem(e);
		return toret;
	}

	@Override
	public Position<E> root() throws EmptyTreeException {
		if(size == 0)
			throw new EmptyTreeException("El arbol esta vacio");
		return raiz;
	}

	@Override
	public Position<E> parent(Position<E> v) throws InvalidPositionException, BoundaryViolationException {
		Bnode<E> n = checkposition(v);
		if(isRoot(n))
			throw new BoundaryViolationException("La raiz no tiene padre");
		return n.getPadre();
	}

	@Override
	public Iterable<Position<E>> children(Position<E> v) throws InvalidPositionException {
		Bnode<E> n = checkposition(v);
		PositionList<Position<E>> toret = new ListDE<Position<E>>();
		if(n.getHizq()!= null)
			toret.addLast(n.getHizq());
		if(n.getHder()!= null)
			toret.addLast(n.getHder());
		return toret;
	}

	@Override
	public boolean isInternal(Position<E> v) throws InvalidPositionException {
		return (!(checkposition(v).getHizq() == null) || !(checkposition(v).getHizq() == null) );
		
	}

	@Override
	public boolean isExternal(Position<E> v) throws InvalidPositionException {
		return !isInternal(v);
	}

	@Override
	public boolean isRoot(Position<E> v) throws InvalidPositionException {
		return checkposition(v).equals(raiz);
	}

	@Override
	public Position<E> left(Position<E> v) throws InvalidPositionException, BoundaryViolationException {
		if(checkposition(v).getHizq() == null)
			throw new BoundaryViolationException("El nodo no posee un hijo izquierdo");
		return checkposition(v).getHizq();
	}

	@Override
	public Position<E> right(Position<E> v) throws InvalidPositionException, BoundaryViolationException {
		if(checkposition(v).getHder() == null)
			throw new BoundaryViolationException("El nodo no posee un hijo derecho");
		return checkposition(v).getHder();
	}

	@Override
	public boolean hasLeft(Position<E> v) throws InvalidPositionException {
		return checkposition(v).getHizq() != null;
	}

	@Override
	public boolean hasRight(Position<E> v) throws InvalidPositionException {
		return checkposition(v).getHder() != null;
	}

	@Override
	public Position<E> createRoot(E r) throws InvalidOperationException {
		if (raiz != null)
			throw new InvalidOperationException("Ya existe un nodo raiz");
		
		Bnode<E> toret = new Bnode<E>(r,null, null, null);
		raiz = toret;
		size++;
		return toret;
	}

	@Override
	public Position<E> addLeft(Position<E> v, E r) throws InvalidOperationException, InvalidPositionException {
		Bnode<E> n = checkposition(v);
		if (n.getHizq() != null)
			throw new InvalidOperationException("El nodo ya posee un hijo izquierdo");
		Bnode<E> toret = new Bnode<E>(r, n, null, null);
		n.setHizq(toret);
		size++;
		return toret;
	}

	@Override
	public Position<E> addRight(Position<E> v, E r) throws InvalidOperationException, InvalidPositionException {
		Bnode<E> n = checkposition(v);
		if (n.getHder() != null)
			throw new InvalidOperationException("El nodo ya posee un hijo derecho");
		Bnode<E> toret = new Bnode<E>(r, n, null, null);
		n.setHder(toret);
		size++;
		return toret;
	}

	@Override
	public E remove(Position<E> v) throws InvalidOperationException, InvalidPositionException {
		Bnode<E> n = checkposition(v);
		if(n.getHizq() != null && n.getHder() != null)
			throw new InvalidOperationException ("no se puede eliminar un nodo con mas de un hijo");
		
		E toret = n.element();
		Bnode<E> hijo = null;
		
		if(n.getHizq() != null) {
			hijo = n.getHizq();
			hijo.setPadre(n.getPadre());
		}
		else if( n.getHder() != null) {
				hijo = n.getHder();
				hijo.setPadre(n.getPadre());
		}
		
		
		if (hijo == null) { // Case: el nodo a eliminar no tenga hijos
			if (n.getPadre() == null) // Case: nodo a eliminar sea la raiz
				raiz = null;
			else
				if (n.getPadre().getHizq().equals(n))
					n.getPadre().setHizq(null);
				else
					n.getPadre().setHder(null);
		}
		
		else { // Case: el nodo a eliminar si tiene hijos
			if (hijo.getPadre() == null) // Case: el nodo a eliminar sea la raiz
				raiz = hijo;
			else // Case: el nodo a eliminar tiene un solo hijo
				if (hijo.getPadre().getHizq().equals(n)) {
					hijo.getPadre().setHizq(hijo);
				}
				else
					hijo.getPadre().setHder(hijo);	
		}
		size--;
		return toret;
			
	}

	@Override
	public void attach(Position<E> r, BinaryTree<E> T1, BinaryTree<E> T2) throws InvalidPositionException {
		Bnode <E> n = checkposition(r);
		if(isInternal(n))
			throw new InvalidPositionException("No se puede realizar la operacion sobre un nodo interno");
		try {
			n.setHder((Bnode<E>) T2.root());
			n.setHizq((Bnode<E>) T1.root());
			size += T1.size() + T2.size();
		} 
		catch (EmptyTreeException e) {
			System.out.println(e.getMessage());
		}
		
		
	}

}
