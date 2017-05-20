package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

import javax.swing.JButton;

import model.Place;
import model.TestProgramme;
import view.MapBackGround;
import view.PlaceImage;

public class RemoveMarkedController implements ActionListener {
	
	private JButton b;
	private TestProgramme prog;
	private MapBackGround map;
	
	public RemoveMarkedController(JButton b, TestProgramme prog, MapBackGround map){
		this.b = b;
		this.prog = prog;
		this.map = map;
	}
	
	@Override
	public void actionPerformed(ActionEvent ave) {
		
		if(ave.getSource() == b){
			LinkedList<PlaceImage> visuals = prog.removeAllMarked();
			for(PlaceImage pI : visuals){
				map.remove(pI);
				
			}
			map.validate();
			map.repaint();
		}
//		for(Place p : prog.places){			//temp (har bara kollat denna lista)-----tabort! är nu en map!
//			System.out.println(p);
//		}
		
	}

}
