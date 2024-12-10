package views;

import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;

import controler.Controller;
import model.Player;

import javax.swing.JButton;

public class MainScrean {
	private static final long serialVersionUID = 1L;

	public static JFrame frame;
	private static JTextField txtNickname1;
	private static JTextField txtNickname2;
	public static String nickName1;
	public static String getNickName1() {
		return nickName1;
	}

	public static void setNickName1(String nickName1) {
		MainScrean.nickName1 = nickName1;
	}

	public static String getNickName2() {
		return nickName2;
	}

	public static void setNickName2(String nickName2) {
		MainScrean.nickName2 = nickName2;
	}

	public static String nickName2;




	/**
	 * Create the application.
	 */
	public MainScrean() {
		initialize();
	}
	public static void mainLog(){
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private static void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(Color.WHITE);
		frame.setBounds(100, 100, 850, 545);
		frame.setResizable(false);
		frame.setVisible(true);
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage(MainScrean.class.getResource("/pic/zebra.png")));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setForeground(new Color(165, 42, 42));
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		//lblNewLabel.setBackground(Color.WHITE);
		lblNewLabel.setBounds(0, 0, 826, 508);
		lblNewLabel.setIcon(new ImageIcon(MainScrean.class.getResource("/pic/marry11.jpg")));
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Welcom To Hamka Game");
		lblNewLabel_1.setForeground(new Color(165, 42, 42));
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 20));
		lblNewLabel_1.setBounds(266, 82, 347, 59);
		frame.getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("First Player :");
		lblNewLabel_2.setForeground(new Color(165, 42, 42));
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 17));
		lblNewLabel_2.setBounds(266, 223, 152, 45);
		frame.getContentPane().add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Secound Player :");
		lblNewLabel_3.setForeground(new Color(165, 42, 42));
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 17));
		lblNewLabel_3.setBounds(266, 324, 152, 45);
		frame.getContentPane().add(lblNewLabel_3);
		
		txtNickname1 = new JTextField();
		txtNickname1.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 15));
		txtNickname1.setToolTipText("nickName");
		txtNickname1.setBounds(471, 226, 152, 45);
		frame.getContentPane().add(txtNickname1);
		txtNickname1.setColumns(10);
		
		txtNickname2 = new JTextField();
		txtNickname2.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 15));
		txtNickname2.setToolTipText("nickName");
		txtNickname2.setColumns(10);
		txtNickname2.setBounds(471, 324, 152, 45);
		frame.getContentPane().add(txtNickname2);
		
		JButton btnNewButton = new JButton("Let's Go");
		btnNewButton.setForeground(new Color(255, 255, 255));
		btnNewButton.setBackground(new Color(165, 42, 42));
		btnNewButton.addActionListener(new ActionListener() {
			//boolean flag =false;
			public void actionPerformed(ActionEvent e) {
     
                if(txtNickname1.getText().equals("") || txtNickname2.getText().equals("")) {
					JOptionPane.showMessageDialog(frame, " User NickName1 or User NickName2 Is Empty, Please Try again");
                        }
               
                else {
                	  Player p1 = new Player(txtNickname1.getText());
                      Player p2 = new Player(txtNickname2.getText());
                      setNickName1( txtNickname2.getText()); 
                      setNickName2(txtNickname1.getText()); 
//                      if(!Controller.getInstance().checkIfPlayerExist(p1)) {
//                    	  Controller.getInstance().AddPlayer(p1);
//  					}
//                      if(!Controller.getInstance().checkIfPlayerExist(p2)) {
//                    	  Controller.getInstance().AddPlayer(p2);
//  					}
                      
                	HomePageForAll   HomePageForPlayer= new HomePageForAll();
					HomePageForPlayer.HomePageForAll.setVisible(true);
					frame.dispose();
                }

				
			}
		});
		btnNewButton.setBounds(381, 427, 131, 45);
		frame.getContentPane().add(btnNewButton);
		
		
	}

	public static JTextField getTxtNickname1() {
		return txtNickname1;
	}

	public static void setTxtNickname1(JTextField txtNickname1) {
		MainScrean.txtNickname1 = txtNickname1;
	}

	public static JTextField getTxtNickname2() {
		return txtNickname2;
	}

	public static void setTxtNickname2(JTextField txtNickname2) {
		MainScrean.txtNickname2 = txtNickname2;
	}
}
