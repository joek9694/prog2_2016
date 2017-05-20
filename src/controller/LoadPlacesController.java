package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

import model.DescribedPlace;
import model.NamedPlace;
import model.Place;
import model.PlaceCategory;
import model.Position;
import model.TestProgramme;
import view.MapBackGround;
import view.PlaceImage;

public class LoadPlacesController implements ActionListener {
	
	private JMenuItem loadPlaces;
	private TestProgramme prog;
	private MapBackGround map;
	
	private PlaceCategory cat;
	private Position pos;
	private String name;
	private Place p;
	private String subType;
	
	private JFileChooser jfc;
	
	public LoadPlacesController(JMenuItem loadPlaces, TestProgramme prog, MapBackGround map){
		this.loadPlaces = loadPlaces;
		this.prog = prog;
		this.map = map;
	}

	@Override
	public void actionPerformed(ActionEvent ave) {
		
		if(ave.getSource() == loadPlaces){
			jfc = new JFileChooser(".");
			int response = jfc.showOpenDialog(null);
			
			if(response == jfc.APPROVE_OPTION){
				
				if(prog.hasUnSavedChanges()){
					int confirmed = JOptionPane.showConfirmDialog(map, "There are unsaved changes, click Yes to proceed.",
							"Unsaved changes", JOptionPane.YES_NO_CANCEL_OPTION);
					if(confirmed == JOptionPane.YES_OPTION){
						
						tryLoad();
					}
				}else{
					tryLoad();
				}
			}
		}
	}
	
	private void tryLoad(){
		try{
			FileReader in = new FileReader(jfc.getSelectedFile());
			BufferedReader br = new BufferedReader(in);
			String readLine;
			
			if(!prog.places.isEmpty())
				prog.removeAll();
			
			while((readLine = br.readLine()) != null){
				String[] tokens = readLine.split(",");
				subType = tokens[0];
				cat = PlaceCategory.valueOf(tokens[1].toUpperCase());
				pos = new Position(Integer.parseInt(tokens[2]), Integer.parseInt(tokens[3]));
				name = tokens[4];
				
				switch(subType){
					case "Described": 
						String description = tokens[5];
						p = new DescribedPlace(pos, name, cat, "Described", description);
						break;
					default :
						p = new NamedPlace(pos, name, cat, "Named");
						break;
						
				}
				
				prog.addPlace(p);
				PlaceImage place = p.getVisual();
				PlaceController placeControl = new PlaceController(p, map, prog);
				place.addMouseListener(placeControl);
				map.add(place);
				
			}
		}catch(FileNotFoundException e){
			
			//FIXME
			
		}catch(IOException e){
			
			//FIXME
		}
		prog.unSavedChange();
		map.validate();
		map.repaint();
	}

}
