package grafoNoDirigido;

public class Arco<V,E> implements Edge<E> {

	protected listas.Position<Arco<V, E>> pos;
	protected Vertice<V, E> v1, v2;
	protected E rotulo;
	
	
	public Arco (Vertice<V, E> v1, Vertice<V, E> v2, E elem) {
		this.v1 = v1;
		this.v2 = v2;
		rotulo = elem;
	}
	public E element() {
		// TODO Auto-generated method stub
		return rotulo;
	}
	
	public Vertice<V, E> v1(){
		return v1;
	}
	
	public Vertice<V, E> v2(){
		return v2;
	}
	
	public listas.Position<Arco<V, E>> getPos() {
		return pos;
	}
	
	public void setPos(listas.Position<Arco<V, E>> position) {
		this.pos = position;
		
	}
	
	
	
	public void setElem(E elem) {
		rotulo = elem;
	}
	
	

}
