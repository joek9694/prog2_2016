package model;

public class DescribedPlace extends Place{
	private String description;
	
	public DescribedPlace(Position pos, String name, PlaceCategory category, String subType, String description){
		super(pos, name, category, subType);
		
		this.description = description;
	}

	@Override
	public String getSpecifics() {
		return description;
	}
	
	
}
