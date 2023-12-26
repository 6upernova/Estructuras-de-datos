package grafoNoDirigido;

import listas.PositionList;

import listas.*;

public class Vertice<V, E> implements Vertex<V> {

    protected listas.Position<Vertice<V, E>> pos;
    protected V rotulo;
    PositionList<Arco<V, E>> adyacentes;
    protected boolean visitado;

    public Vertice(V elem) {
        rotulo = elem;
        adyacentes = new ListDE<>();// Inicializa la lista de adyacentes
        visitado = false;
    }

    public V element() {
        return rotulo;
    }

    public listas.Position<Vertice<V, E>> getPos() {
        return pos;
    }

    public PositionList<Arco<V, E>> getAdyacentes() {
        return adyacentes;
    }

    public void setPos(listas.Position<Vertice<V, E>> position) {
        this.pos = position;
    }

    public void addAdyacentes(Arco<V, E> a) {
        adyacentes.addLast(a);
    }
    
    public void setElem(V elem) {
    	rotulo = elem;
    }
    
    public void setEstado(boolean estado) {
    	visitado = estado;
    }

	@Override
	public boolean getEstado() {
		return visitado;
	}
}

