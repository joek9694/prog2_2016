package model;

import view.PlaceImage;

public class NamedPlace extends Place{
	
	String subType;
	
	public NamedPlace(Position pos, String name, PlaceCategory category, String subType){
		super(pos, name, category, subType);
		
		
	}

	@Override
	public String getSpecifics() {
		return "";
	}

}
