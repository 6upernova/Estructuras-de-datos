package pilas_Nodos;

public class Nodo<E> {
	
	private E elemento;
	private Nodo<E> siguiente;
	//Constructor 1:
	//Inicializa El elemento que almacena el nodo en null y su siguiente nodo en null
	public Nodo() {
		this(null, null);
		
	}
	
	public Nodo(E elem) {
		this(elem, null);
	}
	
	public Nodo(E elem,  Nodo<E> sig) {
		elemento = elem;
		siguiente = sig;
	}
	
	public void setElemento(E elemento) {
		this.elemento = elemento;
	}
	
	public void setSiguiente(Nodo<E> siguiente) {
		this.siguiente = siguiente;
	}
	
	public E getElement() {
		return elemento;
	}
	
	public Nodo<E> getSiguiente(){
		return siguiente;
	}

}
