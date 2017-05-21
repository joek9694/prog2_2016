package view;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.BoxLayout;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import model.DescribedPlace;
import model.NamedPlace;
import model.Place;
import model.PlaceCategory;

public class PlaceImage extends JComponent {
	private Place p;
	private Color c;
	private int x;
	private int y;
	private int[] xPoints = { 0, 15, 30 };
	private int[] yPoints = { 0, 30, 0 };

	public PlaceImage(int x, int y, Place p) {
		this.p = p;
		this.x = x;
		this.y = y;
		setBounds(x, y, 30, 30);
		reCalculateXAndY();
		setColorByCategory(p.getPlaceCategory());
	}

	private void reCalculateXAndY() {
		x = x - 15;
		y = y - 30;
	}

	@Override
	protected void paintComponent(Graphics g) {
		g.setColor(c);
		g.fillPolygon(xPoints, yPoints, 3);
		setBounds(x, y, 30, 30);

		if (p.isMarked()) {
			g.setColor(Color.RED);
			g.drawRect(0, 0, 30 - 1, 30 - 1);
		}
	}

	public void setColorByCategory(PlaceCategory p) {
		switch (p) {
		case NONE:
			c = Color.BLACK;
			break;
		case BUS:
			c = Color.RED;
			break;
		case UNDERGROUND:
			c = Color.BLUE;
			break;
		case TRAIN:
			c = Color.GREEN;
			break;
		}
	}

	public void showInfo() {
		if (p instanceof NamedPlace)
			JOptionPane.showMessageDialog(this, p.getName() + p.getPos());

		if (p instanceof DescribedPlace) {
			JPanel container = new JPanel();
			container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));
			JPanel firstRow = new JPanel();
			JLabel name = new JLabel("Name: ");
			JTextField info = new JTextField(p.getName() + " " + p.getPos());
			info.setEditable(false);
			firstRow.add(name);
			firstRow.add(info);

			JPanel secondRow = new JPanel();
			JLabel label = new JLabel("Description: ");
			JTextField description = new JTextField(p.getSpecifics());
			description.setEditable(false);
			secondRow.add(label);
			secondRow.add(description);

			container.add(firstRow);
			container.add(secondRow);

			JOptionPane.showMessageDialog(this, container);
		}
	}

}
