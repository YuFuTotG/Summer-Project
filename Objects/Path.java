package Objects;

import java.awt.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Queue;
import Objects.*;

public class Path {
	// the probability of a random branch being added to the queue is
	// 100% divided by branchProbability
	private static int branchProbability = 5;
	
	//TODO: determine initial distance
	private static int initialDistance = 0;

	public Queue<Obstacle> obstacles;

	public void addObstacle() {
		Random random = new Random();
		int determineBranch = random.nextInt(branchProbability);
		System.out.println(determineBranch + "\n");
		if (determineBranch == 0){
			Branch branch = new Branch();
			branch.color = random.nextBoolean();
			branch.distance = initialDistance; 
			obstacles.add(branch);
		} else {
			Block block = new Block();
			block.color = random.nextBoolean();
			block.distance = initialDistance;
			obstacles.add(block);
		}			
	}
	
	//TODO: Waiting on UML to complete class

}
