package views;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.Window.Type;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import model.Player;
import javax.swing.SwingConstants;

import controler.Board;

public class HomePageForAll {

	public JFrame HomePageForAll;
	public JFrame frame;
	Object c ;
    Player player;


	/**
	 * Create the application.
	 */
	public HomePageForAll() {
		this.c=c;
		if(c instanceof Player){
			player=(Player) c;
		}
		
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		HomePageForAll = new JFrame();
		HomePageForAll.getContentPane().setBackground(new Color(255, 255, 255));
		HomePageForAll.setTitle("Home Page");
		HomePageForAll.setBounds(100, 100, 657, 504);
		HomePageForAll.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		HomePageForAll.getContentPane().setLayout(null);
		HomePageForAll.setIconImage(Toolkit.getDefaultToolkit().getImage(HomePageForAll.class.getResource("/pic/zebra.png")));

		

		JLabel lblNewLabel = new JLabel("WELCOME TO HAMKA GAME");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 20));
		lblNewLabel.setBounds(182, 57, 343, 60);
		HomePageForAll.getContentPane().add(lblNewLabel); 
		
		
		JButton btnNewButton = new JButton("START GAME");
		btnNewButton.setBackground(new Color(0, 128, 0));
		btnNewButton.setFont(new Font("Tahoma", Font.ITALIC, 14));
		btnNewButton.setForeground(Color.BLACK);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//////------------------------------------//////////
				Board.board();
				HomePageForAll.dispose();

			}
		});
		btnNewButton.setBounds(243, 147, 166, 37);
		HomePageForAll.getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("LOAD GAME");
		btnNewButton_1.setBackground(new Color(0, 100, 0));
		btnNewButton_1.setFont(new Font("Tahoma", Font.ITALIC, 14));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				///////////-------------------------------////
				LoadGAME loadGame = new LoadGAME();
				loadGame.framLoadGAME.setVisible(true);
				HomePageForAll.dispose();
				
			}
		});
		btnNewButton_1.setForeground(Color.BLACK);
		btnNewButton_1.setBounds(243, 194, 166, 37);
		HomePageForAll.getContentPane().add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("GAME HISTORY");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				///////////-------------------------------////
				GameHistory playerHistory = new GameHistory();
				playerHistory.gameHistoryframe.setVisible(true);
				HomePageForAll.dispose();
			}
		});
		btnNewButton_2.setBackground(new Color(0, 100, 0));
		btnNewButton_2.setFont(new Font("Tahoma", Font.ITALIC, 14));
		btnNewButton_2.setForeground(Color.BLACK);
		btnNewButton_2.setBounds(243, 239, 166, 37);
		HomePageForAll.getContentPane().add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("MANAGE QUeSTION");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//-------------------------------------------/////////
				QuestionPart1 question = new QuestionPart1();
				question.Qframe.setVisible(true);
				HomePageForAll.dispose();
			}
		});
		btnNewButton_3.setBackground(new Color(0, 128, 0));
		btnNewButton_3.setFont(new Font("Tahoma", Font.ITALIC, 14));
		btnNewButton_3.setForeground(Color.BLACK);
		btnNewButton_3.setBounds(243, 286, 166, 37);
		HomePageForAll.getContentPane().add(btnNewButton_3);
		
		
		JButton btnNewButton_4 = new JButton("GAME RULES");
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//-------------------------------------------/////////
				try {
					Instructions  rule = new Instructions();
					rule.frmInstructions.setVisible(true);
					HomePageForAll.dispose();

				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnNewButton_4.setForeground(Color.BLACK);
		btnNewButton_4.setFont(new Font("Tahoma", Font.ITALIC, 14));
		btnNewButton_4.setBackground(new Color(0, 100, 0));
		btnNewButton_4.setBounds(243, 333, 166, 37);
		HomePageForAll.getContentPane().add(btnNewButton_4);
		
		JButton btnLogOut = new JButton("LogOut");
		btnLogOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MainScrean log = new MainScrean();
				log.frame.setVisible(true);
				HomePageForAll.dispose();
			}
		});
		btnLogOut.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		btnLogOut.setBounds(10, 394, 106, 30);
		HomePageForAll.getContentPane().add(btnLogOut);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setIcon(new ImageIcon(HomePageForAll.class.getResource("/pic/hhhhhappy.jpg")));
		lblNewLabel_1.setBounds(-301, -56, 1004, 828);
		HomePageForAll.getContentPane().add(lblNewLabel_1);
		
	
	}
}
