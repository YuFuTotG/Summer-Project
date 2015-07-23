package gameplay;

import java.awt.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Queue;

public class Path {
	
	private static final int branchProbability = 5; // the probability of a random branch being added to the queue is 100% divided by branchProbability
	private int obstacleDistGen = 10; // Distance away from obstacle ahead of it
	private int unitOfTravel = 1; // score incrementation, distance regimentation, etc..

	
	private Player curPlayer;
	private Queue<Obstacle> obstacles;
	private int score;
	
	public path(){
			
	}
	
	// generation of branch or block obstacle
	private static Obstacle genRandObstacle(int chance){
		
		Obstacle obs;
		
		// generate random obstacle. Branch/Block
		Random rand = new Random();
		int determineBranch = rand.nextInt(branchProbability);
		return (determineBranch == 0) ? new Branch(obstacleDistGen): new Block(obstacleDistGen);
	}
	
	// switch to the next obstacle in the stack.
	// removes head element  // adds new element to tail
	// runs after player passes previous obstacle
	private void nextObstacle(){
		obstacles.remove();
		obstacles.add(genRandObstacle(branchProbability));
	}
	
	// increments score
	private int  scoreIncr(){
		score += unitOfTravel;
		return score;
	}
	
	//decrement obstacle distance
	private boolean distObstacleDecr(){
		return obstacles.element().decrement(unitOfTravel);
	}
	
	// 
	private boolean checkHitObstacle(){
		Obstacle obs = obstacles.element();
		return ((obs.getDistance() == 0) && (obs.getColor() == curPlayer.getColor()));
	}
	
	//
	public boolean step(){
		if (checkHitObstacle()) { return false; }
		if (distObstacleDecr()) { nextObstacle(); }
		scoreIncr();
		return true;
	}

}
