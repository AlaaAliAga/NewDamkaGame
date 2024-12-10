package views;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JComboBox;
import javax.swing.JLabel;

import controler.Board;
import controler.Controller;
import model.PieceType;
import model.Pieces;
import model.Tiles;
import model.question;

import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class LoadGAME {

	public JFrame framLoadGAME;


	/**
	 * Create the application.
	 */
	public LoadGAME() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	@SuppressWarnings("unchecked")
	private void initialize() {
		framLoadGAME = new JFrame();
		framLoadGAME.getContentPane().setBackground(Color.WHITE);
		framLoadGAME.setBounds(100, 100, 684, 514);
		framLoadGAME.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		framLoadGAME.getContentPane().setLayout(null);
		framLoadGAME.setIconImage(Toolkit.getDefaultToolkit().getImage(LoadGAME.class.getResource("/pic/zebra.png")));

		@SuppressWarnings("rawtypes")
		JComboBox comboBox_SavedGames = new JComboBox();
		comboBox_SavedGames.setBackground(Color.WHITE);
		comboBox_SavedGames.setBounds(114, 80, 411, 47);
		for( String t : Controller.getLoadGames()) {
		//	System.out.println(t+"hhhhhhhhhhh" );

			comboBox_SavedGames.addItem(t);}
		

		
		
		JLabel lbloadGame = new JLabel("Load Game");
		lbloadGame.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 25));
		lbloadGame.setBounds(223, 10, 175, 60);
		framLoadGAME.getContentPane().add(lbloadGame);
		
		
		String G = Controller.getSpecificBoard((String)comboBox_SavedGames.getSelectedItem());
		System.out.println(G);
		framLoadGAME.getContentPane().add(comboBox_SavedGames);

		
		JButton btnLoad = new JButton("Load");
		btnLoad.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		btnLoad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String s = comboBox_SavedGames.getSelectedItem().toString();
			    String[] str = new String[33];
			    str = s.split(",");
			    Board b = new Board();
			    Tiles [][] theBoard = b.getTheBoardF();
			    for(int i = 0 ; i<8 ; i++) {
			    	for(int j=0 ; j<8;j++) {
			    		if((i+j)%2==1) {
			    			theBoard[i][j].setPiece(null);
			    		}
			    	}
			    }
			    String s1 = str[32];
			    String r = "R";
			    String b1 = "B";
			    if(s1.equals(b1)) {
	 		    	b.setTurnCounter(0);}
				else if(s1.equals(r)) {
	 		    	b.setTurnCounter(1);}
			    
			    
			    int i1 = 0 ; 
				for(int i = 0 ; i<8 ; i++) {
					for(int j =0; j<8 ; j ++) {
						if((i+j)%2==1) {
							if(str[i1].equals("2")) {
								Pieces p = new Pieces(i, j, PieceType.BLACK); // (row,
								theBoard[i][j].addPiece(p);
								i1++;
							}else if(str[i1].equals("1")) {
							    Pieces p = new Pieces(i, j, PieceType.RED); // (row,
								theBoard[i][j].addPiece(p);
								i1++;
							}else if(str[i1].equals("22")) {
								Pieces p = new Pieces(i, j, PieceType.BLACK_KING); // (row,
								theBoard[i][j].addPiece(p);
								i1++;
							}else if(str[i1].equals("11")) {
								Pieces p = new Pieces(i, j, PieceType.RED_KING); // (row,
								theBoard[i][j].addPiece(p);
								i1++;
							}else if(str[i1].equals("0")) {
								i1++;
							}
						}
					}
				} 

				
				framLoadGAME.dispose();


				
			}
		});
		btnLoad.setBounds(261, 424, 91, 31);
		framLoadGAME.getContentPane().add(btnLoad);
	
	
		JButton btnNewButton = new JButton("");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				HomePageForAll f = new HomePageForAll();
				f.HomePageForAll.setVisible(true);
				framLoadGAME.dispose();
			}
		});
		btnNewButton.setBounds(10, 10, 67, 36);
		btnNewButton.setIcon(new ImageIcon(LoadGAME.class.getResource("/pic/back.jpg")));
		framLoadGAME.getContentPane().add(btnNewButton);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(150, 80, 300, 395);
		lblNewLabel.setIcon(new ImageIcon(LoadGAME.class.getResource("/pic/zebra2.jpg")));
		framLoadGAME.getContentPane().add(lblNewLabel);

	
	}
}
