package controller;

import javax.swing.JList;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import model.PlaceCategory;
import model.PlacesProgramme;

public class SideBarController implements ListSelectionListener {
	private PlacesProgramme prog;
	private JList<String> sideBar;

	public SideBarController(JList<String> sideBar, PlacesProgramme prog) {
		this.sideBar = sideBar;
		this.prog = prog;
	}

	@Override
	public void valueChanged(ListSelectionEvent e) {
		if (e.getValueIsAdjusting()) {
			String s = sideBar.getSelectedValue();
			PlaceCategory cat = PlaceCategory.valueOf(s.toUpperCase());
			prog.showAllOfCat(cat);
		}

	}

}
