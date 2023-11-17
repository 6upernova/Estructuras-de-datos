package listas;
//Nodo doblemente enlazado
public class Nodo<E> implements Position<E> {
	
	protected E elemento;
	
	protected Nodo<E> siguiente, anterior;

	public Nodo() {
		this(null,null,null);
	}
	
	public Nodo(Nodo<E> sig, Nodo<E> ant, E elem) {
		siguiente = sig;
		anterior = ant;
		elemento = elem;
	}
	
	public void setElemento(E elem) {
		elemento = elem;
	}
	
	public void setSiguiente(Nodo<E> sig) {
		siguiente = sig;	
	}
	
	public void setAnterior(Nodo <E> ant) {
		anterior = ant;
	}
	
	public E element ()	{
		return elemento;
	}
	
	public Nodo<E> getSiguiente(){
		return siguiente;
	}
	
	public Nodo<E> getAnterior(){
		return anterior;
	}
	
	

}
