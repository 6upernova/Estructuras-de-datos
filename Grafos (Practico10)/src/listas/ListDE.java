package listas;
import java.util.Iterator;



import Excepciones.BoundaryViolationException;
import Excepciones.EmptyListException;
import Excepciones.InvalidPositionException;

public class ListDE<E> implements PositionList<E> {
	
	protected int cantElem;
	protected Nodo<E> header; //Dummys
	protected Nodo<E> treiler;

	public ListDE() {
		header = new Nodo<E> (null,null,null); 
		treiler = new Nodo<E>	(null, header, null); //siguiente, anterior, elememento
		header.setSiguiente(treiler);
		cantElem = 0;
	}
	
	public int size() {
		return cantElem;
	}
	
	public boolean isEmpty() {
		return cantElem == 0;
	}
	
	public Position<E> first() throws EmptyListException{
		if(cantElem == 0)
			throw new EmptyListException("La lista esta vacia no existe primer elemento");
		return header.getSiguiente();
	}
	
	public Position<E> last () throws EmptyListException{
		if(cantElem == 0)
			throw new EmptyListException("La lista esta vacia no existe ultimo elemento");
		return treiler.getAnterior();
	}
	
	public Position<E> next(Position<E> p) throws InvalidPositionException, BoundaryViolationException{
		Nodo<E> n = CheckPosition(p);
		if(n.getSiguiente() == treiler)
			throw new BoundaryViolationException("El ultimo elemento no tiene siguiente");
		return n.getSiguiente();
	}
	
	public Position<E> prev(Position<E> p)	throws InvalidPositionException, BoundaryViolationException{
		Nodo<E> n = CheckPosition(p);
		if(n.getAnterior() == header)
			throw new BoundaryViolationException("El primer elemento no tiene anterior");
		return n.getAnterior();
	}
	
	public void addLast(E elem) {
		Nodo<E> n = new Nodo<E> (treiler,treiler.getAnterior(), elem);
		treiler.getAnterior().setSiguiente(n);
		treiler.setAnterior(n);
		cantElem++;
		
	}
	
	public  void addFirst(E elem) {
		Nodo<E> n = new Nodo<E>(header.getSiguiente(),header , elem);
		header.getSiguiente().setAnterior(n);
		header.setSiguiente(n);
		cantElem++;
	}
	
	
	
	private Nodo<E> CheckPosition(Position<E> p) throws InvalidPositionException{
		Nodo<E> n;
		if(p == null)
			throw new InvalidPositionException("p es nulo");
		if(p.element() == null)
			throw new InvalidPositionException("No se puede crear un nodo con elemento nulos");
		if(p == header || p == treiler)
			throw new InvalidPositionException("P no es una posicion valida");
		if(cantElem == 0)
			throw new InvalidPositionException("La lista esta vacia");
		try {
			n = (Nodo<E>) p;
		}
		catch (ClassCastException e ){
			throw new InvalidPositionException("La posicion es invalida"); 
		}
		return  n;
		
		
	}

	@Override
	public void addAfter(Position<E> p, E element) throws InvalidPositionException {
		Nodo <E> n = CheckPosition(p);
		Nodo <E> n2 = new Nodo<E>(n.getSiguiente(), n, element);
		n.getSiguiente().setAnterior(n2);
		n.setSiguiente(n2);
		cantElem++;
		
	}

	@Override
	public void addBefore(Position<E> p, E element) throws InvalidPositionException {
		Nodo <E> n = CheckPosition(p);
		Nodo <E> n2 = new Nodo<E> (n,n.getAnterior(),element);
		n.getAnterior().setSiguiente(n2);
		n.setAnterior(n2);
		cantElem++;
		
	}

	@Override
	public E remove(Position<E> p) throws InvalidPositionException {
		Nodo <E> n = CheckPosition(p);
		n.getAnterior().setSiguiente(n.getSiguiente());
		n.getSiguiente().setAnterior(n.getAnterior());
		cantElem--;
		return n.element();
	}

	@Override
	public E set(Position<E> p, E element) throws InvalidPositionException {
		Nodo <E> n = CheckPosition(p);
		E rtn = n.element();
		n.setElemento(element);
		return rtn;
	}

	@Override
	public Iterator<E> iterator() {
		return new ElementIterator<E>(this);
	}

	@Override
	public Iterable<Position<E>> positions()  {
		PositionList<Position<E>> l = new ListDE<Position<E>>();
		Position<E> p;
		if(!isEmpty()) {
			try {
				p = first();
				while(p != null) {
					l.addLast(p);
					if(p == l.last())
						p = null;
					else
						p = next(p);
				}
			}
			catch(EmptyListException | InvalidPositionException | BoundaryViolationException e){
				e.getMessage();
			}	
		}
		return l;	
	}
	
	public void SegundoyAnteUltimo(E e1, E e2) throws EmptyListException {
		if(isEmpty() || size()<2)
			throw new EmptyListException("La lista esta vacia o no tiene los elemento necesarios para realizar esta operacion");
		//Colocar e1 en la segunda posicion y e2 en la anteultima
		try {
			addAfter(first(), e1);
			addBefore(last(), e2);
		}
		catch (InvalidPositionException | EmptyListException e) {
			System.out.println(e.getMessage());
		}
		
	}
}


