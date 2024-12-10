package views;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Image;
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


public class Instructions implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public  JFrame frmInstructions;
	
	public Instructions() throws IOException {
		initialize();
	}
	
	private void initialize() throws IOException {
		frmInstructions = new JFrame();
		frmInstructions.getContentPane().setBackground(Color.WHITE);
		frmInstructions.setResizable(false);
		frmInstructions.setIconImage(Toolkit.getDefaultToolkit().getImage(Instructions.class.getResource("/pic/instructionss.png")));
		frmInstructions.setBounds(80, 80, 880, 614);
		frmInstructions.setExtendedState(Frame.MAXIMIZED_HORIZ);
		frmInstructions.setLocationRelativeTo(null);
		frmInstructions.getContentPane().setLayout(null);
//		
//		
		
//		JFrame Instructions = new JFrame();
//		Instructions.setResizable(false);
//		Instructions.setIconImage(Toolkit.getDefaultToolkit().getImage(Instructions.class.getResource("/pic/instructionss.png")));
//		Instructions.setTitle("Instructions");
//		frmInstructions = Instructions;
//		Instructions.getContentPane().setLayout(null);
		
//		JLabel lblRules = new JLabel();
//		String result = Controller.GetAllInstructions();
//		lblRules.setText(result);
//		lblRules.setToolTipText("<dynamic>");
//		lblRules.setBounds(44, 115, 827, 306);
//		frmInstructions.getContentPane().add(lblRules);
//		
//		JLabel lblInstructions_1 = new JLabel("Instructions");
//		lblInstructions_1.setFont(new Font("Axure Handwriting", Font.PLAIN, 33));
//		lblInstructions_1.setBounds(276, 11, 177, 64);
//		frmInstructions.getContentPane().add(lblInstructions_1);
//		
//		JButton btnBack = new JButton("");
//		btnBack.setBackground(Color.WHITE);
//		btnBack.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent arg0) {
//				HomePageForAll f = new HomePageForAll();
//				f.HomePageForAll.setVisible(true);
//				frmInstructions.dispose();	
//				
//			}
//		});
//		btnBack.setBounds(44, 620, 107, 45);
//		frmInstructions.getContentPane().add(btnBack);
		
		JButton btnBack = new JButton("");
		btnBack.setBackground(Color.WHITE);
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				HomePageForAll f = new HomePageForAll();
				f.HomePageForAll.setVisible(true);
				frmInstructions.dispose();	
				
			}
		});
		btnBack.setIcon(new ImageIcon(Instructions.class.getResource("/pic/back.jpg")));
		btnBack.setBounds(21, 542, 61, 37);
		frmInstructions.getContentPane().add(btnBack);
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(Instructions.class.getResource("/pic/zebra2.jpg")));
		label.setBounds(601, 247, 263, 332);
		frmInstructions.getContentPane().add(label);
		
		JTextArea txtrIfThe = new JTextArea();
		txtrIfThe.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 15));
		txtrIfThe.setText("* If the opportunity to eat a piece is present, the player must eat the piece.\r\n\r\n* "
				+ "Normal pieces that reach the other side of the board from their side \r\nare crowned Queen.\r\n\r\n "
				+ "* Queens may move diagonally forwards and backwards , \r\nand for long distance .\r\n\r\n"
				+ "* Queens can get out from the wall and enter from the other side.\r\n\r\n"
				+"* Brown Queen is for Black Player & DarkGray Queen is For White Player. \n\n "
				+ "* The first move is made by the black player side. \r\n\r\n* There are other additions that will  achieve later during the game.\r\n\r\n                           Good luck and have fun! \r\n");
		txtrIfThe.setBounds(21, 241, 588, 354);
		frmInstructions.getContentPane().add(txtrIfThe);
		
		JTextArea txtrHamkaGameRules = new JTextArea();
		txtrHamkaGameRules.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 15));
		txtrHamkaGameRules.setText("                                                                Hamka  Game  Rules\r\n\r\n* Hamka is a board game designed to be played by two players. \r\nThe objective is to \"eat\" all the pieces of the other side. \r\n\r\n* This game is played only on the darker tiles of the board. Normal pieces may only move diagonally forward \r\none space at a time, if a same-side piece is present, they are not able to move.\r\n\r\n* Pieces may only eat other-side pieces if there is another piece diagonal to them, \r\nand the tile behind that piece is open.");
		txtrHamkaGameRules.setBounds(10, 10, 846, 221);
		frmInstructions.getContentPane().add(txtrHamkaGameRules);

//		JButton btnNewButton = new JButton("New button");
//		btnNewButton.setIcon(new ImageIcon(Instructions.class.getResource("/pic/zebra2.jpg")));
//		btnNewButton.setBounds(560, 310, 250, 207);
//		frmInstructions.getContentPane().add(btnNewButton);
//	
//		
	}

	private static void setIconImage(Image image) {
		// TODO Auto-generated method stub
		
	}
}
