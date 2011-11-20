package gui.turing;

import javax.swing.*;

import java.awt.*;

import java.awt.event.*;

public class NewTMWindow extends JFrame implements ActionListener{

	JPanel panelName, panelTapes, panelButtons, panelGap0, panelGap1, panelGap2, panelGap3, panelButtonGap ;
	JLabel name, tapes;
	JTextField fieldName;
	JSpinner numberOfTapes;
	JButton buttonCancel, buttonCreate;

	public NewTMWindow(){
		setTitle("New TuringMachine");
		this.setBounds(500,250,390,200);


		name = new JLabel();
		panelName = new JPanel();
		panelGap2 = new JPanel();
		panelGap3 = new JPanel();
		name.setText("Name");
		panelName.add(name);

		fieldName = new JTextField("                            ");
		panelName.add(fieldName);

		tapes = new JLabel();
		panelTapes = new JPanel();
		tapes.setText("Tapes");
		panelTapes.add(tapes);

		SpinnerModel tapemodel = new SpinnerNumberModel(1,1,10,1);
		numberOfTapes = new JSpinner(tapemodel);
		panelTapes.add(numberOfTapes);


		//Buttons
		buttonCreate = new JButton("Create");
		buttonCreate.addActionListener(this);

		buttonCancel = new JButton("Cancel");
		buttonCancel.addActionListener(this);

		panelButtons = new JPanel();
		panelButtonGap = new JPanel();
		panelButtons.setLayout(new GridLayout(1,3));
		panelButtons.add(buttonCancel);
		panelButtons.add(panelButtonGap);
		panelButtons.add(buttonCreate);

		panelGap0 = new JPanel();
		panelGap1 = new JPanel();


		this.setLayout(new GridLayout(6,1));
		this.add(panelGap0);
		this.add(panelName);
		this.add(panelGap2);
		this.add(panelTapes);

		this.add(panelGap1);
		this.add(panelButtons);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}


	public void actionPerformed(ActionEvent e){
		if (e.getSource().equals(buttonCancel)){
			this.setVisible(false);
			dispose();

		}

		else if(e.getSource().equals(buttonCreate)){

		}

	}

	public static void main(String args[]){
		NewTMWindow newTm = new NewTMWindow();
		newTm.setVisible(true);

	}

}

