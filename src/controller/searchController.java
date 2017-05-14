package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JTextField;

import model.TestProgramme;

public class searchController implements ActionListener {
	private JButton search;
	private JTextField searchBar;
	private TestProgramme prog;
	
	public searchController(JButton search, JTextField searchBar, TestProgramme prog){
		this.search = search;
		this.searchBar = searchBar;
		this.prog = prog;
	}

	@Override
	public void actionPerformed(ActionEvent ave) {
		
		if(ave.getSource() == search && !searchBar.getText().equals("search")){
			
			String s = searchBar.getText();
			prog.emptyMarkedSet();
			prog.showAllByName(s);
			
		}
	}

}
