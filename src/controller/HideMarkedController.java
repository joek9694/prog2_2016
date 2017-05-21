package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JList;

import model.TestProgramme;

public class HideMarkedController implements ActionListener {

	private TestProgramme prog;
	private JButton b;
	private JList<String> sideBar;

	public HideMarkedController(JButton b, TestProgramme prog, JList<String> sideBar) {
		this.prog = prog;
		this.b = b;
		this.sideBar = sideBar;
	}

	@Override
	public void actionPerformed(ActionEvent ave) {

		if (ave.getSource() == b) {
			prog.hideAllMarked();
			sideBar.clearSelection();
		}

	}

}
