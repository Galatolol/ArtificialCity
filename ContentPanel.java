
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.Timer;

public class ContentPanel extends JPanel implements ActionListener {
	
	private Image bgImage = null;
	private Timer tm = new Timer(30, this);
	static int counter = 0;
	private Graph myGraph;
	private PedestriansGraph pGraph; 
	private List<Vehicle> vehicleList = new ArrayList<Vehicle>();
	private List<Pedestrian> pList = new ArrayList<Pedestrian>();
	public static List<Vehicle> vehicleList1 = new ArrayList<Vehicle>();

	ContentPanel() {
		//setLayout(new BorderLayout());
		MediaTracker mt = new MediaTracker(this);
		bgImage = Toolkit.getDefaultToolkit().getImage("res/map.jpg");
		mt.addImage(bgImage, 0);
		try {
			mt.waitForAll();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		myGraph = new Graph();
	    myGraph.init();
	    pGraph = new PedestriansGraph();
	    pGraph.init();
		
	    Util.createStreets(myGraph);
		
		ArrayList<Person> personList = new ArrayList<Person>();
		Generator.generate(1, personList);
		
		for (Person person : personList)
		{
			if (person.getIsDriving())
			{
				//vehicleList.add(Generator.generateCar(person));
				for (int i = 0; i < 20; i++)
				{
					//vehicleList.add(dupa());
				}
			}
		}
		
		Person person50 = new Person(50, "", "", "", "", "", "", "", true);
		person50.setAllVertices(myGraph.vertices.get(51), myGraph.vertices.get(47), myGraph.vertices.get(3));
		Pedestrian ped = new Pedestrian(person50);
		ped.setStreet(Util.createPedestriansStreets(pGraph));
		
		int d;
		if (ped.getStreet()[0].clDir)
		{
			d = 0;
		}
		else
		{
			d = ped.getStreet()[0].cellList.length - 1;
		}
		ped.setCurrentCell(ped.getStreet()[0].cellList[d]);
		pGraph.calcWeightedShortestPath(ped);
		pList.add(ped);

		//addCars();
		//addPublicTransport();
		
		Person person2 = new Person(50, "", "", "", "", "", "", "", true);
		person2.setAllVertices(myGraph.vertices.get(78), myGraph.vertices.get(79), myGraph.vertices.get(87));
		vehicleList.add(Generator.generateCar(person2));
		Person person25 = new Person(50, "", "", "", "", "", "", "", true);
		person2.setAllVertices(myGraph.vertices.get(78), myGraph.vertices.get(79), myGraph.vertices.get(87));
		vehicleList.add(Generator.generateCar(person2));
		Person person26 = new Person(50, "", "", "", "", "", "", "", true);
		person2.setAllVertices(myGraph.vertices.get(78), myGraph.vertices.get(79), myGraph.vertices.get(87));
		vehicleList.add(Generator.generateCar(person2));
		Person person27 = new Person(50, "", "", "", "", "", "", "", true);
		person2.setAllVertices(myGraph.vertices.get(78), myGraph.vertices.get(79), myGraph.vertices.get(87));
		vehicleList.add(Generator.generateCar(person2));
		Person person28 = new Person(50, "", "", "", "", "", "", "", true);
		person2.setAllVertices(myGraph.vertices.get(78), myGraph.vertices.get(79), myGraph.vertices.get(87));
		vehicleList.add(Generator.generateCar(person2));
		
		tm.start();
	}
	
	protected void addPublicTransport()
	{
		Vehicle tram1 = Generator.generatePubTran(8, 1); //numer linii, kierunek jazdy
		Vehicle tram2 = Generator.generatePubTran(8, 2);
		Vehicle tram3 = Generator.generatePubTran(4, 1);
		Vehicle tram4 = Generator.generatePubTran(4, 2);
		Vehicle tram5 = Generator.generatePubTran(18, 1);
		Vehicle tram6 = Generator.generatePubTran(18, 2);
		
		Vehicle bus1 = Generator.generatePubTran(159, 1);
		Vehicle bus2 = Generator.generatePubTran(159, 2);
		Vehicle bus3 = Generator.generatePubTran(173, 1);
		Vehicle bus4 = Generator.generatePubTran(173, 2);
		Vehicle bus5 = Generator.generatePubTran(179, 1);
		Vehicle bus6 = Generator.generatePubTran(179, 2);
		
		vehicleList.add(tram1);
		vehicleList.add(tram2);
		vehicleList.add(tram3);
		vehicleList.add(tram4);
		vehicleList.add(tram5);
		vehicleList.add(tram6);
		vehicleList.add(bus1);
		vehicleList.add(bus2);
		vehicleList.add(bus3);
		vehicleList.add(bus4);
		vehicleList.add(bus5);
		vehicleList.add(bus6);
	}
	
	protected Vehicle dupa()
	{
		Person person5 = new Person(50, "", "", "", "", "", "", "", true);
		person5.setAllVertices(myGraph.vertices.get(20), myGraph.vertices.get(76), myGraph.vertices.get(98));
		Vehicle car = Generator.generateCar(person5);
		car.setLaneNr(1);
		return car;
	}
	
	
	protected void addCars()
	{
		
		Person person1 = new Person(50, "", "", "", "", "", "", "", true);
		person1.setAllVertices(myGraph.vertices.get(81), myGraph.vertices.get(80), myGraph.vertices.get(101));
		vehicleList.add(Generator.generateCar(person1));
		
		Person person2 = new Person(50, "", "", "", "", "", "", "", true);
		person2.setAllVertices(myGraph.vertices.get(78), myGraph.vertices.get(79), myGraph.vertices.get(87));
		vehicleList.add(Generator.generateCar(person2));
		
		Person person3 = new Person(50, "", "", "", "", "", "", "", true);
		person3.setAllVertices(myGraph.vertices.get(81), myGraph.vertices.get(80), myGraph.vertices.get(90));
		vehicleList.add(Generator.generateCar(person3));
		
		Person person4 = new Person(50, "", "", "", "", "", "", "", true);
		person4.setAllVertices(myGraph.vertices.get(24), myGraph.vertices.get(25), myGraph.vertices.get(84));
		vehicleList.add(Generator.generateCar(person4));
		
		Person person5 = new Person(50, "", "", "", "", "", "", "", true);
		person5.setAllVertices(myGraph.vertices.get(4), myGraph.vertices.get(14), myGraph.vertices.get(98));
		vehicleList.add(Generator.generateCar(person5));
		
		Person person6 = new Person(50, "", "", "", "", "", "", "", true);
		person6.setAllVertices(myGraph.vertices.get(23), myGraph.vertices.get(22), myGraph.vertices.get(91));
		vehicleList.add(Generator.generateCar(person6));
		
		Person person7 = new Person(50, "", "", "", "", "", "", "", true);
		person7.setAllVertices(myGraph.vertices.get(52), myGraph.vertices.get(51), myGraph.vertices.get(96));
		vehicleList.add(Generator.generateCar(person7));
		
		Person person8 = new Person(50, "", "", "", "", "", "", "", true);
		person8.setAllVertices(myGraph.vertices.get(67), myGraph.vertices.get(66), myGraph.vertices.get(94));
		vehicleList.add(Generator.generateCar(person8));
		
		Person person9 = new Person(50, "", "", "", "", "", "", "", true);
		person9.setAllVertices(myGraph.vertices.get(6), myGraph.vertices.get(8), myGraph.vertices.get(90));
		vehicleList.add(Generator.generateCar(person9));
		
		Person person10 = new Person(50, "", "", "", "", "", "", "", true);
		person10.setAllVertices(myGraph.vertices.get(81), myGraph.vertices.get(80), myGraph.vertices.get(87));
		Vehicle car = Generator.generateCar(person10);
		car.setLaneNr(1);
		vehicleList.add(car);
		
		Person person11 = new Person(50, "", "", "", "", "", "", "", true);
		person11.setAllVertices(myGraph.vertices.get(46), myGraph.vertices.get(45), myGraph.vertices.get(85));
		vehicleList.add(Generator.generateCar(person11));
		
		Person person12 = new Person(50, "", "", "", "", "", "", "", true);
		person12.setAllVertices(myGraph.vertices.get(46), myGraph.vertices.get(45), myGraph.vertices.get(101));
		vehicleList.add(Generator.generateCar(person12));
	}

	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
	
	    g.drawImage(bgImage, 0, 0, 1000, 1000, null);
	    
	    myGraph.paintEdges(g);
	    myGraph.paintVertices(g);
	    pGraph.paintEdges(g);
	    pGraph.paintVertices(g);
	    
	    for(Vehicle v : vehicleList) {
	    	v.paint(g, v.getColor());
	    }		
	    for(Pedestrian p : pList) {
	    	p.paint(g, p.getColor());
	    }	
	}
	
	public void actionPerformed(ActionEvent e) {		
		//Test.displayInfoAboutCar(vehicleList.get(0));
		Movement.move(vehicleList);	
		PedestriansMovement.move(pList);
		counter++;
		repaint();
	}
	
	public static void drawCircle(int x, int y, int r, Graphics g) {
		//g.setColor(Color.RED);
		g.fillOval(x-r/2, y-r/2, r, r);
	}
}