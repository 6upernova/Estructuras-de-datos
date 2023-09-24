package Conjuntos;

public interface Conjunto<E> {
	public int size();
	
	public int capacity();
	
	public boolean isempty();
	
	public E get(int i);
	
	public void put(E elem);
	
	public boolean pertenece(E elem,int n);
	
	public Conjunto<E> interseccion(Conjunto<E> c);
}
