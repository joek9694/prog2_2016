package view;

import model.TestProgramme;
import controller.*;
import java.awt.*;
import javax.swing.*;


public class MainView extends JFrame {
	private TestProgramme programme;
	private JMenu archive;
	private JButton newPlaceB, hideCategory, hide, search, remove;
	private MapBackGround mapBackGround;
	private JList<String> sideBar;
	private NewPlaceController newPlaceController;
	
	public MainView(TestProgramme programme){
		super("Inlupp 2");
		this.programme = programme;
		
		setLayout(new BorderLayout());
				
		mapBackGround = new MapBackGround();
		JScrollPane centerScroll = new JScrollPane(mapBackGround);
		add(centerScroll, BorderLayout.CENTER);
		
		createMenu();
		createNorth();
		createEast();
		
//		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocation(500, 200);
		setSize(900, 700);
		setVisible(true);
		
	}
	
//	public class MapBackGround extends JPanel{
//		private ImageIcon map;
//		
//		public MapBackGround(){	//C:/Users/Johan/Desktop/SU/Prog2 2016/inl2/jarvafaltet.png
//			map = new ImageIcon();
//		}
//		
//		public void setMapBackGround(String filePath){
//			map = new ImageIcon(filePath);
//			this.setSize(map.getIconWidth(), map.getIconHeight());
//			this.setPreferredSize(new Dimension(map.getIconWidth(), map.getIconHeight()));
//			centerScroll.setViewportView(this);
//			this.validate();
//			// kan skapa platser utanför bilden..?? (lösningen är från förra året)
//		}
//		
//		protected void paintComponent(Graphics g){
//			super.paintComponent(g);
//			g.drawImage(map.getImage(), 0, 0,this);
//		}
//	}

	private void createMenu() {
		JMenuBar menubar = new JMenuBar();
		archive = new JMenu("Archive");
		JMenuItem newMap, loadPlaces, save, exit;
		newMap = new JMenuItem("New Map");
		newMap.addActionListener(new NewMapController(newMap, mapBackGround));
		loadPlaces = new JMenuItem("Load Places");
		save = new JMenuItem("Save");
		exit = new JMenuItem("Exit");
		archive.add(newMap);
		archive.add(loadPlaces);
		archive.add(save);
		archive.add(exit);
		menubar.add(archive);
		archive.setSelected(true);
		
		setJMenuBar(menubar);
	}

	private void createNorth() {
		JPanel north = new JPanel();
		
		JPanel radioButtons = new JPanel();
		JRadioButton named = new JRadioButton("Named");
		JRadioButton described = new JRadioButton("Described");
		ButtonGroup rb = new ButtonGroup();
		JRadioButton[] rbArr = {named, described};
		named.setSelected(true);
		
		north.setLayout(new FlowLayout());
		newPlaceB = new JButton("New");
		north.add(newPlaceB);
		newPlaceController = new NewPlaceController(newPlaceB, rbArr, programme, mapBackGround);
		newPlaceB.addActionListener(newPlaceController);
		
		rb.add(named);
		rb.add(described);
		radioButtons.add(named);
		radioButtons.add(described);
		radioButtons.setLayout(new GridLayout(2,1));
		north.add(radioButtons);
		
		JTextField searchBar = new JTextField("search", 10);
		north.add(searchBar);
		
		search = new JButton("Search");
		search.addActionListener(new SearchController(search, searchBar, programme));
		north.add(search);
		hide = new JButton("Hide");
		hide.addActionListener(new HideMarkedController(hide, programme));
		north.add(hide);
		remove = new JButton("Remove");
		remove.addActionListener(new RemoveMarkedController(remove, programme, mapBackGround));
		north.add(remove);
		north.add(new JButton("Coordinates"));
		
		add(north, BorderLayout.NORTH);
	}
	
	private void createEast() {
		JPanel east = new JPanel();
		JPanel eastLower = new JPanel();
		eastLower.setLayout(new GridBagLayout());
		east.setAlignmentY(CENTER_ALIGNMENT);
		east.setLayout(new BoxLayout(east, BoxLayout.Y_AXIS));
		
		JLabel barName = new JLabel("Categories");
		barName.setAlignmentX(CENTER_ALIGNMENT);
		String[] listData = {"Bus","Underground","Train"};
		sideBar = new JList<String>(listData);
		sideBar.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		sideBar.setFixedCellWidth(150);
		SideBarController sideBarControl = new SideBarController(sideBar, programme);
		sideBar.addListSelectionListener(sideBarControl);
		newPlaceController.setSideBar(sideBar);
		hideCategory = new JButton("Hide Category");
		HideCategoryController hideCatControl = new HideCategoryController(hideCategory, sideBar, programme);
		hideCategory.addActionListener(hideCatControl);
		hideCategory.setAlignmentX(CENTER_ALIGNMENT);
		
		east.add(barName);
		east.add(sideBar);
		east.add(hideCategory);
		
		eastLower.add(east);
		add(eastLower, BorderLayout.EAST);
		
	}
}
