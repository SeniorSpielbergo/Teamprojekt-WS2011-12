package gui.turing;

import javax.swing.*;

import java.awt.*;

import java.awt.event.*;

public class NewTMDialogue extends JDialog implements ActionListener{
	
	public enum ReturnValue {
		CANCEL, CREATE
	}

	private JPanel panelName, panelTapes, panelButtons, panelGap0, panelGap1, panelGap2, panelGap3, panelButtonGap ;
	private JLabel name, tapes;
	private	JTextField fieldName;
	private JSpinner numberOfTapes;
	private JButton buttonCancel, buttonCreate;
	private  ReturnValue returnValue;

	public NewTMDialogue(){
		setTitle("New TuringMachine");
		this.setModal(true);
		this.setBounds(500,250,400,250);
		this.setResizable(false);

		name = new JLabel();
		panelName = new JPanel();
		panelGap2 = new JPanel();
		panelGap3 = new JPanel();
		name.setText("Name");
		panelName.add(name);

		fieldName = new JTextField(10);
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
	}


	public void actionPerformed(ActionEvent e){
		if (e.getSource().equals(buttonCancel)){
			returnValue = ReturnValue.CANCEL;
			this.setVisible(false);
			dispose();

		}

		else if(e.getSource().equals(buttonCreate)){
			returnValue = ReturnValue.CREATE;
			this.setVisible(false);
			dispose();
		}

	}
	
	public String getMachinesName(){
		return fieldName.getText();
	}
	
	public Integer getNumberOfTapes(){
		return (Integer)numberOfTapes.getValue();
	}
	
	public ReturnValue showDialogue(){
		this.setVisible(true);
		return returnValue;
	}

	public static void main(String args[]){
		NewTMDialogue newTm = new NewTMDialogue();
		newTm.setVisible(true);

	}

}

