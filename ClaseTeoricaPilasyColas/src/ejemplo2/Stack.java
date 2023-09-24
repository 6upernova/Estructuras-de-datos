package ejemplo2;

public interface Stack<E> {
	
	public int size();
	
	public boolean isEmpty();
	
	public void push(E elem); //Apila
	
	public E pop() throws EmptyStackException;  //Desapila
	
	public E top() throws EmptyStackException;  //Muestra el tope de la pila
	
	
}
