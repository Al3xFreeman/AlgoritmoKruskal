package estructuraDeDatos;

public class Arista implements Comparable<Arista>{

	private final int v, w;
	private double peso;
	
	public Arista (int v, int w, double peso) {
		this.v = v;
		this.w = w;
		this.peso = peso;
	}

	public int either() {
		return v;
	}
	
	public int other(int vertex) {
		if (vertex == v)
			return w;
		else
			return v;
	}
	
	public int compareTo(Arista that) {
		if (this.peso < that.peso) return -1;
		else if (this.peso > that.peso) return 1;
		else return 0;
	}
	
	public String toString() {
		return (String.valueOf(v) + " - " + String.valueOf(w) + " -> " + String.valueOf(peso)); 
	}
	
	public double addPeso(double p) {
		return (p + this.peso);
	}
	
}
