package gui;

import javax.swing.JButton;
import javax.swing.SwingConstants;

public class WelcomeScreenButton extends JButton {
	
	private static final long serialVersionUID = 1756097745682631315L;

	public WelcomeScreenButton(String text) {
		super(text);  
		setBorderPainted(false);  
		setContentAreaFilled(false);  
		setFocusPainted(false);  
		setOpaque(false);
		setHorizontalAlignment(SwingConstants.LEFT);
	}
	
}
