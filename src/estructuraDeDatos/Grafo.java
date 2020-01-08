package estructuraDeDatos;

import java.util.ArrayList;

public class Grafo {

	private final int V;
	private final ArrayList<Arista>[] adj;
	
	private  ArrayList<Arista> listaAristas;
	
	@SuppressWarnings("unchecked")
	public Grafo(int v) {
		 this.V = v;
		 listaAristas = new ArrayList<>();
		 adj = (ArrayList<Arista>[]) new ArrayList[V];
		 for (int i = 0; i < V; i++)
			 adj[i] = new ArrayList<Arista>();
	}
	
	public void addArista(Arista a) {
		int v = a.either(), w = a.other(v);
		 adj[v].add(a);
		 adj[w].add(a);
		 
		 listaAristas.add(a);
	}
	
	public Iterable<Arista> adj(int v) {
		return adj[v];
	}
	
	//Todas las aristas del grafo
	public Iterable<Arista> edges() {
		return listaAristas;
	}
	
	public int V() {
		return V;
	}
	
	public int E() {
		return listaAristas.size();
	}
	
	public String toString() {
		String out = "";
		out.concat("Numero de Vertices: " + V() + System.lineSeparator());
		out.concat("Numero de Aristas: " + E() + System.lineSeparator());
		
		for(Arista a : listaAristas)
			out.concat(a.toString() + System.lineSeparator());
		
		return out;
	}
}
