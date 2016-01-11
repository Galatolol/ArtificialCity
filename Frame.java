import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Insets;
import java.awt.MediaTracker;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.ScrollPaneConstants;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.GridLayout;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.Box;
import javax.swing.ImageIcon;
import java.awt.Component;
import javax.swing.JTextField;

public class Frame {

	private JFrame frame;
	ContentPanel contentPanel ;
	JScrollPane scrollpane;
	public static JLabel timerValue;
	private boolean stopped;
	private JTextField ludzieVal;
	private JTextField godzinaVal;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Frame window = new Frame();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Frame() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(0, 0, 1140, 730);
		frame.setMaximumSize(new Dimension(1050, 1050));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//contentPanel = new ContentPanel();
		//contentPanel.setPreferredSize(new Dimension(1000, 1000));
		
		scrollpane = new JScrollPane(contentPanel);
		scrollpane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollpane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollpane.setAutoscrolls(true);
		frame.getContentPane().add(scrollpane, BorderLayout.CENTER);
		
		JPanel controlPanel = new JPanel();
		frame.getContentPane().add(controlPanel, BorderLayout.EAST);
		controlPanel.setLayout(new BorderLayout(20, 20));
		
		JPanel buttonsPanel = new JPanel();
		controlPanel.add(buttonsPanel, BorderLayout.NORTH);
		buttonsPanel.setPreferredSize(new Dimension(100, 400));
		
		Component rigidArea_3 = Box.createRigidArea(new Dimension(18, 15));
		buttonsPanel.add(rigidArea_3);
		
		JLabel timeLabel = new JLabel("Time: ");
		buttonsPanel.add(timeLabel);
		
		
		JButton fasterBtn = new JButton("Faster");
		fasterBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// tutaj pisac co sie ma dziac
				if (contentPanel.tm.getDelay() >= 100) {
					contentPanel.tm.setDelay(contentPanel.tm.getDelay() - 100);
				}
			}
		});
		
		Component rigidArea_4 = Box.createRigidArea(new Dimension(15, 15));
		buttonsPanel.add(rigidArea_4);
		
		Component rigidArea_5 = Box.createRigidArea(new Dimension(15, 15));
		buttonsPanel.add(rigidArea_5);
		
		timerValue = new JLabel("0");
		buttonsPanel.add(timerValue);
		
		Component rigidArea_1 = Box.createRigidArea(new Dimension(15, 15));
		buttonsPanel.add(rigidArea_1);
		
		Component rigidArea_2 = Box.createRigidArea(new Dimension(15, 15));
		buttonsPanel.add(rigidArea_2);
		fasterBtn.setPreferredSize(new Dimension(90, 30));
		buttonsPanel.add(fasterBtn);
		
		JButton slowerBtn = new JButton("Slower");
		slowerBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (contentPanel.tm.getDelay() <= 900) {
					contentPanel.tm.setDelay(contentPanel.tm.getDelay() + 100);
				}
			}
		});
		slowerBtn.setPreferredSize(new Dimension(90, 30));
		buttonsPanel.add(slowerBtn);
		buttonsPanel.add(Box.createRigidArea(new Dimension(15,15)));
		
		JButton stopBtn = new JButton("\u25B6  ||");
		stopBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (stopped) {
					contentPanel.tm.restart();
				}
				else {
					contentPanel.tm.stop();
				}
				stopped = !stopped;
			}
		});
		stopBtn.setPreferredSize(new Dimension(90, 30));
		buttonsPanel.add(stopBtn);
		
		Component rigidArea = Box.createRigidArea(new Dimension(15, 15));
		buttonsPanel.add(rigidArea);
		
		Component rigidArea_6 = Box.createRigidArea(new Dimension(15, 15));
		buttonsPanel.add(rigidArea_6);
		
		JLabel liczbaLudzi = new JLabel("Population");
		buttonsPanel.add(liczbaLudzi);
		
		ludzieVal = new JTextField();
		ludzieVal.setText("10000");
		buttonsPanel.add(ludzieVal);
		ludzieVal.setPreferredSize(new Dimension(90, 20));
		
		Component rigidArea_7 = Box.createRigidArea(new Dimension(15, 15));
		buttonsPanel.add(rigidArea_7);
		
		Component rigidArea_8 = Box.createRigidArea(new Dimension(15, 15));
		buttonsPanel.add(rigidArea_8);
		
		Component rigidArea_9 = Box.createRigidArea(new Dimension(15, 15));
		buttonsPanel.add(rigidArea_9);
		
		Component rigidArea_10 = Box.createRigidArea(new Dimension(15, 15));
		buttonsPanel.add(rigidArea_10);
		
		JLabel godzina = new JLabel("Set hour");
		buttonsPanel.add(godzina);
		
		godzinaVal = new JTextField();
		godzinaVal.setText("12");
		buttonsPanel.add(godzinaVal);
		godzinaVal.setPreferredSize(new Dimension(90, 20));
		
		JButton restartBtn = new JButton("Start");
		restartBtn.setPreferredSize(new Dimension(90, 40));
		restartBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int l = Integer.valueOf(ludzieVal.getText());
				int g = Integer.valueOf(godzinaVal.getText());
				
				frame.remove(scrollpane);
				//contentPanel.re
				//scrollpane.remove(contentPanel);
				contentPanel = new ContentPanel(l, g);
				contentPanel.setPreferredSize(new Dimension(1000, 1000));
				
				scrollpane = new JScrollPane(contentPanel);
				scrollpane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
				scrollpane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
				scrollpane.setAutoscrolls(true);
				frame.getContentPane().add(scrollpane, BorderLayout.CENTER);
				
			}
		});
		
		Component rigidArea_11 = Box.createRigidArea(new Dimension(15, 15));
		buttonsPanel.add(rigidArea_11);
		buttonsPanel.add(restartBtn);
		
		
		//frame.pack();
	}
}


