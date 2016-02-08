/*
Tyler Martin
4836318

The client gui.
It sort of works and it really plain but does the job
*/



import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.DisplayMode;
import java.awt.GraphicsEnvironment;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class ClientGUI extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JLabel label;
	private JTextField tf;
	private JTextField tfServer, tfPort;
	private JButton login, logout, sendFile;
	private JTextArea ta;
	private boolean connected;
	private Client client;
	private int defaultPort;
	private String defaultHost;
	private String fileName;

	ClientGUI(String host, int port) {

		super("Chat Client");
		defaultPort = port;
		defaultHost = host;
	
		JPanel northPanel = new JPanel(new GridLayout(3,1));
		JPanel serverAndPort = new JPanel(new GridLayout(1,5, 1, 3));
		tfServer = new JTextField(host);
		tfPort = new JTextField("" + port);
		tfPort.setHorizontalAlignment(SwingConstants.RIGHT);

		serverAndPort.add(new JLabel("Server Address:  "));
		serverAndPort.add(tfServer);
		serverAndPort.add(new JLabel("Port Number:  "));
		serverAndPort.add(tfPort);
		serverAndPort.add(new JLabel(""));
		northPanel.add(serverAndPort);
		label = new JLabel("Enter your username below", SwingConstants.CENTER);
		northPanel.add(label);
		tf = new JTextField("Anonymous");
		tf.setBackground(Color.WHITE);
		northPanel.add(tf);
		add(northPanel, BorderLayout.NORTH);

		ta = new JTextArea("Welcome to the Dark Side\n", 80, 80);
		JPanel centerPanel = new JPanel(new GridLayout(1,1));
		centerPanel.add(new JScrollPane(ta));
		ta.setEditable(false);
		add(centerPanel, BorderLayout.CENTER);

		login = new JButton("Login");
		login.addActionListener(this);
		logout = new JButton("Logout");
		logout.addActionListener(this);
		logout.setEnabled(false);
		sendFile = new JButton("Send File");
		sendFile.addActionListener(this);
                sendFile.setEnabled(false);

		JPanel southPanel = new JPanel();
		southPanel.add(login);
		southPanel.add(logout);
		southPanel.add(sendFile);
		add(southPanel, BorderLayout.SOUTH);

		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(600, 600);
		setVisible(true);
		tf.requestFocus();

	}
	void append(String str) {
		ta.append(str);
		ta.setCaretPosition(ta.getText().length() - 1);
	}
	void connectionFailed() {
		login.setEnabled(true);
		logout.setEnabled(false);
                sendFile.setEnabled(false);
		label.setText("Enter your username below");
		tf.setText("Anonymous");
		tfPort.setText("" + defaultPort);
		tfServer.setText(defaultHost);
		tfServer.setEditable(false);
		tfPort.setEditable(false);
		tf.removeActionListener(this);
		connected = false;
	}

	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		if(o == logout) {
			client.sendMessage(new chatMsg(chatMsg.LOGOUT, ""));
			return;
		}
                
		if(o == sendFile){
			JTextField textField = new JTextField(10);
			Object[] array = {"Enter the filename"+"\n",textField};
			final JOptionPane pane = new JOptionPane(array,JOptionPane.QUESTION_MESSAGE,JOptionPane.DEFAULT_OPTION);
			JFrame frame = new JFrame();
			final JDialog dialog = new JDialog(frame,"Send a file YO!",true);
			dialog.setResizable(false);
			dialog.setContentPane(pane);
			dialog.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
			pane.addPropertyChangeListener(
				new PropertyChangeListener(){
					public void propertyChange(PropertyChangeEvent e){
						String prop = e.getPropertyName();
						if (dialog.isVisible() && (e.getSource()== pane) && (prop.equals(JOptionPane.VALUE_PROPERTY)))
							dialog.setVisible(false);
					}
				});
			dialog.pack();
			DisplayMode mode= GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDisplayMode();
			dialog.setLocation((mode.getWidth()-dialog.getWidth())/2,(mode.getHeight()-dialog.getHeight())/2);
			dialog.setVisible(true);
			frame.dispose();
			fileName = textField.getText();	
			client.sendMessage(new chatMsg(chatMsg.SENDING_FILE, new File(fileName)));

		}
                if(connected) {
			client.sendMessage(new chatMsg(chatMsg.MESSAGE, tf.getText()));				
			tf.setText("");
			return;
		}
		
                
		if(o == login) {
			String username = tf.getText().trim();
			if(username.length() == 0)
				return;
			String server = tfServer.getText().trim();
			if(server.length() == 0)
				return;
			String portNumber = tfPort.getText().trim();
			if(portNumber.length() == 0)
				return;
			int port = 0;
			try {
				port = Integer.parseInt(portNumber);
			}
			catch(Exception en) {
				return;
			}

			client = new Client(server, port, username, this);
			if(!client.start()) 
				return;
			tf.setText("");
			label.setText("Enter your message below");
			connected = true;
	
			login.setEnabled(false);
			logout.setEnabled(true);
                        sendFile.setEnabled(true);
			tfServer.setEditable(false);
			tfPort.setEditable(false);
			tf.addActionListener(this);
		}

	}

	// to start the whole thing the server
	public static void main(String[] args) {
		new ClientGUI("localhost", 22222);
	}

}
