package estructuraDeDatos;

import java.util.ArrayList;

public class MinSpanTree {

	private final Grafo g;
	
	private ArrayList<Arista> listaAristas;
	
	public MinSpanTree(Grafo g) {
		this.g = g;
		listaAristas = new ArrayList<>();
	}
	
	public void add(Arista a) {
		listaAristas.add(a);
	}
	
	public int size() {
		return listaAristas.size();
	}
	
	public Iterable<Arista> edges() {
		return listaAristas;
	}
	
	
	//Peso del MST
	public double weigth() {
		double peso = 0;
		for(Arista arista : listaAristas)
			peso = arista.addPeso(peso);
		
		return peso;
	}
	
	public String toString() {
		
		String out = listaAristas.toString(); 
		return out;
	}
	
}
