import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class DisplayPanel extends JFrame{
	
	private int screenBorder, sidePanelWidth, screenWidth, screenHeight;
	
	GridPanel grid;
	
	DisplayPanel(Dimension screenDimension, int _sidePanelWidth, int border) {		
		
		screenBorder = border;
		sidePanelWidth = _sidePanelWidth;
		
		screenWidth = screenDimension.width;
		screenHeight = screenDimension.height;
		
		grid = new GridPanel(screenWidth - sidePanelWidth, screenHeight);
		
		
		JPanel sidePanel = new JPanel();
        sidePanel.setBackground(Color.gray);
        sidePanel.setBounds(screenWidth - sidePanelWidth, 0, screenWidth, screenHeight);
        
        JPanel gridJPanel = new JPanel();
        gridJPanel.setBackground(Color.gray);
        gridJPanel.setBounds(0, 0, screenWidth - sidePanelWidth, screenHeight);
    	
        this.setTitle("Shortest Path Finder");
        
        this.setSize(screenWidth, screenHeight);
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
        
        JTextField size_Field = new JTextField("2");
        size_Field.setBounds(20,70,200,20);
        sidePanel.add(size_Field);
        
        JButton randomize = new JButton("Randomize");
        randomize.setBounds(20, 100, 100, 20);
        sidePanel.add(randomize);
        
        JButton solve = new JButton("Solve");
        solve.setBounds(20, 125, 100, 20);
        sidePanel.add(solve);
        
        Graph graph = new Graph();
        
        randomize.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                int numNodes = Integer.parseInt( size_Field.getText());
                
                graph.generateNodes(numNodes, screenWidth - sidePanelWidth - screenBorder, screenHeight - screenBorder);
                
                grid.clearGrid();
                grid.plotGraph(graph);
                
                for(int i = 0; i < graph.nodes.size() - 1; i++) {
                	//grid.drawLine(graph.nodes.get(i), graph.nodes.get(i + 1));
                }
            }
        });
    }
}