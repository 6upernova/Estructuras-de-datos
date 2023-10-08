import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
public class Ejercicio1 {


	public static void main(String[] args) {
		int [] arr = {1,3,4,5,6};
		ArrayList<Integer> list1 = new ArrayList<Integer>(Arrays.asList(1,2,4,5,3,3,3,3,3,3));
		System.out.println((isElement2(list1, 3)));
		System.out.println(almenosNVeces(list1, 3, 3));

	}
	
	public static <E> boolean isElement(ArrayList<E> list, E element ) {
		return list.contains(element);
	}
	
	public static <E> boolean almenosNVeces(ArrayList<E> list, E element, int n) {
		int cont=0;
		boolean hay = false;
		for(int i=0; i<list.size() && !hay; i++) {
			if(list.get(i)==element)
				cont++;
			if(cont==n)
				hay = true;
		}
		return hay;
	}
	
	public static <E> boolean almenosNVeces2(ArrayList<E> list, E element, int n) {
		int cont = 0;
		Iterator<E> it = list.iterator();
		while(it.hasNext() && cont < n) {
			E elem = it.next();
			if(elem == element)
				cont++;
			
		}
		return cont == n;
	}
	
	public static <E> boolean isElement2(ArrayList<E> list, E element) {
		boolean esta = false;
		Iterator <E> it = list.iterator();
		while(it.hasNext()) {
			E elem = it.next();
			if(elem.equals(element))
				esta = true;
		}
		return esta;
			
			
	}
	
	
	
	

}
