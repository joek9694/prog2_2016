package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.swing.JFileChooser;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import model.PlacesProgramme;

public class SaveController implements ActionListener {

	private JMenuItem save;
	private PlacesProgramme prog;

	public SaveController(JMenuItem save, PlacesProgramme prog) {
		this.save = save;
		this.prog = prog;
	}

	@Override
	public void actionPerformed(ActionEvent ave) {

		if (ave.getSource() == save) {

			JFileChooser jfc = new JFileChooser(".");
			int svar = jfc.showSaveDialog(null);

			if (svar == JFileChooser.APPROVE_OPTION) {

				try {
					FileWriter fw = new FileWriter(jfc.getSelectedFile());
					PrintWriter out = new PrintWriter(fw);

					ArrayList<String> placesAsTxT = new ArrayList<>();
					placesAsTxT.addAll(prog.placesAsSave());

					for (String place : placesAsTxT) {
						out.println(place);
					}
					out.close();

				} catch (IOException e) {
					JOptionPane.showMessageDialog(null, e.getMessage(),"Error", JOptionPane.ERROR_MESSAGE);
				}

				prog.noUnsavedChanges();

			}
		}
	}

}
