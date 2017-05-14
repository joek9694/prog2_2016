package model;

import view.PlaceImage;

public class NamedPlace extends Place{
	
//	public NamedPlace(Position pos, String name){
//		super(pos, name);
//	}
	
	public NamedPlace(Position pos, String name, PlaceCategory category){
		super(pos, name, category);
		
	}

	@Override
	public String getSpecifics() {
		return "";
	}
	

}
