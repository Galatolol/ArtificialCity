
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
import java.util.ListIterator;

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
	private String timerValueStr;
	private ArrayList<Person> personList;
	private int min1, min2, hrs1, hrs2;
	

	ContentPanel() {
		stopArray = new String[64];
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
		Util.createPedestriansStreets(pGraph);
		
		ArrayList<Person> personList2 = new ArrayList<Person>();
		ArrayList<Person> personList1 = new ArrayList<Person>();
		Generator.generate(10000, personList2, false);
		Generator.generate(25000, personList1, true);
		for (Person p : personList1)
		{
			p.driving = true;
		}
		personList = new ArrayList<Person>();
		personList.addAll(personList1);
		personList.addAll(personList2);
		System.out.println(personList.size());
		
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
		timerValue = timerValue + ((1000-tm.getDelay())/100);
		timerValueStr = Util.convertTimerValueToTime(timerValue);
		Frame.timerValue.setText(timerValueStr);
		
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
		
		if (timerValueStr.substring(6,8).equals("00"))
		{
			ListIterator<Person> iter = personList.listIterator();
			while (iter.hasNext())
			{
				Person person = iter.next();
				try
				{
					min2 = Integer.parseInt(person.getGoingOut().split(":")[1]);
				} catch (Exception exc) { min2 = 0; }
				try
				{
					hrs2 = Integer.parseInt(person.getGoingOut().split(":")[0]);
				} catch (Exception exc) { hrs2 = 0; }
				
				min1 = Integer.parseInt(timerValueStr.split(":")[1]);
				hrs1 = Integer.parseInt(timerValueStr.split(":")[0]);
				if (hrs1 == hrs2 && min1 == min2)
				{
					try
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
					catch (Exception exc) 
					{
						iter.remove();
						continue;
					}
				}
			}
		}
		
		Movement.move(vehicleList);	
		PedestriansMovement.move(pList);
		for (int i = 0; i < stopArray.length; i++)
		{
			stopArray[i] = "";
		}
		counter++;
		repaint();
	}
	
	public static void drawCircle(int x, int y, int r, Graphics g) {
		g.fillOval(x-r/2, y-r/2, r, r);
	}
}