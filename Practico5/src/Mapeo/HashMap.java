package Mapeo;
import java.security.InvalidKeyException;


import java.util.Iterator;

import Excepciones.InvalidPositionException;
import listas.*;
import Mapeo.*;

public class HashMap<K, V> implements Map<K,V> {
	
	protected int N; //Cantidad de buckets
	
	protected int n; //Cantidad de entradas del mapeo
	
	protected PositionList<Entrada<K,V>> [] buckets; 

	public HashMap() {
		n = 0;
		N = 11;
		buckets = new ListDE[N];
		for(int i = 0; i<N; i++)
			buckets[i] = new ListDE<Entrada<K,V>>();
			
	}

	@Override
	public int size() {
		return n;
	}

	@Override
	public boolean isEmpty() {
		return n == 0;
	}
	
	/**
	 * Metodo que te permite convertir cualquier Parametro Key a un int codigohash al cual le aplicamos la funcion mod (Resto de una division) 
	 * con el numero de buckets y nos da el numero de la posicion del arreglo donde se colocara esa entrada 
	 * @param key
	 * @return void
	 * @throws InvalidKeyException
	 */
	private int h(K key) throws InvalidKeyException {
		if(key == null)
			throw new InvalidKeyException("La clave pasada por parametro no puede ser nula");
		return Math.abs(key.hashCode() % N);
	}
	
	/**
	 * Metodo que te permite automaticamente agrandar la lista y reacomodar sus componentes de manera equitativa
	 */
	
	private void rehash() {
		Iterable<Entry<K,V>> entradas = entries(); //Guardo todas las entradas para reinicializar los buckets con un tamaño mayor
		N = nextprimo(N);
		n=0;
		buckets = new ListDE[N];
		for(int i = 0; i < N; i++)
			buckets[i] = new ListDE<Entrada<K,V>>();
		for(Entry<K,V> e : entradas)
			try {
				put(e.getKey(), e.getValue());
			} catch (InvalidKeyException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
	}
	
	private int nextprimo(int n) {
		int siguiente = n+1;
		int toreturn = 0;
		
		while(toreturn == 0) {
			if(esprimo(siguiente))
				toreturn = siguiente;
			siguiente++;
		}
		return toreturn;
	}
	private boolean esprimo(int n) {
		boolean es = true;
		if(n < 2)
			es = false;
		
			for(int i = 2 ; i<Math.sqrt(n) && es; i++)
				if(n % i == 0)
					es = false;
		return es;
	}
	 

	@Override
	public  V get(K key)throws InvalidKeyException {
		if(key == null)
			throw new InvalidKeyException("La clave pasada por parametro no puede ser nula");
		
		boolean encontre = false;
		V toret = null;
		
		Iterator<Entrada<K,V>> it = buckets[h(key)].iterator(); 
		
		while(it.hasNext() && !encontre) {
			Entrada<K,V> e = it.next();
			if(e.getKey().equals(key)) { //Implementado sin equals por el tester
				toret = e.getValue();
				encontre = true;
			}
				
			
		}
		return toret;
	}

	@Override
	public V put(K key, V value) throws InvalidKeyException {
		if(key == null)
			throw new InvalidKeyException("La clave pasada por parametro no puede ser nula");
		boolean existe  = false;
		V toret = null;
		Iterator<Entrada<K,V>> it = buckets[h(key)].iterator();
		while(it.hasNext() && !existe) {
			Entrada<K,V> e = it.next();
			if(e.getKey().equals(key)) { //Comparados sin equals por el tester
				toret = e.getValue();
				e.setValue(value);
				existe = true;
			}
		}
		if(!existe) {
			Entrada<K,V> nueva = new Entrada<K,V>(key, value);
			buckets[h(key)].addLast(nueva);
			n++;
		}
		if(!((n/N) < 0.5))
			rehash();
		return toret;
	}

	@Override
	public V remove(K key) throws InvalidKeyException {
		if(key == null)
			throw new InvalidKeyException("La clave pasada por parametro no puede ser nula");
		
		V toret = null;
		boolean esta = false;
		Iterator<Position<Entrada<K,V>>> it = buckets[h(key)].positions().iterator();
		
		while(it.hasNext() && !esta) {
			
			Position<Entrada<K,V>> e = it.next();
			if(e.element().getKey().equals(key)) {
				
				toret = e.element().getValue();
				try {
					buckets[h(key)].remove(e);
					
				} catch (InvalidKeyException | InvalidPositionException e1) {
					e1.printStackTrace();
				}
				
				n--;	
			}
		}
		
		if(!((n/N) < 0.5))
			rehash();
		
		return toret;
	}

	@Override
	public Iterable<K> keys() {
		PositionList<K> toret = new ListDE<K>();
		for(PositionList<Entrada<K,V>> p : buckets )
			for(Entrada<K,V> e : p)
				toret.addLast(e.getKey());
		return toret;
		
	}

	@Override
	public Iterable<V> values() {
		PositionList<V> toret = new ListDE<V>();
		for(PositionList<Entrada<K,V>> p : buckets )
			for(Entrada<K,V> e : p)
				toret.addLast(e.getValue());
		return toret;	}

	@Override
	public Iterable<Entry<K, V>> entries() {
		PositionList<Entry<K, V>> toret = new ListDE<Entry<K,V>>();
		for(PositionList<Entrada<K,V>> p : buckets )
			for(Entrada<K,V> e : p)
				toret.addLast(e);
		return toret;	}

}
