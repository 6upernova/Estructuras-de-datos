package MapeoconABB;

import java.security.InvalidKeyException;

import java.util.Comparator;
import listas.*;

public class MapeoconABB<K, V> implements Map<K,V> {
	
	private ABBnode<Entry<K,V>> root;
	private int size;
	private Comparator<K> comp;
	

	public MapeoconABB(Comparator<K> c) {
		size = 0;
		comp = c;
		root = new ABBnode<Entry<K,V>> (null, null); // nodo raiz dummy hasta que se inserte una entrada
		
	}
	
	private ABBnode<Entry<K,V>> buscar(K key) throws InvalidKeyException {
		checkKey(key);
		if(size != 0)
			return BuscarAux(key , root);
		else
			return root;
	}
	
	private ABBnode<Entry<K,V>> BuscarAux(K key, ABBnode<Entry<K,V>> n) {
		if (n.element() == null) // El elemento no se encuentra en el mapeo
			return n;
		else {
			int c = comp.compare(key, n.element().getKey());
			if (c == 0)
				return n; //Se encontro el elemento
			else 
				if (c < 0)
					return BuscarAux(key, n.getHijoizq());
				else 
					return BuscarAux(key, n.getHijoder());
		}
	}
	
	private void checkKey(K key) throws InvalidKeyException {
		if (key == null )
			throw new InvalidKeyException ("La clave pasada por parametro es invalida");
		
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	@Override
	public V get(K key) throws InvalidKeyException {
		checkKey(key);
		V toret = null;
		ABBnode<Entry<K,V>> n = buscar(key);
		if(n.element() != null)
			toret = n.element().getValue();
		return toret;
			
	}

	@Override
	public V put(K key, V value) throws InvalidKeyException {
		checkKey(key);
		//En esta implementacion utilizamos el buscar en vez de hacer otro algoritmo insertar 
		//ya que si no existe la entrada nos devolver el dummy en donde deberiamos insertarlo y de lo contrario simplemente devuelve el rotulo a modificar
		ABBnode<Entry<K,V>> n = buscar(key);
		Entry<K,V> en = new Entrada<K,V> (key, value);
		V toret = null;
		if(n.element() != null) {
			toret = n.element().getValue();
			n.element().setValue(value);
		}
		else {
			n.setElem(en);
			n.setHijoder(new ABBnode<Entry<K,V>> (null, n)); //Seteo de dummys
			n.setHijoizq(new ABBnode<Entry<K,V>>(null, n ));
			size++;
		}
		return toret;
			
		
	}

	@Override
	public V remove(K key) throws InvalidKeyException {
		checkKey(key);
		ABBnode<Entry<K, V>> n= buscar (key);
		V toret = null;
		if(n.element() != null) {
			toret = n.element().getValue();
			size--;
			RemoveAux(n);
			
		}
		return toret;
		
	}
	private void RemoveAux(ABBnode<Entry<K, V>> n) {
		ABBnode<Entry<K, V>> aux = null;
		int count = 0;
		//El nodo a eliminar tiene 2 hjos
		if( n.getHijoizq().element() != null && n.getHijoder().element() != null ) {
			aux = buscarSucesorInorder(n.getHijoder());
			RemoveAux(aux);
			count++;
		}
		//El nodo  a eliminar tiene solo un hijo izquierdo
		else if (n.getHijoizq().element() != null)
			aux = n.getHijoizq();
		//El nodo a eliminar tiene solo un hijo derecho
		else if (n.getHijoder().element() != null)
			aux = n.getHijoder();
		//El nodo a eliminar es un nodo hoja
		else {
			n.setElem(null);
			n.setHijoder(null);
			n.setHijoizq(null);
		}
		
		//Se encarga de realizar la sustitucion inorder
		if(aux != null && count == 1) {
			n.setElem(aux.element());
			
		}
		//Se encarga de realizar el bypass
		if(aux != null && count == 0)
			intercambiar(n, aux);
		//En caso de que la estructura quede vacia reinicia los nodos
		if (size == 0) {
			root.setElem(null);
			root.setHijoder(null);
			root.setHijoizq(null);
			
		}
		
			
	}
	
	private void intercambiar(ABBnode<Entry<K, V>> n, ABBnode<Entry<K, V>> aux ) {
		ABBnode<Entry<K, V>> padre = n.getPadre();
		
		if(padre != null) {
			if(padre.getHijoder() == n) {
				padre.setHijoder(aux);
				aux.setPadre(padre);
			}
			if(padre.getHijoizq() == n) {
				padre.setHijoizq(aux);
				aux.setPadre(padre);
			}
		}
		
	}
	
	private ABBnode<Entry<K,V>> buscarSucesorInorder(ABBnode<Entry<K,V>> n) {
		//Visita con llamada recursiva
		if(n.getHijoizq() != null && n.getHijoizq().element() != null)
			buscarSucesorInorder(n.getHijoizq());
		
		//Caso base
		return n;
		
	}
	


	@Override
	public Iterable<K> keys() {
		PositionList<K> pl = new ListDE<K> ();
		
		for(Entry<K,V> e : Alista())
			pl.addLast(e.getKey());
		return pl;
	}

	@Override
	public Iterable<V> values() {
		PositionList<V> pl = new ListDE<V> ();
		
		for(Entry<K,V> e : Alista())
			pl.addLast(e.getValue());
		return pl;
	}

	@Override
	public Iterable<Entry<K, V>> entries() {
		return Alista();
	}
	
	private Iterable<Entry<K,V>> Alista(){
		PositionList<Entry<K,V>> pl = new ListDE<Entry<K,V>>();
		if (size != 0)
			preorden(pl, root);
		return pl;
		
	}
	
	private void preorden(PositionList<Entry<K,V>> pl, ABBnode<Entry<K, V>> n  ) {
		//Visita
		if(n.element() != null)
			pl.addLast(n.element());
		//Llamada Recursiva
		if(n.getHijoizq() != null)
			preorden(pl, n.getHijoizq());
		if(n.getHijoder() != null)
			preorden(pl, n.getHijoder());
	}
	/**
	 *  Este método debe
		agregar al mapeo entradas cuyas claves sean las que aparecen en la lista l y sus valores sean value. Asuma
		que l no tiene claves repetidas ni nulas. Este método deberá retornar un iterable con entradas equivalentes a
		las agregadas. Considere que si alguna clave de l ya está en el mapeo que recibe el mensaje, no se podrá
		insertar. En ese caso simplemente se pasa a la siguiente clave.
	 * 
	 */
	
	public Iterable<Entry<K,V>> insertarMasivo(V value, PositionList<K> l) {
		PositionList<Entry<K,V>> toret = new ListDE<Entry<K,V>> ();
		for(K key : l) 
			try {
				if(this.get(key)==null) {
					this.put(key, value);
					toret.addLast(new Entrada<K,V> (key, value));
				}
			} 
			catch (InvalidKeyException e) {
				e.printStackTrace();
			}
		return toret;
	}
	
	/**
	 * 	Este método debe retornar
		verdadero si y solo si key2 es el sucesor inorder de key1. Asuma que key1 será encontrado dentro de un nodo
		interno del ABB(con dos hijos que no sean Dummy).
	 */
	
	public boolean sucesorInorder(K key1, K key2) throws InvalidKeyException {
		checkKey(key1);
		checkKey(key2);
		boolean toret = false;
		ABBnode <Entry<K,V>> aux1 = buscar(key1);
		ABBnode <Entry<K,V>> aux2 = buscar(key2);
		if(buscarSucesorInorder(aux1.getHijoder()) == aux2)
			toret = true;	
		return toret;

	}

}
