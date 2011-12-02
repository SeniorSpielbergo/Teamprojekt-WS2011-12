package gui.turing;

import javax.swing.*;

import java.awt.*;

import java.awt.event.*;

public class NewTMDialogue extends JDialog implements ActionListener{
	
	public enum ReturnValue {
		CANCEL, CREATE
	}

	private JPanel panelName, panelTapes, panelButtons;
	private JLabel name, tapes;
	private	JTextField fieldName;
	private JSpinner numberOfTapes;
	private JButton buttonCancel, buttonCreate;
	private  ReturnValue returnValue;

	public NewTMDialogue(){
		setTitle("New TuringMachine");
		this.setModal(true);
		this.setBounds(500,250,300,150);
		this.setResizable(false);

		name = new JLabel();
		panelName = new JPanel();
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
		panelButtons.setLayout(new BoxLayout(panelButtons, BoxLayout.LINE_AXIS));
		panelButtons.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 10));
		panelButtons.add(buttonCancel);
		panelButtons.add(Box.createHorizontalGlue());
		panelButtons.add(Box.createRigidArea(new Dimension(10, 0)));
		panelButtons.add(buttonCreate);
		
		getRootPane().setDefaultButton(buttonCreate);


		this.setLayout(new BorderLayout());
		this.add(panelName, BorderLayout.NORTH);
		this.add(panelTapes, BorderLayout.CENTER);
		this.add(panelButtons, BorderLayout.SOUTH);
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
}

