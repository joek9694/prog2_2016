package model;

public class Position {
	private int x;
	private int y;
	
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
	@Override
	public boolean equals(Object o){
		if(o instanceof Position){
			Position o2 = (Position)o;
			return this.x == o2.x && this.y == o2.y;
		}
		return false;
	}
	
	@Override
	public int hashCode(){
		
		return x < y ? y * y + x : x * x + x + y;
	}
}
