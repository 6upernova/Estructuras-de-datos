package grafoNoDirigido;

import java.util.Iterator;

import Excepciones.EmptyListException;
import Excepciones.InvalidPositionException;
import Excepciones.InvalidVertexException;
import listas.ListDE;
import listas.PositionList;

public class GrafoND<V, E> implements Graph<V, E> {
	
	protected PositionList<Vertice<V,E>> ldv;
	protected PositionList<Arco<V,E>> lda;
	
	public GrafoND() {
		ldv = new ListDE<Vertice<V,E>> ();
		lda = new ListDE<Arco<V,E>> ();
	}

	@Override
	public Iterable<Vertex<V>> vertices() {
		PositionList<Vertex<V>> toret = new ListDE<Vertex<V>> ();
		for(Vertice<V,E> v : ldv)
			toret.addLast(v);
		return toret;
	}

	@Override
	public Iterable<Edge<E>> edges() {
		PositionList<Edge<E>> toret = new ListDE<Edge<E>> ();
		for(Arco<V,E> a : lda)
			toret.addLast(a);
		return toret;
	}

	@Override
	public Iterable<Edge<E>> incidentEdges(Vertex<V> v) throws InvalidVertexException {
		Vertice<V, E> ve = checkV(v);
		PositionList<Edge<E>> toret = new ListDE<Edge<E>>();
		for(Arco<V,E> a : ve.getAdyacentes())
			toret.addLast(a);
		System.out.println(ve.getAdyacentes().size());
		return toret;
		
	}

	@Override
	public Vertex<V> opposite(Vertex<V> v, Edge<E> e) throws InvalidVertexException, InvalidEdgeException {
		Arco<V,E> ar = checkE(e);
		Vertice<V,E> ve = checkV(v);
		Vertex<V> toret = null;
		if(ar.v1() != ve && ar.v2() != ve)
			throw new InvalidEdgeException("El arco no coincide con el vertice");
		
		if(ar.v1() == ve)
			toret = ar.v2();
		else
			toret = ar.v1();
		return toret;
	}

	@Override
	public Vertex<V>[] endvertices(Edge<E> e) throws InvalidEdgeException {
		Arco<V,E> ar = checkE(e);
		Vertex<V> [] toret = new Vertex [2];
		toret[0] = ar.v1();
		toret[1] = ar.v2();
		return toret;
	}

	@Override
	public boolean areAdjacent(Vertex<V> v, Vertex<V> w) throws InvalidVertexException {
		Vertice<V, E> v1 = checkV(v);
		Vertice<V, E> v2 = checkV(w);
		
		Iterator<Arco<V,E>>	 it = lda.iterator();
		boolean son = false;
		while(it.hasNext() && !son) {
			Arco<V, E> a = it.next();
			son = (a.v1() == v1 && a.v2() == v2 ) || (a.v2() == v1 && a.v1() == v2);
		}
		return son;
	}

	@Override
	public V replace(Vertex<V> v, V x) throws InvalidVertexException {
		Vertice<V,E> ve = checkV(v);
		V toret = ve.element();
		ve.setElem(x);
		return toret;
	}

	@Override
	public Vertex<V> insertVertex(V x) {
		Vertice<V,E> toret = new Vertice<V,E> (x);
		ldv.addLast(toret);
		try {
			toret.setPos(ldv.last());
		} catch (EmptyListException e) {
			e.printStackTrace();
		}
		return toret;
		
	}

	@Override
	public Edge<E> insertEdge(Vertex<V> v, Vertex<V> w, E e) throws InvalidVertexException {
		Vertice<V,E> v1 = checkV(v);
		Vertice<V,E> v2 = checkV(w);
		Arco<V,E> toret = new Arco<V,E> (v1, v2,  e);
		lda.addLast(toret);
		try {
			toret.setPos(lda.last());
			v1.getAdyacentes().addLast(toret);
			v2.getAdyacentes().addLast(toret);
		} catch (EmptyListException e1) {
			e1.printStackTrace();
		}
		
		return toret;
	}

	@Override
	public V removeVertex(Vertex<V> v) throws InvalidVertexException {
		Vertice<V,E> ve = checkV(v);
		V toret = ve.element();
		try {
			for(Arco<V, E> a: ve.getAdyacentes())
					lda.remove(a.getPos());
			ldv.remove(ve.getPos());
		}
		catch (InvalidPositionException e) {
			e.printStackTrace();
		}
		return toret;
		
	}

	@Override
	public E removeEdge(Edge<E> e) throws InvalidEdgeException {
		Arco<V,E> ar = checkE(e);
		E toret = ar.element();
		try {
			for(listas.Position<Arco<V, E>> pa :ar.v1().getAdyacentes().positions())
				if(pa.element() == ar) {
					ar.v1().getAdyacentes().remove(pa);
					break;
				}
			for(listas.Position<Arco<V, E>> pa :ar.v2().getAdyacentes().positions())
				if(pa.element() == ar) {
					ar.v2().getAdyacentes().remove(pa);
					break;
				}
					
			lda.remove(ar.getPos());
		}
		catch (InvalidPositionException e1) {
			e1.printStackTrace();
		}
		return toret;
	}
	
	private Vertice<V, E> checkV(Vertex<V> v) throws InvalidVertexException{
		if(v==null)
		   throw new InvalidVertexException("Edge nulo");
		Vertice<V, E> toRet=null;
        try {
			 toRet= (Vertice<V, E>) v;
		    }
		catch(ClassCastException msg) {
			System.out.println(msg.getMessage());
		}
        return toRet;
	}
	
	private Arco<V,E> checkE(Edge<E> e) throws InvalidEdgeException{
		if(e==null)
		   throw new InvalidEdgeException("Edge nulo");
		Arco<V,E> toRet=null;
		try {
			 toRet= (Arco<V,E>) e;
		    }
		catch(ClassCastException msg) {
			System.out.println(msg.getMessage());
		}
		if(toRet.v1() == null || toRet.v2() == null)
			throw new InvalidEdgeException("No puede haber un arco sin alguno de sus vertices");
		return toRet;
	}

}
