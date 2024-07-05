package DFS_BFS;

import listas.*;
import Excepciones.InvalidVertexException;
import grafoNoDirigido.*;


public class comp_conexasDFS {
	
	
	
	public static<E,V>  PositionList<PositionList<Vertex<V>>> comp_conex(Graph<V, E> g){
		return DFSshell(g);
		
		
	}
	
	public static<E,V> PositionList<PositionList<Vertex<V>>> DFSshell(Graph<V,E> g){
		PositionList<PositionList<Vertex<V>>> componenteS = new ListDE<PositionList<Vertex<V>>>(); //Es una forma de represenatar a las componentes conexas se podrian guardan en subgrafos tambien
		for (Vertex<V> v : g.vertices()) //O(n)
			v.setEstado(false);
		
		for(Vertex<V> v :g.vertices())
			if(!v.getEstado()) {
				PositionList<Vertex<V>> componente = new ListDE<Vertex<V>>();
				DFS(g, v, componente);
				componenteS.addLast(componente);
				
			}
		return componenteS;
	}
	
	

	public static<E,V> void DFS(Graph<V,E> g, Vertex<V> v, PositionList<Vertex<V>> componente ) {
		v.setEstado(true);
		componente.addLast(v);
		try {
			for(Edge<E> e : g.incidentEdges(v)) {
				Vertex<V> w = g.opposite(v, e);
				if(!w.getEstado())
					DFS(g,w,componente);
			}
		}
		catch(InvalidVertexException | InvalidEdgeException e) {
			e.printStackTrace();
		}
	}
   
	
	  public static void main(String[] args) {
	        // Ejemplo de uso
	        GrafoND<Integer, String> g = new GrafoND<Integer,String>(); // Grafo con vértices de tipo Integer y aristas de tipo String
	        Vertex<Integer> v0 = g.insertVertex(0);
	        Vertex<Integer> v1 = g.insertVertex(1);
	        Vertex<Integer> v2 = g.insertVertex(2);
	        Vertex<Integer> v3 = g.insertVertex(3);
	        Vertex<Integer> v4 = g.insertVertex(4);

	        try {
				g.insertEdge(v0, v1, "a");
				g.insertEdge(v1, v2, "b");
				g.insertEdge(v3, v4, "c");
	        } catch (InvalidVertexException e) {
				e.printStackTrace();
			}
	        
	        PositionList<PositionList<Vertex<Integer>>> componentes = comp_conex(g);
	       
	        System.out.println("Las componentes conexas son:");
	        for (PositionList<Vertex<Integer>> componente : componentes) {
	        	System.out.print("[");
	        	 boolean primerElem = true;
	            for (Vertex<Integer> v : componente) {
	            	if(primerElem) {
	            		System.out.print(v.element());
	            		primerElem = false;
	            	}
	            	else
	            		System.out.print( ", " + v.element());
	            }
	            System.out.print("]");
	            System.out.println();
	        }
	    }

}
