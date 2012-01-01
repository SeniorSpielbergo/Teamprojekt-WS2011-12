package tape;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class GraphicTapeCanvas extends Canvas {
	private Tape tape;
	private int animation_offset = 0;

	public GraphicTapeCanvas(GraphicTape tape) {
		super();
		this.tape = tape;
		this.setPreferredSize(new Dimension(50,50));
		repaint();
	}

	@Override
	public void paint(Graphics g) {
		System.out.println("Test");
		BufferedImage img = null;
		try {
			img = ImageIO.read(new File("tape/images/tape_field.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		g.drawImage(img, animation_offset, 0, null);
	}
	
	public void move(int old_position) {
		for (int i = 0; i < 50; i++) {
			animation_offset = i;
			repaint();
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}


}
