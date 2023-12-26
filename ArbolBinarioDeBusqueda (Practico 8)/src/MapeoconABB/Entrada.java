package MapeoconABB;

public class Entrada<K,V> implements Entry<K,V> {
	
	private K clave;
	private V valor;
	public Entrada(K key, V value) {
		clave = key;
		valor = value;
		
	}
	
	public K getKey() {
		return clave;
	}
	public void setKey(K clave) {
		this.clave = clave;
	}
	public V getValue() {
		return valor;
	}
	public void setValue(V valor) {
		this.valor = valor;
	}

	public String toString() {
		return "Entrada [getKey()=" + getKey() + ", getValue()=" + getValue() + "]";
	}
	
	
	

}
