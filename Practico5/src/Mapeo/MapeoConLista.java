package Mapeo;
import java.security.InvalidKeyException;
import java.util.Iterator;

import Excepciones.InvalidPositionException;
import listas.*;

public class MapeoConLista<K,V> implements Map<K,V> {
	
	protected PositionList<Entry<K,V>> l;
	
	public MapeoConLista() {
		l = new ListDE<Entry<K,V>>();
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
		Iterator<Entry<K,V>> it = l.iterator();
		boolean esta = false;
		V toret = null;
		while(it.hasNext() && !esta) {
			Entry<K,V> e = it.next();
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
		
		Iterator<Entry<K,V>> it = l.iterator();
		boolean hay = false;
		V toret = null;
		
		while(it.hasNext() && !hay) {
			Entry<K,V> e = it.next();
			if(e.getKey() == key) {
				hay = true;
				toret = e.getValue();
				e.setValue(value);
			}
		}
		if(hay == false) {
			Entry<K,V> en = new Entrada<K,V>(key, value);
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
		Iterator <Position<Entry<K,V>>> it = l.positions().iterator();
		while(it.hasNext() && !esta) {
			Position<Entry<K, V>> e = it.next();
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
		for(Entry<K,V> e : l) 
			toret.addLast(e.getKey());
		
		return toret;
	}

	@Override
	public Iterable<V> values() {
		PositionList<V> toret = new ListDE<V> ();
		for(Entry<K,V> e : l) 
			toret.addLast(e.getValue());
		
		return toret;
	}

	@Override
	public Iterable<Entry<K, V>> entries() {
		PositionList<Entry<K,V>> toret = new ListDE<Entry<K,V>> ();
		for(Entry<K,V> e : l) 
			toret.addLast(e);
		
		return toret;
	}
	
	

}
