
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Paint;
import java.io.File;
import java.io.FileNotFoundException;
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
	public SparseMultigraph<V, E> graph;
	public static LinkedList<V> vertices = new LinkedList<V>();
	
	public void init() {
		graph = new SparseMultigraph<V, E>();
		
		try {
			this.getVertices("res/vertices.txt");
			this.getEdges("res/edges.txt");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
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
	
	public void calcWeightedShortestPath(V start, V end) {
        Transformer<E, Integer> wtTransformer = new Transformer<E,Integer>() {
            public Integer transform(E edge) {
                return edge.getLength();
            }
        };
        
        DijkstraShortestPath<V,E> alg = new DijkstraShortestPath<V, E>(graph, wtTransformer);
        List<E> path = alg.getPath(start, end);
        
        double distance = (double)alg.getDistance(start, end);
        System.out.println("The shortest weighted path from " + start.toString()  + " to " + end.toString() + " is:");
        //System.out.println(path.toString());
        
        for(E e : path) {
        	System.out.println(e.toString());
        	System.out.println(e.cellsToString());
        }
        
        
        System.out.println("and the length of the path is: " + distance);
    }
	
	public static void main(String[] args) {
        Graph myApp = new Graph();
        myApp.init();
        
        //System.out.println(myApp.graph.toString());
        myApp.calcWeightedShortestPath(vertices.get(19), vertices.get(5));
        
        Transformer<V, Paint> edgeStroke = new Transformer<V, Paint>() {
		    public Paint transform(V s) {
		    	
		    	new BasicStroke();
		        return Color.GREEN;
		    }
		};
	
       Transformer<V,Paint> vertexColor = new Transformer<V,Paint>() {
            public Paint transform(V i) {

	    			return Color.ORANGE;
	    		} 
            
        };
	
        Layout<V, E> layout;

        	layout = new ISOMLayout<V, E>(myApp.graph); 
 
        layout.setSize(new Dimension(500, 500)); 
        BasicVisualizationServer<V,E> vv = new BasicVisualizationServer<V,E>(layout);
        vv.setPreferredSize(new Dimension(500, 500)); 
        vv.getRenderContext().setVertexLabelTransformer(new ToStringLabeller<V>());
        vv.getRenderContext().setVertexFillPaintTransformer(vertexColor);
        //vv.getRenderContext().setEdgeDrawPaintTransformer(edgeStroke);
        vv.getRenderer().getVertexLabelRenderer().setPosition(Position.CNTR);
        
        JFrame frame = new JFrame("Graf");
    	frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
    	frame.getContentPane().add(vv); 
    	frame.pack();
    	frame.setVisible(true);  
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
