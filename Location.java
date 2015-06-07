
//TODO: Document Class
public class Location {
	static int x;
	static int y;
	
	public int getX(){
		return Location.x;
	}
	
	public int getY(){
		return Location.y;
	}
	
	public void moveX(int x){
		Location.x += x;
	}
	
	public void moveY(){
		Location.y -= 1;
	}
}
