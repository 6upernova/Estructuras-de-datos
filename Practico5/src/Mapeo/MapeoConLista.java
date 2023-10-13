package Mapeo;
import java.security.InvalidKeyException;

import java.util.Iterator;

import Excepciones.InvalidPositionException;
import listas.*;

public class MapeoConLista<K,V> implements Map<K,V> {
	
	protected PositionList<Entrada<K,V>> l;
	
	public MapeoConLista() {
		l = new ListDE<Entrada<K,V>>();
	}
	
	public int size() {
		return l.size();
	}
	
	public boolean isEmpty() {
		return l.isEmpty();
	}
	
	public V get(K key) throws InvalidKeyException {
		if(key == null)
			throw new InvalidKeyException("El parametro k no puede ser nulo");
		Iterator<Entrada<K,V>> it = l.iterator();
		boolean esta = false;
		V toret = null;
		while(it.hasNext() && !esta) {
			Entrada<K,V> e = it.next();
			if(key == e.getKey()) {
				toret = e.getValue();
				esta = true;
			}
		}
		return toret;	
	}

	@Override
	public V put(K key, V value) throws InvalidKeyException {
		if(key == null)
			throw new InvalidKeyException("El parametro k no puede ser nulo");
		
		Iterator<Entrada<K,V>> it = l.iterator();
		boolean hay = false;
		V toret = null;
		
		while(it.hasNext() && !hay) {
			Entrada<K,V> e = it.next();
			if(e.getKey() == key) {
				hay = true;
				toret = e.getValue();
				e.setValue(value);
			}
		}
		if(hay == false) {
			Entrada<K,V> en = new Entrada<K,V>(key, value);
			l.addLast(en);
		}
		return toret;
	}

	@Override
	public V remove(K key) throws InvalidKeyException {
		if(key == null)
			throw new InvalidKeyException("la llave es nula");
		V toret = null;
		boolean esta = false;
		Iterator <Position<Entrada<K,V>>> it = l.positions().iterator();
		while(it.hasNext() && !esta) {
			Position<Entrada<K, V>> e = it.next();
			if(e.element().getKey() == key){
				esta = true;
				toret = e.element().getValue();
				try {
					l.remove(e);
				} catch (InvalidPositionException e1) {
					
					e1.printStackTrace();
				}
			}
		}
		return toret;
	}

	@Override
	public Iterable<K> keys() {
		PositionList<K> toret = new ListDE<K> ();
		for(Entrada<K,V> e : l) 
			toret.addLast(e.getKey());
		
		return toret;
	}

	@Override
	public Iterable<V> values() {
		PositionList<V> toret = new ListDE<V> ();
		for(Entrada<K,V> e : l) 
			toret.addLast(e.getValue());
		
		return toret;
	}

	@Override
	public Iterable<Entry<K, V>> entries() {
		PositionList<Entry<K,V>> toret = new ListDE<Entry<K,V>> ();
		for(Entrada<K,V> e : l) 
			toret.addLast(e);
		
		return toret;
	}
	
	

}
