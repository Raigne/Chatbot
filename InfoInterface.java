package iSKOBOT;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class InfoInterface {

	private JFrame frame;
	private JLabel lblImage;
	private JTextArea taInfo;
	private JScrollPane scroll;
	private JPanel panel;
	private JButton btnBack;
	private JButton btnQuit;

	public static void Info() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InfoInterface window = new InfoInterface();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public InfoInterface() {
		initialize();
	}

	private void initialize() {
	
		//Frame
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		//Panel
		panel = new JPanel();
		panel.setLayout(new FlowLayout());
		panel.setBackground(new Color(21,173,228));
		
		//Label
		lblImage = new JLabel("");
		Image img = new ImageIcon(this.getClass().getResource("/FontMoto.jpg")).getImage();
		lblImage.setIcon(new ImageIcon(img));	
		
		//Text Area and Scroll
		taInfo = new JTextArea("This is an offline chatbot that specially made for the students of PUP. The name \niskobot codmes from two words; isko- for Iskolar because students of PUP are\ncommonly knowed as the 'Iskolar ng Bayan' and the bot in short for ChatBot.\nThe main reason of this chatbot is to inform the students about the different\nplaces in PUP. It can be an aid for the new students of PUP especially those\nstudents who are freshmen. This chatbot can give trivias and facts about the\nplaces they are looking for. The chatbot is user friendly, understandable and \neasy to access.\n\n\n\n\t\t\tThis chatbot is created by: \n\n\t\t\tJan Lenard Cruz \n\t\t\tPrince Cruz \n\t\t\tDerrick James Gaban\n\t\t\tJuan Miguel Pascual\n\t\t\tMaridel Ranises\n\t\t\tJomar Vedad", 20,41);
		scroll = new JScrollPane(taInfo, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		taInfo.setFont(new Font("Century Gothic", Font.PLAIN, 16));
		taInfo.setEditable(false);
		//Button Quit
		btnQuit = new JButton("Quit");
		btnQuit.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		frame.dispose();
        	}
        });
		
		//Button Back
		btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		FrontPage front = new FrontPage();
        		front.main(null);
        		frame.dispose();
        	}
        });
		
		//Adding to Panel
		panel.add(lblImage);
		panel.add(scroll);
		panel.add(btnBack);
		panel.add(btnQuit);
		
		//Adding to Frame
		frame.add(panel);
		frame.setResizable(false);
		frame.setSize(700,600);

	}
}