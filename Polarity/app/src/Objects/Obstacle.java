package Objects;

public abstract class Obstacle{
	
	// true = white, false = black
	public Boolean color;
	public int distance;
	public int length;
	
	/*
	 * Constructor
	 */
	public Obstacle(Boolean color, int distance, int length){
		this.color = color;
		this.distance = distance;
		this.length = length;
	}
	
	public void decreaseDistance(){
		this.distance -= 1;
	}
	
	public void decreaseLength(){
		this.length -= 1;
	}
	
	public void draw(){
		//TODO: Implement draw() with Android Project framework.
	}
}
