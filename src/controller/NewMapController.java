package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

import view.MainView;
import view.MapBackGround;

public class NewMapController implements ActionListener {
	private JMenuItem newMap;
	private MapBackGround mapBackGround;
	private JScrollPane mapScroll;
	
	public NewMapController(JMenuItem newMap, MapBackGround mapBackGround){
		this.newMap = newMap;
		this.mapBackGround = mapBackGround;
	}

	@Override
	public void actionPerformed(ActionEvent ave) {
		if(ave.getSource() == newMap){		
			FileFilter ff = new FileNameExtensionFilter("Bilder","jpg","gif","png");	//filtrering av vilka filer som visas
			JFileChooser jfc = new JFileChooser(".");
			jfc.setFileFilter(ff);
			int svar = jfc.showOpenDialog(null);
			
			if(svar == jfc.APPROVE_OPTION){
				File file = jfc.getSelectedFile();
				String fileEnd = file.getName().substring(file.getName().lastIndexOf("."));
				if(fileEnd.equals(".jpg")|| fileEnd.equals(".gif") || fileEnd.equals(".png")) //gör nödvändiga checks på filen och sänd som parameter.
//					center.removeAll();
//					mapBackGround = new MapBackGround(file.getAbsolutePath());
					mapBackGround.setMapBackGround(file.getAbsolutePath());
//					center.add(mapBackGround);
//					center.validate();
//					center.repaint();
			}
		}
	}

}
