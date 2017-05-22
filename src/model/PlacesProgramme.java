package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;

import view.MainView;
import view.PlaceImage;

public class PlacesProgramme {

	private HashMap<Position, Place> places = new HashMap<>();
	private HashMap<PlaceCategory, ArrayList<Place>> placesByCat = new HashMap<>();
	private HashMap<String, LinkedList<Place>> placesByName = new HashMap<>();
	private HashSet<Place> markedSet = new HashSet<>();
	private boolean unSavedChanges = false;

	private PlacesProgramme() {
		createListsForCategories();
		new MainView(this);
	}

	public static void main(String[] args) {
		new PlacesProgramme();
	}

	public boolean hasUnSavedChanges() {
		return unSavedChanges;
	}

	public void unSavedChange() {
		unSavedChanges = true;
	}

	public void noUnsavedChanges() {
		unSavedChanges = false;
	}

	public void addPlace(Place p) {
		addToCategory(p);
		addToByName(p);
		places.put(p.getPos(), p);
	}

	public LinkedList<PlaceImage> removeAllMarked() {
		Iterator<Place> markedIterator = markedSet.iterator();
		LinkedList<PlaceImage> visuals = new LinkedList<>();

		while (markedIterator.hasNext()) {
			Place p = markedIterator.next();
			visuals.add(p.getVisual());
			ArrayList<Place> catList = placesByCat.get(p.getPlaceCategory());
			LinkedList<Place> namedList = placesByName.get(p.getName());

			places.remove(p.getPos());
			catList.remove(p);

			namedList.remove(p);
			if (namedList.isEmpty()) {
				placesByName.remove(namedList);
			}

			markedIterator.remove();
		}
		return visuals;
	}

	private void addToByName(Place p) {
		String name = p.getName();
		LinkedList<Place> nameList;
		if (!placesByName.containsKey(name)) {
			nameList = new LinkedList<>();
			nameList.add(p);
			placesByName.put(name, nameList);
		} else {
			nameList = placesByName.get(name);
			nameList.add(p);
		}
	}

	private void addToCategory(Place p) {
		PlaceCategory cat = p.getPlaceCategory();
		ArrayList<Place> placesOfCategoryCat = placesByCat.get(cat);
		placesOfCategoryCat.add(p);
	}

	private void createListsForCategories() {
		PlaceCategory[] cats = PlaceCategory.values();
		for (PlaceCategory p : cats) {
			ArrayList<Place> temp = new ArrayList<>();
			placesByCat.put(p, temp);
		}
	}

	public void hideAllByCat(PlaceCategory cat) {
		ArrayList<Place> places = placesByCat.get(cat);

		for (Place p : places) {
			p.getVisual().setVisible(false);
		}

	}

	public void showAllOfCat(PlaceCategory cat) {
		ArrayList<Place> placeList = placesByCat.get(cat);

		for (Place p : placeList) {
			p.getVisual().setVisible(true);
		}
	}

	public void showAllByName(String name) {
		if (placesByName.containsKey(name)) {
			LinkedList<Place> places = placesByName.get(name);

			for (Place p : places) {
				p.getVisual().setVisible(true);
				if (!p.isMarked()) {
					p.setIsMarked();
					addToMarked(p);
				}
			}
		}
	}

	public void addToMarked(Place p) {
		markedSet.add(p);
	}

	public void removeFromMarked(Place p) {
		markedSet.remove(p);
	}

	public void emptyMarkedSet() {
		Iterator<Place> markedIterator = markedSet.iterator();
		while (markedIterator.hasNext()) {
			Place p = markedIterator.next();
			p.setIsMarked();
			markedIterator.remove();
		}
	}

	public void hideAllMarked() {
		Iterator<Place> markedIterator = markedSet.iterator();
		while (markedIterator.hasNext()) {
			Place p = markedIterator.next();
			p.getVisual().setVisible(false);
			p.setIsMarked();
			markedIterator.remove();
		}

	}

	public void showPlaceByPos(Position pos) {
		Place p = places.get(pos);
		p.getVisual().setVisible(true);
		p.setIsMarked();
		markedSet.add(p);

	}

	public ArrayList<String> placesAsSave() {
		ArrayList<String> placesStringList = new ArrayList<>();

		for (Place p : places.values()) {
			placesStringList.add(p.toString());
		}

		return placesStringList;
	}

	public void removeAll() {
		for (Place p : places.values()) {
			addToMarked(p);
		}

		removeAllMarked();
	}

	public boolean placesContainsKey(Position p) {
		return places.containsKey(p);
	}

	public boolean placesIsEmpty() {
		return places.isEmpty();
	}

}
