import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class DisplayPanel extends JFrame {

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
        size_Field.setBounds(20, 70, 200, 20);
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
                int numNodes = Integer.parseInt(size_Field.getText());

                graph.generateNodes(numNodes, screenWidth - sidePanelWidth - screenBorder, screenHeight - screenBorder);

                grid.clearGrid();

                grid.plotGraph(graph);
                //grid.drawLine(graph);

                String shortestPath = "";
                ArrayList<Node> shortestPathArr = new ArrayList<Node>();
                // calculate the shortest path passing through all nodes. starting point can be
                // any node in the list
                // withouth repeating any node
                for (int i = 0; i < graph.nodes.size(); i++) {
                    String path = graph.nodes.get(i).nodeName;
                    ArrayList<Node> pathArr = new ArrayList<Node>();
                    ArrayList<Node> tempNodes = new ArrayList<Node>();
                    tempNodes.addAll(graph.nodes);
                    tempNodes.remove(i);

                    while (tempNodes.size() > 0) {
                        float shortestDistance = 1000000000;
                        int shortestIndex = 0;

                        for (int j = 0; j < tempNodes.size(); j++) {
                            float tempDistance = distance(graph.nodes.get(i), tempNodes.get(j));

                            if (tempDistance < shortestDistance) {
                                shortestDistance = tempDistance;
                                shortestIndex = j;
                            }
                        }
                        
                        pathArr.add(tempNodes.get(shortestIndex));
                        path += " -> " + tempNodes.get(shortestIndex).nodeName;
                        // graph.nodes.get(i).posX = tempNodes.get(shortestIndex).posX;
                        // graph.nodes.get(i).posY = tempNodes.get(shortestIndex).posY;
                        tempNodes.remove(shortestIndex);
                    }

                    if (i == 0) {
                        shortestPath = path;
                        shortestPathArr = pathArr;
                    } else {
                        if (path.length() < shortestPath.length()) {
                            shortestPath = path;
                            shortestPathArr = pathArr;
                        }
                    }
                }

                // print the shortest path
                System.out.println(shortestPath);
                graph.solvedPath = shortestPathArr;
                graph._linkNodes();
                grid.drawLine(graph);
                
                graph._showNodes();
            }
        });

    }

    public static float distance(Node n1, Node n2) {
        float distance = (float) Math.sqrt(Math.pow(n1.posX - n2.posX, 2) + Math.pow(n1.posY - n2.posY, 2));
        return distance;
    }
}