
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.Timer;

public class ContentPanel extends JPanel implements ActionListener {
	
	private Image bgImage = null;
	private Timer tm = new Timer(1000, this);
	static int counter = 0;
	private Graph myGraph;
	private List<Vehicle> vehicleList = new ArrayList<Vehicle>();

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
		
	    Util.createStreets(myGraph);
	    
	    Person driver1 = new Person(50, null, null, myGraph.vertices.get(81), myGraph.vertices.get(80), myGraph.vertices.get(90), null, null, null, null, null, true);
	    	    
		Vehicle auto2 = new Car(driver1);
		myGraph.calcWeightedShortestPath((Car)auto2);
		auto2.setStreet(Util.getOuterStreet(97, 81)[0].forward);
		auto2.setLaneNr(0);
		auto2.setCurrentCell(auto2.getStreet()[0].cellList[0]);
		auto2.setSpeed(1);
		//vehicleList.add(auto2);
		
		Vehicle tram1 = Generator.generateTram(8, 1);
		Vehicle tram2 = Generator.generateTram(8, 2);
		
		vehicleList.add(tram1);
		vehicleList.add(tram2);
	
		tm.start();
	}

	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
	
	    g.drawImage(bgImage, 0, 0, 1000, 1000, null);
	    
	    myGraph.paintEdges(g);
	    myGraph.paintVertices(g);
	    
	    for(Vehicle v : vehicleList) {
	    	v.paintVehicle(g, v.getColor());
	    }			
	}
	
	public void actionPerformed(ActionEvent e) {		
		Test.displayInfoAboutCar(vehicleList.get(0));
		//Test.displayInfoAboutCar(vehicleList.get(1));
		Movement.move(vehicleList);	
		counter++;
		repaint();
	}
	
	public static void drawCircle(int x, int y, int r, Graphics g) {
		//g.setColor(Color.RED);
		g.fillOval(x-r/2, y-r/2, r, r);
	}
}