package MapeoconABB;

public class ABBnode<E> implements Position<E> {
	
	private E elem;
	private ABBnode<E> padre;
	private ABBnode<E> hijoder;
	private ABBnode<E> hijoizq;
	
	public ABBnode( E elem, ABBnode<E> p) {
		padre = p;
		this.elem = elem;
		setHijoder(null);
		setHijoizq(null);
		
	}
	
	
	public E element() {
		return elem;
	}
	public E getElem() {
		return elem;
	}
	public void setElem(E elem) {
		this.elem = elem;
	}
	public ABBnode<E> getPadre() {
		return padre;
	}
	public void setPadre(ABBnode<E> padre) {
		this.padre = padre;
	}
	public ABBnode<E> getHijoder() {
		return hijoder;
	}
	public void setHijoder(ABBnode<E> hijoder) {
		this.hijoder = hijoder;
	}
	public ABBnode<E> getHijoizq() {
		return hijoizq;
	}
	public void setHijoizq(ABBnode<E> hijoizq) {
		this.hijoizq = hijoizq;
	}
	
	
	
	

}
