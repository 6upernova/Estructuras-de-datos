package Conjuntos;

public class TesterConjunto {

	public static void main(String[] args) {
		Conjunto<Integer> conjunto12 = new ConjuntoArreglo<Integer>(4);
		conjunto12.put(7);
		conjunto12.put(9);
		conjunto12.put(10);
		conjunto12.put(3);
		mostrar(conjunto12);
		
		System.out.println("------------------------------------------");
		Conjunto<Integer> conjunto13 = new ConjuntoArreglo<Integer>(6);
		conjunto13.put(7);
		conjunto13.put(9);
		conjunto13.put(5);
		conjunto13.put(10);
		conjunto13.put(12);
		conjunto13.put(3);
		System.out.println(conjunto13.pertenece(3,7));
		mostrar(conjunto12.interseccion(conjunto13));
	}
	
	public static void mostrar(Conjunto<?> c) {
		for(int i=0; i<c.capacity(); i++)
			System.out.println(c.get(i));
		System.out.println(c.size());
	}
}
