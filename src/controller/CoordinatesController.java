package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import model.Position;
import model.TestProgramme;
import view.MapBackGround;

public class CoordinatesController implements ActionListener {
	
	private JButton b; 
	private TestProgramme prog;
	private MapBackGround map;
	
	public CoordinatesController (JButton b, TestProgramme prog, MapBackGround map){
		this.b = b;
		this.prog = prog;
		this.map = map;
		
		
	}

	@Override
	public void actionPerformed(ActionEvent ave) {
		
		if(ave.getSource() == b){
			JPanel container = new JPanel();
			JTextField x = new JTextField(5);
			JTextField y = new JTextField(5);
			
			container.add(new JLabel("x:"));
			container.add(x);
			container.add(new JLabel("y:"));
			container.add(y);
			
			int response = JOptionPane.showConfirmDialog(map, container, "Find by X & Y",JOptionPane.OK_CANCEL_OPTION);
			
			if(response == JOptionPane.OK_OPTION){
				int xVal;
				int yVal;
				try{
					xVal = Integer.parseInt(x.getText());
					yVal = Integer.parseInt(y.getText());
					
					if(xVal > 0 && xVal < map.getWidth() && yVal >0 && yVal < map.getHeight()){
						Position p = new Position(xVal, yVal);
						System.out.println(p + ", " + prog.places.containsKey(p));		//temp, varf�r inte i MAP?
						if(prog.places.containsKey(p)){
							prog.emptyMarkedSet();
							prog.showPlaceByPos(p);
						}else{
							// empty pos
							JOptionPane.showMessageDialog
							(map, "There is no Place on that Position", "Info", JOptionPane.INFORMATION_MESSAGE);
						}
					}else{
						//out of bounds
						JOptionPane.showMessageDialog
						(map, "These values are outside of legal values for this map", "Info", JOptionPane.INFORMATION_MESSAGE);
					}
				}catch(NumberFormatException e){
					//faulty values
					JOptionPane.showMessageDialog(map, "x & y have to be integers", "Error" , JOptionPane.ERROR_MESSAGE);
				}
			}
		}
	}

}