package view;

import model.PlacesProgramme;
import controller.*;
import java.awt.*;
import javax.swing.*;

public class MainView extends JFrame {
	private PlacesProgramme programme;
	private JMenu archive;
	private JButton newPlaceB, hideCategory, hide, search, remove, coordinates;
	private MapBackGround mapBackGround;
	private JList<String> sideBar;
	private NewPlaceController newPlaceController;

	public MainView(PlacesProgramme programme) {
		super("Inlupp 2");
		this.programme = programme;

		setLayout(new BorderLayout());

		mapBackGround = new MapBackGround();
		JScrollPane centerScroll = new JScrollPane(mapBackGround);
		add(centerScroll, BorderLayout.CENTER);

		setupVariables();
		createNorth();
		createEast();
		createMenu();

		this.addWindowListener(new ExitController(programme, mapBackGround));
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		setLocation(500, 200);
		setSize(900, 700);
		setVisible(true);
		archive.doClick();

	}

	private void setupVariables() {
		archive = new JMenu("Archive");

		newPlaceB = new JButton("New");
		search = new JButton("Search");
		hide = new JButton("Hide");
		remove = new JButton("Remove");
		coordinates = new JButton("Coordinates");
		hideCategory = new JButton("Hide Category");

		String[] listData = { "Bus", "Underground", "Train" };
		sideBar = new JList<String>(listData);

	}

	private void createMenu() {
		JMenuBar menubar = new JMenuBar();
		setJMenuBar(menubar);

		menubar.add(archive);

		JMenuItem newMap, loadPlaces, save, exit;
		newMap = new JMenuItem("New Map");
		newMap.addActionListener(new NewMapController(newMap, mapBackGround));
		loadPlaces = new JMenuItem("Load Places");
		loadPlaces.addActionListener(new LoadPlacesController(loadPlaces, programme, mapBackGround));
		save = new JMenuItem("Save");
		save.addActionListener(new SaveController(save, programme));
		exit = new JMenuItem("Exit");
		exit.addActionListener(new ExitController(exit, programme, mapBackGround));
		archive.add(newMap);
		archive.add(loadPlaces);
		archive.add(save);
		archive.add(exit);

	}

	private void createNorth() {
		JPanel north = new JPanel();

		JPanel radioButtons = new JPanel();
		JRadioButton named = new JRadioButton("Named");
		JRadioButton described = new JRadioButton("Described");
		ButtonGroup rb = new ButtonGroup();
		JRadioButton[] rbArr = { named, described };
		named.setSelected(true);

		north.setLayout(new FlowLayout());
		north.add(newPlaceB);
		newPlaceController = new NewPlaceController(newPlaceB, rbArr, programme, mapBackGround);
		newPlaceB.addActionListener(newPlaceController);

		rb.add(named);
		rb.add(described);
		radioButtons.add(named);
		radioButtons.add(described);
		radioButtons.setLayout(new GridLayout(2, 1));
		north.add(radioButtons);

		JTextField searchBar = new JTextField("search", 10);
		north.add(searchBar);

		search.addActionListener(new SearchController(search, searchBar, programme, mapBackGround));
		north.add(search);
		hide.addActionListener(new HideMarkedController(hide, programme, sideBar));
		north.add(hide);
		remove.addActionListener(new RemoveMarkedController(remove, programme, mapBackGround, sideBar));
		north.add(remove);
		coordinates.addActionListener(new CoordinatesController(coordinates, programme, mapBackGround));
		north.add(coordinates);

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
		sideBar.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		sideBar.setFixedCellWidth(150);
		SideBarController sideBarControl = new SideBarController(sideBar, programme);
		sideBar.addListSelectionListener(sideBarControl);
		newPlaceController.setSideBar(sideBar);
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
