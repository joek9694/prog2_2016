package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import model.TestProgramme;
import view.MapBackGround;

public class ExitController extends WindowAdapter implements ActionListener {
	private JMenuItem exit;
	private TestProgramme prog;
	private MapBackGround map;

	public ExitController(TestProgramme prog, MapBackGround map) {
		this.prog = prog;
		this.map = map;
	}

	public ExitController(JMenuItem exit, TestProgramme prog, MapBackGround map) {
		this.exit = exit;
		this.prog = prog;
		this.map = map;
	}

	@Override
	public void actionPerformed(ActionEvent ave) {

		if (ave.getSource() == exit) {
			closeOperation();
		}

	}

	@Override
	public void windowClosing(WindowEvent wev) {

		if (wev.getID() == WindowEvent.WINDOW_CLOSING) {
			closeOperation();
		}
	}

	private void closeOperation() {
		if (prog.hasUnSavedChanges()) {
			int response = JOptionPane.showConfirmDialog(map, "There are unsaved changes, click YES to proceed",
					"Unsaved changes", JOptionPane.YES_NO_OPTION);

			if (response == JOptionPane.YES_OPTION) {
				System.exit(0);
			}
		} else {
			System.exit(0);
		}
	}

}
