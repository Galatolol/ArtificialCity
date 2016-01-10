
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
	public static double timerValue;
	public static String[] stopArray;
	private String timerValueStr;
	private ArrayList<Person> personList;
	private ArrayList<Person> personList3 = new ArrayList<Person>();
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
		Generator.generate(20000, personList2, false);
		Generator.generate(30000, personList1, true);
		for (Person p : personList1)
		{
			p.driving = true;
		}
		personList = new ArrayList<Person>();
		//personList.addAll(personList1);
		//personList.addAll(personList2);

			Person person10 = new Person(50, "", "", "", "", "", "", "", true);
			person10.setAllVertices(myGraph.vertices.get(32), myGraph.vertices.get(40), myGraph.vertices.get(47));
			Vehicle car = Generator.generateCar(person10);
			car.setLaneNr(0);
			vehicleList.add(car);
		
		tm.start();
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
	
	public void add()
	{
		if (personList.size() < 15000)
		{
			ArrayList<Person> personList2 = new ArrayList<Person>();
			Generator.generate(1000, personList2, false);
			personList.addAll(personList2);
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
			min1 = Integer.parseInt(timerValueStr.split(":")[1]);
			hrs1 = Integer.parseInt(timerValueStr.split(":")[0]);
			
			PublicTransport pubTran = Util.spawnPubTran(timerValueStr.substring(0, 5));
			if (pubTran != null)
			{
				vehicleList.add(pubTran);
			}
			
			ListIterator<Person> iter = personList.listIterator();
			while (iter.hasNext())
			{ 
				Person person = iter.next();
				try
				{
					if (person.isGoingBack)
					{
						min2 = Integer.parseInt(person.getGoingBack().split(":")[1]);
					}
					else
					{
						min2 = Integer.parseInt(person.getGoingOut().split(":")[1]);
					}
				} catch (Exception exc) { min2 = 0; }
				try
				{
					if (person.isGoingBack)
					{
						hrs2 = Integer.parseInt(person.getGoingBack().split(":")[0]);
					}
					else
					{
						hrs2 = Integer.parseInt(person.getGoingOut().split(":")[0]);
					}
				} catch (Exception exc) { iter.remove(); continue; }
				
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
						person.isGoingBack = !person.isGoingBack;
					}
					catch (Exception exc) 
					{ 
						iter.remove();
						continue;
					}
				}
			}
		}
		

		Person person10 = new Person(50, "", "", "", "", "", "", "", true);
		person10.setAllVertices(myGraph.vertices.get(32), myGraph.vertices.get(40), myGraph.vertices.get(47));
		Vehicle car = Generator.generateCar(person10);
		car.setLaneNr(0);
		vehicleList.add(car);
		
		Movement.move(vehicleList);	
		PedestriansMovement.move(pList);
		for (int i = 0; i < stopArray.length; i++)
		{
			stopArray[i] = "";
		}
		if (hrs1 == 12 && min1 == 10)
		{
			myGraph.graph.removeEdge(myGraph.getEdge(38, 39));
		}
		
		if (timerValueStr.substring(0,  2).equals("11") ||Integer.parseInt(timerValueStr.substring(0,  2)) % 2 == 0)
		{
			//add();
		}
		counter++;
		Signaling.signaling();
		repaint();
	}
	
	public static void drawCircle(int x, int y, int r, Graphics g) {
		g.fillOval(x-r/2, y-r/2, r, r);
	}
}