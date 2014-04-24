/*
 * Copyright (c) 2009-2010, Sergey Karakovskiy and Julian Togelius
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *     * Redistributions of source code must retain the above copyright
 *       notice, this list of conditions and the following disclaimer.
 *     * Redistributions in binary form must reproduce the above copyright
 *       notice, this list of conditions and the following disclaimer in the
 *       documentation and/or other materials provided with the distribution.
 *     * Neither the name of the Mario AI nor the
 *       names of its contributors may be used to endorse or promote products
 *       derived from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED.
 * IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT,
 * INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT
 * NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
 * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY,
 * WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE.
 */

package ch.idsia.scenarios;

import java.util.ArrayList;

import ch.idsia.agents.Agent;
import ch.idsia.agents.AgentsPool;
import ch.idsia.agents.controllers.AtributosEvaluar;
import ch.idsia.agents.controllers.ForwardAgent;
import ch.idsia.benchmark.mario.environments.Environment;
import ch.idsia.benchmark.mario.environments.MarioEnvironment;
import ch.idsia.benchmark.tasks.BasicTask;
import ch.idsia.tools.MarioAIOptions;

//Esto quitar luego


/**
 * Created by IntelliJ IDEA. User: Sergey Karakovskiy, sergey at idsia dot ch Date: Mar 17, 2010 Time: 8:28:00 AM
 * Package: ch.idsia.scenarios
 */
public final class Main
{	
	public static int tick;
	public final static ArrayList <String> ticks = new ArrayList <String>();
	public static AtributosEvaluar estadoTick= new AtributosEvaluar();
	
	public static void guardarTick(int tick, Environment environment, boolean[] accion){
		String datos=""; 
		int monedas=0;
		int enemigos=0;
		int ladrillos=0;
		int[] marioState;
		byte [][] env;
		int[] infoEvaluacion;
		
		env = environment.getMergedObservationZZ(1, 1);
		float [] posicion=environment.getMarioFloatPos();
		float marioPosX=posicion[0];
		float marioPosY=posicion[1];
		String matriz="";
		for (int mx = 0; mx < env.length; mx++){
			matriz+="[";
			for (int my = 0; my < env[mx].length; my++){
				matriz+=env[mx][my] + " ";
				switch(env[mx][my]){
				case 2:
					monedas++;
					break;
				case 80:
				case 93:
					enemigos++;
					break;
				case -24:
					ladrillos++;
					break;
				}			
			}
			matriz+="]";
		}
		marioState = environment.getMarioState();
		String state= "[";
		for(int i=0;i< marioState.length;i++){
			if(i!=marioState.length-1){
				state+=marioState[i] + " ";
			}else{
				state+=marioState[i] + "]";
			}
		}
		
		
		infoEvaluacion = environment.getEvaluationInfoAsInts();
		String info="[";
		for (int mx = 0; mx < infoEvaluacion.length; mx++){
			if(mx!=infoEvaluacion.length-1){
				info+=infoEvaluacion[mx] + " ";
			}else{
				info+=infoEvaluacion[mx] + "]";
			}
		}
		
		String action="[";
		for (int mx = 0; mx < accion.length; mx++){
			if(mx!=accion.length-1){
				action+=accion[mx] + " ";
			}else{
				action+=accion[mx] + "]";
			}
		}
		
		int puntuacion = environment.getIntermediateReward();
		datos = "Tick: " + tick + " - " + marioPosX + "," + marioPosY + "," + state + "," + matriz + ","+ info + "," + action + "," + monedas + "," + enemigos + "," + ladrillos + "," + puntuacion;
		Main.ticks.add(datos);
	}
	
	public static void main(String[] args)
	{
		
//  final String argsString = "-vis on";
		
    final MarioAIOptions marioAIOptions = new MarioAIOptions(args);
//        final Environment environment = new MarioEnvironment();
//        final Agent agent = new ForwardAgent();
//        final Agent agent = marioAIOptions.getAgent();
//        final Agent a = AgentsPool.loadAgent("ch.idsia.controllers.agents.controllers.ForwardJumpingAgent");
    final BasicTask basicTask = new BasicTask(marioAIOptions);
//        for (int i = 0; i < 10; ++i)
//        {
//            int seed = 0;
//            do
//            {
//    marioAIOptions.setLevelDifficulty(i);
//               marioAIOptions.setLevelRandSeed(seed++);
    basicTask.setOptionsAndReset(marioAIOptions);
//    basicTask.runSingleEpisode(1);
    basicTask.doEpisodes(1,true,1);
//    System.out.println(basicTask.getEnvironment().getEvaluationInfoAsString());
//            } while (basicTask.getEnvironment().getEvaluationInfo().marioStatus != Environment.MARIO_STATUS_WIN);
//        }
//
		System.exit(0);
	}

}
