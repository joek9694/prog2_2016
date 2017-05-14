package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JList;

import model.PlaceCategory;
import model.TestProgramme;

public class HideCategoryController implements ActionListener{
	
	private TestProgramme prog;
	private JButton b;
	private JList<String> sideBar;
	
	public HideCategoryController(JButton b, JList<String> sideBar, TestProgramme prog){
		this.prog = prog;
		this.b = b;
		this.sideBar = sideBar;
	}
	
	@Override
	public void actionPerformed(ActionEvent ave) {
		if(ave.getSource() == b){
			PlaceCategory placeCategory = PlaceCategory.NONE;
			if(sideBar.getSelectedValue() != null){
				String category = sideBar.getSelectedValue().toUpperCase();
				placeCategory = PlaceCategory.valueOf(category);
				sideBar.clearSelection();
			}
			
			prog.hideAllByCat(placeCategory);
			
		}
		
	}

}
