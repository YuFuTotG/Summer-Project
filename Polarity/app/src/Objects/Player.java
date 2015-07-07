package Objects;

public class Player {
	
	// true = white, false = black
	public Boolean color;
	
	public void swapColor(){
		this.color = !this.color;
	}
}
