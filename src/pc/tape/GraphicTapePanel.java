package tape;

import gui.AppData;
import gui.ErrorDialog;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Composite;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class GraphicTapePanel extends JPanel {
	private GraphicTape tape;
	private BufferedImage fieldImage = null;
	private BufferedImage headImage = null;
	private int fieldWidth = 28;
	private int fieldHeight = 32;
	private char oldChar = '#';
	private int moveAnimationOffset = 0;
	private int writeAnimationFrameCount = 100;
	private String style = "";

	public GraphicTapePanel(GraphicTape tape) {
		super();
		this.tape = tape;
		
		this.setStyle("default");
	}
	
	public GraphicTapePanel(GraphicTape tape, String style) {
		super();
		this.tape = tape;
		
		this.setStyle(style);
	}
	
	public String getStyle() {
		return style;
	}

	public void setStyle(String style) {
		this.style = style;
		this.loadStyle(style);
	}

	private void loadStyle(String style) {
		try {
			fieldImage = ImageIO.read(new File(AppData.tapeStylesDirectory.getAbsolutePath() + File.separator + style + File.separator + "tape_field.png"));
			fieldWidth = fieldImage.getWidth();
			fieldHeight = fieldImage.getHeight();
		} catch (IOException e) {
			ErrorDialog.showError("The image '" + AppData.tapeStylesDirectory.getAbsolutePath() + File.separator + style + File.separator + "tape_field.png' couldn't be opened.", e);
		}
		try {
			headImage = ImageIO.read(new File(AppData.tapeStylesDirectory.getAbsolutePath() + File.separator + style + File.separator + "head.png"));
		} catch (IOException e) {
			ErrorDialog.showError("The image '" + AppData.tapeStylesDirectory.getAbsolutePath() + File.separator + style + File.separator + "head.png' couldn't be opened.", e);
		}
		this.setMinimumSize(new Dimension(2 * fieldWidth,fieldHeight)); //at least show 2 fields
		this.setPreferredSize(new Dimension(10 * fieldWidth,fieldHeight));
		repaint();
	}

	@Override
	public void paintComponent(Graphics g2) {
		Graphics2D g = (Graphics2D) g2;
		g.clearRect(0, 0, (int)this.getSize().getWidth(), (int)this.getSize().getHeight());
		Font f = new Font("Courier",Font.PLAIN, this.fieldHeight/2);
		g.setFont(f);
		for (int i = this.tape.getPosition()-this.getNumberOfFields()/2; i <= this.tape.getPosition() + this.getNumberOfFields()/2; i++) {
			g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER));
			g.setColor(Color.BLACK);

			g.drawImage(fieldImage, this.getFieldPositionX(i), 0, null);

			String symbol = "";
			if (i == this.tape.getPosition()) {
				if (this.writeAnimationFrameCount < 33) {
					//fade out old char
					symbol = ((Character)this.oldChar).toString();
					Composite c = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1 - (float)this.writeAnimationFrameCount * (1.0f/33.0f));
					g.setComposite(c);
				}
				else if (this.writeAnimationFrameCount >= 33 && this.writeAnimationFrameCount < 66){
					//fade in new char highlighted
					symbol = ((Character)this.tape.get(i)).toString();
					g.setColor(Color.RED);
					Composite c = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, (float)(this.writeAnimationFrameCount-33) * (1.0f/33.0f));
					g.setComposite(c);
				}
				else if (this.writeAnimationFrameCount >= 66 && this.writeAnimationFrameCount < 99){
					//fade from highlight color to normal black
					symbol = ((Character)this.tape.get(i)).toString();
					g.setColor(new Color(255-((this.writeAnimationFrameCount-66) * (255/33)),0,0));
				}
				else {
					symbol = ((Character)this.tape.get(i)).toString();
				}
			}
			else {
				symbol = ((Character)this.tape.get(i)).toString();
			}
			this.drawCenteredString(symbol, this.getFieldPositionX(i), 0, this.fieldWidth, this.fieldHeight, g);
		}

		g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER));
		g.setColor(Color.BLACK);
		g.drawImage(headImage, (int) (this.getSize().getWidth()/2 - this.fieldWidth/2), 0, null);	
	}

	private int getNumberOfFields() {
		return (int) (this.getSize().getWidth()/this.fieldWidth + 2);
	}

	private int getFieldPositionX(int field) {
		int currentFieldPositionX = (int) (this.getSize().getWidth()/2 - this.fieldWidth/2) - this.tape.getPosition() * this.fieldWidth + moveAnimationOffset;
		return currentFieldPositionX + field*this.fieldWidth;
	}

	private void drawCenteredString(String s, int x, int y, int width, int height, Graphics g) {
		FontMetrics fm = g.getFontMetrics();
		int stringX = (width - fm.stringWidth(s)) / 2 + x;
		int stringY = (fm.getAscent() + (height - (fm.getAscent() + fm.getDescent())) / 2) + y;
		g.drawString(s, stringX, stringY);
	}

	public void move(int oldPosition) {
		if (this.tape.getDelay()) {
			this.moveAnimationOffset = (this.tape.position - oldPosition)*this.fieldWidth;
			while (moveAnimationOffset != 0) {
				if (moveAnimationOffset > 0) {
					moveAnimationOffset--;
				}
				else if (moveAnimationOffset < 0) {
					moveAnimationOffset++;
				}
				repaint();
				try {
					Thread.sleep(20);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
		else {
			this.moveAnimationOffset = 0;
			repaint();
		}
	}

	public void write(char oldChar) {
		this.oldChar = oldChar;
		if (this.tape.getDelay() && this.tape.get(this.tape.getPosition()) != oldChar) {
			for (int i = 0; i < 100; i++) {
				this.writeAnimationFrameCount = i;
				repaint();
				try {
					Thread.sleep(10);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			this.writeAnimationFrameCount = 100;
		}
		else {
			repaint();
		}
	}
	
	public int getHeight() {
		return this.fieldHeight;
	}
}
