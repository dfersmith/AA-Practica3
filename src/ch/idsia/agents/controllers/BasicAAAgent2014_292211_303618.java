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

package ch.idsia.agents.controllers;

import ch.idsia.agents.Agent;
import ch.idsia.benchmark.mario.engine.sprites.Mario;
import ch.idsia.benchmark.mario.environments.Environment;
import ch.idsia.scenarios.Main;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Random;

/**
 * Created by IntelliJ IDEA.
 * User: Sergey Karakovskiy
 * Date: Mar 28, 2009
 * Time: 10:37:18 PM
 * Package: ch.idsia.controllers.agents.controllers;
 */
public class BasicAAAgent2014_292211_303618 extends BasicMarioAIAgent implements Agent
{

	public BasicAAAgent2014_292211_303618(){
		super("BasicAAAgent2014_292211_303618");
		reset();
	}

	public void reset(){
		action = new boolean[Environment.numberOfKeys];
	    action[Mario.KEY_RIGHT] = true;	    	    
	}

	
	private boolean DangerOfAny(){
	        if ((getReceptiveFieldCellValue(marioEgoRow + 2, marioEgoCol + 1) == 0 &&
	            getReceptiveFieldCellValue(marioEgoRow + 1, marioEgoCol + 1) == 0) ||
	            getReceptiveFieldCellValue(marioEgoRow, marioEgoCol + 1) != 0 ||
	            getReceptiveFieldCellValue(marioEgoRow, marioEgoCol + 2) != 0 ||
	            getEnemiesCellValue(marioEgoRow, marioEgoCol + 1) != 0 ||
	            getEnemiesCellValue(marioEgoRow, marioEgoCol + 2) != 0)
	            return true;
	        else
	            return false;
	}
	public boolean[] getAction(){
	    			
	    if (DangerOfAny()) {
	    	action[Mario.KEY_JUMP] = isMarioAbleToJump || !isMarioOnGround;
	    }
	     
	    return action;
	}

}


