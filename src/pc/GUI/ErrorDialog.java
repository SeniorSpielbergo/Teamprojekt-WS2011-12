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
	private JPanel jPanelBottom;
	private JPanel jPanelCenter;
	private JButton jButtonClose;
	private JButton jButtonToggleDetails;
	private JScrollPane jScrollPaneErrorMsg;
	private JTextPane jTextPaneErrorMsg;
	private JScrollPane jScrollPaneException;
	private JTextArea jTextAreaException;


	public ErrorDialog(String msg) {
		this(msg, null);
	}

	public ErrorDialog(String msg, Throwable exception) {
		this.setTitle("Error");
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setModal(true);
		this.setResizable(false);

		jPanelTop = new JPanel();
		jPanelTop.setLayout(null);
		jPanelTop.setPreferredSize(new Dimension(480, 100));

		jTextPaneErrorMsg = new JTextPane();
		jTextPaneErrorMsg.setFont(jTextPaneErrorMsg.getFont().deriveFont(
				jTextPaneErrorMsg.getFont().getStyle() | Font.BOLD, jTextPaneErrorMsg.getFont().getSize()+1));
		jTextPaneErrorMsg.setBorder(null);
		jTextPaneErrorMsg.setEditable(false);
		jTextPaneErrorMsg.setOpaque(true);
		
		jScrollPaneErrorMsg = new JScrollPane(jTextPaneErrorMsg);
		jScrollPaneErrorMsg.setBorder(null);
		jScrollPaneErrorMsg.setSize(new Dimension(405, 80));
		jScrollPaneErrorMsg.setLocation(new Point(13, 13));
		jScrollPaneErrorMsg.setOpaque(true);
		jScrollPaneErrorMsg.getViewport().setOpaque(false);
	
		jPanelTop.add(jScrollPaneErrorMsg);

		jPanelCenter = new JPanel();
		jPanelCenter.setSize(new Dimension(420, 300));
		jTextAreaException = new JTextArea();
		jScrollPaneException = new JScrollPane(jTextAreaException);
		jScrollPaneException.setPreferredSize(new Dimension(470, 300));
		jPanelCenter.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		jPanelCenter.add(jScrollPaneException);

		jPanelBottom = new JPanel();
		jButtonToggleDetails = new JButton();
		jButtonClose = new JButton();
		jPanelBottom.setLayout(new FlowLayout(FlowLayout.CENTER, 30, 15));

		jButtonToggleDetails.setText("Show details");
		jButtonToggleDetails.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				toggleDetails();
			}
		});
		jPanelBottom.add(jButtonToggleDetails);

		jButtonClose.setText("Ok");
		jButtonClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				dispose();
			}
		});
		jPanelBottom.add(jButtonClose);

		this.setLayout(new BorderLayout());
		this.add(jPanelTop, BorderLayout.NORTH);
		this.add(jPanelCenter, BorderLayout.CENTER);
		this.add(jPanelBottom, BorderLayout.SOUTH);

		//this.jTextPaneErrorMsg.setEditorKit(new VerticalCenteredEditorKit());
		this.jTextPaneErrorMsg.setText(msg);

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
			this.jPanelCenter.setVisible(false);
			this.pack();
			centerDialogOnTheScreen();
		} else {
			this.jButtonToggleDetails.setText("Hide details");
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