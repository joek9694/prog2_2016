package controller;

import java.awt.Cursor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import model.DescribedPlace;
import model.NamedPlace;
import model.PlaceCategory;
import model.Position;
import model.PlacesProgramme;
import view.MapBackGround;
import view.PlaceImage;

public class CenterMouse extends MouseAdapter {
	private MapBackGround map;
	private PlacesProgramme prog;
	private JRadioButton[] rbs;
	private final PlaceCategory category;
	private JButton b;

	public CenterMouse(MapBackGround map, PlacesProgramme prog, JRadioButton[] rbs, PlaceCategory category, JButton b) {
		this.map = map;
		this.prog = prog;
		this.rbs = rbs;
		this.category = category;
		this.b = b;
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		if (e.getSource().equals(map)) {
			
			map.setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR));
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		JRadioButton selected = null;
		Position p;

		if ((e.getX() >= 0 && e.getX() <= (map.getImageIcon().getIconWidth()))
				&& (e.getY() >= 0 && e.getY() <= (map.getImageIcon().getIconHeight()))) {

			p = new Position(e.getX(), e.getY());
			if(!prog.placesContainsKey(p)){
				
				for (JRadioButton rb : rbs) {
					if (rb.isSelected())
						selected = rb;
				}

				String name;

				if (selected.getText().equals("Named")) {

					name = JOptionPane.showInputDialog("Ange namn: ");

					if (name != null && !name.equals("")) {
						NamedPlace newPlace = new NamedPlace(p, name, category, "Named");
						PlaceController placeControl = new PlaceController(newPlace, map, prog);
						newPlace.getVisual().addMouseListener(placeControl);
						prog.addPlace(newPlace);
						PlaceImage place = newPlace.getVisual();
						map.add(place);

						prog.unSavedChange();

						map.validate();
						map.repaint();
					}

				} else if (selected.getText().equals("Described")) {
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

					if ((name != null && !name.equals("")) && (description != null && !description.equals(""))) {
						DescribedPlace newPlace = new DescribedPlace(p, name, category, "Described", description);
						PlaceController placeControl = new PlaceController(newPlace, map, prog);
						newPlace.getVisual().addMouseListener(placeControl);
						prog.addPlace(newPlace);
						PlaceImage place = newPlace.getVisual();
						map.add(place);

						prog.unSavedChange();

						map.validate();
						map.repaint();
					}

				}
			}else{
				JOptionPane.showMessageDialog(map, "There is already a Place at theese coordinates.", "Info",
						JOptionPane.INFORMATION_MESSAGE);
			}
		}
		b.setEnabled(true);
		map.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
		map.removeMouseListener(this);

	}
}
