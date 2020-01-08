package estructuraDeDatos;

public class WeightedUF {
	
	private int[] parent;
	private int[] size;
	
	public WeightedUF(int N) {
			parent = new int[N];
			size = new int[N];
			
			for(int i = 0; i < N; i++) {
				parent[i] = i;
				size[i] = i;
			}
	}
	
	public int root(int p) {
		while (p != parent[p]) {
			parent[p] = parent[parent[p]];
			p = parent[p];
		}
		return p;
	}
	
	public boolean connected(int p, int q) {
		return root(p) == root(q);
	}
	
	public void union(int p, int q) {
		int i = root(p);
		int j = root(q);
		
		if (i == j) return;
		if (size[i] < size[j]) {
			parent[i] = j;
			size[j] += size[i];
		}
		else {
			parent[j] = i;
			size[i] += size[j];
		}
	}
	
}
