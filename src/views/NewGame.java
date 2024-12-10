package views;

import model.PieceType;
import model.Pieces;
import model.Tiles;
import model.question;

import javax.swing.*;

import controler.Board;
import controler.Controller;
import controler.Mouse;
import controler.Move;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.time.format.DateTimeFormatter;

public class NewGame extends JComponent {

	final private int COL = 8;
	final private int ROW = 8;
	private static JFrame frame;
	//  private static JFrame frame2;
	private JMenuBar menuBar;
	private JMenu menu; // Menu
	private JMenuItem help;
	private JMenuItem resign; // Resign option
	private JPanel panel;
	private JLabel lblNewLabel_1;
	private JLabel lblNewLabel_2;
	private JLabel lblNewLabel_3;
	private JButton btnNewButton_1;
	private JLabel lblNewLabel_4;
	private JButton btnNewButton;
	public static String score1;
	public static String score2;
	public static JLabel scoreP1;
	public static JLabel scoreP2;
	public static JLabel timeS;
 
	public static String min;
	public static String second;
	public static String minGame;
	public static String secondGame;


	public static  JLabel lblQ;
	public static JRadioButton choice1, choice2, choice3,choice4;
	public static int i = 0;
	public static JPanel panel2;
	public static ButtonGroup group ; 
	private JLabel lblNewLabel_5;
	private JLabel lblNewLabel_6;
	public static JLabel GameTime;


	public NewGame(Board board){
		createComponents();
		addingTiles(board);
		createMenu(board);
		frame.setVisible(true);
	}

