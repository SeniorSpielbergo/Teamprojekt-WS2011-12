package gui.turing;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.*;

public class Palette extends JPanel implements MouseListener {
	
	private static final long serialVersionUID = 2826433120960614428L;
	private JLabel[] icons = new JLabel[4];
	
	private String clicked = null; 
	
	public Palette() {
		this.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		icons[0] = new JLabel("System", new ImageIcon("gui/images/system.png"), JLabel.LEFT);
		icons[1] = new JLabel("State", new ImageIcon("gui/images/state.png"), JLabel.LEFT);
		icons[2] = new JLabel("Edge", new ImageIcon("gui/images/edge.png"), JLabel.LEFT);
		icons[3] = new JLabel("Text", new ImageIcon("gui/images/text.png"), JLabel.LEFT);
		for (int i = 0; i < 4; i++) {
			c.fill = GridBagConstraints.BOTH;
			c.gridx = 0;
			c.gridy = i;
			icons[i].addMouseListener(this);
			this.add(icons[i], c);
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if(clicked == null)
			clicked = e.getComponent().getName();
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
