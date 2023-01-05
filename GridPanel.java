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

public class GridPanel extends JPanel{

	private ArrayList<Node> nodes = new ArrayList<Node>();
	private ArrayList<Node[]> nodeConnections = new ArrayList<Node[]>();
	
	private Graphics2D g2D;
	
	public GridPanel(int width, int height) {
		this.setPreferredSize(new Dimension(width, height));
	}
	
	public void paint(Graphics g) {
		super.paint(g);
		g2D = (Graphics2D) g;
		
		g2D.setStroke(new BasicStroke(2));
		g2D.setColor(Color.gray);
		for(int i = 0; i < nodeConnections.size(); i++) {			
			g2D.drawLine(nodeConnections.get(i)[0].posX + 5, nodeConnections.get(i)[0].posY + 5, nodeConnections.get(i)[1].posX + 5, nodeConnections.get(i)[1].posY + 5);
		}

		g2D.setFont(new Font("TimesRoman", Font.BOLD, 15));
		
		for(int i = 0; i < nodes.size(); i++) {
			
			String displayName = nodes.get(i).nodeName + ": (" + nodes.get(i).posX + ", "+ nodes.get(i).posY + ")";
			
			g2D.setColor(Color.red);
			g2D.fillOval(nodes.get(i).posX, nodes.get(i).posY, 10, 10);			
			g2D.setColor(Color.blue);
			g2D.drawString(displayName , nodes.get(i).posX, nodes.get(i).posY + 25);
		}
		
	}
	
	public void plotGraph(Graph graph) {
		for (Node node : graph.nodes) {
			plotPoint(node);
		}
	}
	
	public void plotPoint(Node node) {
		nodes.add(node);
		repaint();
	}
	
	public void drawLine(Node node1, Node node2) {
		Node[] connection = {node1, node2};
		nodeConnections.add(connection);
	}
	
	public void clearGrid() {
		nodes.clear();
		nodeConnections.clear();
		repaint();
	}
	
}