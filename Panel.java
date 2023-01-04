import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;

public class Panel extends JFrame implements ActionListener{
	
	Random random;
	
	Grid grid;
	JButton button;
	
	Panel() {		
		
		random = new Random();
		
		grid = new Grid();
		
		button = new JButton();
		button.setBounds(200, 100, 100, 50);
		button.setText("REFRESH");
		button.addActionListener(this);
		
		this.setTitle("MY PANEL");
		this.setSize(500, 500);

		this.add(button);
		this.add(grid);
		this.pack();
		
		this.setResizable(false);
		this.setLayout(null);
		this.setVisible(true);
		
		
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		grid.plotPoint(100, 100);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == button) {
			grid.plotPoint(random.nextInt(5, 500), random.nextInt(5, 500));			
		}
	}
}
