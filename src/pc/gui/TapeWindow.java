package gui;

import javax.swing.*;

public class TapeWindow extends JFrame {

	JScrollPane scrollpaneRight;
	tape.GraphicTape graphicTape = new tape.GraphicTape();

	public TapeWindow(){
		setTitle("Tapes");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	public void init(){
		try{
			this.graphicTape.init();
		}
		catch(tape.TapeException e){}

		this.add(this.graphicTape.getTapePanel());

	}

}
