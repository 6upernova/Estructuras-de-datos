package ejemplo2;
import java.util.Stack;

public class ParentesisBalanceados {

	public static void main(String[] args) {
	

	}
	
	/**
	 * Verifica si una cadena tiene los parentesis bien balanceados
	 * 
	 *
	 */
	public static boolean ParentesisBienBalanceados(String cadena) {
		Stack<Character> pila = new Stack<Character>();
		boolean cumple = true;
		
		for (int i=0; i< cadena.length() && cumple; i++ ) {
			char c = cadena.charAt(i);
			if(c == '(')
				pila.push(c);
			if(c == ')') {
				if(!pila.isEmpty())
					pila.pop();
				else
					cumple = false;
			}
		}
		return cumple;
			
	}

}
