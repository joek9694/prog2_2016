package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.swing.JFileChooser;
import javax.swing.JMenuItem;

import model.TestProgramme;
import view.MapBackGround;

public class SaveController implements ActionListener {
	
	private JMenuItem save;
	private TestProgramme prog;
	private MapBackGround map;
	
	public SaveController(JMenuItem save, TestProgramme prog, MapBackGround map){
		this.save = save;
		this.prog = prog;
		this.map = map;
		
	}

	@Override
	public void actionPerformed(ActionEvent ave) {
		
		if(ave.getSource() == save){
			
			JFileChooser jfc = new JFileChooser(".");
			int svar = jfc.showSaveDialog(null);
			
			if(svar == jfc.APPROVE_OPTION){
				
				try{
					FileWriter fw = new FileWriter(jfc.getSelectedFile());
					PrintWriter out = new PrintWriter(fw);
					
					ArrayList<String> placesAsTxT = new ArrayList<>();
					placesAsTxT.addAll(prog.placesAsSave());
					
					for(String place: placesAsTxT){
						out.println(place);
					}
					out.close();
					
					}catch(IOException e){
//					JOptionPane.showMessageDialog(PersonReg.this,"Fel");
						//FIXME
						
					}
				
				prog.noUnsavedChanges();
				
			}
		}
	}
	
	
}
