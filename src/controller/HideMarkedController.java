package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import model.TestProgramme;

public class HideMarkedController implements ActionListener {
	
	private TestProgramme prog;
	private JButton b;
	
	
	public HideMarkedController(JButton b, TestProgramme prog){
		this.prog = prog;
		this.b = b;
	}

	@Override
	public void actionPerformed(ActionEvent ave) {
		
		if(ave.getSource() == b){
			prog.hideAllMarked();
		}
		
	}

}
