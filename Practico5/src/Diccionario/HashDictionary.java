package Diccionario;

import java.security.InvalidKeyException;

import java.util.Iterator;

import Excepciones.InvalidEntryException;
import Excepciones.InvalidPositionException;
import listas.*;

public class HashDictionary<K,V> implements Dictionary<K,V> {
	
	protected int N;
	protected int n;
	protected PositionList<Entrada<K,V>> [] buckets;
	

	public HashDictionary() {
		N=11;
		n=0;
		buckets = new ListDE [N];
		for(int i = 0; i < N; i++)
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
	
	private void checkKey(K key ) throws InvalidKeyException {
		if(key == null)
			throw new InvalidKeyException("La clave pasada por parametro no puede ser nula");
	}
	
	private int hash(K key) throws InvalidKeyException {
		checkKey(key);
		return Math.abs(key.hashCode() % N);
		
	}
	
	private void rehash() {
		Iterable<Entry<K,V>> entradas = entries();
		N = nextprimo(N);
		n = 0;
		buckets = new ListDE [N];
		
		for(int i = 0; i<N ; i++ )
			buckets[i] = new ListDE<Entrada<K,V>>();
		for(Entry<K,V> e : entradas)
			try {
				this.insert(e.getKey(), e.getValue());
			} catch (InvalidKeyException e1) {
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
	public Entry<K, V> find(K key) throws InvalidKeyException {
		Entry<K,V> toret = null;
		boolean existe = false;
		Iterator<Entrada<K,V>> it = buckets[hash(key)].iterator();
		while (it.hasNext() && !existe) {
			Entrada<K,V> e = it.next();
			if(e.getKey().equals(key)) {
				toret = e;
				existe = true;
			}	
		}
		return toret;
	}

	@Override
	public Iterable<Entry<K, V>> findAll(K key) throws InvalidKeyException {
		checkKey(key);
		PositionList<Entry<K,V>> toret = new ListDE<Entry<K,V>> ();
		for(Entrada<K,V> e : buckets[hash(key)])
			if(e.getKey().equals(key))
				toret.addLast(e);
		return toret;
	}

	@Override
	public Entry<K, V> insert(K key, V value) throws InvalidKeyException {
		checkKey(key);
		Entrada<K,V> nueva = new Entrada<K,V>(key,value);
		buckets[hash(key)].addLast(nueva);
		n++;
		return nueva;
	}

	@Override
	public Entry<K, V> remove(Entry<K, V> e) throws InvalidEntryException {
		Entrada<K,V> toret = null;
			try {
				if(e == null || this.find(e.getKey()) == null) // Tener en cuenta el detalle de que quizas haya un caso donde habiendo una entrada con la misma clave pero distinto valor
					throw new InvalidEntryException("La entrada debe existir en el diccionario");
			} catch (InvalidKeyException e1) {
				e1.printStackTrace();
				
			}

		try {
			Iterator<Position<Entrada<K, V>>> it = buckets[hash(e.getKey())].positions().iterator();
			while(toret == null && it.hasNext() ) {
				Position<Entrada<K,V>> e2 = it.next();
				if(e2.element().equals(e)) {
					toret = e2.element();
					buckets[hash(e.getKey())].remove(e2);
					n--;
				}
			}
		}
		catch (InvalidKeyException | InvalidPositionException e1) {
			System.out.println(e1.getMessage());
		}
		
		return toret;
	}

	@Override
	public Iterable<Entry<K, V>> entries() {
		PositionList<Entry<K, V>> toret = new ListDE<Entry<K,V>>();
		for(PositionList<Entrada<K,V>> p : buckets )
			for(Entrada<K,V> e : p)
				toret.addLast(e);
		return toret;	
	}

}
