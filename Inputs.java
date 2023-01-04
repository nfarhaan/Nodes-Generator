import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.*;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JButton;


//l1 represents size input
public class Inputs extends JFrame{

    public Inputs(){
    	
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
        		int numNodes = Integer.parseInt( size_Field.getText());
        		System.out.print(numNodes);
        	}
        });
        

    }
    
    public static void main(String [] args) {
    	new Inputs();
    }
}