import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.Iterator;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.text.StyledEditorKit.UnderlineAction;

public class DisplayPanel extends JFrame {

    private int screenBorder, sidePanelWidth, screenWidth, screenHeight;

    private JCheckBox displayAllPossiblePath, displayShortestPath, displayCoordinates;
    private JLabel shortestPathListDisplay, distanceDisplay, timeDisplay;
    
    GridPanel grid;

    DisplayPanel(Dimension screenDimension, int _sidePanelWidth, int border) {

        screenBorder = border;
        sidePanelWidth = _sidePanelWidth;

        screenWidth = screenDimension.width;
        screenHeight = screenDimension.height;

        Font titleFont = new Font("SANS_SERIF", Font.BOLD, 15);
        Font normalFont = new Font("SANS_SERIF", Font.PLAIN, 15);
        Color color = Color.white;

        grid = new GridPanel(screenWidth - sidePanelWidth, screenHeight);

        this.setTitle("Shortest Path Finder");

        this.setSize(screenWidth, screenHeight);
        this.setResizable(false);
        this.setLayout(null);
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        grid.setBounds(0, 0, screenWidth - sidePanelWidth, screenHeight);
        this.add(grid);
        
        JPanel sidePanel = new JPanel();
        sidePanel.setBackground(color);
        sidePanel.setBounds(screenWidth - sidePanelWidth, 0, screenWidth, screenHeight);
        this.add(sidePanel);        
        //JFrame underSidePanel = new JFrame();
        //underSidePanel.setBackground(color);
        //underSidePanel.setBounds(screenWidth - sidePanelWidth, screenHeight - 300, screenWidth, 300);
        
//        underSidePanel.setTitle("Shortest Path Finder");
//
//        underSidePanel.setSize(screenWidth, screenHeight);
//        underSidePanel.setResizable(false);
//        underSidePanel.setLayout(null);
//        underSidePanel.setVisible(true);
//        underSidePanel.setLocationRelativeTo(null);
//        underSidePanel.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //this.add(underSidePanel);

//        JPanel gridJPanel = new JPanel();
//        gridJPanel.setBackground(color);
//        gridJPanel.setBounds(0, 0, screenWidth - sidePanelWidth, screenHeight);
        //this.add(gridJPanel);
        //gridJPanel.add(grid);

        JLabel size = new JLabel("Number of Nodes");
        size.setBounds(20, 10, 200, 20);
        size.setFont(titleFont);
        sidePanel.add(size);

        JTextField size_Field = new JTextField("6");
        size_Field.setBounds(20, 35, 50, 20);
        sidePanel.add(size_Field);

        JLabel displayOptions = new JLabel("Display Options");
        displayOptions.setBounds(20, 75, 200, 20);
        displayOptions.setFont(titleFont);
        sidePanel.add(displayOptions);

        displayCoordinates = new JCheckBox("Display the coordinates of the nodes");
        displayCoordinates.setSelected(true);
        displayCoordinates.setBackground(color);
        displayCoordinates.setBounds(20, 100, 300, 20);
        sidePanel.add(displayCoordinates);

        displayAllPossiblePath = new JCheckBox("Show all possible paths");
        displayAllPossiblePath.setSelected(true);
        displayAllPossiblePath.setBackground(color);
        displayAllPossiblePath.setBounds(20, 125, 200, 20);
        sidePanel.add(displayAllPossiblePath);

        displayShortestPath = new JCheckBox("Display the shortest path");
        displayShortestPath.setSelected(true);
        displayShortestPath.setBackground(color);
        displayShortestPath.setBounds(20, 150, 200, 20);
        sidePanel.add(displayShortestPath);

        JLabel controls = new JLabel("Controls");
        controls.setBounds(20, 185, 100, 20);
        controls.setFont(titleFont);
        sidePanel.add(controls);

        JButton randomize = new JButton("Randomize");
        randomize.setBounds(20, 210, 100, 20);
        sidePanel.add(randomize);

        JButton solve = new JButton("Solve");
        solve.setBounds(130, 210, 100, 20);
        sidePanel.add(solve);

        JLabel shortestPathTitle = new JLabel("Results");
        shortestPathTitle.setBounds(20, 255, 200, 20);
        shortestPathTitle.setFont(titleFont);
        sidePanel.add(shortestPathTitle);
        
        distanceDisplay = new JLabel("DISTANCE: NOT YET COMPUTED"); // ONLY 33 CHAR PER LINE
        distanceDisplay.setBounds(20, 280, 300, 20);
        distanceDisplay.setFont(normalFont);
        sidePanel.add(distanceDisplay);
        
        timeDisplay = new JLabel("TIME TAKEN: NOT YET COMPUTED"); // ONLY 33 CHAR PER LINE
        timeDisplay.setBounds(20, 300, 300, 20);
        timeDisplay.setFont(normalFont);
        sidePanel.add(timeDisplay);
        
        shortestPathListDisplay = new JLabel("PATH: NOT YET COMPUTED"); // ONLY 33 CHAR PER LINE
        shortestPathListDisplay.setBounds(20, 300, 300, 60);
        shortestPathListDisplay.setFont(normalFont);
        sidePanel.add(shortestPathListDisplay);
        
        Graph graph = new Graph();

        resetCheckboxes();
        sidePanel.repaint();

        displayAllPossiblePath.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                System.out.println(displayAllPossiblePath.isSelected());
                grid.setShowAllPath(displayAllPossiblePath.isSelected());
            }
        });

        displayShortestPath.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                grid.setShowShortestPath(displayShortestPath.isSelected());
            }
        });

        displayCoordinates.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                grid.setShowCoordinates(displayCoordinates.isSelected());
            }
        });

        randomize.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                int numNodes = Integer.parseInt(size_Field.getText());

                graph.generateNodes(numNodes, screenWidth - sidePanelWidth - screenBorder, screenHeight - screenBorder);

                grid.clearGrid();

                grid.plotGraph(graph);
                grid.drawLine(graph);

                
                //sidePanel.removeAll();
                
                // Algorithm.permute(graph);
                // graph.linkNodes(Algorithm.shortestPath);

                // grid.drawLine(graph);
            }
        });

        solve.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                // int numNodes = Integer.parseInt(size_Field.getText());

                // graph.generateNodes(numNodes, screenWidth - sidePanelWidth - screenBorder,
                // screenHeight - screenBorder);

                displayShortestPath.setSelected(true);
                grid.setShowShortestPath(true);

                grid.clearGrid();

                grid.plotGraph(graph);

                Algorithm.permute(graph);
                graph.linkNodes(Algorithm.shortestPath);

                grid.drawLine(graph);
                
                // display path + distance
                
                String shortestDistance = "DISTANCE: " + Math.round(Algorithm.shortestDistance * 100.0) / 100.0;
                String timeTaken = "TIME TAKEN: " + Algorithm.timeTaken + " S";
                String shortestPath = "PATH: " + graph.nodeConnections.get(0)[0].nodeName;
                for (int i = 0; i < graph.nodeConnections.size(); i++) {
                	shortestPath += " " + graph.nodeConnections.get(i)[1].nodeName;
                }
                
                sidePanel.remove(distanceDisplay);
                sidePanel.remove(shortestPathListDisplay);
                sidePanel.remove(timeDisplay);
                
                distanceDisplay = setText(distanceDisplay, shortestDistance, normalFont);
                shortestPathListDisplay = setText(shortestPathListDisplay, shortestPath, normalFont);
                timeDisplay = setText(timeDisplay, timeTaken, normalFont);
                
                sidePanel.add(distanceDisplay);
                sidePanel.add(shortestPathListDisplay);
                sidePanel.add(timeDisplay);
                
                sidePanel.repaint();
            }
        });
    }

    private JLabel setText(JLabel label, String text, Font font) {
    	JLabel tempLabel = new JLabel(text); // ONLY 33 CHAR PER LINE
    	tempLabel.setBounds(label.getBounds());
    	tempLabel.setFont(font);
        return tempLabel;
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