package controller;

import java.awt.event.ActionListener;

import javax.swing.*;
import model.*;
import view.MapBackGround;

import java.awt.event.ActionEvent;

public class NewPlaceControl implements ActionListener{
	private TestProgramme prog;
	private JButton b;
	private JRadioButton [] rbs;
	private MapBackGround map;
	private JList<String> sideBar;

	
	public NewPlaceControl(JButton b, JRadioButton[] rbs, TestProgramme prog, MapBackGround map){
		this.b = b;
		this.rbs = rbs;
		this.prog = prog;
		this.map = map;
	}

	@Override
	public void actionPerformed(ActionEvent ave) {
		if(ave.getSource() == b){
			// m�ste ha n�n styr variabel i Map(MapBackGround) som setts true och setts false efter new Place har skapats.
			
			PlaceCategory placeCategory = PlaceCategory.NONE;
			if(sideBar.getSelectedValue() != null){
				String category = sideBar.getSelectedValue().toUpperCase();
				placeCategory = PlaceCategory.valueOf(category);
			} 
			
			
			CenterMouse cm = new CenterMouse(map, prog, rbs, placeCategory);
			map.addMouseListener(cm);
			
			
		}
		
	}
	
	public void setSideBar(JList<String> sideBar){
		this.sideBar = sideBar;
	}
}
