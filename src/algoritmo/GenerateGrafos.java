package algoritmo;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class GenerateGrafos {

	//Número inicial de aristas
	private int ratio;
	
	//Número máximo de vertices que se computarán
	private int max_vertices;
	
	//Intervalo de incremento de aristas en cada iteración
	private int intervalo;
	
	//public GenerateGrafos(int ratio, int max, int intervalo) {
	public GenerateGrafos(int max, int intervalo) {
		ratio = 4;
		max_vertices = max;
		this.intervalo = intervalo;
		if (interval())
			generate();
	}
	
	
	private void generate() {
		BufferedWriter writer = null;
		try {
			
			File file = new File("grafos.txt");
			
	        writer = new BufferedWriter(new FileWriter(file));
	        //Esto vacía el archivo para escribir los nuevos grafos
	        writer.write((""));
	        
	        
	        
			//Comienza generando gráfos de un grafo con 10 vértices
			//i indica el número de vértices del grafo
	        int i = intervalo;
			while(i < max_vertices) {
				
				//Indica el número de aristas del grafo
				writer.append(String.valueOf(i));
				writer.newLine();
				
		        //Genera las aristas iniciales de forma que nos aseguramos de que el grafo vaya a ser conexo
		        aristasIniciales(i, writer);
		        restoAristas(i, (i*ratio) - i, writer);
		        
		        writer.newLine();
		        i += intervalo;
			}
	        
		}
		catch (Exception e) {
            e.printStackTrace();
        } finally {
        	try {
				writer.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
	}
	
	//De esta forma nos aseguramos de que el grafo generado siempre va a ser conexo
	private void aristasIniciales(int numVertices, BufferedWriter writer) {
		ArrayList<Integer> noSeleccionados = new ArrayList<>();
		for(int i = 0; i < numVertices; i++)
			noSeleccionados.add(i);
		
		Random rand = new Random();
		int randomNum = rand.nextInt(noSeleccionados.size());
		
		int primero = noSeleccionados.get(randomNum);
		int segundo;
		double peso;
		noSeleccionados.remove(randomNum);
		
		while (!noSeleccionados.isEmpty()) {
			randomNum = rand.nextInt(noSeleccionados.size());
			segundo = noSeleccionados.get(randomNum);
			noSeleccionados.remove(randomNum);
			peso = rand.nextDouble();
			
			try {
				writer.append(String.valueOf(primero) + " " + String.valueOf(segundo) + " " + String.valueOf(peso));
				writer.newLine();
				if (!noSeleccionados.isEmpty()) {
					primero = segundo;
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	    
		
	}
	
	private void restoAristas(int numVertices, int numAristas, BufferedWriter writer) {
		for(int i = 0; i < numAristas; i++) {
			Random rand = new Random();
			int randomNum = rand.nextInt(numVertices);
			int randomNum2 = rand.nextInt(numVertices);
			double peso = peso = rand.nextDouble();
			
			while (randomNum == randomNum2) 
				randomNum2 = rand.nextInt(numVertices);
			
			try {
				writer.append(String.valueOf(randomNum) + " " + String.valueOf(randomNum2) + " " + String.valueOf(peso));
				writer.newLine();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}
	
	
	public boolean interval() {
		if (intervalo > 0)
			return true;
		else
			return false;
	}
	
}
