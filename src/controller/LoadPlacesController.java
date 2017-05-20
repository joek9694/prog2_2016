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
	
	public LoadPlacesController(JMenuItem loadPlaces, TestProgramme prog, MapBackGround map){
		this.loadPlaces = loadPlaces;
		this.prog = prog;
		this.map = map;
	}

	@Override
	public void actionPerformed(ActionEvent ave) {
		PlaceCategory cat;
		Position pos;
		String name;
		Place p;
		String subType;
		
		if(ave.getSource() == loadPlaces){
			JFileChooser jfc = new JFileChooser(".");
			int svar = jfc.showOpenDialog(null);
			
			if(svar == jfc.APPROVE_OPTION){
				
				try{
					FileReader in = new FileReader(jfc.getSelectedFile());
					BufferedReader br = new BufferedReader(in);
					String readLine;
					while((readLine = br.readLine()) != null){
						String[] tokens = readLine.split(",");
						subType = tokens[0];
						cat = PlaceCategory.valueOf(tokens[1].toUpperCase());
						pos = new Position(Integer.parseInt(tokens[2]), Integer.parseInt(tokens[3]));
						name = tokens[4];
						
						switch(subType){
							case "description": 
								String description = tokens[5];
								p = new DescribedPlace(pos, name, cat, description);
								break;
							default :
								p = new NamedPlace(pos, name, cat);
								break;
								
						}
						
						prog.addPlace(p);
						PlaceImage place = p.getVisual();
						PlaceController placeControl = new PlaceController(p, map, prog);
						place.addMouseListener(placeControl);
						map.add(place);
						
						// add place
					}
				}catch(FileNotFoundException e){
					
				}catch(IOException e){
					
				}
				map.validate();
				map.repaint();
			}
			
			
		}
		
	}

}
