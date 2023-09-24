package Conjuntos;
import java.util.Vector;

public class ConjuntoVector<E> implements Conjunto<E> {

		protected Vector<E> conj;
		
		public ConjuntoVector() {
			conj=new Vector<E> ();
		}
		
		public int size() {
			return conj.size();
		}
		
		public int capacity() {
			return conj.capacity();
		}
		
		public boolean isempty() {
			boolean estavacio=true;
			for(int i=0; i<capacity() && estavacio; i++)
				if(conj.get(i)!=null)
					estavacio=false;
			return estavacio;
		}
		
		public E get(int i) {
			return conj.get(i);	
		}
		
		public void put(E elem) {
			conj.add(elem);
		}
		
		public boolean pertenece(E elem, int n) {
			boolean esta=false;
			//Caso base
			if(n==0)
				esta=false;
			else
				if(conj.get(n-1).equals(elem))
					esta=true;
				else
					esta=pertenece(elem,n-1);
			return esta;
		}
		
		public Conjunto<E> interseccion(Conjunto<E> c){
			Conjunto<E> masLongo;
			Conjunto<E> menosLongo;
			Conjunto<E> toReturn;
			if(conj.capacity()<c.capacity()) {
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