	public void createComponents() { // Creation of JComponents
		//    	frame2 = new JFrame();
		//    	frame2.setSize(new Dimension(800,800));
		frame = new JFrame();
		frame.setSize(new Dimension(829, 731));
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		panel = new JPanel((LayoutManager) null);
		panel.setBounds(56, 49, 594, 549);
		frame.getContentPane().add(panel);
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage(NewGame.class.getResource("/pic/zebra.png")));
		panel.setLayout(new GridLayout(8, 8));

	}

	public void addingTiles(Board board) { // Creation and storage of Tiles. Tiles extends JPanel
		Mouse m = new Mouse();
		Tiles[][] tile = board.getTile();
		Tiles[][] theBoard = Board.getTheBoard();
		for (int i = 0; i < ROW; i++) {
			for (int j = 0; j < COL; j++) {
				tile[i][j] = new Tiles(i, j, board); // Tile(row, column, Board
				// this);
				theBoard[i][j] = tile[i][j];
				theBoard[i][j].addMouseListener(m);
				panel.add(theBoard[i][j]); // i is row, j is column
			}
		}
	}

	public void createMenu(Board board) {
		menuBar = new JMenuBar();
		menu = new JMenu("File");
		resign = new JMenuItem("Resign");
		help = new JMenuItem("Help");

		menuBar.add(menu);
		menu.add(help);
		menu.add(resign);
		frame.setJMenuBar(menuBar);
		frame.getContentPane().setLayout(null);

		JLabel lblNewLabel = new JLabel("Player Name: ");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel.setBounds(244, 615, 98, 22);
		frame.getContentPane().add(lblNewLabel);

		lblNewLabel_1 = new JLabel("Score:");
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1.setBounds(241, 640, 80, 22);
		frame.getContentPane().add(lblNewLabel_1);

		lblNewLabel_2 = new JLabel("Player Name:");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_2.setForeground(Color.WHITE);
		lblNewLabel_2.setBounds(221, 3, 128, 22);
		frame.getContentPane().add(lblNewLabel_2);

		lblNewLabel_3 = new JLabel("Score:");
		lblNewLabel_3.setForeground(Color.WHITE);
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_3.setBounds(221, 25, 100, 22);
		frame.getContentPane().add(lblNewLabel_3);

		btnNewButton_1 = new JButton("Save Game");
		btnNewButton_1.setBackground(new Color(139, 69, 19));
		btnNewButton_1.setForeground(new Color(255, 255, 255));
		btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Controller.saveGame();
			}
		});
		btnNewButton_1.setBounds(654, 321, 109, 40);
		frame.getContentPane().add(btnNewButton_1);

		lblNewLabel_4 = new JLabel("Turn Time:");
		lblNewLabel_4.setForeground(Color.WHITE);
		lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_4.setBounds(654, 394, 98, 38);
		frame.getContentPane().add(lblNewLabel_4);

		btnNewButton = new JButton("");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int result = JOptionPane.showConfirmDialog(frame,"Sure? You want to go back?", "Warrning",
						JOptionPane.YES_NO_OPTION,
						JOptionPane.QUESTION_MESSAGE);
				if(result == JOptionPane.YES_OPTION){
					HomePageForAll f = new HomePageForAll();
					f.HomePageForAll.setVisible(true);
					frame.dispose();  

					Board.setScoreRedPlayer(0);
					Board.setScoreBlackPlayer(0);
					Board.setTurnCounter(0);
					if(Board.timer!= null)
						Board.timer.stop();
					Board.setSecond(0);
					ViewQuestionInGame.setCounterCurrectAnswerRed(0);
					ViewQuestionInGame.setCounterCurectAnswerBlack(0);
					ViewQuestionInGame.setCounterQuestionBlack(0);
					ViewQuestionInGame.setCounterQuestionRed(0);
					Move.blueFlag = false;
					Move.redFlag = false;
					Move.flagSeq = false;
					Move.flagSeq=false;
					NewGame.scoreP1.setText(String.valueOf(Board.getScoreRedPlayer()));			
					NewGame.scoreP2.setText(String.valueOf(Board.getScoreBlackPlayer()));			

				}else if (result == JOptionPane.NO_OPTION){
					return;
				}
			}
		});
		btnNewButton.setBounds(22, 4, 61, 35);
		btnNewButton.setIcon(new ImageIcon(NewGame.class.getResource("/pic/back.jpg")));
		frame.getContentPane().add(btnNewButton);
		JLabel nickName11 = new JLabel();
		nickName11.setFont(new Font("Tahoma", Font.PLAIN, 14));
		nickName11.setForeground(Color.WHITE);
		nickName11.setText(MainScrean.getNickName1());
		nickName11.setBounds(357, 3, 164, 22);
		frame.getContentPane().add(nickName11);
		score1 = String.valueOf(Board.getScoreRedPlayer());
		scoreP1 = new JLabel(score1);
		scoreP1.setFont(new Font("Tahoma", Font.BOLD, 14));
		scoreP1.setForeground(Color.WHITE);
		scoreP1.setBounds(331, 31, 67, 14);
		frame.getContentPane().add(scoreP1);

		JLabel nickName22 = new JLabel();
		nickName22.setForeground(Color.WHITE);
		nickName22.setFont(new Font("Tahoma", Font.BOLD, 14));
		nickName22.setText(MainScrean.getNickName2());
		nickName22.setBounds(357, 615, 89, 22);
		frame.getContentPane().add(nickName22);
		score2 = String.valueOf(Board.getScoreBlackPlayer());
		scoreP2 = new JLabel(score2);
		scoreP2.setForeground(Color.WHITE);
		scoreP2.setFont(new Font("Tahoma", Font.BOLD, 14));
		scoreP2.setBounds(357, 640, 80, 22);
		frame.getContentPane().add(scoreP2);
		if(Board.getSecond()/60<10 &&Board.getSecond()%60<10 ) {
			min = String.valueOf("0"+Board.getSecond()/60);
			second = String.valueOf("0"+Board.getSecond()%60);	
		}else if(Board.getSecond()/60>=10 &&Board.getSecond()%60>=10 ) {
			min = String.valueOf(Board.getSecond()/60);
			second = String.valueOf(Board.getSecond()%60);	
		}else if(Board.getSecond()/60>=10 &&Board.getSecond()%60<10 ) {
			min = String.valueOf(Board.getSecond()/60);
			second = String.valueOf("0"+Board.getSecond()%60);	
		}else if(Board.getSecond()/60<10 &&Board.getSecond()%60>=10 ) {
			min = String.valueOf("0"+Board.getSecond()/60);
			second = String.valueOf(Board.getSecond()%60);	
		}
		
		timeS = new JLabel(min + ":" + second);
		timeS.setForeground(Color.WHITE);
		timeS.setFont(new Font("Tahoma", Font.BOLD, 14));
		timeS.setBounds(755, 402, 58, 22);
		frame.getContentPane().add(timeS);
		
		lblNewLabel_6 = new JLabel("Game Time");
		lblNewLabel_6.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_6.setForeground(Color.WHITE);
		lblNewLabel_6.setBounds(654, 443, 98, 38);
		frame.getContentPane().add(lblNewLabel_6);
		if(Board.getGameSecond()/60<10 &&Board.getGameSecond()%60<10 ) {
			minGame = String.valueOf("0"+Board.getGameSecond()/60);
			secondGame = String.valueOf("0"+Board.getGameSecond()%60);	
		}else if(Board.getGameSecond()/60>=10 &&Board.getGameSecond()%60>=10 ) {
			minGame = String.valueOf(Board.getGameSecond()/60);
			secondGame = String.valueOf(Board.getGameSecond()%60);	
		}else if(Board.getGameSecond()/60>=10 &&Board.getGameSecond()%60<10 ) {
			minGame = String.valueOf(Board.getGameSecond()/60);
			secondGame = String.valueOf("0"+Board.getGameSecond()%60);	
		}else if(Board.getGameSecond()/60<10 &&Board.getGameSecond()%60>=10 ) {
			minGame = String.valueOf("0"+Board.getGameSecond()/60);
			secondGame = String.valueOf(Board.getGameSecond()%60);	
		}
		GameTime = new JLabel(minGame+ ":" + secondGame);
		GameTime.setForeground(Color.WHITE);
		GameTime.setFont(new Font("Tahoma", Font.BOLD, 14));
		GameTime.setBounds(755, 443, 58, 26);
		frame.getContentPane().add(GameTime);
		
		lblNewLabel_5 = new JLabel();
		lblNewLabel_5.setBounds(0, 0, 823, 680);
		lblNewLabel_5.setIcon(new ImageIcon(NewGame.class.getResource("/pic/newGame.jpg")));
		frame.getContentPane().add(lblNewLabel_5);
		
		



		//        panel = new JPanel((LayoutManager) null);
		//
		//        panel.setLayout(new GridLayout(ROW, COL));


		help.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				try {
					Instructions2  rule = new Instructions2();
					rule.frmInstructions2.setVisible(true);

				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}		
			}

		});
		resign.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (Board.getTurnCounter() % 2 == 1) {
					//RED.lost();
					Board.setLoser(MainScrean.getNickName1());
					HomePageForAll home = new HomePageForAll();
					
					Board.timer.stop();
					Board.setScoreRedPlayer(0);
					Board.setScoreBlackPlayer(0);
					Board.setTurnCounter(0);
					Board.setSecond(0);
					ViewQuestionInGame.setCounterCurrectAnswerRed(0);
					ViewQuestionInGame.setCounterCurectAnswerBlack(0);
					ViewQuestionInGame.setCounterQuestionBlack(0);
					ViewQuestionInGame.setCounterQuestionRed(0);
					Move.blueFlag = false;
					Move.redFlag = false;
					Move.flagSeq = false;
					Move.flagSeq=false;
					timeS.setText(String.valueOf(Board.getSecond()));
					scoreP1.setText(String.valueOf(Board.getScoreRedPlayer()));			
					scoreP2.setText(String.valueOf(Board.getScoreBlackPlayer()));
                    home.HomePageForAll.setVisible(true);
                    frame.dispose();

				} else {
					//BLACK.lost();
					Board.setLoser(MainScrean.getNickName2());
					HomePageForAll home = new HomePageForAll();
					Board.timer.stop();
					Board.setScoreRedPlayer(0);
					Board.setScoreBlackPlayer(0);
					Board.setTurnCounter(0);
					Board.setSecond(0);
					ViewQuestionInGame.setCounterCurrectAnswerRed(0);
					ViewQuestionInGame.setCounterCurectAnswerBlack(0);
					ViewQuestionInGame.setCounterQuestionBlack(0);
					ViewQuestionInGame.setCounterQuestionRed(0);
					Move.blueFlag = false;
					Move.redFlag = false;
					Move.flagSeq = false;
					Move.flagSeq=false;
					timeS.setText(String.valueOf(Board.getSecond()));
					scoreP1.setText(String.valueOf(Board.getScoreRedPlayer()));			
					scoreP2.setText(String.valueOf(Board.getScoreBlackPlayer()));
                    home.HomePageForAll.setVisible(true);
                    frame.dispose();

				}
				JOptionPane.showMessageDialog(frame, board.getLoser()
						+ "-side player resigned! Good game.");
				frame.dispose();
			}
		});


		lblQ = new JLabel("");
		panel2 = new JPanel(); 
		choice1 = new JRadioButton();
		choice2 = new JRadioButton();
		choice3 = new JRadioButton();
		choice4 = new JRadioButton();
		group = new ButtonGroup();
	}

	public static String getScore1() {
		return score1;
	}

	public static void setScore1(String score1) {
		NewGame.score1 = score1;
	}

	public static String getScore2() {
		return score2;
	}

	public static void setScore2(String score2) {
		NewGame.score2 = score2;
	}
	public static void displayDialog(String loser) { /// Dialog Box for who win 
		JOptionPane.showMessageDialog(frame, loser
				+ "- lost! Well played!");
		HomePageForAll home = new HomePageForAll();
        home.HomePageForAll.setVisible(true);
        frame.dispose();

	}
	public static void displayDialogForExpInAddPiece() { // Dialog Box For Adding Piece in a Worng Place 
		JOptionPane.showMessageDialog(frame,"You add the Piece in a WRONG Place !!!! \n Please Enter it in another Place !!!! ");
	}





	// view question


	public static void viewQuest() {
		ViewQuestionInGame v = new ViewQuestionInGame();
		v.frameViewQuestionInGame.setVisible(true);
	}
}
