package Objects;

public class Player {
	/*
	 * true = white, false = black
	 */
	private boolean color;
	
	public Player() {
		this.color = true;
	}
	
	public boolean swapColor(){
		color = !color;
		return color;
	}
	
	public boolean getColor(){
		return this.color;
	}
	
}