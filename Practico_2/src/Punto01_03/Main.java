package Punto01_03;
import java.util.Queue;
import java.util.Stack;
import java.util.LinkedList;


public class Main {

	public static void main(String[] args) {
		
		

	}
	
	public static void invertirPersonasPila(persona[] parray) {
		Stack<persona> s= new Stack<persona>();
		while(!parray.isEmpety())
			s.put(parray.rmbottom());
		while(!s.isEmpety())
			parray.add(s.pop());
	}
	
	public static Queue<Integer> ColaImpares(Queue<Integer> cola) {
		Queue<Integer> aux = new LinkedList<Integer>();
		while(!cola.isEmpty())
			if(esImpar(cola.peek()))
				aux.add(cola.poll());
			else
				cola.poll();
		return aux;
	}
	
	public static boolean esImpar(Integer num) {
		boolean es=false;
		if((num % 2)==1)
			es=true;
		return es;
	}
	
	public static<E> Stack<E> Intercalar(Stack<E> pila1, Stack<E> pila2) {
		Stack<E> pila3 = new Stack<E>();
		while(!pila1.isEmpty() && !pila2.isEmpty()) {
			if(!pila1.isEmpty())
				pila3.push(pila1.pop());
			if(!pila2.isEmpty())
				pila3.push(pila2.pop());
		}
		return pila3;
	}
	
	
	

}
