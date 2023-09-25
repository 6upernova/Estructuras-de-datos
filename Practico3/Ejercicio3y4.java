import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;
import java.util.Collections;

public class Ejercicio3y4{

	public static void main(String[] args) {
		
		ArrayList<Integer> lista1 = new ArrayList<Integer>(Arrays.asList(1,2,3,4,4,3,2,1,5));
		ArrayList<Integer> lista2 = new ArrayList<Integer>(Arrays.asList(1, 2));
		System.out.println(listaEspejo(lista1, lista2));
		eliminar(lista1,lista2);
		System.out.println(lista1);

	}
	
	public static <E> void Invertir(ArrayList<E> l) {
		Stack <E> P = new Stack<E>();
		for(int i=0; i<l.size(); i++) {
			P.push(l.get(i));
		}
		l.clear();
		while(!P.isEmpty())
			l.add(P.pop());
	}
	
	//Ejercicio 4
	
	public static <E> boolean listaEspejo(ArrayList<E> L1, ArrayList<E> L2) {
		boolean es = true;
		if(L1.size()!=2*L2.size())
			es = false;
		
		for(int i=0; i<L2.size() && es; i++) {
				if(!L2.get(i).equals(L1.get(i)))
					es=false;	
		}
		for(int i=0; i<L2.size() ;i++)
			if(!L1.get(L1.size()-i-1).equals(L2.get(i)))
				es=false;
		
		return es;
	}
	
	//Ejercicio 5
	
	public static <E> void eliminar(ArrayList<E> L1, ArrayList<E> L2) {
		Stack<E> p = new Stack<E>();
		
		L1.removeAll(L2);
		
		for(E elemento:L2) //Element iterator
			p.push(elemento);
			
		L1.addAll(p);
		
	}
	

}
