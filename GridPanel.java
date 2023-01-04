import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.io.Console;

import javax.swing.JPanel;

public class GridPanel extends JPanel{

	private int x, y;
	private String name = "test";
	private Graphics2D g2D;
	
	public GridPanel() {
		this.setPreferredSize(new Dimension(800, 720));
	}
	
	public void paint(Graphics g) {
		super.paint(g);
		g2D = (Graphics2D) g;
		//g2D.drawRect(x, y, 5, 5);
		//g2D.drawOval(x, y, 5, 5);
		
		g2D.drawString(name, x, y);
		
		g2D.setColor(Color.red);
		g2D.fillOval(x, y, 10, 10);
	}
	
	public void plotPoint(int _x, int _y) {
		x = _x;
		y = _y;
		
		repaint();
	}
	
}