import java.util.LinkedList;
import java.util.Stack;
import java.util.Queue;

public class EjemplosInversion {
	
	public static void main(String args[]) {
		Stack<String> Pila1 = new Stack<String>();
		
		
		
	}
	//Invertir Pila utilizando una cola
	public static void invertirPila(Stack<String> p) {
		Queue<String> c = new LinkedList<String>();
		
		while(!p.isEmpty()) {
			c.add(p.pop());
		}
		while(!c.isEmpty())
			p.add(c.remove());
	}
	//Invertir una cola utilizando una pila
	public static void invertirCola(Queue<String> c) {
		Stack<String> p= new Stack<String>();
		
		while(!c.isEmpty())
			p.add(c.remove());
		while(!p.isEmpty())
			c.add(p.pop());
	}
}
