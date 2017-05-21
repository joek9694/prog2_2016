package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

import view.MapBackGround;

public class NewMapController implements ActionListener {
	private JMenuItem newMap;
	private MapBackGround mapBackGround;

	public NewMapController(JMenuItem newMap, MapBackGround mapBackGround) {
		this.newMap = newMap;
		this.mapBackGround = mapBackGround;
	}

	@Override
	public void actionPerformed(ActionEvent ave) {
		if (ave.getSource() == newMap) {
			FileFilter ff = new FileNameExtensionFilter("Bilder", "jpg", "gif", "png");
			JFileChooser jfc = new JFileChooser(".");
			jfc.setFileFilter(ff);
			int svar = jfc.showOpenDialog(null);

			if (svar == JFileChooser.APPROVE_OPTION) {
				File file = jfc.getSelectedFile();
				String fileEnd = file.getName().substring(file.getName().lastIndexOf("."));
				if (fileEnd.equals(".jpg") || fileEnd.equals(".gif") || fileEnd.equals(".png"))

					mapBackGround.setMapBackGround(file.getAbsolutePath());
			}
		}
	}

}
