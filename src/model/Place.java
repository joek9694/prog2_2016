package model;

import view.PlaceImage;

public abstract class Place {
	private PlaceCategory category;	//temporärt
	private final Position pos;
	private String name;
	private PlaceImage visual;
	private boolean marked = false;
	private String subType;
	
//	public Place(Position pos, String name){
//		this.pos = pos;
//		this.name = name;
//		category = PlaceCategory.NONE;
//		visual = new PlaceImage(pos.getX(), pos.getY(), this);
//	}
	
	public Place(Position pos, String name, PlaceCategory category, String subType){
		this.pos = pos;
		this.name = name;
		this.category = category;
		this.subType = subType;
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
		//type,category,X,Y,Name,getSpecifics();
		
		return subType +"," + category +"," +pos.getX() + "," + pos.getY()+ ","+ name + spec;
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
		visual.repaint();
		System.out.println("marked? : " +this);			//temp
	}
	
}
