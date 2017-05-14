package model;

import view.PlaceImage;

public abstract class Place {
	private PlaceCategory category;	//temporärt
	private final Position pos;
	private String name;
	private PlaceImage visual;
	private boolean marked = false;
	
//	public Place(Position pos, String name){
//		this.pos = pos;
//		this.name = name;
//		category = PlaceCategory.NONE;
//		visual = new PlaceImage(pos.getX(), pos.getY(), this);
//	}
	
	public Place(Position pos, String name, PlaceCategory category){
		this.pos = pos;
		this.name = name;
		this.category = category;
		visual = new PlaceImage(pos.getX(), pos.getY(), this);
	}
	
	public abstract String getSpecifics();
	
	public String getName(){
		return name;
	}
	
	public Position getPos(){
		return pos;
	}
	
	@Override
	public String toString(){
		String spec = getSpecifics();
		if(spec != ""){
			spec = "," +spec;
		}
		return "" + pos + "," + name +","+ category + spec +","+ marked;
	}
	
	public PlaceImage getVisual(){
		return visual;
	}
	
	public PlaceCategory getPlaceCategory(){
		return category;
	}

	public boolean isMarked() {
		return marked;
	}
	
	public void setIsMarked(){
		marked = !marked;
		System.out.println("marked? : " +this);			//temp
	}
	
}
