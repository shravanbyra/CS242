import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class Gui extends JFrame 
{
	//	Screen and frame
	private static final int	SCREEN_WIDTH = (int)Toolkit.getDefaultToolkit().getScreenSize().getWidth(),
								SCREEN_HEIGHT = (int)Toolkit.getDefaultToolkit().getScreenSize().getHeight(),
								SCALE = (SCREEN_WIDTH/400);
	
	private static final int 	FRAME_WIDTH = 400*SCALE,
								FRAME_HEIGHT = 225*SCALE,
								FRAME_X = (SCREEN_WIDTH-FRAME_WIDTH)/2,
								FRAME_Y = (SCREEN_HEIGHT-FRAME_HEIGHT)/2;
	
	private static final int	NUM_COLS = 16, NUM_ROWS = 9;
		
	private boolean server;
	
	private JPanel panel;
	
	//	Headers
	private JLabel 				header, subheaderLeft, subheaderRight;
	private static final int 	HEADER_WIDTH = 14, SUBHEADER_WIDTH = 6,
								HEADER_HEIGHT = 2, SUBHEADER_HEIGHT = 2,
								HEADER_X = 1, SUBHEADERLEFT_X = 1, SUBHEADERRIGHT_X = 9,
								HEADER_Y = 0, SUBHEADERLEFT_Y = 0, SUBHEADERRIGHT_Y = 0;
	
	private static final Color	HEADER_COLOR = Color.WHITE;
	
	private static final Font 	HEADER_FONT = new Font("Arial", Font.BOLD, SCALE*30);
	
	//	Buttons
	private JButton 			buttonLeftTop, buttonLeftBot, buttonRight, buttonQuit;
	private static final int 	BUTTON_WIDTH = 6, BUTTONLEFT_HEIGHT = 2, BUTTONRIGHT_HEIGHT = 1,
								BUTTONLEFT_X = 1, BUTTONRIGHT_X = 9,
								BUTTONTOP_Y = 3, BUTTONBOT_Y = 6;
	
	private static final Color	BUTTON_COLOR = Color.BLACK, 
								BUTTON_BG = Color.WHITE, BUTTON_BG_HOVER = Color.LIGHT_GRAY;
								
	private static final Font 	BUTTONLEFT_FONT = new Font("Arial", Font.BOLD, SCALE*15),
								BUTTONRIGHT_FONT = new Font("Arial", Font.BOLD, SCALE*10);
	
	//	Input
	private JLabel 				ipLabel;
	private JFormattedTextField ipField;
	private static final int 	IP_WIDTH = 6, IP_HEIGHT = 1,
								IP_X = 9, IPLABEL_Y = 3, IPFIELD_Y = 4;
	
	private static final Color	IPLABEL_COLOR = Color.WHITE, 
								IPFIELD_COLOR = Color.BLACK, IPFIELD_BG = Color.WHITE;
	
	private static final Font 	IP_FONT = new Font("Arial", Font.BOLD, SCALE*10);
	
	private JTextField			throttleField;
	private static final int	THROTTLE_WIDTH = 6, THROTTLE_HEIGHT = 1,
								THROTTLE_X = 9, THROTTLE_Y = 8;
	
	private static final Font 	THROTTLE_FONT = new Font("Arial", Font.BOLD, SCALE*10);
	
	//	Threading
	private CommThread thread;
	
	
	public Gui()
	{
		initFrame();
		initJPanel();
		
		initComponents();
		
		loadStartScreen();
	}

	private void initFrame()
	{
		this.setTitle("MP4: Dynamic Balance Loader");
		this.setResizable(false);
		this.setUndecorated(false);
		this.setBounds(FRAME_X, FRAME_Y, FRAME_WIDTH, FRAME_HEIGHT+SCALE*25);
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}
	
	
	private void initJPanel() 
	{
		panel = new JPanel();
		panel.setBounds(0,0,FRAME_WIDTH,FRAME_HEIGHT);
		panel.setBackground(Color.DARK_GRAY);
		panel.setLayout(null);
		this.add(panel);
		
	}

	private void initComponents()
	{
		header = new JLabel();
		header.setText("Let's get started.");
		header.setFont(HEADER_FONT);
		header.setForeground(HEADER_COLOR);
		
		buttonLeftTop = new JButton();
		buttonLeftTop.setText("SERVER");
		buttonLeftTop.setBackground(BUTTON_BG);
		buttonLeftTop.setForeground(BUTTON_COLOR);
		buttonLeftTop.setFont(BUTTONLEFT_FONT);
		buttonLeftTop.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		buttonLeftTop.setFocusPainted(false);
		buttonLeftTop.setBorderPainted(false);
		buttonLeftTop.addActionListener(buttonListener);
		buttonLeftTop.addMouseListener(mouseAdaptor);
		
		buttonLeftBot = new JButton();
		buttonLeftBot.setText("CLIENT");
		buttonLeftBot.setBackground(BUTTON_BG);
		buttonLeftBot.setForeground(BUTTON_COLOR);
		buttonLeftBot.setFont(BUTTONLEFT_FONT);
		buttonLeftBot.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		buttonLeftBot.setFocusPainted(false);
		buttonLeftBot.setBorderPainted(false);
		buttonLeftBot.addActionListener(buttonListener);
		buttonLeftBot.addMouseListener(mouseAdaptor);
		
		buttonQuit = new JButton();
		buttonQuit.setText("X");
		buttonQuit.setBackground(Color.DARK_GRAY);
		buttonQuit.setForeground(BUTTON_BG);
		buttonQuit.setFont(BUTTONRIGHT_FONT);
		buttonQuit.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		buttonQuit.setFocusPainted(false);
		buttonQuit.setBorderPainted(false);
		buttonQuit.addActionListener(buttonListener);
		buttonQuit.addMouseListener(mouseAdaptor);
		
		ipLabel = new JLabel();
		ipLabel.setText("Server IP:");
		ipLabel.setForeground(IPLABEL_COLOR);
		ipLabel.setFont(IP_FONT);
		
		ipField = new JFormattedTextField();
		ipField.setText(Global.IP_ADDRESS);
		ipField.setMargin(new Insets(15,15,15,0));
		ipField.setForeground(IPFIELD_COLOR);
		ipField.setBackground(IPFIELD_BG);
		ipField.setFont(IP_FONT);
		
		buttonRight = new JButton();
		buttonRight.setText("CONNECT");
		buttonRight.setBackground(BUTTON_BG);
		buttonRight.setForeground(BUTTON_COLOR);
		buttonRight.setFont(BUTTONRIGHT_FONT);
		buttonRight.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		buttonRight.setFocusPainted(false);
		buttonRight.setBorderPainted(false);
		buttonRight.addActionListener(buttonListener);
		buttonRight.addMouseListener(mouseAdaptor);
		
		throttleField = new JTextField();
		throttleField.setText(""+Global.DEFAULT_THROTTLE);
		throttleField.setMargin(new Insets(15,15,15,0));
		throttleField.setForeground(Color.BLACK);
		throttleField.setBackground(Color.WHITE);
		throttleField.setFont(THROTTLE_FONT);
		throttleField.addKeyListener(new KeyListener(){

			@Override
			public void keyTyped(KeyEvent e) {}

			@Override
			public void keyPressed(KeyEvent e) {}

			@Override
			public void keyReleased(KeyEvent e) {
				int key = e.getKeyCode();
				
				if(key == KeyEvent.VK_ENTER)
					Global.hardwareMonitor.setThrottle(Integer.parseInt(throttleField.getText()));
			}
			
		});
		throttleField.setVisible(true);
		
	}
	
	private void loadStartScreen()
	{
		clearPanel();
		
		panel.add(header);
		panel.add(buttonLeftTop);
		panel.add(buttonLeftBot);
		
		set(header, HEADER_X, HEADER_Y, HEADER_WIDTH, HEADER_HEIGHT);
		set(buttonLeftTop, BUTTONLEFT_X, BUTTONTOP_Y, BUTTON_WIDTH, BUTTONLEFT_HEIGHT);
		set(buttonLeftBot, BUTTONLEFT_X, BUTTONBOT_Y, BUTTON_WIDTH, BUTTONLEFT_HEIGHT);
	}
	
	private void drawMainPage()
	{
		clearPanel();
		
		
		if(server)
		{
			header.setText("Server");
			thread = new CommThread();
		}
		else
		{
			header.setText("Client");
			thread = new CommThread(ipField.getText());
		}
		
		panel.add(header);
		panel.add(throttleField);
		set(header, HEADER_X, HEADER_Y, HEADER_WIDTH, HEADER_HEIGHT);
		set(throttleField, THROTTLE_X, THROTTLE_Y, THROTTLE_WIDTH, THROTTLE_HEIGHT);
		
		(new Thread(thread)).start();
	}
	
	private void drawIpPrompt()
	{
		panel.add(ipLabel);
		panel.add(ipField);
		panel.add(buttonRight);
		
		set(ipLabel,IP_X,IPLABEL_Y,IP_WIDTH,IP_HEIGHT);
		set(ipField,IP_X,IPFIELD_Y,IP_WIDTH,IP_HEIGHT);
		set(buttonRight,BUTTONRIGHT_X,BUTTONBOT_Y,BUTTON_WIDTH,BUTTONRIGHT_HEIGHT);
	}
	
	private ActionListener buttonListener = new ActionListener(){

		@Override
		public void actionPerformed(ActionEvent e) {
			String buttonLabel = e.getActionCommand();
			
			if(buttonLabel.equals(buttonQuit.getText()))
			{
				System.exit(0);
			}
			else if(buttonLabel.equals(buttonLeftTop.getText()))
			{
				server = true;
				drawMainPage();			
			}
			else if(buttonLabel.equals(buttonLeftBot.getText()))
			{
				server = false;
				drawIpPrompt();
			}
			else if(buttonLabel.equals(buttonRight.getText()))
			{
				server = false;
				drawMainPage();
			}
		}
	};
	
	MouseAdapter mouseAdaptor = new java.awt.event.MouseAdapter() {
	    public void mouseEntered(java.awt.event.MouseEvent evt) {
	    	JButton button = ((JButton)evt.getSource());
	        if(button.getText().equals("X")) button.setForeground(BUTTON_BG_HOVER);
	        else button.setBackground(BUTTON_BG_HOVER);
	    }

	    public void mouseExited(java.awt.event.MouseEvent evt) {
	    	JButton button = ((JButton)evt.getSource());
	        if(button.getText().equals("X")) button.setForeground(BUTTON_BG);
	        else button.setBackground(BUTTON_BG);
	    }
	};
	
	private void clearPanel()
	{
		panel.removeAll();
		panel.repaint();
		//panel.add(buttonQuit);
		//set(buttonQuit, 15, 0, 1, 1);
	}

	private void set(JComponent comp,int x,int y, int w, int h)
	{
		int factor = FRAME_WIDTH/NUM_COLS;
		
		comp.setBounds(x*factor, y*factor, w*factor, h*factor);
	}
	
}
