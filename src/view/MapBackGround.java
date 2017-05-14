package view;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class MapBackGround extends JPanel{
	private ImageIcon map;
	
	public MapBackGround(){
		setLayout(null);
		map = new ImageIcon();
	}
	public MapBackGround(String filePath){	//C:/Users/Johan/Desktop/SU/Prog2 2016/inl2/jarvafaltet.png
		setLayout(null);
		map = new ImageIcon();
		
		map = new ImageIcon(filePath);
		this.setBounds(0,0,map.getIconWidth(), map.getIconHeight());
		this.setPreferredSize(new Dimension(map.getIconWidth(), map.getIconHeight()));

		this.validate();
		this.repaint();
	}
	
	public void setMapBackGround(String filePath){
		map = new ImageIcon(filePath);
		this.setBounds(0,0,map.getIconWidth(), map.getIconHeight());
		this.setPreferredSize(new Dimension(map.getIconWidth(), map.getIconHeight()));
		
		this.validate();
		this.repaint();
		// kan skapa platser utanför bilden..?? (lösningen är från förra året)
	}
	
	protected void paintComponent(Graphics g){
		super.paintComponent(g);
		g.drawImage(map.getImage(), 0, 0,this);
	}
	
	public ImageIcon getImageIcon(){
		return map;
	}
}
