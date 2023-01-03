import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.io.Console;

import javax.swing.JPanel;

public class Grid extends JPanel{

	private Graphics2D g2D;
	
	public Grid() {
		this.setPreferredSize(new Dimension(500, 500));
		System.out.println("1");
	}
	
	public void paint(Graphics g) {
		g2D = (Graphics2D) g;
		System.out.println("2");
	}
	
	public void plotPoint(int x, int y) {
		while (g2D == null) {
			System.out.println("xxx");
		}
		g2D.drawRect(x, y, 5, 5);
	}
	
}