import java.awt.Color;

import javax.swing.*;
import javax.swing.JFrame;


//l1 represents size input
public class Inputs extends JFrame{
    JLabel l1;
    JTextField t1;
    JButton b1;

    public Inputs(){
        // super("Node Generator");
        
        // //labels
        // l1= new JLabel("Size:");
        
        // //Fields
        // t1= new JTextField(10);

        // //Buttons
        // b1= new JButton("Randomize");

        // setLayout(new FlowLayout(FlowLayout.LEFT, 150, 10));
        
        //     add(l1);
        //     add(t1);
        //     add(b1);
        
        // setSize(400, 300);
        // setVisible(true);
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

        
    }
    
    public static void main(String [] args) {
    	new Inputs();
    }
}