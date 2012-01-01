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
		g.drawImage(img, 0, 0, null);
	}


}
