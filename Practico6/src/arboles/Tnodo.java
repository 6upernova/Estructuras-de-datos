package arboles;

import listas.*;

public class Tnodo<E> implements Position<E>{
	
	protected E elem;
	protected PositionList<Tnodo<E>> hijos;
	protected Tnodo<E> padre;
	
	public Tnodo(E elem, Tnodo<E> p) {
		this.elem = elem;
		padre = p;
		hijos = new ListDE<Tnodo<E>> ();
	}

	@Override
	public E element() {
		return elem;
	}
	
	public PositionList<Tnodo<E>> getHijos() {
		return hijos;
	}
	
	public Tnodo<E> getPadre() {
		return padre;
	}

	public void setElem(E elem) {
		this.elem = elem;
	}

	

	public void setPadre(Tnodo<E> padre) {
		this.padre = padre;
	}
	
	

}
