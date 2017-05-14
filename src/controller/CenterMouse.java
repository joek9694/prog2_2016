package controller;

import java.awt.Cursor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JRadioButton;

import model.DescribedPlace;
import model.NamedPlace;
import model.PlaceCategory;
import model.Position;
import model.TestProgramme;
import view.MapBackGround;
import view.PlaceImage;

public class CenterMouse extends MouseAdapter{
	private MapBackGround map;
	private TestProgramme prog;
	private JRadioButton [] rbs;
	private final PlaceCategory category;
	
	public CenterMouse(MapBackGround map, TestProgramme prog, JRadioButton [] rbs, PlaceCategory category){
		this.map = map;
		this.prog = prog;
		this.rbs = rbs;
		this.category = category;
	}
	
	@Override
	public void mouseEntered(MouseEvent e){
		if(e.getSource().equals(map)){		// m�ste checka emot styr variabel i mapBackGround ??? po�ngen �r den byter �ver hela centerPanel
			mouseMoved(e);					// on�dig om det inte hj�lper.
		}
	}
	@Override
	public void mouseMoved(MouseEvent e){
		if(e.getSource() == map){	//.getX() < 100
//			 map.getImageIcon().getImage().getWidth(map) && e.getY() <  map.getImageIcon().getImage().getHeight(map)
			map.setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR));
		}
		else if(e.getSource() == map){	//.getX() < 100
//			map.getImageIcon().getImage().getWidth(map) && e.getY() >= map.getImageIcon().getImage().getHeight(map)
			map.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		JRadioButton selected = null;
		Position p;
		
		
		if ((e.getX() >= 0 && e.getX()<= (map.getImageIcon().getIconWidth())) &&
				(e.getY() >= 0 && e.getY() <= (map.getImageIcon().getIconHeight()))){		//Formulera om! (map byter storlek n�r man f�rstorar/ f�rminskar f�nstret. m�ste relatera till bildens X & Y)
			
			p = new Position(e.getX(), e.getY());
			System.out.println(map.getImageIcon().getIconWidth() +";" + map.getImageIcon().getIconHeight());		//test
		
			for (JRadioButton rb : rbs) {
				if (rb.isSelected())
					selected = rb;
			}
	
			if (selected.getText().equals("Named")) { // tempor�rt fungerande. L�gg till klick p� kartan f�r koordinater.
	
				NamedPlace newPlace = new NamedPlace(p , "Named", category);
				PlaceController placeControl = new PlaceController(newPlace, map, prog);
				newPlace.getVisual().addMouseListener(placeControl);
				prog.addPlace(newPlace);
				PlaceImage place = newPlace.getVisual();
				map.add(place);
				map.validate();
				map.repaint();
				
				System.out.println(newPlace);
				
			}else if (selected.getText().equals("Described")){
				DescribedPlace newPlace = new DescribedPlace(p , "Described", category, "V�rsta beskrivningen");
				PlaceController placeControl = new PlaceController(newPlace, map, prog);
				newPlace.getVisual().addMouseListener(placeControl);
				prog.addPlace(newPlace);
				PlaceImage place = newPlace.getVisual();
				map.add(place);
				map.validate();
				map.repaint();
				
				System.out.println(newPlace);
			}
		}
		map.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
		map.removeMouseListener(this);
		
	}
}
