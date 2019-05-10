package iSKOBOT;

import java.util.ArrayList;
import java.util.regex.*;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import java.sql.*;

public class ChatRoom implements KeyListener, ActionListener{

	private JFrame frame;
	private JTextArea taMessageArea = null;
	private JScrollPane scroll;
	private JTextField tfSendArea = null;
	private JLabel label;
	private JButton btnExit;
	private JButton btnBack;
	
	private static Connection cncDatabase;
	private final static String USER = new String("root");
	private final static String PASS = new String("12345");
	private final static String URL = new String("jdbc:mysql://localhost/ChatBotDB");
	private String strHolder;
	private ArrayList<String> strWord = new ArrayList<String>();
	private ArrayList<String> strNoun = new ArrayList<String>();
	private ArrayList<String> strVerb = new ArrayList<String>();
	private ArrayList<String> strPrep = new ArrayList<String>();
	private ArrayList<String> strRes = new ArrayList<String>();
	private ArrayList<String> strRes2 = new ArrayList<String>();
	private ArrayList<Double> dblValue = new ArrayList<Double>();
	private ArrayList<Double> dblValue2 = new ArrayList<Double>();
	static String noun = null;
	static String verb = null;
	static String adjective = null;
	static String prep = null;
	static String response = null;
	static String response2 = null;
	static String strRaku = null;
	static Double dblTempVal = 0.0;
	static Double dblTempVal2 = 0.0;
	private Pattern pattern = Pattern.compile("\\w+");
	String strWhere = "where";
	String strWhat = "What";
	String strResponse = null;
	double dblProb1 = 0.1d;
	double dblProb2 = 0.3d;
	double dblProb3 = 0.5d;
	double dblProb4 = 0.7d;
	double dblProb5 = 0.9d;
	double dblMulti1 = 0.0;
	double dblMulti2 = 0.0;
	double dblMulti3 = 0.0;
	double dblMulti4 = 0.0;
	double dblMulti5 = 0.0;
	double dblProduct = 0.0;
	String strInterog = null;
	
	
	public static void conection(){

		/*establish a connection*/
		try{
			
			cncDatabase = DriverManager.getConnection(URL, USER, PASS);
			System.out.println("CONNECTED");
			
		}catch(SQLException objSqlException){
			
			objSqlException.printStackTrace();
		}//catch

	}
	
	public static void NewScreen() {
		conection();
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ChatRoom window = new ChatRoom();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public ChatRoom() {
		initialize();
	}
	
	private void initialize() {
		//Frame initialize
		frame = new JFrame();
		
		//Panel for Message Area
		JPanel panelMessageArea = new JPanel();
		panelMessageArea.setBackground(new Color(21,173,228));		
		
		//Panel for Send Area
		JPanel panelSendArea = new JPanel();
		panelSendArea.setBackground(new Color(21,173,228));
		
		//Message Area
		label = new JLabel("");
		Image img = new ImageIcon(this.getClass().getResource("/TEXT.png")).getImage();
		label.setIcon(new ImageIcon(img));		
		
		taMessageArea = new JTextArea(15,50);
		scroll = new JScrollPane(taMessageArea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		taMessageArea.setEditable(false);
		taMessageArea.setFont(new Font("Century Gothic", Font.PLAIN, 16));
		panelMessageArea.add(label);
		panelMessageArea.add(scroll);

		//Send Area
		tfSendArea = new JTextField(43);
		tfSendArea.setFont(new Font("Century Gothic", Font.PLAIN, 16));
		panelSendArea.add(tfSendArea);
		tfSendArea.addKeyListener(this);
		
		//Button Back
		btnBack = new JButton("Back");
		panelSendArea.add(btnBack);
		btnBack.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		FrameListener fl = new FrameListener();
        		fl.windowClosing(null);
        		FrontPage front = new FrontPage();
        		front.main(null);
        		frame.dispose(); 
        	}
        });
		
