package gui.turing;

import javax.swing.*;

import java.awt.*;

public class NewTMWindow extends JFrame {


	JPanel panelName, panelTapes, panelButtons ;
	JLabel name, tapes;
	JTextField fieldName;
	JButton buttonCancel, buttonCreate;

	public NewTMWindow(){
		setTitle("New TuringMachine");
		this.setBounds(250,250,250,200);
		
		name = new JLabel();
		panelName = new JPanel();
		name.setText("Name");
		panelName.add(name);
		
		fieldName = new JTextField("");
		panelName.add(fieldName);

		tapes = new JLabel();
		panelTapes = new JPanel();
		tapes.setText("Tapes");
		panelTapes.add(tapes);
		
		

		this.setLayout(new BorderLayout());
		this.add(panelName,BorderLayout.NORTH);
		this.add(panelTapes,BorderLayout.CENTER);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}


	public static void main(String args[]){
		NewTMWindow newTm = new NewTMWindow();
		newTm.setVisible(true);

	}

}

