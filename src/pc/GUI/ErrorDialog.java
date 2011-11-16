package GUI;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;

import javax.swing.*;

/**
 * A error dialog that can display a message and details of an exception
 * @author nils
 *
 */
@SuppressWarnings("serial")
public class ErrorDialog extends JDialog {
	private JPanel jPanelTop;
	private JPanel jPanelButtons;
	private JPanel jPanelCenter;
	private JButton jButtonClose;
	private JButton jButtonToggleDetails;
	private JScrollPane jScrollPaneException;
	private JTextArea jTextAreaException;
	private JTextArea jTextAreaErrorMsg;	
	private JTextArea jTextAreaErrorMsgSmall;



	/**
	 * Creates a new ErrorDialog based on a message
	 * @param msg The message that shall be displayed
	 */
	private ErrorDialog(String msg) {
		this(msg, null);
	}

	/**
	 * Creates a new ErrorDialog based on a message and an exception.
	 * @param msg The message that shall be displayed
	 * @param exception The exception that caused the error
	 */
	private ErrorDialog(String msg, Throwable exception) {
		this.setTitle("Error");
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setModal(true);
		this.setResizable(false);
		
		//Error message
		jTextAreaErrorMsg = new JTextArea();
		jTextAreaErrorMsg.setEditable(false);
		jTextAreaErrorMsg.setLineWrap(true);
		jTextAreaErrorMsg.setWrapStyleWord(true);
		jTextAreaErrorMsg.setPreferredSize(new Dimension(400,50));
		jTextAreaErrorMsg.setMaximumSize(new Dimension(500,300));
		jTextAreaErrorMsg.setText(msg);
		jTextAreaErrorMsg.setFont(jTextAreaErrorMsg.getFont().deriveFont(
				jTextAreaErrorMsg.getFont().getStyle() | Font.BOLD, jTextAreaErrorMsg.getFont().getSize()+1));
		jTextAreaErrorMsg.setBackground(this.getBackground());
		
		jPanelTop = new JPanel();
		jPanelTop.setLayout(new BorderLayout());
		jPanelTop.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		jPanelTop.add(jTextAreaErrorMsg, BorderLayout.NORTH);
	
		//Exception details
		jPanelCenter = new JPanel();
		jTextAreaException = new JTextArea();
		jScrollPaneException = new JScrollPane(jTextAreaException);
		jScrollPaneException.setPreferredSize(new Dimension(470, 300));
		jPanelCenter.setLayout(new BorderLayout());
		jPanelCenter.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		jPanelCenter.add(jScrollPaneException);

		//Add Buttons
		jButtonToggleDetails = new JButton();
		jButtonClose = new JButton();

		jButtonToggleDetails.setText("Show details");
		jButtonToggleDetails.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				toggleDetails();
			}
		});

		jButtonClose.setText("Ok");
		jButtonClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				dispose();
			}
		});

		jPanelButtons = new JPanel();
		jPanelButtons.setLayout(new BoxLayout(jPanelButtons, BoxLayout.LINE_AXIS));
		jPanelButtons.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 10));
		jPanelButtons.add(Box.createHorizontalGlue());
		jPanelButtons.add(jButtonToggleDetails);
		jPanelButtons.add(Box.createRigidArea(new Dimension(10, 0)));
		jPanelButtons.add(jButtonClose);
		
		getRootPane().setDefaultButton(jButtonClose);

		//build layout
		this.setLayout(new BorderLayout());
		this.add(jPanelTop, BorderLayout.NORTH);
		this.add(jPanelCenter, BorderLayout.CENTER);
		this.add(jPanelButtons, BorderLayout.SOUTH);

		this.jPanelCenter.setVisible(false);

		if (exception != null) {
			String exceptionText = "Error: " + msg + "\n\n";
			exceptionText += getStackTraceString(exception);
			jTextAreaException.setText(exceptionText);
			jTextAreaException.setEditable(false);
			
			jTextAreaErrorMsgSmall = new JTextArea();
			jTextAreaErrorMsgSmall.setEditable(false);
			jTextAreaErrorMsgSmall.setLineWrap(true);
			jTextAreaErrorMsgSmall.setWrapStyleWord(true);
			jTextAreaErrorMsgSmall.setPreferredSize(new Dimension(400,60));
			jTextAreaErrorMsgSmall.setMaximumSize(new Dimension(600,300));
			jTextAreaErrorMsgSmall.setText(exception.getMessage());
			jTextAreaErrorMsgSmall.setBackground(this.getBackground());
			jPanelTop.add(jTextAreaErrorMsgSmall, BorderLayout.SOUTH);

		} else {
			this.jButtonToggleDetails.setVisible(false);
		}

		this.registerEscapeKey();

		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		setModal(true);
		this.pack();
		centerDialogOnTheScreen();
	}

	/**
	 * Center the dialog window on the screen
	 */
	private void centerDialogOnTheScreen() {
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		Dimension dialogSize = this.getSize();
		int centerPosX = (screenSize.width - dialogSize.width) / 2;
		int centerPosY = (screenSize.height - dialogSize.height) / 2;
		setLocation(centerPosX, centerPosY);
	}

	/**
	 * Show/hide the exception details text area
	 */
	private void toggleDetails() {
		if (this.jPanelCenter.isVisible()) {
			this.jButtonToggleDetails.setText("Show details");
			this.setResizable(false);
			this.jPanelCenter.setVisible(false);
			this.pack();
			centerDialogOnTheScreen();
		} else {
			this.jButtonToggleDetails.setText("Hide details");
			this.setResizable(true);
			this.jPanelCenter.setVisible(true);
			this.pack();
			centerDialogOnTheScreen();
		}
	}
	
	/**
	 * Make sure that ESC closes the ErrorDialog
	 */
	private void registerEscapeKey() {
		KeyStroke escapeKeyStroke = KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0, false);
		Action escapeAction = new AbstractAction() {
			public void actionPerformed(ActionEvent e) {
				jButtonClose.doClick();
			}
		};

		this.rootPane.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(
				escapeKeyStroke, "ESCAPE");
		this.rootPane.getActionMap().put("ESCAPE", escapeAction);
	}

	/**
	 * Gets the stack strace of an exception as a string representation
	 * @param exception The exception that occurred
	 * @return String representation of the exception message and stack trace
	 */
	public static String getStackTraceString(Throwable exception) {
		Writer result = new StringWriter();
		PrintWriter printWriter = new PrintWriter(result);
		exception.printStackTrace(printWriter);
		return result.toString();
	}

	/**
	 * Shows a blocking ErrorDialog based on a message string and an exception
	 * The message is always displayed to the user. The exception is used for the details text area,
	 * that is displayed after the user selects the "Show details" button.
	 * @param msg The error message that should be displayed to the user
	 * @param throwable The exception that occurred
	 */
	public static void showError(String msg, Throwable throwable) {
		//console output as fallback
		System.out.println("Error: " + msg);
		
		if (throwable != null) {
			throwable.printStackTrace();
		}
		
		ErrorDialog errorDialog = new ErrorDialog(msg, throwable);
		errorDialog.setVisible(true);
	}

	/**
	 * Shows a blocking ErrorDialog based on a message string
	 * @param msg The error message that should be displayed to the user
	 */
	public static void showError(String msg) {
		ErrorDialog.showError(msg, null);
	}
}