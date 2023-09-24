package TDAPila;

public class Nodo<E> {
	
	private E elemento;
	private Nodo<E> siguiente;
	
	//constructores
	/**
	 * Constructor 1
	 * Inicializa el elemento que almacena el Nodo en null, y su siguiente Nodo en Null.
	 */
	public Nodo() {
		this(null,null);
	}
	/**
	 * Constructor 2
	 * Inicializa el Nodo con el elemento recibido por paramátro, y su siguiente Nodo en null.
	 * @param item:elemento a almacenar en el corriente Nodo.
	 */
	public Nodo(E item) {
		this(item,null);
	}
	
	/**
	 * Constructor 3
	 * Inicializa ambos atributos del Nodo actual.
	 * @param item:elemento a almacenar en el corriente Nodo.
	 * @param sig:siguiente nodo al corriente.
	 */
	public Nodo(E item,Nodo<E> sig) {
		elemento = item;
		siguiente = sig;
	}
	
	//setters
	/**
	 * Establece el elemento que almacenará el Nodo.
	 * @param elemento:elemento a almacenar en el nodo.
	 */
	public void setElemento(E elemento) {
		this.elemento = elemento;
	}
	
	/**
	 * Establece el siguiente nodo del corriente nodo.
	 * @param siguiente:nodo siguiente.
	 */
	public void setSiguiente(Nodo<E> siguiente) {
		this.siguiente = siguiente;
	}
	
	//getters
	/**
	 * @return El elemento que almacena el Nodo.
	 */
	public E getElemento() {
		return elemento;
	}
	
	/**
	 * @return El siguiente Nodo del Nodo actual.
	 */
	public Nodo<E> getSiguiente(){
		return siguiente;
	}
}
