package model;

public class NamedPlace extends Place {

	public NamedPlace(Position pos, String name, PlaceCategory category, String subType) {
		super(pos, name, category, subType);

	}

	@Override
	public String getSpecifics() {
		return "";
	}

}
