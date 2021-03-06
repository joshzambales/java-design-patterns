package com.iluwatar;
 import java.util.*;
import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;

/**
 * The Client class is responsible for handling the input and running them through filters inside the filterManager
 *
 * This is where Filters come to play as the client pre-processes the request before being displayed in the Target
 * 
 * @author joshzambales
 *
 */
public class Client extends JFrame{
	private FilterManager filterManager;
	private JLabel jl;
	private JTextField[] jtFields;
	private JTextArea[] jtAreas;
	private JButton clearButton, processButton;
	public Client(){
		super("Client System");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(300,300);
		jl = new JLabel("RUNNING...");
		jtFields = new JTextField[3];
		for(int i = 0; i < 3; i++){
			jtFields[i] = new JTextField();
		}
		jtAreas = new JTextArea[2];
		for(int i = 0; i < 2; i++){
			jtAreas[i] = new JTextArea();
		}
		 clearButton = new JButton("Clear");
		 processButton = new JButton("Process");

		setup();
	}
	private void setup(){
		setLayout(new BorderLayout());
		JPanel panel = new JPanel();
		add(jl,BorderLayout.SOUTH);
		add(panel, BorderLayout.CENTER);
		panel.setLayout(new GridLayout(6,2));
		panel.add(new JLabel("Name"));
		panel.add(jtFields[0]);
		panel.add(new JLabel("Contact Number"));
		panel.add(jtFields[1]);
		panel.add(new JLabel("Address"));
		panel.add(jtAreas[0]);
		panel.add(new JLabel("Deposit Number"));
		panel.add(jtFields[2]);
		panel.add(new JLabel("Order"));
		panel.add(jtAreas[1]);
		panel.add(clearButton);
		panel.add(processButton);

		clearButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){
				for(JTextArea i : jtAreas){
					i.setText("");
				}
				for(JTextField i : jtFields){
					i.setText("");
				}
			}
		});

		processButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){
				String request = String.format("%s&%s&%s&%s&%s",jtFields[0].getText(),jtFields[1].getText(),jtAreas[0].getText(),jtFields[2].getText(),jtAreas[1].getText());
				
				jl.setText(sendRequest(request));
			}
		});

		JRootPane rootPane = SwingUtilities.getRootPane(processButton); 
		rootPane.setDefaultButton(processButton);
		setVisible(true);
	} 
	public void setFilterManager(FilterManager filterManager){
		this.filterManager = filterManager;
	}
	public String sendRequest(String request){
		return filterManager.filterRequest(request);
	}
}