package Conjuntos;

public class ConjuntoArreglo<E> implements Conjunto<E>{

	protected E[] conj;
	
	public ConjuntoArreglo(int max) {
		conj=(E[]) new Object[max];
	}
	
	public int size() {
		int cont=0;
		for(int i=0; i<capacity(); i++)
			if(conj[i]!=null)
				cont++;
		return cont;
	}
	
	public int capacity() {
		return conj.length;
	}
	
	public boolean isempty() {
		boolean estavacio=true;
		for(int i=0; i<capacity() && estavacio; i++)
			if(conj[i]!=null)
				estavacio=false;
		return estavacio;
	}
	
	public E get(int i) {
		return conj[i];	
	}
	
	public void put(E elem) {
		boolean corte=false;
		for(int i=0; i<capacity() && !corte; i++)
			if(conj[i]==null) {
				conj[i]=elem;
				corte=true;
			}
	}
	
	public boolean pertenece(E elem, int n) {
		//n = tamaño del conjunto
		boolean esta=false;
		//Caso base
		if(n==0)
			esta=false;
		//Caso General
		else
			if(conj[n-1].equals(elem))
				esta=true;
			else
				esta=pertenece(elem,n-1);
		return esta;
	}
	
	public Conjunto<E> interseccion(Conjunto<E> c){
		Conjunto<E> masLongo;
		Conjunto<E> menosLongo;
		Conjunto<E> toReturn;
		if(conj.length<c.capacity()) {
			menosLongo = this;
			masLongo = c;
		}
		else {
			menosLongo=c;
			masLongo=this;
		}
		toReturn = new ConjuntoArreglo<E>(menosLongo.capacity());
		for(int i=0; i<toReturn.capacity(); i++)
			if(masLongo.pertenece(menosLongo.get(i),masLongo.capacity() ))
				toReturn.put(menosLongo.get(i));
		
		return toReturn;
			
	}
	
	//private 
	
	
	
	
}
