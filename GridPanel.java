import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.io.Console;
import java.sql.Connection;
import java.util.ArrayList;

import javax.swing.JPanel;

// This class is responsible for displaying the the graph part (where the nodes and connections are rendered)
public class GridPanel extends JPanel{

	private ArrayList<Node> nodes = new ArrayList<Node>();	// Store all the nodes that needs to be rendered
	public ArrayList<Node[]> allPossibleNodeConnections = new ArrayList<Node[]>();	// Store all the links between the nodes that needs to be rendered
	private ArrayList<Node[]> nodeConnections = new ArrayList<Node[]>();	// Store the connections of the shortest path that needs to be rendered
	
	// Variables for the checkboxes
	private boolean showShortestPath, showAllPath, showCoordinates;
	
	// Constructor of the class to set limits of the graph
	public GridPanel(int width, int height) {
		this.setPreferredSize(new Dimension(width, height));
	}

	// Function responsible to actually display the paths and nodes onto the graph
	public void paint(Graphics g) {
		
		super.paint(g);
		Graphics2D g2D = (Graphics2D) g;
		
		// Will display all the possible connections between all the nodes
		if(showAllPath) {			
			g2D.setStroke(new BasicStroke(1));
			g2D.setColor(Color.gray);
			for(int i = 0; i < allPossibleNodeConnections.size(); i++) {			
				g2D.drawLine(allPossibleNodeConnections.get(i)[0].posX + 5, allPossibleNodeConnections.get(i)[0].posY + 5, allPossibleNodeConnections.get(i)[1].posX + 5, allPossibleNodeConnections.get(i)[1].posY + 5);
			}
		}
		
		// Will display the shortest path
		if(showShortestPath) {		
			g2D.setStroke(new BasicStroke(2));
			g2D.setColor(Color.red);
			for(int i = 0; i < nodeConnections.size(); i++) {			
				g2D.drawLine(nodeConnections.get(i)[0].posX + 5, nodeConnections.get(i)[0].posY + 5, nodeConnections.get(i)[1].posX + 5, nodeConnections.get(i)[1].posY + 5);
			}
		}

		g2D.setFont(new Font("TimesRoman", Font.BOLD, 15));	// Set the font of the node names
		
		for(int i = 0; i < nodes.size(); i++) {
			if(showCoordinates) {	// Will display the names and coordinates if checkbox is checked
				String displayName = nodes.get(i).nodeName + " (" + nodes.get(i).posX + ", "+ nodes.get(i).posY + ")";
				g2D.setColor(Color.blue);
				g2D.drawString(displayName , nodes.get(i).posX, nodes.get(i).posY + 25);	
			}
			
			// Plot the nodes in red at the correct position on the graph
			g2D.setColor(Color.red);
			g2D.fillOval(nodes.get(i).posX, nodes.get(i).posY, 10, 10);			
		}
	}
	
	// Function to plot all the nodes on the graph
	public void plotGraph(Graph graph) {
		for (Node node : graph.nodes) {
			plotPoint(node);
		}
	}
	
	// Function to plot one node
	public void plotPoint(Node node) {
		nodes.add(node);
		repaint();
	}
	
	// Function to draw the lines between the nodes
	public void drawLine(Graph graph) {
		allPossibleNodeConnections = graph.allPossibleNodeConnections;
		nodeConnections = graph.shortestNodeConnection;
		repaint();
	}
	
	// Clear all nodes and lines from the grid
	public void clearGrid() {
		nodes.clear();
		repaint();
	}
	
	// Show shortest path checkbox
	public void setShowShortestPath(boolean status) {
		showShortestPath = status;
		repaint();
	}
	
	// Show all paths checkbox
	public void setShowAllPath(boolean status) {
		showAllPath = status;
		repaint();
	}
	
	// Show coordinates checkbox
	public void setShowCoordinates(boolean status) {
		showCoordinates = status;
		repaint();
	}
}