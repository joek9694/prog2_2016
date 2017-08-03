package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JTextField;

import model.PlacesProgramme;
import view.MapBackGround;

public class SearchController implements ActionListener {
	private JButton search;
	private JTextField searchBar;
	private PlacesProgramme prog;
	private MapBackGround mapBackGround;

	public SearchController(JButton search, JTextField searchBar, PlacesProgramme prog, MapBackGround mapBackGround) {
		this.search = search;
		this.searchBar = searchBar;
		this.prog = prog;
		this.mapBackGround = mapBackGround;
	}

	@Override
	public void actionPerformed(ActionEvent ave) {

		if (ave.getSource() == search && !searchBar.getText().equals("search")) {

			String s = searchBar.getText();
			prog.emptyMarkedSet();
			prog.showAllByName(s);
			mapBackGround.repaint();

		}
	}

}
