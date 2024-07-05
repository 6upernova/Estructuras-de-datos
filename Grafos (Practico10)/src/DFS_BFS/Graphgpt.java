package DFS_BFS;

import java.util.ArrayList;
import java.util.List;

public class Graphgpt {
    private int V;   // Número de vértices
    private List<List<Integer>> adj; // Lista de adyacencia

    // Constructor
    public Graphgpt(int V) {
        this.V = V;
        adj = new ArrayList<>(V);
        for (int i = 0; i < V; i++) {
            adj.add(new ArrayList<>());
        }
    }

    // Método para agregar una arista al grafo
    public void addEdge(int v, int w) {
        adj.get(v).add(w);
        adj.get(w).add(v); // Para un grafo no dirigido
    }

    // Método DFS para marcar los vértices alcanzables desde v
    private void DFSUtil(int v, boolean[] visited, List<Integer> component) {
        visited[v] = true;
        component.add(v);

        for (int n : adj.get(v)) {
            if (!visited[n]) {
                DFSUtil(n, visited, component);
            }
        }
    }

    // Método para encontrar las componentes conexas
    public List<List<Integer>> connectedComponents() {
        boolean[] visited = new boolean[V];
        List<List<Integer>> components = new ArrayList<>();

        for (int v = 0; v < V; ++v) {
            if (!visited[v]) {
                List<Integer> component = new ArrayList<>();
                DFSUtil(v, visited, component);
                components.add(component);
            }
        }
        return components;
    }

    public static void main(String[] args) {
        // Ejemplo de uso
        Graphgpt g = new Graphgpt(5); // Grafo con 5 vértices
        g.addEdge(0, 1);
        g.addEdge(1, 2);
        g.addEdge(3, 4);

        List<List<Integer>> components = g.connectedComponents();
        System.out.println("Las componentes conexas son:");
        for (List<Integer> component : components) {
            System.out.println(component);
        }
    }
}
