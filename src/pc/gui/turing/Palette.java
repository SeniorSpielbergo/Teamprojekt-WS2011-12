package gui.turing;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.datatransfer.DataFlavor;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DragGestureEvent;
import java.awt.dnd.DragGestureListener;
import java.awt.dnd.DragSource;

import javax.swing.*;

import com.mxgraph.model.mxCell;
import com.mxgraph.model.mxGeometry;
import com.mxgraph.swing.util.mxGraphTransferable;
import com.mxgraph.util.mxConstants;
import com.mxgraph.util.mxRectangle;

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
		mxCell cell = new mxCell(null, new mxGeometry(0, 0, 50, 50), null);//debug
		cell.setVertex(true);
		icons[0] = this.addIcon("Frame", "gui/images/frame.png", cell);
		icons[1] = this.addIcon("State", "gui/images/state.png", cell);
		icons[2] = this.addIcon("Text", "gui/images/text.png", cell);

//		icons[0] = new JLabel("Frame", new ImageIcon("gui/images/frame.png"), JLabel.LEFT);
//		icons[1] = new JLabel("State", new ImageIcon("gui/images/state.png"), JLabel.LEFT);
//		icons[2] = new JLabel("Text", new ImageIcon("gui/images/text.png"), JLabel.LEFT);

		for (int i = 0; i < 3; i++) {
			c.fill = GridBagConstraints.BOTH;
			c.gridx = i % 2;
			c.gridy = (i < 2) ? 0 : 1;
			c.insets = new Insets(5,5,5,5);
			icons[i].addMouseListener(this);
			this.add(icons[i], c);
		}
	}
	
	public JLabel addIcon(String name, String icon, mxCell cell) {
		JLabel label = new JLabel(name, new ImageIcon(icon), JLabel.LEFT);
		label.setName(name);
		
		mxRectangle bounds = (mxGeometry) cell.getGeometry().clone();
		final mxGraphTransferable t = new mxGraphTransferable(
				new Object[] { cell }, bounds);
		
		DragGestureListener dragGestureListener = new DragGestureListener()
		{
			/**
			 * 
			 */
			public void dragGestureRecognized(DragGestureEvent e)
			{
				e
						.startDrag(null, mxConstants.EMPTY_IMAGE, new Point(),
								t, null);
			}

		};

		DragSource dragSource = new DragSource();
		dragSource.createDefaultDragGestureRecognizer(label,
				DnDConstants.ACTION_COPY, dragGestureListener);
		
		return label;
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
