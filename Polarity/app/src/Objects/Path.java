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

	// TODO: determine initial values
	private static int initialDistance = 10;
	private static int initialLength = 1;
	private static Boolean initialColor = true;
	
	// TODO: determine score increment
	private static int scoreIncrement = 1;

	public Queue<Obstacle> obstacles;
	public Player currentPlayer;
	public int score;
	public int obstacleDistance;

	public Obstacle nextObstacle() {
		Random random = new Random();
		int determineBranch = random.nextInt(branchProbability);
		System.out.println(determineBranch + "\n");
		if (determineBranch == 0) {
			Branch branch = new Branch(initialColor, initialDistance,
					initialLength);
			branch.color = random.nextBoolean();
			branch.distance = initialDistance;
			return branch;
		} else {
			Block block = new Block(initialColor, initialDistance,
					initialLength);
			block.color = random.nextBoolean();
			block.distance = initialDistance;
			return block;
		}
	}

	public boolean checkHitObstacle() {
		if (obstacles.peek().distance <= 0) {
			return true;
		} else {
			return false;
		}
	}

	public int increaseScore() {
		this.score += scoreIncrement;
		return this.score;

	}

	public boolean step(){
		if (checkHitObstacle() && currentPlayer.color == obstacles.peek().color) {
			// Phased through object
			obstacles.remove();
			obstacles.add(nextObstacle());
			increaseScore();
		} else if (checkHitObstacle()) {
			// Hit object without phasing through
			// TODO: Launch Game Over sequence
		} else {
			// No contact between player and obstacle yet
			obstacles.peek().decreaseDistance();
			
		}
		return false;
		
	}
}
