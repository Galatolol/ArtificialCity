
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

import cern.colt.Arrays;

public class ContentPanel extends JPanel implements ActionListener {
	
	private Image bgImage = null;
	public Timer tm = new Timer(500, this);
	static int counter = 0;
	private Graph myGraph;
	private PedestriansGraph pGraph; 
	private List<Vehicle> vehicleList = new ArrayList<Vehicle>();
	private List<Pedestrian> pList = new ArrayList<Pedestrian>();
	public static List<Vehicle> vehicleList1 = new ArrayList<Vehicle>();
	public static double timerValue;
	public static String[] stopArray;

	ContentPanel() {
		stopArray = new String[62];
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
		Generator.generate(100, personList);
		Util.createPedestriansStreets(pGraph);
		
		for (Person person : personList)
		{
			if (person.getIsDriving())
			{
				vehicleList.add(Generator.generateCar(person));
			}
			else
			{
				pList.add(Generator.generatePed(person));
			}
		}
		
		Person person11 = new Person(50, "", "", "", "", "", "", "", true);
		person11.setAllVertices(myGraph.vertices.get(56), myGraph.vertices.get(55), myGraph.vertices.get(23));
		Pedestrian ped = Generator.generatePed(person11);
		pList.add(ped);
		
		addPublicTransport();

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
		//timerValue = Double.valueOf(Frame.timerValue.getText());
		timerValue = timerValue + ((1000-tm.getDelay())/100);
		Frame.timerValue.setText(Util.convertTimerValueToTime(timerValue));
		
		Movement.move(vehicleList);	
		PedestriansMovement.move(pList);
		for (int i = 0; i < stopArray.length; i++)
		{
			stopArray[i] = "";
		}
		if (vehicleList.size() == 0)
		{
			Person person10 = new Person(50, "", "", "", "", "", "", "", true);
			person10.setAllVertices(myGraph.vertices.get(81), myGraph.vertices.get(80), myGraph.vertices.get(87));
			Vehicle car = Generator.generateCar(person10);
			car.setLaneNr(1);
			vehicleList.add(car);
		}
		if (pList.size() == 0)
		{
			Person person11 = new Person(50, "", "", "", "", "", "", "", true);
			person11.setAllVertices(myGraph.vertices.get(56), myGraph.vertices.get(55), myGraph.vertices.get(23));
			Pedestrian ped = Generator.generatePed(person11);
			pList.add(ped);
		}
		//System.out.println(Util.convertTimerValueToTime(timerValue));
		counter++;
		repaint();
	}
	
	public static void drawCircle(int x, int y, int r, Graphics g) {
		//g.setColor(Color.RED);
		g.fillOval(x-r/2, y-r/2, r, r);
	}
}