package ch.idsia.agents.controllers;

import ch.idsia.agents.Agent;
import ch.idsia.benchmark.mario.engine.sprites.Mario;
import ch.idsia.benchmark.mario.environments.Environment;
import ch.idsia.scenarios.Main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Random;

/**
 * Created by IntelliJ IDEA.
 * User: Sergey Karakovskiy
 * Date: Mar 28, 2009
 * Time: 10:37:18 PM
 * Package: ch.idsia.controllers.agents.controllers;
 */
public class Practica2AgentAA2014_292211_303618 extends BasicMarioAIAgent implements Agent{
	AtributosEvaluar centroides [] = new AtributosEvaluar [5];
	ArrayList<AtributosEvaluar> datos = new ArrayList<AtributosEvaluar>();

	//private Random R = null;
	public Practica2AgentAA2014_292211_303618()
	{
		super("Practica2AgentAA2014_292211_303618");

		for(int i=0;i<centroides.length;i++){
			centroides[i]=new AtributosEvaluar();
		}
		//El cluster0 tiene todo 0's
		centroides[1].enemigoDelante=1; //los otros 3 atributos son 0 y los otros campos no se usan aqui
		centroides[2].bloqueEncima=1; //los otros 3 atributos son 0 y los otros campos no se usan aqui
		centroides[3].obstaculoDelante=1; //los otros 3 atributos son 0 y los otros campos no se usan aqui
		centroides[4].monedaEncima=1; //los otros 3 atributos son 0 y los otros campos no se usan aqui
	

		File lectura = null;
		FileReader fread = null;
		BufferedReader bread = null;
		try {
			// Abre fichero para leer
			System.out.println("abre fichero para leer");
			lectura = new File ("instanciasClusterizadas.arff");
			fread = new FileReader (lectura);
			bread = new BufferedReader(fread);

			// Lectura del fichero
			System.out.print("lectura fichero: ");
			String linea;
			int contar=0;
			while((linea=bread.readLine())!=null){
				if(linea.trim().length()>0 && linea.charAt(0)!='@'){ //Solo coge los ticks
					String aux [] = linea.split(",");
					int a=1; //No coge el num de instancia
					AtributosEvaluar instancia = new AtributosEvaluar();				
					instancia.enemigoDelante=Integer.parseInt(aux[a++]);
					instancia.monedaEncima=Integer.parseInt(aux[a++]);
					instancia.bloqueEncima=Integer.parseInt(aux[a++]);
					instancia.obstaculoDelante=Integer.parseInt(aux[a++]);
					
					instancia.left=aux[a++];
					instancia.right=aux[a++];
					instancia.down=aux[a++];
					instancia.jump=aux[a++];
					instancia.speed=aux[a++];
					instancia.up=aux[a++];
					instancia.PuntuacionDistancia=Integer.parseInt(aux[a++]);
					instancia.cluster=aux[a];
					datos.add(instancia);
					contar++;
				}
			}
			System.out.println(contar+" instancias");
		}
		catch(Exception e){
			e.printStackTrace();
		}finally{
			// En el finally cerramos el fichero
			try{                    
				if( null != fread ){   
					fread.close();     
				}                  
			}catch (Exception e2){ 
				e2.printStackTrace();
			}
		}

		//reset();		
	}

	public void reset()
	{
		// Dummy reset, of course, but meet formalities!
		//R = new Random();
	}

	public boolean[] getAction()
	{
		// La accion es un array de booleanos de dimension 6
		// action[Mario.KEY_LEFT] Mueve a Mario a la izquierda
		// action[Mario.KEY_RIGHT] Mueve a Mario a la derecha
		// action[Mario.KEY_DOWN] Mario se agacha si esta en estado grande
		// action[Mario.KEY_JUMP] Mario salta
		// action[Mario.KEY_SPEED] Incrementa la velocidad de Mario y dispara si esta en modo fuego
		// action[Mario.KEY_UP] Arriba
		// Se puede utilizar cualquier combinacion de valores true, false para este array
		// Por ejemplo: (false true false true false false) Mario salta a la derecha
		// IMPORTANTE: Si se ejecuta la accion anterior todo el tiempo, Mario no va a saltar todo el tiempo hacia adelante. 
		// Cuando se ejecuta la primera vez la accion anterior, se pulsa el boton de saltar, y se mantiene pulsado hasta que 
		// no se indique explicitamente action[Mario.KEY_JUMP] = false. Si habeis podido jugar a Mario en la consola de verdad, 
		// os dareis cuenta que si manteneis pulsado todo el tiempo el boton de saltar, Mario no salta todo el tiempo sino una 
		// unica vez en el momento en que se pulsa. Para volver a saltar debeis despulsarlo (action[Mario.KEY_JUMP] = false), 
		// y volverlo a pulsar (action[Mario.KEY_JUMP] = true).


		int cluster=0;
		double euclides=hallarEuclides(centroides[0],Main.estadoTick);
		for(int i=0;i<centroides.length;i++){			
			double aux=hallarEuclides(centroides[i],Main.estadoTick);
			if(aux<euclides){
				euclides=aux;
				cluster=i;
			}
		}
		
		int puntDist=0;
		int aux =0;
		int instancia=0;
		for(int i=0;i<datos.size();i++){
			if(datos.get(i).cluster.equals("cluster"+cluster)){				
				aux=datos.get(i).PuntuacionDistancia;
				if(aux>puntDist){
					puntDist=aux;
					action[Mario.KEY_LEFT] = datos.get(i).left.equals("Si");
					action[Mario.KEY_RIGHT] = datos.get(i).right.equals("Si");
					action[Mario.KEY_DOWN] = datos.get(i).down.equals("Si");
					action[Mario.KEY_JUMP] = datos.get(i).jump.equals("Si") && (isMarioAbleToJump || !isMarioOnGround);
					action[Mario.KEY_SPEED] = datos.get(i).speed.equals("Si");
					action[Mario.KEY_UP] = datos.get(i).up.equals("Si");
					instancia=i;
				}
			}
		}
		//System.out.println("Este tick pertenece al cluster: " + cluster);
		//System.out.println("Se utiliza la instancia " + instancia);
		return action;
	}

	public double hallarEuclides(AtributosEvaluar centroides, AtributosEvaluar tick){
		double dif=0;
		double cuadrado=0;
		double suma =0;
		double euclides =0;
		dif=centroides.enemigoDelante-tick.enemigoDelante;
		cuadrado=Math.pow(dif, 2);
		suma+=cuadrado;
		dif=centroides.monedaEncima-tick.monedaEncima;		
		cuadrado=Math.pow(dif, 2);
		suma+=cuadrado;
		dif=centroides.bloqueEncima-tick.bloqueEncima;		
		cuadrado=Math.pow(dif, 2);
		suma+=cuadrado;
		dif=centroides.obstaculoDelante-tick.obstaculoDelante;		
		cuadrado=Math.pow(dif, 2);
		suma+=cuadrado;		
		euclides=Math.sqrt(suma);
		return euclides;		
	}
}



