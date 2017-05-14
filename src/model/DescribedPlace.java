package model;

public class DescribedPlace extends Place{
	private String description;
	
	public DescribedPlace(Position pos, String name, PlaceCategory category, String description){
		super(pos, name, category);
		
		this.description = description;
	}

	@Override
	public String getSpecifics() {
		return description;
	}
	
	
}
