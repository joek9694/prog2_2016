package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;

import view.MainView;

public class TestProgramme {
	
	private MainView startView;
	private ArrayList<Place> places = new ArrayList<>();			//temporär
	private HashMap<PlaceCategory, ArrayList<Place>> placesByCat = new HashMap<>();
	private HashMap<String, LinkedList<Place>> placesByName = new HashMap<>();
	private HashSet<Place> markedSet = new HashSet<>();
	
	private TestProgramme (){
		createListsForCategories();
		
	}
	
	public static void main(String[] args) {
		TestProgramme programme = new TestProgramme();
		programme.startView = new MainView(programme);
	}
	
	public void addPlace(Place p){
		addToCategory(p);
		addToByName(p);
		places.add(p);		//temporär
	}
	
	private void addToByName(Place p) {
		String name = p.getName();
		LinkedList<Place> nameList;
		if(!placesByName.containsKey(name)){
			nameList = new LinkedList<>();
			nameList.add(p);
			placesByName.put(name, nameList);
		}else{
			nameList = placesByName.get(name);
			nameList.add(p);			//behövs put i map??
		}
	}

	private void addToCategory(Place p) {
		PlaceCategory cat = p.getPlaceCategory();
		ArrayList<Place> placesOfCategoryCat = placesByCat.get(cat);
		placesOfCategoryCat.add(p);
	}
	
	private void createListsForCategories() {
		PlaceCategory [] cats =PlaceCategory.values();
		for(PlaceCategory p : cats){
			ArrayList<Place> temp = new ArrayList<>();
			placesByCat.put(p, temp);
		}
	}

	public void hideAllByCat(PlaceCategory cat) {
		ArrayList<Place> places = placesByCat.get(cat);
		
		for(Place p : places){
			p.getVisual().setVisible(false);
		}
		
	}

	public void showAllOfCat(PlaceCategory cat) {
		ArrayList<Place> places = placesByCat.get(cat);
		
		for(Place p : places){
			p.getVisual().setVisible(true);
		}
	}
	
	public void showAllByName(String name){
		if(placesByName.containsKey(name)){
			LinkedList<Place> places = placesByName.get(name);
			
			for(Place p : places){
				p.getVisual().setVisible(true);
				if(!p.isMarked()){
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
	
	public void emptyMarkedSet(){
		Iterator<Place> markedIterator = markedSet.iterator();
		while(markedIterator.hasNext()){
			Place p = markedIterator.next();
			p.setIsMarked();
			markedIterator.remove();
		}
	}
	
	public void hideAllMarked(){
		Iterator<Place> markedIterator = markedSet.iterator();
		while(markedIterator.hasNext()){
			Place p = markedIterator.next();
			p.getVisual().setVisible(false);
			p.setIsMarked();
			markedIterator.remove();
		}
		
		
	}
	
}
