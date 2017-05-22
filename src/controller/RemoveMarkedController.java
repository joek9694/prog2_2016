package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JList;

import java.util.LinkedList;

import model.PlacesProgramme;
import view.MapBackGround;
import view.PlaceImage;

public class RemoveMarkedController implements ActionListener {

	private JButton b;
	private PlacesProgramme prog;
	private MapBackGround map;
	private JList<String> sideBar;

	public RemoveMarkedController(JButton b, PlacesProgramme prog, MapBackGround map, JList<String> sideBar) {
		this.b = b;
		this.prog = prog;
		this.map = map;
		this.sideBar = sideBar;
	}

	@Override
	public void actionPerformed(ActionEvent ave) {

		if (ave.getSource() == b) {
			LinkedList<PlaceImage> visuals = prog.removeAllMarked();
			for (PlaceImage pI : visuals) {
				map.remove(pI);

			}
			sideBar.clearSelection();
			map.validate();
			map.repaint();
		}

	}

}
