package ArbolBi;

import listas.Position;

public class Bnode<E> implements Position<E>{
	
	protected E elem;
	protected Bnode<E> Hder;
	protected Bnode<E> Hizq;
	protected Bnode<E> padre;

	public Bnode(E elem, Bnode<E> padre, Bnode<E> izq, Bnode<E> der ) {
		this.elem = elem;
		this.padre = padre;
		Hizq = izq;
		Hder = der;
		
	}

	@Override
	public E element() {
		return elem;
	}

	public E getElem() {
		return elem;
	}

	public void setElem(E elem) {
		this.elem = elem;
	}

	public Bnode<E> getHder() {
		return Hder;
	}

	public void setHder(Bnode<E> hder) {
		Hder = hder;
	}

	public Bnode<E> getHizq() {
		return Hizq;
	}

	public void setHizq(Bnode<E> hizq) {
		Hizq = hizq;
	}

	public Bnode<E> getPadre() {
		return padre;
	}

	public void setPadre(Bnode<E> padre) {
		this.padre = padre;
	}

}
