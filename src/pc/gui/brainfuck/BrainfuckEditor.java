package gui.brainfuck;

import java.util.Observable;
import java.util.Observer;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.UndoableEditEvent;
import javax.swing.event.UndoableEditListener;
import javax.swing.text.*;
import javax.swing.undo.UndoManager;

import gui.ErrorDialog;
import gui.MachineEditor;

import machine.Simulation;
import machine.brainfuck.BrainfuckSimulation;

/**
 * Represents an editor for brainfuck files.
 * @author Sven Schuster
 */
public class BrainfuckEditor extends MachineEditor implements Observer, ActionListener, UndoableEditListener {
	private static final long serialVersionUID = -6379014025769077968L;
	
	private JTextPane codeArea;
	private JScrollPane codePane;
	private BrainfuckDocument document;
	private JMenu editMenu;
	private JMenuItem undoAction;
	private JMenuItem redoAction;
	private JMenuItem cutAction;
	private JMenuItem copyAction;
	private JMenuItem pasteAction;
	private JMenuItem selectAction;
	private UndoManager undoManager;

	/**
	 * Creates a new BrainfuckEditor.
	 */
	public BrainfuckEditor() {
		document = new BrainfuckDocument();
		document.addUndoableEditListener(this);
		codeArea = new JTextPane(document);
		codeArea.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 14));

		codePane = new JScrollPane(codeArea);
		codePane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

		initEditMenu();
		
		undoManager = new UndoManager();
		
		setLayout(new BorderLayout());
		add(codePane, BorderLayout.CENTER);
	}
	
	/**
	 * Set code of the editor.
	 * @param code Code
	 */
	public void setCode(String code) {
		this.codeArea.setText(code);
	}
	
	/**
	 * Returns code of the editor.
	 * @return code
	 */
	public String getCode() {
		return this.document.getText();
	}
	
	@Override
	public void setEditable(boolean editable) {
		if(!editable)
			this.codeArea.setBackground(Color.LIGHT_GRAY);
		else
			this.codeArea.setBackground(Color.WHITE);
		this.codeArea.setEditable(editable);
	}

	/**
	 * Observes whether there is a character to highlight.
	 */
	@Override
	public void update(Observable obs, Object obj) {
		if(obs instanceof BrainfuckSimulation && obj instanceof Integer) {
			document.resetHighlights();
			document.setHighlight((Integer) obj);
		}
		else if(obs instanceof BrainfuckSimulation && obj instanceof Simulation.simulationState) {
			document.resetHighlights();
			this.setEditable(true);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(undoAction)) {
			if(undoManager.canUndo())
				undoManager.undo();
			setUndoRedoMenuItems();
		}
		else if(e.getSource().equals(redoAction)) {
			if(undoManager.canRedo())
				undoManager.redo();
			setUndoRedoMenuItems();
		}
		else if(e.getSource().equals(copyAction)) {
			codeArea.copy();
		}
		else if(e.getSource().equals(cutAction)) {
			codeArea.cut();
		}
		else if(e.getSource().equals(pasteAction)) {
			codeArea.paste();
		}
		else if(e.getSource().equals(selectAction)) {
			codeArea.selectAll();
		}
	}

	@Override
	public void undoableEditHappened(UndoableEditEvent e) {
		undoManager.addEdit(e.getEdit());
		setUndoRedoMenuItems();
	}
	
	// Enables or disables the MenuItems for undo and redo.
	private void setUndoRedoMenuItems() {
		undoAction.setEnabled(undoManager.canUndo());
		redoAction.setEnabled(undoManager.canRedo());
	}
	
	// Creates the EditMenu
	private void initEditMenu() {
		editMenu = new JMenu("Edit");
		
		undoAction = new JMenuItem("Undo");
		undoAction.addActionListener(this);
		undoAction.setAccelerator(KeyStroke.getKeyStroke('Z', Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
		undoAction.setEnabled(false);
		editMenu.add(undoAction);
		
		redoAction = new JMenuItem("Redo");
		redoAction.addActionListener(this);
		redoAction.setAccelerator(KeyStroke.getKeyStroke('Y', Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
		redoAction.setEnabled(false);
		editMenu.add(redoAction);
		
		editMenu.addSeparator();
		
		copyAction = new JMenuItem("Copy");
		copyAction.addActionListener(this);
		copyAction.setAccelerator(KeyStroke.getKeyStroke('C', Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
		editMenu.add(copyAction);
		
		cutAction = new JMenuItem("Cut");
		cutAction.addActionListener(this);
		cutAction.setAccelerator(KeyStroke.getKeyStroke('X', Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
		editMenu.add(cutAction);
		
		pasteAction = new JMenuItem("Paste");
		pasteAction.addActionListener(this);
		pasteAction.setAccelerator(KeyStroke.getKeyStroke('V', Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
		editMenu.add(pasteAction);

		editMenu.addSeparator();
		
		selectAction = new JMenuItem("Select all");
		selectAction.addActionListener(this);
		selectAction.setAccelerator(KeyStroke.getKeyStroke('A', Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
		editMenu.add(selectAction);
		
		this.getMenus().add(editMenu);
	}
	
	/**
	 * Represents a highlightable document for brainfuck code.
	 * @author Sven Schuster
	 */
	public class BrainfuckDocument extends DefaultStyledDocument {
		private static final long serialVersionUID = 3162006872505645953L;

		/**
		 * Creates a new BrainfuckDocument.
		 */
		public BrainfuckDocument() {
			super();
		}
		
		/**
		 * Highlight the character at the specified position. The highlighted character will be shown in red.
		 * @param position Position of character to be highlighted.
		 */
		public void setHighlight(int position) {
			SimpleAttributeSet attributes = new SimpleAttributeSet();
			StyleConstants.setForeground(attributes, Color.RED);
			document.setCharacterAttributes(position, 1, attributes, false);
		}
		
		/**
		 * Reset all highlights. The whole document will then have a black foreground color.
		 */
		private void resetHighlights() {
			SimpleAttributeSet attributes = new SimpleAttributeSet();
			StyleConstants.setForeground(attributes, Color.BLACK);
			document.setCharacterAttributes(0, document.getLength(), attributes, false);
		}
		
		/**
		 * Returns the whole text held by the document. 
		 * @return text
		 */
		public String getText() {
			try {
				return super.getText(0, this.getLength());
			} 
			catch (BadLocationException e) {
				ErrorDialog.showError("Something went terribly wrong.", e);
			}
			return "";
		}
	}
}