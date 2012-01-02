package tape;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class GraphicTapeCanvas extends Canvas {
	private GraphicTape tape;
	private BufferedImage fieldImage = null;
	private BufferedImage headImage = null;
	private int fieldWidth = 28;
	private int fieldHeight = 32;
	private char oldChar = '#';
	private int moveAnimationOffset = 0;
	private int writeAnimationFrameCount = 0;

	public GraphicTapeCanvas(GraphicTape tape) {
		super();
		this.tape = tape;
		try {
			fieldImage = ImageIO.read(new File("tape/images/tape_field.png"));
			fieldWidth = fieldImage.getWidth();
			fieldHeight = fieldImage.getHeight();
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			headImage = ImageIO.read(new File("tape/images/head.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.setMinimumSize(new Dimension(2 * fieldWidth,fieldHeight)); //at least show 2 fields
		this.setPreferredSize(new Dimension(10 * fieldWidth,fieldHeight));
		repaint();
	}

	@Override
	public void paint(Graphics g) {
		Font f = new Font("Courier",Font.PLAIN, this.fieldHeight/2);
		g.setFont(f);
		for (int i = this.tape.getPosition()-this.getNumberOfFields()/2; i <= this.tape.getPosition() + this.getNumberOfFields()/2; i++) {
			g.drawImage(fieldImage, this.getFieldPositionX(i), 0, null);
			this.drawCenteredString(((Character)this.tape.get(i)).toString(), this.getFieldPositionX(i), 0, this.fieldWidth, this.fieldHeight, g);
		}
		g.drawImage(headImage, (int) (this.getSize().getWidth()/2 - this.fieldWidth/2), 0, null);	
	}

	private int getNumberOfFields() {
		return (int) (this.getSize().getWidth()/this.fieldWidth + 2);
	}

	private int getFieldPositionX(int field) {
		int currentFieldPositionX = (int) (this.getSize().getWidth()/2 - this.fieldWidth/2) - this.tape.getPosition() * this.fieldWidth + moveAnimationOffset;
		return currentFieldPositionX + field*this.fieldWidth;
	}

	public void drawCenteredString(String s, int x, int y, int width, int height, Graphics g) {
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
					// TODO Auto-generated catch block
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
		for (int i = 0; i < 100; i++) {
			this.writeAnimationFrameCount = i;
			repaint();
		}
		this.writeAnimationFrameCount = 0;
	}


}
