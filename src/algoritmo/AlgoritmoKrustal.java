package algoritmo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.PriorityQueue;

import estructuraDeDatos.Arista;
import estructuraDeDatos.Grafo;
import estructuraDeDatos.MinSpanTree;
import estructuraDeDatos.WeightedUF;

public class AlgoritmoKrustal {

	//private PriorityQueue<Arista> mst = new PriorityQueue<>();
	private MinSpanTree mst;
	
	public AlgoritmoKrustal(Grafo g) {
		
		mst = new MinSpanTree(g);
		
		ArrayList<Arista> sortedL = new ArrayList<>();
		sortedL = (ArrayList<Arista>) g.edges();
		Collections.sort(sortedL);
		
		WeightedUF wUF = new WeightedUF(g.V());
		
		while(!sortedL.isEmpty() && mst.size() < g.V() - 1) {
			//Elimina el elemento más pequeño, como está ordenada la lista, es el de índice 0
			Arista a = sortedL.remove(0);
			
			int v = a.either();
			int w = a.other(v);
			
			//Une las componentes no conexas del grafo
			if(!wUF.connected(v, w)) {
				wUF.union(v, w);
				mst.add(a);
			}
		}
		
		//MinPQ<Arista> pq = new MinPQ<Arista>(g.ed)
	}
	
	public Iterable<Arista> edges() {
		return mst.edges();
	}
	
	public String toString() {
		String out = "Krustal MST: ";
		
		out += mst.toString();
		out += "   total weigth: ";
		out += String.valueOf(mst.weigth());
		
		return out;
	}
	
}