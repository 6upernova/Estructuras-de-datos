import java.util.ArrayList;
public class Ejercicio1 {


	public static void main(String[] args) {
		ArrayList<Integer> list1 = new ArrayList<Integer>();
		list1.add(3);
		System.out.println((isElement(list1, 3)));

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
	
	
	
	

}