		//Button Quit
		btnExit = new JButton("Exit");
		panelSendArea.add(btnExit);
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
        		FrameListener fl = new FrameListener();
        		fl.windowClosing(null);
				frame.dispose();
		    }
		});
		
		frame.add(panelMessageArea);
		frame.add(panelSendArea, "South");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(800, 600);
		frame.setResizable(false);
	}
	public void addText(String strString){
		taMessageArea.setText(taMessageArea.getText()+strString);
	}
	public void replyText(String strString){
		taMessageArea.setText(taMessageArea.getText()+strString);
	}
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_ENTER){
			getData();
		}
	}
	
	public void keyReleased(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_ENTER){
			tfSendArea.setEditable(true);
		}
	}
	
	public void keyTyped(KeyEvent arg0) {
		
	}
	
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}	
	
	public void getData(){
		//Text
		String resNoun = null;
		String resVerb = null;
		String resPrep = null;
		String resRes = null;
		String resRes2 = null;
		Double dblFinal = 0.0;
		strHolder = tfSendArea.getText();
		Matcher matcher = pattern.matcher(strHolder);
		tfSendArea.setEditable(false);
		tfSendArea.setText("");
		addText("\nChitoge: "+strHolder);
		//SQL STATEMENT
		String sql = "SELECT * FROM nountb";
		String sql2 = "SELECT * FROM verbtb";
		String sql3 = "SELECT * FROM prepostb";
		String sql4 = "SELECT * FROM rwheretb";
		String sql5 = "SELECT * FROM rwhattb";
			try{
				//Get Connection
				Connection con = DriverManager.getConnection(URL, USER, PASS);
				//GET PREPARED STMT
				Statement s = con.prepareStatement(sql);
				ResultSet rs = s.executeQuery(sql);
				Statement s2 = con.prepareStatement(sql2);
				ResultSet rs2 = s2.executeQuery(sql2);
				Statement s3 = con.prepareStatement(sql3);
				ResultSet rs3 = s3.executeQuery(sql3);
				Statement s4 = con.prepareStatement(sql4);
				ResultSet rs4 = s4.executeQuery(sql4);
				Statement s5 = con.prepareStatement(sql5);
				ResultSet rs5 = s5.executeQuery(sql5);
				//LOOP THRU GETTING ALL NAMES
				int intCtr=0;
				while(matcher.find()){
					strWord.add(matcher.group());
				}
				while(rs.next()){
					noun = rs.getString(2);
					strNoun.add(noun);
				}
				while(rs2.next()){
					verb = rs2.getString(2);
					strVerb.add(verb);
					}
				while(rs3.next()){
					prep = rs3.getString(2);
					strPrep.add(prep);
				}
				while(rs4.next()){
					response = rs4.getString(2);
					dblTempVal = rs4.getDouble(1);
					strRes.add(response);
					dblValue.add(dblTempVal);					
				}
				while(rs5.next()){
					response2 = rs5.getString(2);
					dblTempVal2 = rs5.getDouble(1);
					strRes2.add(response2);
					dblValue2.add(dblTempVal2);
				}
				
				int intCtr3 =0, intCtr2 = 0,intNoun=0,intVerb=0,intPrep=0,intCtrNoun=0, intCtrVerb=0, intCtrAdjective=0, intRes = 0;
				for(intCtr=0;intCtr<strWord.size();intCtr++){
					if(strWhere.equalsIgnoreCase(strWord.get(intCtr))){
						strInterog = strWhere;
					}
					if(strWhat.equalsIgnoreCase(strWord.get(intCtr))){
						strInterog = strWhat;
					}
						for(intCtr2=0;intCtr2<strWord.size();intCtr2++){
							for(intNoun=0;intNoun<strNoun.size();intNoun++){
								if(strNoun.get(intNoun).equalsIgnoreCase(strWord.get(intCtr2))){
									resNoun = strNoun.get(intNoun);
								}
							}
						}
						for(intCtr2=0;intCtr2<strWord.size();intCtr2++){
							for(intVerb=0;intVerb<strVerb.size();intVerb++){
								if(strVerb.get(intVerb).equalsIgnoreCase(strWord.get(intCtr2))){
									resVerb = strVerb.get(intVerb);
								}
							}				
						}
						for(intCtr2=0;intCtr2<strWord.size();intCtr2++){
							for(intPrep=0;intPrep<strPrep.size();intPrep++){
								if(strPrep.get(intPrep).equalsIgnoreCase(strWord.get(intCtr2))){
									resPrep = strPrep.get(intPrep);
								}
							}				
						}//adjective
						if(resNoun.equalsIgnoreCase("main building")){
							dblMulti1 = dblProb1;
						}
						if(resNoun.equalsIgnoreCase("freedom park")){
							dblMulti1 = dblProb2;
						}
						if(resNoun.equalsIgnoreCase("lagoon")){
							dblMulti1 = dblProb3;
						}
						if(resNoun.equalsIgnoreCase("admission")){
							dblMulti1 = dblProb4;
						}
						if(resNoun.equalsIgnoreCase("Nalrc")){
							dblMulti1 = dblProb5;
						}
						if(resVerb.equalsIgnoreCase("find")){
							dblMulti2 = dblProb1;
						}
						if(resVerb.equalsIgnoreCase("get")){
							dblMulti2 = dblProb2;
						}
						if(resVerb.equalsIgnoreCase("eat")){
							dblMulti2 = dblProb3;
						}
						if(resVerb.equalsIgnoreCase("call")){
							dblMulti2 = dblProb4;
						}
						if(resPrep.equalsIgnoreCase("in")){
							dblMulti3 = dblProb1;
						}
						if(resPrep.equalsIgnoreCase("near")){
							dblMulti3 = dblProb2;
						}
						if(resPrep.equalsIgnoreCase("across")){
							dblMulti3 = 0.6d;
						}
						if(resPrep.equalsIgnoreCase("behind")){
							dblMulti3 = dblProb4;
						}
						if(resPrep.equalsIgnoreCase("front")){
							dblMulti3 = dblProb5;
						}
				}//for top
				if(strInterog.equalsIgnoreCase(strWhere)){
					dblProduct = dblMulti1 * dblMulti2 * dblMulti3;
					for(intCtr2=0;intCtr2<dblValue.size();intCtr2++){
						if(dblProduct >= dblValue.get(intCtr2)){
							strRaku = strRes.get(intCtr2);
						}
					}
					replyText("\nRaku: "+strRaku);
				}
				if(strInterog.equalsIgnoreCase(strWhat)){
					dblProduct = dblMulti1 * dblMulti2 * dblMulti3;
					for(intCtr2=0;intCtr2<dblValue2.size();intCtr2++){
						if(dblProduct >= dblValue2.get(intCtr2)){
							strRaku = strRes2.get(intCtr2);
						}
					}
					replyText("\nRaku: "+strRaku);
				}
					
			}
			catch(SQLException e){
				e.printStackTrace();
			}
	}
	
	public class FrameListener extends WindowAdapter{
		public void windowClosing(WindowEvent objEvent){
			try{	
				
				cncDatabase.close();
				System.out.println("DISCONNECTED");
				
			}catch(SQLException objSqlException){
				
				objSqlException.printStackTrace();
				
			}//catch
		}//windowClosing
	}//FrameListener
}