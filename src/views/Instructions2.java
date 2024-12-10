package views;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.Serializable;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import controler.Controller;
import javax.swing.JTextPane;
import javax.swing.JTextArea;


public class Instructions2 implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public  JFrame frmInstructions2;
	
	public Instructions2() throws IOException {
		initialize();
	}
	
	private void initialize() throws IOException {
		frmInstructions2 = new JFrame();
		frmInstructions2.getContentPane().setBackground(Color.WHITE);
		frmInstructions2.setResizable(false);
		frmInstructions2.setIconImage(Toolkit.getDefaultToolkit().getImage(Instructions2.class.getResource("/pic/instructionss.png")));
		frmInstructions2.setBounds(80, 80, 880, 626);
		frmInstructions2.setExtendedState(Frame.MAXIMIZED_HORIZ);
		frmInstructions2.setLocationRelativeTo(null);
		frmInstructions2.getContentPane().setLayout(null);
//		
//		
		
//		JFrame Instructions = new JFrame();
//		Instructions.setResizable(false);
//		Instructions.setIconImage(Toolkit.getDefaultToolkit().getImage(Instructions.class.getResource("/pic/instructionss.png")));
//		Instructions.setTitle("Instructions");
//		frmInstructions2 = Instructions;
//		Instructions.getContentPane().setLayout(null);
		
//		JLabel lblRules = new JLabel();
//		String result = Controller.GetAllInstructions();
//		lblRules.setText(result);
//		lblRules.setToolTipText("<dynamic>");
//		lblRules.setBounds(44, 115, 827, 306);
//		frmInstructions2.getContentPane().add(lblRules);
//		
//		JLabel lblInstructions_1 = new JLabel("Instructions");
//		lblInstructions_1.setFont(new Font("Axure Handwriting", Font.PLAIN, 33));
//		lblInstructions_1.setBounds(276, 11, 177, 64);
//		frmInstructions2.getContentPane().add(lblInstructions_1);
//		
//		JButton btnBack = new JButton("");
//		btnBack.setBackground(Color.WHITE);
//		btnBack.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent arg0) {
//				HomePageForAll f = new HomePageForAll();
//				f.HomePageForAll.setVisible(true);
//				frmInstructions2.dispose();	
//				
//			}
//		});
//		btnBack.setBounds(44, 620, 107, 45);
//		frmInstructions2.getContentPane().add(btnBack);
		
		JButton btnBack = new JButton("");
		btnBack.setBackground(Color.WHITE);
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				frmInstructions2.dispose();	
				
			}
		});
		btnBack.setIcon(new ImageIcon(Instructions2.class.getResource("/pic/back.jpg")));
		btnBack.setBounds(10, 549, 61, 37);
		frmInstructions2.getContentPane().add(btnBack);
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(Instructions2.class.getResource("/pic/zebra2.jpg")));
		label.setBounds(601, 254, 263, 332);
		frmInstructions2.getContentPane().add(label);
		
		JTextArea txtrIfThe = new JTextArea();
		txtrIfThe.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 15));
		txtrIfThe.setText("* If the opportunity to eat a piece is present, the player must eat the piece.\r\n\r\n* "
				+ "Normal pieces that reach the other side of the board from their side \r\nare crowned Queen.\r\n\r\n "
				+ "* Queens may move diagonally forwards and backwards , \r\nand for long distance .\r\n\r\n"
				+ "* Queens can get out from the wall and enter from the other side.\r\n\r\n"
				+"* Brown Queen is for Black Player & DarkGray Queen is For White Player. \n\n "
				+ "* The first move is made by the black player side. \r\n\r\n* There are other additions that will  achieve later during the game.\r\n\r\n                           Good luck and have fun! \r\n");
		txtrIfThe.setBounds(21, 241, 588, 345);
		frmInstructions2.getContentPane().add(txtrIfThe);
		
		JTextArea txtrHamkaGameRules = new JTextArea();
		txtrHamkaGameRules.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 15));
		txtrHamkaGameRules.setText("                                                                Hamka  Game  Rules\r\n\r\n* Hamka is a board game designed to be played by two players. \r\nThe objective is to \"eat\" all the pieces of the other side. \r\n\r\n* This game is played only on the darker tiles of the board. Normal pieces may only move diagonally forward \r\none space at a time, if a same-side piece is present, they are not able to move.\r\n\r\n* Pieces may only eat other-side pieces if there is another piece diagonal to them, \r\nand the tile behind that piece is open.");
		txtrHamkaGameRules.setBounds(10, 10, 846, 221);
		frmInstructions2.getContentPane().add(txtrHamkaGameRules);

//		JButton btnNewButton = new JButton("New button");
//		btnNewButton.setIcon(new ImageIcon(Instructions.class.getResource("/pic/zebra2.jpg")));
//		btnNewButton.setBounds(560, 310, 250, 207);
//		frmInstructions2.getContentPane().add(btnNewButton);
//	
//		
	}
}
