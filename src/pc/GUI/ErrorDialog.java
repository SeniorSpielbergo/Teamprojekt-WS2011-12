package GUI;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;

import javax.swing.*;

@SuppressWarnings("serial")
public class ErrorDialog extends JDialog {
	private JPanel jPanelTop;
	private JPanel jPanelButtons;
	private JPanel jPanelCenter;
	private JButton jButtonClose;
	private JButton jButtonToggleDetails;
	private JScrollPane jScrollPaneException;
	private JTextArea jTextAreaException;
	private JLabel jLabelErrorMsg;


	public ErrorDialog(String msg) {
		this(msg, null);
	}

	public ErrorDialog(String msg, Throwable exception) {
		this.setTitle("Error");
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setModal(true);
		this.setResizable(false);
		
		//Error message
		jLabelErrorMsg = new JLabel();
		jLabelErrorMsg.setText(msg);
		jLabelErrorMsg.setFont(jLabelErrorMsg.getFont().deriveFont(
				jLabelErrorMsg.getFont().getStyle() | Font.BOLD, jLabelErrorMsg.getFont().getSize()+1));
		
		jPanelTop = new JPanel();
		jPanelTop.setLayout(new BorderLayout());
		jPanelTop.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		jPanelTop.add(jLabelErrorMsg);
	
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
		jPanelButtons .add(jButtonClose);
		
		this.setLayout(new BorderLayout());
		this.add(jPanelTop, BorderLayout.NORTH);
		this.add(jPanelCenter, BorderLayout.CENTER);
		this.add(jPanelButtons, BorderLayout.SOUTH);

		this.jPanelCenter.setVisible(false);

		if (exception != null) {
			String exceptionText = getStackTraceString(exception);
			jTextAreaException.setText(exceptionText);
			jTextAreaException.setEditable(false);
		} else {
			this.jButtonToggleDetails.setVisible(false);
		}

		this.registerEscapeKey();

		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		setModal(true);
		this.pack();
		centerDialogOnTheScreen();
	}

	private void centerDialogOnTheScreen() {
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		Dimension dialogSize = this.getSize();
		int centerPosX = (screenSize.width - dialogSize.width) / 2;
		int centerPosY = (screenSize.height - dialogSize.height) / 2;
		setLocation(centerPosX, centerPosY);
	}

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

	public String getStackTraceString(Throwable exception) {
		Writer result = new StringWriter();
		PrintWriter printWriter = new PrintWriter(result);
		exception.printStackTrace(printWriter);
		return result.toString();
	}

	public void registerEscapeKey() {
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

	public void hideAndDisposeDialog() {
		this.setVisible(false);
		this.dispose();
	}

	public static void showError(String errorMessage, Throwable throwable) {
		ErrorDialog errorDialog = new ErrorDialog(errorMessage, throwable);
		errorDialog.setVisible(true);
	}

	public static void showError(String errorMessage) {
		ErrorDialog.showError(errorMessage, null);
	}
}