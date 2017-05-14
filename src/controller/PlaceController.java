package controller;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JOptionPane;

import model.Place;
import model.TestProgramme;
import view.MapBackGround;


public class PlaceController extends MouseAdapter{
	private TestProgramme prog;
	private Place p;
	private MapBackGround map;
	
	public PlaceController(Place p, MapBackGround map, TestProgramme prog){
		this.prog = prog;
		this.p = p;
		this.map = map;
		
	}
	
	@Override
	public void mouseClicked(MouseEvent mev){
		if(mev.getButton() == MouseEvent.BUTTON1){
			if(p.isMarked()){
				prog.removeFromMarked(p);
			}else{
				prog.addToMarked(p);
			}
			p.setIsMarked();
//			map.validate();
			map.repaint();
		}
		
		if(mev.getButton() == MouseEvent.BUTTON3){
			p.getVisual().showInfo();
		}
		
	}

}
