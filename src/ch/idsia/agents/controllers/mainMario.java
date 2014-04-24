package ch.idsia.agents.controllers;

import java.io.*;
import java.util.*;

public class mainMario{
	public static void main(String[] args){
		File archivo = null;
		FileReader fr = null;
		BufferedReader br = null;

		// 1. CREACION DE LAS TUPLAS DE EXPERIENCIA A PARTIR DEL FICHERO .ARFF GENERADO CON LAS EJECUCIONES DE LOS AGENTES
		
		int numeroAtributos = 5; // Numero de atributos utilizados para los estados. MODIFICAR DEPENDIENDO DEL NUMERO DE ATRIBUTOS QUE TENGAN VUESTROS ESTADOS
		String separador = ","; // Separador de atributos utilizado en el fichero .arff. MODIFICAR DEPENDIENDO DEL SEPARADOR QUE HAYAIS UTILIZADO
		List<Tupla> tuplas  = new ArrayList<Tupla>();
		try {
			// Apertura del fichero y creacion de BufferedReader para poder hacer la carga de las tuplas
			archivo = new File (args[0]); // El nombre del fichero se pasa como parametro
			fr = new FileReader (archivo);
			br = new BufferedReader(fr);
			
			// Lectura del fichero
			String linea = "";
			while((linea=br.readLine())!=null){
				if((linea.length() > 0) && !(linea.startsWith("@"))){
					StringTokenizer stTupla = new StringTokenizer(linea, separador);
					double[] estadoActual = new double[numeroAtributos];
					double[] accion = new double[1];
					double[] estadoSiguiente = new double[numeroAtributos];
					double puntuacion = 0;
					for(int i = 0; i < numeroAtributos; i++) estadoActual[i] = Double.parseDouble(stTupla.nextToken());
					accion[0] = Double.parseDouble(stTupla.nextToken());
					for(int i = 0; i < numeroAtributos; i++) estadoSiguiente[i] = Double.parseDouble(stTupla.nextToken());
					puntuacion = Double.parseDouble(stTupla.nextToken());
					tuplas.add(new Tupla(estadoActual, accion, estadoSiguiente, puntuacion));
				}
			}
			if(fr != null) fr.close();
		}catch(Exception e){
			e.printStackTrace();
		}
		
		// 2. GENERACION DE LA TABLA Q. 
		// Equivalente a lo que se describe en el enunciado en el apartado Fase 2. Aprendizaje: generacion de la tabla Q
		// No es necesario transformar los estados de cada tupla a sus clusters correspondientes. Lo hace intermente el codigo que os hemos dejado. Os ahorrais un paso!!!!
		
		// Las acciones son las que vienen definidas en el enunciado!!!!
		String[][] acciones = {{"0"},{"1"}, {"2"}, {"3"}, {"4"}, {"5"}};
		
		// Los centroides son los que obtenemos con WEKA a la hora de hacer el clustering sobre los atributos del estado actual del fichero .arff!!!
		// Representan las filas de nuestra tabla de comportamiento. He puesto 4 centroides, por lo tanto la Tabla Q tendra 4 filas, donde cada centroide tiene 5 componentes
		// MEJOR QUE PASARAIS COMO PARAMETRO EL FICHERO DONDE SE ENCUENTRAN LOS CENTROIDES Y LOS CARGARAIS DE FORMA AUTOMATICA. AGILIZARIAIS LAS PRUEBAS!!!!!
		String[][] centroides = {{"2.4715", "2.0115", "1.914", "1.5405", "2.2535"},{"1.2746", "1.4689", "1.6417", "1.1154", "0.6794"}, {"0.7371", "0.9688", "0.9018", "1.57", "2.3547"}, {"1.465", "2.2368", "1.2246", "2.3089", "1.2757"}};

		// Tasa de aprendizaje. MODIFICAR
		double alpha = 0.225;
		// Factor de descuento. MODIFICAR
		double gamma = 0.9;
		// Numero de centroides que son nuestras filas de la tabla Q. MODIFICAR SI SE UTILIZA UN NUMERO DE CLUSTER DIFERENTE
		int numeroCentroides = 4;
		// Numero de acciones que son nuestras columnas de la tabla Q. MOIDIFCAR SI SE UTILIZA UN NUMERO DE ACCIONES DIFERENTE
		int numeroAcciones = 6;
		
		// Creamos el objeto QLearning con el constructor public QLearning(double valorInicial, double alpha, double gamma, String[][] estados, String[][] acciones, int numeroEstados, int dimensionEstados, int numeroAcciones, int dimensionAcciones)
		QLearning ql = new QLearning(0, alpha, gamma, centroides, acciones, numeroCentroides, numeroAtributos, numeroAcciones, 1);
		
		int ciclosMaximos = 3; // MODIFICAR DEPENDIENDO DE CUAL SEA EL NUMERO DE CICLOS MAXIMO QUE QUERAIS ESTABLECER!!
		int ciclos = 0;
		while (ciclos < ciclosMaximos){
            for (int i = 0; i < tuplas.size(); i++)
                ql.actualizarTablaQ(tuplas.get(i));
            
            ciclos++;
        }
        
        ql.mostrarTablaQ();
	ql.guardarTablaQ("TablaQ.txt");
	//ql.cargarTablaQ("TablaQ.txt");
	//ql.mostrarTablaQ();
        
	}
}
