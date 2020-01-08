package algoritmo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

import com.github.plot.Plot;

import estructuraDeDatos.Arista;
import estructuraDeDatos.Grafo;

public class Inicio {
	
	public static void main(String[] args) {

		ArrayList<Long> tiempos = new ArrayList<>();
		ArrayList<Integer> numeroVertices = new ArrayList<>();
		
		GenerateGrafos generate = new GenerateGrafos(5000, 10);

		if (generate.interval()) {
			
			try {
				BufferedReader reader = new BufferedReader(new FileReader(new File("grafos.txt")));
				String line;

				while ((line = reader.readLine()) != null) {

					//String sNumVertices = reader.readLine();
					int numVertices = Integer.parseInt(line);
					numeroVertices.add(numVertices);
					
					Grafo g = new Grafo(numVertices);

					line = reader.readLine();
					while (!line.isEmpty()) {
						String[] aristaStr = line.split(" ");
						Arista a = new Arista(Integer.parseInt(aristaStr[0]), Integer.parseInt(aristaStr[1]),
								Double.parseDouble(aristaStr[2]));
						g.addArista(a);

						line = reader.readLine();
					}
					long startTime = 0, endTime = 0, duration = 0, totalDur = 0;
					
					for (int i = 0; i < 3; i++) {
						startTime = System.nanoTime();
						AlgoritmoKrustal krustal = new AlgoritmoKrustal(g);
						endTime = System.nanoTime();
						// Divide entre 1000000 (1 millón) para obtener el resultado en milisegundos
						duration = (endTime - startTime);
						totalDur += duration;
					}
					totalDur /= 3;
					System.out.println(totalDur);
					tiempos.add(totalDur);
					//System.out.println(krustal.toString());
					
				}
				reader.close();
			} catch (Exception e) {
				// System.err.format("Exception occurred trying to read ");
				e.printStackTrace();
			}

		} else {
			System.out.println("Especifique un intervalo mayor que 0");
		}

		//Double[] tiemposA = (Double[]) tiempos.toArray();
		double[] tiemposAd = new double[tiempos.size()];
		
		//Double[] numeroVerticesA = (Double[]) numeroVertices.toArray();
		double[] numeroVerticesAd = new double[numeroVertices.size()];
		
		for(int i = 0; i < tiempos.size(); i++)
			tiemposAd[i] = tiempos.get(i).doubleValue();
			
		for(int i = 0; i < tiempos.size(); i++)
			numeroVerticesAd[i] = numeroVertices.get(i).doubleValue();	

		
		Plot plot = Plot.plot(Plot.plotOpts().
						title("Kruskal").
						legend(Plot.LegendFormat.BOTTOM)).
					xAxis("número de nodos", Plot.axisOpts().
						range(Collections.min(numeroVertices), Collections.max(numeroVertices))).
					yAxis("tiempo de ejecución", Plot.axisOpts().
						range(0, Collections.max(tiempos))).
					series("Data", Plot.data().
						xy(numeroVerticesAd, tiemposAd), null);
		
		try {
			plot.save("GRAFO", "png");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
