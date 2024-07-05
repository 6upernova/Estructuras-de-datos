package DFS_BFS;
import Excepciones.InvalidVertexException;

import grafoNoDirigido.*;
public class DFSconexo<V,E>	{

	public boolean Esconex(Graph<V, E> g) { // En esta implementacion cuando se termina de ejecutar void DFS volvera ciclo for del metodo DFSshell
											// y si quedo algun vertice separado a los recorridos por dfs significa que el grafo es inconexo
		return DFSshell(g);
	}
	
	public boolean DFSshell(Graph<V, E> g) {
		boolean toret = false;
		for(Vertex<V> v : g.vertices())
			v.setEstado(false);
		for(Vertex<V> v : g.vertices())
			if(!v.getEstado()) {
				toret = !toret;
				DFS(g,v);
			}
		return toret;
	}
	
	public void DFS(Graph<V,E> g, Vertex<V> v) { 
		v.setEstado(true);
		try {
			for(Edge<E> e : g.incidentEdges(v)) {
				Vertex<V> w = g.opposite(v, e);
				if(!w.getEstado())
					DFS(g,w);
				
			}
		} catch (InvalidVertexException | InvalidEdgeException e) {
			e.printStackTrace();
		}
	}
	
}