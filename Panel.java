import javax.swing.JFrame;

public class Panel extends JFrame{
	
	Grid grid;
	
	Panel() {		
		
		grid = new Grid();
		
		this.setTitle("MY PANEL");
		this.setSize(500, 500);

		this.add(grid);
		this.pack();
		
		this.setResizable(false);
		this.setLayout(null);
		this.setVisible(true);
		
		
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		grid.plotPoint(100, 100);
		
	}
}
