
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Paint;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import javax.swing.JFrame;

import org.apache.commons.collections15.Transformer;

import edu.uci.ics.jung.algorithms.layout.ISOMLayout;
import edu.uci.ics.jung.algorithms.layout.Layout;
import edu.uci.ics.jung.algorithms.shortestpath.DijkstraShortestPath;
import edu.uci.ics.jung.graph.SparseMultigraph;
import edu.uci.ics.jung.graph.util.EdgeType;
import edu.uci.ics.jung.visualization.BasicVisualizationServer;
import edu.uci.ics.jung.visualization.decorators.ToStringLabeller;
import edu.uci.ics.jung.visualization.renderers.Renderer.VertexLabel.Position;

public class Graph {
	public static SparseMultigraph<V, E> graph;
	public static LinkedList<V> vertices = new LinkedList<V>();
	
	
	public Graph() {
		graph = new SparseMultigraph<V, E>();
		
		try {
			this.getVertices("res/vertices.txt");
			this.getEdges("res/edges.txt");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	public void init() {
		graph = new SparseMultigraph<V, E>();
		
		try {
			this.getVertices("res/vertices.txt");
			this.getEdges("res/edges.txt");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		//this.createGraph();	
	}
	
	private void createVertices() {
		vertices.add(new V(0, 0, 0));
		
		
		vertices.add(new V(1, 55, 965));
		vertices.add(new V(2, 90, 965));
		vertices.add(new V(3, 280, 965));
		vertices.add(new V(4, 450, 1000));
		vertices.add(new V(5, 650, 1000));
		vertices.add(new V(6, 750, 1000));
		vertices.add(new V(7, 1000, 1000));
		vertices.add(new V(8, 1000, 900));
		vertices.add(new V(9, 800, 900));
		vertices.add(new V(10, 650, 900));
		vertices.add(new V(11, 550, 900));
		vertices.add(new V(12, 425, 880));
		vertices.add(new V(13, 390, 905));
		vertices.add(new V(14, 325, 935));
		vertices.add(new V(15, 0, 800));
		vertices.add(new V(16, 80, 825));
		vertices.add(new V(17, 110, 830));
		vertices.add(new V(18, 140, 830));
		vertices.add(new V(19, 315, 820));
		vertices.add(new V(20, 405, 820));
		vertices.add(new V(21, 395, 735));
		vertices.add(new V(22, 530, 730));
		vertices.add(new V(23, 700, 650));
		vertices.add(new V(24, 730, 760));
		vertices.add(new V(25, 900, 800));
		vertices.add(new V(26, 950, 700));
		vertices.add(new V(27, 1000, 730));
		
		vertices.add(new V(42, 360, 625)); //28
		vertices.add(new V(43, 165, 555)); //29
		vertices.add(new V(44, 130, 540)); // 30
		vertices.add(new V(45, 0, 470));   //31
		
	}
	
	public static String getDirection(Lane lane, V vertex) {
		if (lane.forward != null && lane.forward[0].end == vertex) {
			return "forward";
		}
		else if (lane.right != null && lane.right[0].end == vertex) {
			return "right";
		}
		else if (lane.left != null && lane.left[0].end == vertex) {
			return "left";
		}
		else {
			System.out.println("Problem z polaczeniem ulic. Początek krawędzi: " + lane.begin.toString() + 
					" Koniec: " + lane.end + " Następny wierzchołek: " + vertex);
			return "";
		}
	}
	
	public static void calcWeightedShortestPath(Car car) {
        Transformer<E, Integer> wtTransformer = new Transformer<E,Integer>() {
            public Integer transform(E edge) {
                return edge.getLength() + edge.getWeight();
            }
        };
        
        V prev = car.driver.getPrevVertex();
        V current = car.driver.getCurrentVertex();
        V end = car.driver.getDestinationVertex();
        
        DijkstraShortestPath<V,E> alg = new DijkstraShortestPath<V, E>(graph, wtTransformer);
        List<E> path = alg.getPath(current, end);
        V nextVertex = path.get(0).getEnd();
        Lane lane = getEdge(prev, current).street[car.getLaneNr()];

        String direction = getDirection(car.getStreet()[0], nextVertex);
        System.out.println("Dijikstra – kierunek: " + direction + " " + nextVertex);
        
        switch (direction) {
	        case "forward": car.moveForward();
	        break;
	        
	        case "left": car.curveLeft();
	    	break;
	        
	        case "right": car.curveRight();
	    	break;
        }
        
        //if
        
       // c.driver.setPrevVertex(current);
       // c.driver.setCurrentVertex(nextVertex);
    }
	
	private void getVertices(String path) throws FileNotFoundException {
	    File file = new File(path);
	    Scanner in = new Scanner(file);
	            
	    String scanLine;
	    vertices.add(new V(0,0,0));
	    while(in.hasNextLine()) {
		    scanLine = in.nextLine();
	        String[] v = scanLine.split(",");
	        vertices.add(new V(Integer.parseInt(v[0]), 
	        				   Integer.parseInt(v[1]), 
	        				   Integer.parseInt(v[2])));      	    	  
		}
	    in.close();
	}
	
	public E getEdge(int v1nr, int v2nr)
	{
		Collection<E> edges = this.graph.getOutEdges(Graph.vertices.get(v1nr));
	    LinkedList<E> le = new LinkedList<E>(edges);
	    for(int i = 0; i < le.size(); i++) {
	    	if(this.graph.isDest(Graph.vertices.get(v2nr), le.get(i))) {
	    		return le.get(i);
	    	}
	    }
	    return null;
	}
	
	public static E getEdge(V v1, V v2)
	{
		Collection<E> edges = graph.getOutEdges(v1);
	    LinkedList<E> le = new LinkedList<E>(edges);
	    for(int i = 0; i < le.size(); i++) {
	    	if(graph.isDest(v2, le.get(i))) {
	    		return le.get(i);
	    	}
	    }
	    return null;
	}
	
	private void getEdges(String path) throws FileNotFoundException {
	    File file = new File(path);
	    Scanner in = new Scanner(file);
	            
	    String scanLine;
	    while(in.hasNextLine()) {
		    scanLine = in.nextLine();
	        String[] v = scanLine.split(",");
	        
	        graph.addEdge(new E(vertices.get(Integer.parseInt(v[0])),
	        					vertices.get(Integer.parseInt(v[1])), 
	        					Integer.parseInt(v[2]), 
	        					Integer.parseInt(v[3]),
	        					Integer.parseInt(v[4]),
	        					Boolean.parseBoolean(v[5])),
	        					vertices.get(Integer.parseInt(v[0])), 
	        					vertices.get(Integer.parseInt(v[1])), 
	        					EdgeType.DIRECTED);
		}
	    in.close();
	}

	public void paintVertices(Graphics g) {
		for(V v : vertices) {
	    	g.setColor(Color.RED);
	    	ContentPanel.drawCircle(v.getX(), v.getY(), 20, g);
			
	    	g.setColor(Color.BLACK);
			g.drawChars(v.toString().toCharArray(), 0,
						v.toString().length(), 
						v.getX()-6, 
						v.getY()+5);
		}
	}
	
	public V getVertexById(int id){
		Collection<V> edges = graph.getVertices();
	    LinkedList<V> le = new LinkedList<V>(edges);

	    for(int i = 0; i < le.size(); i++) {
	    	if(le.get(i).id == id) {
	    		return le.get(i);
	    	}
	    }
	    return null;
	}
	
	public void paintEdges(Graphics g) {
		for(E e : graph.getEdges()) {
	    	g.setColor(Color.green);
	    	//g.drawLine(e.getBeginX(), e.getBeginY(), e.getEndX(), e.getEndY());
	    	
	    	for(int i = 0; i < e.street.length; i++) {
	    		for(int j = 0; j < e.street[i].length; j++) {
	    			g.fillRect(e.street[i].cellList[j].getX(),
	    					   e.street[i].cellList[j].getY(), 
	    					  4, 4);
	    		}
	    	}
	    	
		}
	}
}
