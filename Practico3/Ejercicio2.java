import java.util.ArrayList;
import java.util.Arrays;

public class Ejercicio2 {


	public static void main(String[] args) {
		
		
		ArrayList<Integer> lista1 = new ArrayList<Integer>(Arrays.asList(1,2,3,4,67,90));
		ArrayList<Integer> lista2 = new ArrayList<Integer>(Arrays.asList(1,5,6,7,4,5,8,10));
		System.out.println(Intercalar2(lista1,lista2));
		
		

	}
	
	public static <E> ArrayList<E> Intercalar (ArrayList<E> lista, ArrayList<E> lista2){
		
		ArrayList<E> ListaToreturn = new ArrayList<E>(lista.size()+lista2.size());
		int listaindex = 0;
		int lista2index = 0;
		int i = 0;
		
		while(lista.size()>listaindex && lista2.size()>lista2index) {
			if(i%2 == 0) {
				ListaToreturn.add(lista.get(listaindex));
				listaindex++;
			}
			else { 
				ListaToreturn.add(lista2.get(lista2index));
				lista2index++;
			}
			
			i++;	
		}
		if(lista.size() > listaindex)
			for(int j=listaindex ; j<lista.size(); j++)
				ListaToreturn.add(lista.get(j));
			
		if(lista2.size() > lista2index)
			for(int l=lista2index; l<lista2.size(); l++)
				ListaToreturn.add(lista2.get(l));
		
		return ListaToreturn;
	}
//	Dadas dos listas de enteros ordenadas L1 y L2, se desea obtener una tercera lista ordenada producto de la
//	intercalación de L1 y L2. En la lista resultante no debe haber elementos repetidos.
	
	public static <E> ArrayList<E> Intercalar2 (ArrayList<E> lista, ArrayList<E> lista2){
		ArrayList<E> ListaToreturn = new ArrayList<E>(lista.size()+lista2.size());
		int listaindex = 0;
		int lista2index = 0;
		int i = 0;
		
		while(lista.size()>listaindex && lista2.size()>lista2index) {
			if(i%2 == 0 ) {
				if(!ListaToreturn.contains(lista.get(listaindex)))
					ListaToreturn.add(lista.get(listaindex));
				else
					i++;
				listaindex++;
				
					
			}
			else if(i%2 != 0 ) {
				if(!ListaToreturn.contains(lista2.get(lista2index)))
					ListaToreturn.add(lista2.get(lista2index));
				else
					i++;
				lista2index++;
			}
			
			i++;	
		}
		if(lista.size() > listaindex)
			for(int j=listaindex ; j<lista.size(); j++)
				if(!ListaToreturn.contains(lista.get(j)))
					ListaToreturn.add(lista.get(j));
			
		if(lista2.size() > lista2index)
			for(int l=lista2index; l<lista2.size(); l++)
				if(!ListaToreturn.contains(lista2.get(l)))
					ListaToreturn.add(lista2.get(l));
		
		return ListaToreturn;
		
	}

}
