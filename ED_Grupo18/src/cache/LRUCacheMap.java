package cache;

import java.security.InvalidKeyException;


import excepciones.EmptyListException;
import excepciones.InvalidPositionException;
import listas.ListDE;
import listas.Position;
import listas.PositionList;
import mapeo.HashMap;
import mapeo.Map;

public class LRUCacheMap<K,V> implements LRUCache<K,V> {
	
	/**
	 * Creamos 3 Estructuras subyacentes para emular la lru cache:
	 * Hmap(Mapeo): Es la ED principal que se encarga de almacenar las entradas K,V ingresadas por el usuario, 
	 * Prioridad(PositionList): Es una lista ordenada donde almacenamos las claves K y las ordenamos de acuerdo a su ultimo uso(De mas a menos)
	 * Posmap(Mapeo) : Utilizado en guardar las posiciones de la PL prioridad para asi poder hacer remove sin la necesidad de recorrer toda la estructura en busqueda de las mismas 
	 */
	
	protected PositionList<K> prioridad;
	protected Map<K,V> hmap;
	protected Map<K,Position<K>> posmap;
	protected int capacity;

	public LRUCacheMap(int c) {
		capacity = c;
		hmap = new HashMap<K,V>();
		prioridad = new ListDE<K>();
		posmap = new HashMap<K,Position<K>>();
	}

	@Override
	public V get(K key) {
		V toret = null;
		try {
			toret = hmap.get(key);
			
			if(toret != null) {
				prioridad.remove(posmap.get(key)); //Si el elemento existe en hmap entonces pasa a ser el primer elemento de la prioridad
				prioridad.addFirst(key);
				posmap.put(key, prioridad.first()); //Actualizamos la posicion luego de intercambiar su lugar
			}
		}
		catch (InvalidKeyException | InvalidPositionException | EmptyListException e) {
			e.printStackTrace();
		}
		return toret;
		
	}
	
	
	@Override
	public void put(K key, V value) {
		try {
			
			if(hmap.size() == capacity && hmap.get(key) == null) { //Verificamos si no se excedio de la capacidad y si el elemento ya existia previamente en la ED
				hmap.remove(prioridad.last().element());
				posmap.remove(prioridad.last().element()); // Si es el caso entonces eliminamos el ultimo elemento que se encuentra en la prioridad ( el menos usado)
				prioridad.remove(prioridad.last());
			}
			
			if(hmap.put(key,value) !=null) //Colocamos la entrada en la cache y nuevamente verificamos si el elemento ya existe pero para eliminarlo de la prioridad si es el caso
				prioridad.remove(posmap.get(key));
			
			prioridad.addFirst(key); // Agregamos el nuevo elemento como primer elemento de la prioridad (elemento mas reciente)
			posmap.put(key, prioridad.first()); // Guardamos su posicion en el mapeo auxiliar para facilitar el o(1)
		}
		 catch (InvalidKeyException | EmptyListException | InvalidPositionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void remove(K key) {
		try {
			hmap.remove(key);
			prioridad.remove(posmap.get(key));
			posmap.remove(key);
		}
		 catch (InvalidKeyException | InvalidPositionException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void clear() {
		hmap = new HashMap<K,V>();
		prioridad = new ListDE<K>();
		posmap = new HashMap<K,Position<K>>();
		
	}

	@Override
	public int size() {
		return hmap.size();
	}

}
