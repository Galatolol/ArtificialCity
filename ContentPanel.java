
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
		
	    //myGraph.graph.get
	    Collection<E> edges = myGraph.graph.getOutEdges(Graph.vertices.get(16));
	    LinkedList<E> le = new LinkedList(edges);
	    
	    Collection<E> edges13 = myGraph.graph.getOutEdges(Graph.vertices.get(13));
	    LinkedList<E> le13 = new LinkedList(edges13);
	    
	    for(E e : le13) {
	    	System.out.println(e);
	    }
	    
	    for(int i = 0; i < le13.size(); i++) {
	    	Lane[] road = new Lane[3];
	    	if(myGraph.graph.isDest(Graph.vertices.get(5), le13.get(i))) {
	    		for(int j = 0; j < le13.get(i).street.length; j++) {
	    			road[j] = le13.get(i).street[0];
	    			road[j].speedLimit = 4; System.out.println(road[0].cellList.length);
	    		}
		    	for(int j = 0; j < road.length; j++) {
		    		le.get(0).street[j].forward = road;
	    		}
    			for(int k = 0; k < road[2].cellList.length - 1; k++)
    			{
    				road[2].cellList[k].setForbidden(true); //buspas
    			}
	    	}
	    	else if(myGraph.graph.isDest(Graph.vertices.get(12), le13.get(i))) {
	    		road[0] = le13.get(i).street[0];
	    		road[0].speedLimit = 2;
		    	for (int j = 0; j < road.length; j++) {
		    		le.get(0).street[j].right = road;
	    		}
	    	}
	    }
	    
	    for(int i = 0; i < 3; i++)
	    {
	    		le.get(0).street[i].speedLimit = 3;
	    }
		for(int k = 0; k < le.get(0).street[2].cellList.length - 1; k++)
		{
			le.get(0).street[2].cellList[k].setForbidden(true); //buspas
		}
	
	    
		Vehicle auto1 = new Car(null);
		auto1.setRoad(le.get(0).street);
		auto1.setLaneNr(1);
		auto1.setCurrentCell(le.get(0).street[1].cellList[0]);
		auto1.setSpeed(1);
		auto1.curveRight();
		vehicleList.add(auto1);
		
		Vehicle auto2 = new Car(null);
		auto2.setRoad(le.get(0).street);
		auto2.setLaneNr(0);
		auto2.setCurrentCell(le.get(0).street[0].cellList[0]);
		auto2.setSpeed(1);
		auto2.moveForward();
		vehicleList.add(auto2);
		
		Vehicle auto3 = new Car(null); 
		auto3.setRoad(le.get(0).street);
		auto3.setLaneNr(0);
		auto3.setCurrentCell(le.get(0).street[0].cellList[0]);
		auto3.setSpeed(1);
		auto3.curveLeft();
		//vehicleList.add(auto3);
		
		Vehicle auto4 = new Car(null);
		auto4.setRoad(le.get(0).street);
		auto4.setLaneNr(1);
		auto4.setCurrentCell(le.get(0).street[1].cellList[0]);
		auto4.setSpeed(1);
		auto4.moveForward();
		//vehicleList.add(auto4);
		
		tm.start();
	}

	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
	
	    g.drawImage(bgImage, 0, 0, 1000, 1000, null);
	    
	    myGraph.paintEdges(g);
	    //myGraph.paintVertices(g);
	    
	    
	    for(Vehicle v : vehicleList) {
	    	v.paintVehicle(g);
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