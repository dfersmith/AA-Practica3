/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ch.idsia.agents.controllers;

import java.util.*;


/**
 *
 * @author moises
 */
public class main 
{
    public static void main(String[] args) 
    {   
        List<Tupla> mapa  = new ArrayList<Tupla>();
        
        String[] acciones = {"0", "1", "2", "3"};
        String[] estados  = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15"};
        
        double[] estado = {2};
        double[] estadoFinal = {13};
        double[] accion = {0};
        double refuerzo = 0;
        
        boolean encontrado;
        
        QLearning ql        = new QLearning(0, 0.6, 0.3, estados, acciones, 16, 4);
        int ciclosMaximos   = 4;
        int ciclos          = 0;
        int posicion;
		
	mapa.add(new Tupla(0, 0, 1, 1.25)); 
        mapa.add(new Tupla(0, 1, 5, 1.25)); 
        mapa.add(new Tupla(1, 0, 2, 1.3)); 
        mapa.add(new Tupla(1, 1, 6, 1.3)); 
        mapa.add(new Tupla(1, 2, 0, 0.8)); 
        mapa.add(new Tupla(2, 0, 3, 0.75)); 
        mapa.add(new Tupla(2, 2, 1, 0.75)); 
        mapa.add(new Tupla(3, 0, 4, 0.8)); 
        mapa.add(new Tupla(3, 1, 7, 1.3)); 
        mapa.add(new Tupla(3, 2, 2, 1.3)); 
        mapa.add(new Tupla(4, 2, 3, 1.25)); 
        mapa.add(new Tupla(5, 0, 6, 1.3)); 
        mapa.add(new Tupla(5, 1, 8, 1.3)); 
        mapa.add(new Tupla(5, 3, 0, 0.8)); 
        mapa.add(new Tupla(6, 2, 5, 0.75)); 
        mapa.add(new Tupla(6, 3, 1, 0.75)); 
        mapa.add(new Tupla(7, 1, 9, 1.5)); 
        mapa.add(new Tupla(7, 3, 3, 0.75)); 
        mapa.add(new Tupla(8, 1, 11, 1.5)); 
        mapa.add(new Tupla(8, 3, 5, 0.75)); 
        mapa.add(new Tupla(9, 0, 10, 0.6)); 
        mapa.add(new Tupla(9, 1, 14, 2)); 
        mapa.add(new Tupla(9, 3, 7, 0.6)); 
        mapa.add(new Tupla(10, 1, 15, 1.5));
        mapa.add(new Tupla(10, 2, 9, 1.5));
        mapa.add(new Tupla(11, 0, 12, 2));
        mapa.add(new Tupla(11, 3, 8, 0.6));
        mapa.add(new Tupla(12, 0, 13, 4));
        mapa.add(new Tupla(12, 2, 11, 0.5));
        mapa.add(new Tupla(13, 0, 14, 0.1));
        mapa.add(new Tupla(13, 2, 12, 0.1));
        mapa.add(new Tupla(14, 0, 15, 0.5));
        mapa.add(new Tupla(14, 2, 13, 4));
        mapa.add(new Tupla(14, 3, 9, 0.5));
        mapa.add(new Tupla(15, 2, 14, 2));
        mapa.add(new Tupla(15, 3, 10, 0.6));

        while (ciclos < ciclosMaximos)
        {
            for (int i = 0; i < mapa.size(); i++)
                ql.actualizarTablaQ(mapa.get(i));
            
            ciclos++;
        }
        
        ql.mostrarTablaQ();
        boolean salir = false;
        while (estado[0] != estadoFinal[0] && !salir)
        {
            accion      = ql.obtenerMejorAccion(estado);
            refuerzo    = ql.obtenerFuncionQMax(estado);
            posicion    = 0;
            encontrado  = false;
            
            while (!encontrado && !salir)
            {
                if ((mapa.get(posicion).getEstado(0) == estado[0]) && (mapa.get(posicion).getAccion(0) == accion[0])) 
                {
                    System.out.print("Transito de " + estado[0]);
                            
                    estado[0] = mapa.get(posicion).getEstadoSiguiente(0);
                    
                    System.out.println(" a " + estado[0] + " con " + accion[0] + "(" + (double)Math.round(refuerzo * 100)/100 + ")");
                    
                    encontrado = true;
                }
                
                posicion++;

		if(posicion >= mapa.size()) salir = true;
            }
        }
    }
}
