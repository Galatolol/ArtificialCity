
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

public class PedestriansGraph {
	public static SparseMultigraph<V, E> graph;
	public static LinkedList<V> vertices = new LinkedList<V>();
		
	public PedestriansGraph() {
		graph = new SparseMultigraph<V, E>();
		
		try {
			this.getVertices("res/pedestriansVertices.txt");
			this.getEdges("res/pedestriansEdges.txt");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	public void init() {
		graph = new SparseMultigraph<V, E>();
		
		try {
			this.getVertices("res/pedestriansVertices.txt");
			this.getEdges("res/pedestriansEdges.txt");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}	
	}
	
	public static String getDirection(Lane lane, V vertex) {
		if (lane.forward != null && lane.forward[0].getEnd() == vertex) {
			return "forward";
		}
		else if (lane.right != null && lane.right[0].getEnd() == vertex) {
			return "right";
		}
		else if (lane.left != null && lane.left[0].getEnd() == vertex) {
			return "left";
		}
		else if (lane.getBegin() == vertex)
		{
			return "backward";
		}
		else {
			//System.out.println("Problem z polaczeniem ulic. Początek krawędzi: " + lane.getBegin() + " Koniec: " + lane.getEnd() + " Następny wierzchołek: " + vertex);
			return "";
		}
	}
	
	public static void calcWeightedShortestPath(Pedestrian ped) {
        Transformer<E, Integer> wtTransformer = new Transformer<E,Integer>() {
            public Integer transform(E edge) {
                return edge.getLength() + edge.getWeight();
            }
        };
        

        
        
        V prev = ped.controller.getPrevVertex();
        V current = ped.controller.getCurrentVertex();
        V end = ped.controller.getDestinationVertex();
        
        DijkstraShortestPath<V,E> alg = new DijkstraShortestPath<V, E>(graph, wtTransformer);
        List<E> path = alg.getPath(current, end);
        V nextVertex;
        
        if(current == path.get(0).getBegin())
        	nextVertex = path.get(0).getEnd();
        else 
        	nextVertex = path.get(0).getBegin();
        
        /*
        System.out.println();
        System.out.println("Con.prev: " + ped.controller.getPrevVertex());
        System.out.println("Con.curr: " + ped.controller.getCurrentVertex());
        System.out.println("Con.dest: " + ped.controller.getDestinationVertex());
        System.out.println("Street beg: " + ped.getStreet()[0].getBegin());
        System.out.println("Street end: " + ped.getStreet()[0].getEnd());
        System.out.println("Next: " + nextVertex);
        System.out.println(); */

        String direction = getDirection(ped.getStreet()[0], nextVertex);
        
        switch (direction) {
	        case "forward": ped.moveForward();
	        break;
	        
	        case "left": ped.curveLeft();
	    	break;
	        
	        case "right": ped.curveRight();
	    	break;
	    	
	        case "backward": ped.setStreet(Util.getPedStreet(current.id, prev.id));
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
			ped.moveForward();
			ped.controller.setCurrentVertex(prev);
			ped.controller.setPrevVertex(current);
			break;
        }
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

	    return this.graph.findEdge(PedestriansGraph.vertices.get(v1nr), 
		           (PedestriansGraph.vertices.get(v2nr)));
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
	        					Boolean.parseBoolean(v[5]),
	        					1),
	        					vertices.get(Integer.parseInt(v[0])), 
	        					vertices.get(Integer.parseInt(v[1])), 
	        					EdgeType.UNDIRECTED);
		}
	    in.close();
	}

	public void paintVertices(Graphics g) {
		for(V v : vertices) {
	    	g.setColor(Color.BLUE);
	    	ContentPanel.drawCircle(v.getX(), v.getY(), 20, g);
			
	    	g.setColor(Color.WHITE);
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
	    	g.setColor(Color.blue);
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
