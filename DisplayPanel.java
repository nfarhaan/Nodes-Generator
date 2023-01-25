import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

// This class is responsible for the program window 
public class DisplayPanel extends JFrame {

	// Variables for window dimensions
    private int screenBorder, sidePanelWidth, screenWidth, screenHeight;

    // Variables for UI elements of the program 
    private JTextField size_Field;
    private JButton solve, randomize;
    private JCheckBox displayAllPossiblePath, displayShortestPath, displayCoordinates;
    private JLabel shortestPathListDisplay, distanceDisplay, timeDisplay;
    
    GridPanel grid;

    // Constructor of the class to display the GUI window
    DisplayPanel(Dimension screenDimension, int _sidePanelWidth, int border) {

        screenBorder = border;
        sidePanelWidth = _sidePanelWidth;

        screenWidth = screenDimension.width;
        screenHeight = screenDimension.height;

        Font titleFont = new Font("SANS_SERIF", Font.BOLD, 15);
        Font normalFont = new Font("SANS_SERIF", Font.PLAIN, 15);
        Color color = Color.white;

        grid = new GridPanel(screenWidth - sidePanelWidth, screenHeight); 

        // Set properties of the window frame
        this.setTitle("Shortest Path Finder");

        this.setSize(screenWidth, screenHeight);
        this.setResizable(false);
        this.setLayout(null);
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Set the limits of the grid
        grid.setBounds(0, 0, screenWidth - sidePanelWidth, screenHeight);
        this.add(grid);
        
        // Setting properties of the side panel 
        // and the different elements
        JPanel sidePanel = new JPanel();
        sidePanel.setBackground(color);
        sidePanel.setBounds(screenWidth - sidePanelWidth, 0, screenWidth, screenHeight);
        this.add(sidePanel);        

        JLabel size = new JLabel("Number of Nodes");
        size.setBounds(20, 10, 200, 20);
        size.setFont(titleFont);
        sidePanel.add(size);

        size_Field = new JTextField("6");
        size_Field.setBounds(20, 35, 50, 20);
        sidePanel.add(size_Field);

        JLabel displayOptions = new JLabel("Display Options");
        displayOptions.setBounds(20, 75, 200, 20);
        displayOptions.setFont(titleFont);
        sidePanel.add(displayOptions);

        // Display coordinates checkbox
        displayCoordinates = new JCheckBox("Display the coordinates of the nodes");
        displayCoordinates.setSelected(true);
        displayCoordinates.setBackground(color);
        displayCoordinates.setBounds(20, 100, 300, 20);
        sidePanel.add(displayCoordinates);

        // Show all possible paths checkbox
        displayAllPossiblePath = new JCheckBox("Show all possible paths");
        displayAllPossiblePath.setSelected(true);
        displayAllPossiblePath.setBackground(color);
        displayAllPossiblePath.setBounds(20, 125, 200, 20);
        sidePanel.add(displayAllPossiblePath);

        // Display shortest path checkbox
        displayShortestPath = new JCheckBox("Display the shortest path");
        displayShortestPath.setSelected(true);
        displayShortestPath.setBackground(color);
        displayShortestPath.setBounds(20, 150, 200, 20);
        sidePanel.add(displayShortestPath);

        JLabel controls = new JLabel("Controls");
        controls.setBounds(20, 185, 100, 20);
        controls.setFont(titleFont);
        sidePanel.add(controls);

        // The randomize buttons
        randomize = new JButton("Randomize");
        randomize.setBounds(20, 210, 100, 20);
        sidePanel.add(randomize);

        // The solve button
        solve = new JButton("Solve");
        solve.setBounds(130, 210, 100, 20);
        sidePanel.add(solve);

        JLabel shortestPathTitle = new JLabel("Results");
        shortestPathTitle.setBounds(20, 255, 200, 20);
        shortestPathTitle.setFont(titleFont);
        sidePanel.add(shortestPathTitle);
        
        // Show the distance of the shortest path
        distanceDisplay = new JLabel("DISTANCE: NOT YET COMPUTED");
        distanceDisplay.setBounds(20, 280, 300, 20);
        distanceDisplay.setFont(normalFont);
        sidePanel.add(distanceDisplay);
        
        // Show the time taken to calculate the shortest path
        timeDisplay = new JLabel("TIME TAKEN: NOT YET COMPUTED");
        timeDisplay.setBounds(20, 300, 300, 20);
        timeDisplay.setFont(normalFont);
        sidePanel.add(timeDisplay);
        
        // Show the nodes consisting the shortest path
        shortestPathListDisplay = new JLabel("PATH: NOT YET COMPUTED");
        shortestPathListDisplay.setBounds(20, 300, 300, 60);
        shortestPathListDisplay.setFont(normalFont);
        sidePanel.add(shortestPathListDisplay);
        
        Graph graph = new Graph();

        // Will display all possible paths when checkbox is checked
        displayAllPossiblePath.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                grid.setShowAllPath(displayAllPossiblePath.isSelected());
            }
        });

        // Will display the shortest path when checkbox is checked
        displayShortestPath.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                grid.setShowShortestPath(displayShortestPath.isSelected());
            }
        });

        // Will display node name and coordinates when checkbox is checked
        displayCoordinates.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                grid.setShowCoordinates(displayCoordinates.isSelected());
            }
        });

        // When randomize button is pressed, all nodes will be randomly generated
        randomize.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
            	int numNodes = 2;
            	
            	// Validation to allow only numeric values that are greater than 2
            	try {
            		numNodes = Integer.parseInt(size_Field.getText());	
            		if(numNodes < 2) {
            			throw new Exception("Number of nodes should at least 2");
            		}
				} catch (Exception e) {
					sidePanel.remove(size_Field);
					
					JTextField tempText = new JTextField("2");
					tempText.setBounds(size_Field.getBounds());
					size_Field = tempText;
					
					sidePanel.add(size_Field);
					numNodes = 2;
				}

                graph.generateNodes(numNodes, screenWidth - sidePanelWidth - screenBorder, screenHeight - screenBorder);

                grid.clearGrid();

                grid.plotGraph(graph);
                grid.drawLine(graph);
            }
        });
        
        solve.addMouseListener(new MouseAdapter() {
        	public void mousePressed(MouseEvent e) {
        		displayShortestPath.setSelected(true);
        		grid.setShowShortestPath(true);
        		toggleInputs(false);
        	}
        	
        	// Will only execute when the mouse is released
        	public void mouseReleased(MouseEvent e) {  
        		grid.clearGrid();
        		grid.plotGraph(graph);
                Algorithm.permute(graph);	// Call the function to calculate the shortes path
                graph.linkNodes(Algorithm.shortestPath);
                
                grid.drawLine(graph);	// Link all the nodes of the shortest path
                
                // Update the texts to show relevant information
                String shortestDistance = "DISTANCE: " + Math.round(Algorithm.shortestDistance * 100.0) / 100.0;
                String timeTaken = "TIME TAKEN: " + Algorithm.timeTaken + " S";
                String shortestPath = "PATH: " + graph.shortestNodeConnection.get(0)[0].nodeName;
                for (int i = 0; i < graph.shortestNodeConnection.size(); i++) {
                	shortestPath += " " + graph.shortestNodeConnection.get(i)[1].nodeName;
                }
                
                // Clear the texts
                sidePanel.remove(distanceDisplay);
                sidePanel.remove(shortestPathListDisplay);
                sidePanel.remove(timeDisplay);
                
                distanceDisplay = setText(distanceDisplay, shortestDistance, normalFont);
                shortestPathListDisplay = setText(shortestPathListDisplay, shortestPath, normalFont);
                timeDisplay = setText(timeDisplay, timeTaken, normalFont);
                
                // Add the texts to show relevant information
                sidePanel.add(distanceDisplay);
                sidePanel.add(shortestPathListDisplay);
                sidePanel.add(timeDisplay);
                
                sidePanel.repaint();
                toggleInputs(true);
            }  
        });
        
        resetCheckboxes();
        randomize.doClick();
        sidePanel.repaint();
    }
    
    // Function to set text
    private JLabel setText(JLabel label, String text, Font font) {
    	JLabel tempLabel = new JLabel(text);
    	tempLabel.setBounds(label.getBounds());
    	tempLabel.setFont(font);
        return tempLabel;
    }
    
    // Enable/Disable the inputs based on status
    private void toggleInputs(boolean status) {
    	size_Field.setEnabled(status);
        solve.setEnabled(status);
        randomize.setEnabled(status);
        displayAllPossiblePath.setEnabled(status);
        displayShortestPath.setEnabled(status);
        displayCoordinates.setEnabled(status);
    }
    
    private void resetCheckboxes() {
        displayAllPossiblePath.setSelected(true);
        displayShortestPath.setSelected(true);
        displayCoordinates.setSelected(true);

        grid.setShowShortestPath(true);
        grid.setShowAllPath(true);
        grid.setShowCoordinates(true);
    }
}