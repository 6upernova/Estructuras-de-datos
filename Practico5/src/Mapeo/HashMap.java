package Mapeo;
import java.security.InvalidKeyException;

import java.util.Iterator;

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
	
	private int h(K key) throws InvalidKeyException {
		if(key == null)
			throw new InvalidKeyException("La clave pasada por parametro no puede ser nula");
		return Math.abs(key.hashCode() % N);
	}
	
	private void rehash() {
		PositionList<Entrada<K,V>> entradas = entries();
		nextprimo(N);
		n=0;
		buckets = new ListDE[N];
		for(int i = 0; i<N; i++)
			buckets[i] = new ListDE<Entrada<K,V>>();
		for(Entrada<K,V> e : entradas)
			put(e.getKey(), e.getValue());
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
		
		V toret = null;
		for(Entrada<K,V> e : buckets[h(key)] ) {
			if(e.getKey().equals(key))
				toret = e.getValue();
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
			if(e.getValue().equals(value)) {
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
		for(Entrada<K,V> e : buckets[h(key)] ) {
			if(e.getKey().equals(key)) {
				toret = e.getValue();
				
			}
				
		}
		return toret;
		
		
	}

	@Override
	public Iterable<K> keys() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterable<V> values() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterable<Entry<K, V>> entries() {
		// TODO Auto-generated method stub
		return null;
	}

}
