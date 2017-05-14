package model;

public class Position {
	private int x;
	private int y;
	
	//f�rbered f�r att agera som hashnyckel
	public Position (int x, int y){
		this.x = x;
		this.y = y;
	}
	@Override
	public String toString(){
		return "[" + x + "," + y +"]";
	}
	
	public int getX(){
		return x;
	}
	
	public int getY(){
		return y;
	}
}
