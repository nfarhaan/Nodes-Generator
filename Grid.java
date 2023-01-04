import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.io.Console;

import javax.swing.JPanel;

public class Grid extends JPanel{

	private int x, y;
	private Graphics2D g2D;
	
	public Grid() {
		this.setPreferredSize(new Dimension(500, 500));
	}
	
	public void paint(Graphics g) {
		g2D = (Graphics2D) g;
		
		g2D.drawRect(x, y, 5, 5);
	}
	
	public void plotPoint(int _x, int _y) {
		x = _x;
		y = _y;
		
		repaint();
	}
	
}