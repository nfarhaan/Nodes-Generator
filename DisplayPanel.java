import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class DisplayPanel extends JFrame{
	
	Random random;
	GridPanel grid;
	
	DisplayPanel() {		
		
		random = new Random();
		
		grid = new GridPanel();

		
		JPanel sidePanel = new JPanel();
        sidePanel.setBackground(Color.red);
        sidePanel.setBounds(800, 0, 1080, 720);
        
        JPanel gridJPanel = new JPanel();
        gridJPanel.setBackground(Color.yellow);
        gridJPanel.setBounds(0, 0, 800, 720);
    	
        this.setTitle("Title");
        
        this.setSize(1080, 720);
        this.setResizable(false);
        this.setLayout(null);
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.add(sidePanel);
        this.add(gridJPanel);
        
        gridJPanel.add(grid);
        
        JLabel size = new JLabel("Size");
        size.setBounds(20, 50, 100, 20);
        sidePanel.add(size);
        
        JTextField size_Field = new JTextField();
        size_Field.setBounds(20,70,200,20);
        sidePanel.add(size_Field);
        
        JButton randomize = new JButton("Randomize");
        randomize.setBounds(20, 100, 100, 20);
        sidePanel.add(randomize);
        
        randomize.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent ae) {
        		grid.clearGrid();
        		
        		Node node1 = new Node("A", random.nextInt(0, 760), random.nextInt(10, 720));
        		Node node2 = new Node("B", random.nextInt(0, 760), random.nextInt(10, 720));
        		Node node3 = new Node("B", random.nextInt(0, 760), random.nextInt(10, 720));
        		grid.plotPoint(node1);	
        		grid.plotPoint(node2);	
        		grid.plotPoint(node3);
        		
        		grid.drawLine(node1, node2);
        		grid.drawLine(node3, node2);
        	}
        });
	}
}