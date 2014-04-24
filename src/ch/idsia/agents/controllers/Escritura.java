package ch.idsia.agents.controllers;

import java.io.FileWriter;
import java.io.PrintWriter;

import ch.idsia.scenarios.Main;

public class Escritura {
	String datos= "";
	
	public void escribirFichero(){
		FileWriter fichero = null;
		PrintWriter pw = null;
		try{
			//Estas dos lineas se comentan si se usa el script en linux
			//fichero = new FileWriter("./instancias.txt",true);
			//pw = new PrintWriter(fichero);

			for(int i=0;i<Main.ticks.size();i++){
				//System.out.println(Main.ticks.get(i)); //DESCOMENTAR ESTO PARA IMPRIMIR INSTANCIAS
														//por pantalla para ser cogidas por el Script en Linux
				//pw.println(Main.ticks.get(i));  //DESCOMENTAR ESTO PARA IMPRIMIR INSTANCIAS
												//directamente en fichero, si no se cambia nombre se escriben todas
												//a continuacion en el mismo fichero
			}
			
		}catch (Exception e){
			e.printStackTrace();
		}finally{
			try{
				// Nuevamente aprovechamos el finally para 
				// asegurarnos que se cierra el fichero.
				if (null != fichero)
					fichero.close();
			}catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}
}
