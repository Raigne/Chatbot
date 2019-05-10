package iSKOBOT;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.UIManager.LookAndFeelInfo;

public class FrontPage{
	
	private JFrame frame;
    private JButton jButton1;
    private	JButton jButton2;
    private JButton jButton3;
    private JLabel jLabel1;
    private JLabel jLabel2;
    private JLabel jLabel3;
    private JLabel jLabel4;
    private JPanel jPanel1;
		
	public static void main(String[] args) {
		try {
            for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(FrontPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(FrontPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(FrontPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (UnsupportedLookAndFeelException ex) {
            Logger.getLogger(FrontPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrontPage window = new FrontPage();
					window.frame.setVisible(true);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public FrontPage() {
		initialize();
		frame.setSize(517,530);
	}
	
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	jPanel1 = new JPanel();
        jLabel4 = new JLabel();
        jButton1 = new JButton();
        jButton2 = new JButton();
        jButton2.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent arg0) {
        		InfoInterface info = new InfoInterface();
        		info.Info();
        		frame.dispose();
        	}
        });
        jButton3 = new JButton();
        jButton3.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		frame.dispose();
        	}
        });
        jPanel1.setLayout(null);
        frame.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/StartDefault.png"))); // NOI18N
        jButton1.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent evt) {
                jButton1MouseEntered(evt);
            }
            public void mouseExited(MouseEvent evt) {
                jButton1MouseExited(evt);
            }
            public void mousePressed(MouseEvent evt) {
                jButton1MousePressed(evt);
            }
            public void mouseReleased(MouseEvent evt) {
                jButton1MouseReleased(evt);
            }
        });
        jButton1.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				ChatRoom chat = new ChatRoom();
				chat.NewScreen();
				frame.dispose();
			}
        });
        jLabel1 = new JLabel();
        frame.getContentPane().add(jLabel1);
        jLabel1.setBounds(40, 400, 80, 40);
        jLabel1.getAccessibleContext().setAccessibleName("jLabel1");
        frame.getContentPane().add(jButton1);
        jButton1.setBounds(40, 400, 80, 40);
        jButton1.getAccessibleContext().setAccessibleName("jLabel1");
        jLabel2 = new JLabel();
        frame.getContentPane().add(jLabel2);
        jLabel2.setBounds(210, 400, 80, 40);
        jLabel2.getAccessibleContext().setAccessibleName("jLabel2");
        jButton2.setIcon(new ImageIcon(getClass().getResource("/InfoDefault.png"))); // NOI18N
        jButton2.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent evt) {
                jButton2MouseEntered(evt);
            }
            public void mouseExited(MouseEvent evt) {
                jButton2MouseExited(evt);
            }
            public void mousePressed(MouseEvent evt) {
                jButton2MousePressed(evt);
            }
            public void mouseReleased(MouseEvent evt) {
                jButton2MouseReleased(evt);
            }
        });
        frame.getContentPane().add(jButton2);
        jButton2.setBounds(210, 400, 80, 40);
        jLabel3 = new JLabel();
        frame.getContentPane().add(jLabel3);
        jLabel3.setBounds(380, 400, 80, 40);
        jLabel3.getAccessibleContext().setAccessibleName("jLabel3");
        jButton3.setIcon(new ImageIcon(getClass().getResource("/ExitDefault.png"))); // NOI18N
        jButton3.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent evt) {
                jButton3MouseEntered(evt);
            }
            public void mouseExited(MouseEvent evt) {
                jButton3MouseExited(evt);
            }
            public void mousePressed(MouseEvent evt) {
                jButton3MousePressed(evt);
            }
            public void mouseReleased(MouseEvent evt) {
                jButton3MouseReleased(evt);
            }
        });
        frame.getContentPane().add(jButton3);
        jButton3.setBounds(380, 400, 80, 40);
        jLabel4.setIcon(new ImageIcon(getClass().getResource("/BG.png"))); // NOI18N
        frame.getContentPane().add(jLabel4);
        jLabel4.setBounds(0, 0, 500, 490);
        jLabel4.getAccessibleContext().setAccessibleName("jLabel4");
    }
    private void jButton1MousePressed(MouseEvent evt) {//GEN-FIRST:event_jButton1MousePressed
        ImageIcon II = new ImageIcon(getClass().getResource("/StartPressed.png"));
        jLabel1.setIcon(II);
    }
    private void jButton1MouseReleased(MouseEvent evt) {//GEN-FIRST:event_jButton1MouseReleased
         ImageIcon II = new ImageIcon(getClass().getResource("/StartDefault.png"));
         jLabel1.setIcon(II);
    }
    private void jButton1MouseEntered(MouseEvent evt) {//GEN-FIRST:event_jButton1MouseEntered
         ImageIcon II = new ImageIcon(getClass().getResource("/StartHover.png"));
         jLabel1.setIcon(II);
    }
    private void jButton1MouseExited(MouseEvent evt) {//GEN-FIRST:event_jButton1MouseExited
         ImageIcon II = new ImageIcon(getClass().getResource("/StartDefault.png"));
         jLabel1.setIcon(II);
    }
   
	private void jButton2MousePressed(MouseEvent evt) {//GEN-FIRST:event_jButton1MousePressed
        ImageIcon II = new ImageIcon(getClass().getResource("/InfoPressed.png"));
        jLabel2.setIcon(II);
    }
    private void jButton2MouseReleased(MouseEvent evt) {//GEN-FIRST:event_jButton1MouseReleased
         ImageIcon II = new ImageIcon(getClass().getResource("/InfoDefault.png"));
         jLabel2.setIcon(II);
    }
    private void jButton2MouseEntered(MouseEvent evt) {//GEN-FIRST:event_jButton1MouseEntered
         ImageIcon II = new ImageIcon(getClass().getResource("/InfoHover.png"));
         jLabel2.setIcon(II);
    }
    private void jButton2MouseExited(MouseEvent evt) {//GEN-FIRST:event_jButton1MouseExited
         ImageIcon II = new ImageIcon(getClass().getResource("/InfoDefault.png"));
         jLabel2.setIcon(II);
    }
    private void jButton3MousePressed(MouseEvent evt) {//GEN-FIRST:event_jButton1MousePressed
        ImageIcon II = new ImageIcon(getClass().getResource("/ExitPressed.png"));
        jLabel3.setIcon(II);
    }
    private void jButton3MouseReleased(MouseEvent evt) {//GEN-FIRST:event_jButton1MouseReleased
         ImageIcon II = new ImageIcon(getClass().getResource("/ExitDefault.png"));
         jLabel3.setIcon(II);
    }
    private void jButton3MouseEntered(MouseEvent evt) {//GEN-FIRST:event_jButton1MouseEntered
         ImageIcon II = new ImageIcon(getClass().getResource("/ExitHover.png"));
         jLabel3.setIcon(II);
    }
    private void jButton3MouseExited(MouseEvent evt) {//GEN-FIRST:event_jButton1MouseExited
         ImageIcon II = new ImageIcon(getClass().getResource("/ExitDefault.png"));
         jLabel3.setIcon(II);
    }
    
}