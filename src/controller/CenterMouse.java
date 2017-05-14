package controller;

import java.awt.Cursor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

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
		if(e.getSource().equals(map)){		// måste checka emot styr variabel i mapBackGround ??? poängen är den byter över hela centerPanel
			mouseMoved(e);					// onödig om det inte hjälper.
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
				(e.getY() >= 0 && e.getY() <= (map.getImageIcon().getIconHeight()))){		//Formulera om! (map byter storlek när man förstorar/ förminskar fönstret. måste relatera till bildens X & Y)
			
			p = new Position(e.getX(), e.getY());
			System.out.println(map.getImageIcon().getIconWidth() +";" + map.getImageIcon().getIconHeight());		//test
		
			for (JRadioButton rb : rbs) {
				if (rb.isSelected())
					selected = rb;
			}
			
			String name;
			
			if (selected.getText().equals("Named")) { // temporärt fungerande. Lägg till klick på kartan för koordinater.
				
				name = JOptionPane.showInputDialog("Ange namn: ");
				
				if(name != null && !name.equals("")){
					NamedPlace newPlace = new NamedPlace(p , name, category);
					PlaceController placeControl = new PlaceController(newPlace, map, prog);
					newPlace.getVisual().addMouseListener(placeControl);
					prog.addPlace(newPlace);
					PlaceImage place = newPlace.getVisual();
					map.add(place);
					map.validate();
					map.repaint();
					
					System.out.println(newPlace);
				}
				
				
			}else if (selected.getText().equals("Described")){
				name = "";
				JPanel container = new JPanel();
				container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));
				JPanel firstRow = new JPanel();
				JPanel secondRow = new JPanel();
				
				JLabel placeName = new JLabel("name: ");
				JTextField placeNameField = new JTextField(10);
				firstRow.add(placeName);
				firstRow.add(placeNameField);
				
				JLabel placeDescription = new JLabel("description: ");
				JTextField placeDescriptionField = new JTextField(10);
				secondRow.add(placeDescription);
				secondRow.add(placeDescriptionField);
				
				container.add(firstRow);
				container.add(secondRow);
				JOptionPane.showMessageDialog(null, container);
				
				name = placeNameField.getText();
				String description = placeDescriptionField.getText();
				
				if((name != null && !name.equals("")) && (description != null && !description.equals(""))){
					DescribedPlace newPlace = new DescribedPlace(p , name, category, description);
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
		}
		map.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
		map.removeMouseListener(this);
		
	}
}
