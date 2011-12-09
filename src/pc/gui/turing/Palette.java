package gui.turing;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.*;

public class Palette extends JPanel implements MouseListener {

	private static final long serialVersionUID = 2826433120960614428L;
	private JLabel[] icons = new JLabel[4];

	private String clicked = null;

	/**
	 * Constructs a palette
	 */
	public Palette() {
		this.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		icons[0] = new JLabel("Frame", new ImageIcon("gui/images/frame.png"), JLabel.LEFT);
		icons[1] = new JLabel("State", new ImageIcon("gui/images/state.png"), JLabel.LEFT);
		icons[2] = new JLabel("Text", new ImageIcon("gui/images/text.png"), JLabel.LEFT);
		icons[0].setName("Frame");
		icons[1].setName("State");
		icons[2].setName("Text");
		for (int i = 0; i < 3; i++) {
			c.fill = GridBagConstraints.BOTH;
			c.gridx = i % 2;
			c.gridy = (i < 2) ? 0 : 1;
			c.insets = new Insets(5,5,5,5);
			icons[i].addMouseListener(this);
			this.add(icons[i], c);
		}
	}
	
	/**
	 * Returns the clicked item
	 * @return The clicked item
	 */
	public String getClicked() {
		return clicked;
	}
	
	/**
	 * Sets the clicked item
	 * @param value The name of the clicked item
	 */
	public void setClicked(String value) {
		this.clicked = value;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if(clicked != null && clicked.equals(e.getComponent().getName()))
			clicked = null;
		else
			clicked = e.getComponent().getName();
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}

	@Override
	public void mousePressed(MouseEvent e) {
	}

	@Override
	public void mouseReleased(MouseEvent e) {
	}
}
