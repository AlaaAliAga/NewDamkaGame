package views;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import controler.Board;
import controler.Controller;
import controler.Move;
import model.PieceType;
import model.question;

import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;

public class ViewQuestionInGame {

	public JFrame frameViewQuestionInGame;
	private JRadioButton choice0;
	private JRadioButton choice1;
	private JRadioButton choice2;
	private JRadioButton choice3;
	private final int strongTrueAnswer = 500;
	private final int strongFalseAnswer = -50;
	private final int mediumTrueAnswer = 200;
	private final int mediumFalseAnswer = -100;
	private final int easyTrueAnswer = 100;
	private final int easyFalseAnswer = -250;
	public static int counterQuestionRed = 0;
	public static int counterQuestionBlack = 0 ; 
	public static int counterCurectAnswerBlack = 0;
	public static int counterCurrectAnswerRed = 0;
	
	
	




	/**
	 * Create the application.
	 */
	public ViewQuestionInGame() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	
	public void scoreForFalseQuestion(question q) {
		if(Board.getLastPieceMoved().getType() == PieceType.BLACK ||Board.getLastPieceMoved().getType() == PieceType.BLACK_KING ) {
			if(q.getLevel().equals("1")) {
				Board.setScoreBlackPlayer(Board.getScoreBlackPlayer() + easyFalseAnswer);
				NewGame.scoreP2.setText(String.valueOf(Board.getScoreBlackPlayer()));	
				counterQuestionBlack = counterQuestionBlack +1 ;
			}else if(q.getLevel().equals("2")) {
				Board.setScoreBlackPlayer(Board.getScoreBlackPlayer() + mediumFalseAnswer);
				NewGame.scoreP2.setText(String.valueOf(Board.getScoreBlackPlayer()));
				counterQuestionBlack = counterQuestionBlack +2 ;
			}else if(q.getLevel().equals("3")) {
				Board.setScoreBlackPlayer(Board.getScoreBlackPlayer() + strongFalseAnswer);
				NewGame.scoreP2.setText(String.valueOf(Board.getScoreBlackPlayer()));
				counterQuestionBlack = counterQuestionBlack +3 ;
			}
			
		}else {
			if(q.getLevel().equals("1")) {
				Board.setScoreRedPlayer(Board.getScoreRedPlayer() + easyFalseAnswer);
				NewGame.scoreP1.setText(String.valueOf(Board.getScoreRedPlayer()));
				counterQuestionRed = counterQuestionRed+1;
			}else if(q.getLevel().equals("2")) {
				Board.setScoreRedPlayer(Board.getScoreRedPlayer() + mediumFalseAnswer);
				NewGame.scoreP1.setText(String.valueOf(Board.getScoreRedPlayer()));
				counterQuestionRed = counterQuestionRed+2;
			}else if(q.getLevel().equals("3")) {
				Board.setScoreRedPlayer(Board.getScoreRedPlayer() + strongFalseAnswer);
				NewGame.scoreP1.setText(String.valueOf(Board.getScoreRedPlayer()));
				counterQuestionRed = counterQuestionRed+3;
			}
		}
	}

	
	public void scoreForTrueQuestion(question q) {
		if(Board.getLastPieceMoved().getType() == PieceType.BLACK ||Board.getLastPieceMoved().getType() == PieceType.BLACK_KING ) {
			if(q.getLevel().equals("1")) {
				Board.setScoreBlackPlayer(Board.getScoreBlackPlayer() + easyTrueAnswer);
				NewGame.scoreP2.setText(String.valueOf(Board.getScoreBlackPlayer()));
				counterCurectAnswerBlack = counterCurectAnswerBlack+1;
				counterQuestionBlack = counterQuestionBlack +1 ;
			}else if(q.getLevel().equals("2")) {
				Board.setScoreBlackPlayer(Board.getScoreBlackPlayer() + mediumTrueAnswer);
				NewGame.scoreP2.setText(String.valueOf(Board.getScoreBlackPlayer()));
				counterCurectAnswerBlack = counterCurectAnswerBlack+2;
				counterQuestionBlack = counterQuestionBlack +2 ;
			}else if(q.getLevel().equals("3")) {
				Board.setScoreBlackPlayer(Board.getScoreBlackPlayer() + strongTrueAnswer);
				NewGame.scoreP2.setText(String.valueOf(Board.getScoreBlackPlayer()));
				counterCurectAnswerBlack = counterCurectAnswerBlack+3;
				counterQuestionBlack = counterQuestionBlack +3 ;
			}
			
		}else {
			if(q.getLevel().equals("1")) {
				Board.setScoreRedPlayer(Board.getScoreRedPlayer() + easyTrueAnswer);
				NewGame.scoreP1.setText(String.valueOf(Board.getScoreRedPlayer()));	
				counterCurrectAnswerRed = counterCurrectAnswerRed+1;
				counterQuestionRed = counterQuestionRed+1;
			}else if(q.getLevel().equals("2")) {
				Board.setScoreRedPlayer(Board.getScoreRedPlayer() + mediumTrueAnswer);
				NewGame.scoreP1.setText(String.valueOf(Board.getScoreRedPlayer()));
				counterCurrectAnswerRed = counterCurrectAnswerRed+2;
				counterQuestionRed = counterQuestionRed+2;
			}else if(q.getLevel().equals("3")) {
				Board.setScoreRedPlayer(Board.getScoreRedPlayer() + strongTrueAnswer);
				NewGame.scoreP1.setText(String.valueOf(Board.getScoreRedPlayer()));
				counterCurrectAnswerRed = counterCurrectAnswerRed+3;
				counterQuestionRed = counterQuestionRed+3;
			}
		}
	}
	
	private void initialize() {
		frameViewQuestionInGame = new JFrame();
		frameViewQuestionInGame.setBounds(100, 100, 1127, 492);
		frameViewQuestionInGame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frameViewQuestionInGame.setResizable(false);
		frameViewQuestionInGame.setIconImage(Toolkit.getDefaultToolkit().getImage(ViewQuestionInGame.class.getResource("/pic/zebra.png")));
		frameViewQuestionInGame.getContentPane().setBackground(Color.WHITE);
		frameViewQuestionInGame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBounds(196, 113, 894, 264);
		frameViewQuestionInGame.getContentPane().add(panel);
		panel.setLayout(null);
		
		choice0 = new JRadioButton(" ");
		choice0.setHorizontalAlignment(SwingConstants.CENTER);
		choice0.setBounds(6, 18, 882, 46);
		panel.add(choice0);
		
		choice1 = new JRadioButton(" ");
		choice1.setHorizontalAlignment(SwingConstants.CENTER);
		choice1.setBounds(6, 86, 882, 46);
		panel.add(choice1);
		
		choice2 = new JRadioButton(" ");
		choice2.setVisible(false);
		choice2.setHorizontalAlignment(SwingConstants.CENTER);
		choice2.setBounds(6, 148, 882, 46);
		
		panel.add(choice2);
		
		choice3 = new JRadioButton(" ");
		choice3.setVisible(false);
		choice3.setHorizontalAlignment(SwingConstants.CENTER);
		choice3.setBounds(6, 214, 882, 44);
		panel.add(choice3);
		
		 question q =  	Controller.getRandomQuestion();
		    System.out.println(q);
				//if(i!=readSize) {
		    ButtonGroup g = new ButtonGroup();

				   choice0.setText(q.getAnswers().get(0));
				   choice1.setText(q.getAnswers().get(1));
				   choice2.setText(q.getAnswers().get(2));
				   choice3.setText(q.getAnswers().get(3));
				   if(!choice2.getText().equals(""))
					   choice2.setVisible(true);
				   if(!choice3.getText().equals(""))
					   choice3.setVisible(true);
				   g.add(choice0);
				   g.add(choice1);
				   g.add(choice2);
				   g.add(choice3);
//				   
				   
				   
				   JLabel lblNewLabel = new JLabel("");
				   lblNewLabel.setBounds(0, 68, 185, 398);
				   lblNewLabel.setIcon(new ImageIcon(ViewQuestionInGame.class.getResource("/pic/checkAnswer.jpg")));

				   frameViewQuestionInGame.getContentPane().add(lblNewLabel);
				   

				      JButton checkAns = new JButton("Done");
					   checkAns.addActionListener(new ActionListener() {
					   	public void actionPerformed(ActionEvent e) {
						      String answer = q.getCorrectAns();
						      System.out.println(answer);
					   	   if (choice0.isSelected() ){
						   		String s = "0";
						   		if(s.equals(answer)) {
						   			JOptionPane.showMessageDialog(frameViewQuestionInGame,"True answer!");
									choice1.setSelected(false);
									 scoreForTrueQuestion(q);
									frameViewQuestionInGame.dispose();
									if(!Move.flagSeq) {
										Board.switchTurns();
										Controller.checkStatus();
									}
										
						   		}
						   		else {
						   			/// the selected answer in the place 0 is the selected
						   			if(answer.equals("1")) {
						   				JOptionPane.showMessageDialog(frameViewQuestionInGame,"false answer! "+" "+  q.getAnswers().get(1) +" is the correct answer");
						   				scoreForFalseQuestion(q);      
						   				frameViewQuestionInGame.dispose();
						   				if(!Move.flagSeq) {
											Board.switchTurns();
											Controller.checkStatus();
										}
						   			}	if(answer.equals("2")) {
							   			JOptionPane.showMessageDialog(frameViewQuestionInGame,"false answer! "+" "+  q.getAnswers().get(2) +" is the correct answer");
							   			scoreForFalseQuestion(q);      
							   			frameViewQuestionInGame.dispose();
							   			if(!Move.flagSeq) {
											Board.switchTurns();
											Controller.checkStatus();
										}
										   }	
						   			else if(answer.equals("3")) {
						   				JOptionPane.showMessageDialog(frameViewQuestionInGame,"false answer! "+" "+  q.getAnswers().get(3) +" is the correct answer");
						   				scoreForFalseQuestion(q);      
						   				frameViewQuestionInGame.dispose();
						   				if(!Move.flagSeq) {
											Board.switchTurns();
											Controller.checkStatus();
										}
						   			}
						   		}
				            }
						   	 else if (choice1.isSelected()) {
						   		String s = "1";
						   		if(s.equals(answer)) {
						   			JOptionPane.showMessageDialog(frameViewQuestionInGame,"True answer!");
									choice2.setSelected(false);
									scoreForTrueQuestion(q);
									frameViewQuestionInGame.dispose();
									if(!Move.flagSeq) {
										Board.switchTurns();
										Controller.checkStatus();
									}

						   		}
						   		else {
						   			/// the selected answer in the place 1 is the selected
						   			if(answer.equals("0")) {
						   				JOptionPane.showMessageDialog(frameViewQuestionInGame,"false answer! "+" "+  q.getAnswers().get(0) +" is the correct answer");
						   				scoreForFalseQuestion(q);      
						   				frameViewQuestionInGame.dispose();
						   				if(!Move.flagSeq) {
											Board.switchTurns();
											Controller.checkStatus();
										}
						   			}	if(answer.equals("2")) {
							   			JOptionPane.showMessageDialog(frameViewQuestionInGame,"false answer! "+" "+  q.getAnswers().get(2) +" is the correct answer");
							   			scoreForFalseQuestion(q);      
							   			frameViewQuestionInGame.dispose();
							   			if(!Move.flagSeq) {
											Board.switchTurns();
											Controller.checkStatus();
										}
										   }	
						   			else if(answer.equals("3")) {
						   				JOptionPane.showMessageDialog(frameViewQuestionInGame,"false answer! "+" "+  q.getAnswers().get(3) +" is the correct answer");
						   				scoreForFalseQuestion(q);      
						   				frameViewQuestionInGame.dispose();
						   				if(!Move.flagSeq) {
											Board.switchTurns();
											Controller.checkStatus();
										}
						   			}
						   		}

						   	 }
						   	 else if (choice2.isSelected()) {
						   		String s = "2";
						   		if(s.equals(answer)) {
						   			JOptionPane.showMessageDialog(frameViewQuestionInGame,"True answer!");
									choice3.setSelected(false);
									scoreForTrueQuestion(q);
									frameViewQuestionInGame.dispose();
									if(!Move.flagSeq) {
										Board.switchTurns();
										Controller.checkStatus();
									}
						   		}
						 		else {
						   			/// the selected answer in the place 2 is the selected
						   			if(answer.equals("0")) {
						   				JOptionPane.showMessageDialog(frameViewQuestionInGame,"false answer! "+" "+  q.getAnswers().get(0) +" is the correct answer");
						   				scoreForFalseQuestion(q);      
						   				frameViewQuestionInGame.dispose();
						   				if(!Move.flagSeq) {
											Board.switchTurns();
											Controller.checkStatus();
										}
						   			}	if(answer.equals("1")) {
							   			JOptionPane.showMessageDialog(frameViewQuestionInGame,"false answer! "+" "+  q.getAnswers().get(1) +" is the correct answer");
							   			scoreForFalseQuestion(q);      
							   			frameViewQuestionInGame.dispose();
							   			if(!Move.flagSeq) {
											Board.switchTurns();
											Controller.checkStatus();
										}
										   }	
						   			else if(answer.equals("3")) {
						   				JOptionPane.showMessageDialog(frameViewQuestionInGame,"false answer! "+" "+  q.getAnswers().get(3) +" is the correct answer");
						   				scoreForFalseQuestion(q);      
						   				frameViewQuestionInGame.dispose();
						   				if(!Move.flagSeq) {
											Board.switchTurns();
											Controller.checkStatus();
										}
						   			}
						   		}
						   	 }
						   	 else if (choice3.isSelected() ) {
						   		String s = "3";
						   		if(s.equals(answer)) {
						   			JOptionPane.showMessageDialog(frameViewQuestionInGame,"True answer!");
									choice3.setSelected(false);
									scoreForTrueQuestion(q);
									frameViewQuestionInGame.dispose();
									if(!Move.flagSeq) {
										Board.switchTurns();
										Controller.checkStatus();
									}

						   		}
						   		else {
						   			/// the selected answer in the place 3 is the selected
						   			if(answer.equals("0")) {
						   				JOptionPane.showMessageDialog(frameViewQuestionInGame,"false answer! "+" "+  q.getAnswers().get(0) +" is the correct answer");
						   				scoreForFalseQuestion(q);   
						   				frameViewQuestionInGame.dispose();
						   				if(!Move.flagSeq) {
											Board.switchTurns();
											Controller.checkStatus();
										}
						   			}	if(answer.equals("1")) {
							   			JOptionPane.showMessageDialog(frameViewQuestionInGame,"false answer! "+" "+  q.getAnswers().get(1) +" is the correct answer");
							   			scoreForFalseQuestion(q);      
							   			frameViewQuestionInGame.dispose();
							   			if(!Move.flagSeq) {
											Board.switchTurns();
											Controller.checkStatus();
										}
										   }	
						   			else if(answer.equals("2")) {
						   				JOptionPane.showMessageDialog(frameViewQuestionInGame,"false answer! "+" "+  q.getAnswers().get(2) +" is the correct answer");
						   				scoreForFalseQuestion(q);     
						   				frameViewQuestionInGame.dispose();
						   				if(!Move.flagSeq) {
											Board.switchTurns();
											Controller.checkStatus();
										}
						   				}
						   		}
              
						   	 }
						   	 else {
						         	JOptionPane.showMessageDialog(frameViewQuestionInGame,"please select Answer!");
									//frameViewQuestionInGame.dispose();

						   	 }
					   	}
					   });
					   checkAns.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 15));
					   checkAns.setBounds(564, 388, 161, 44);
					   frameViewQuestionInGame.getContentPane().add(checkAns);
					   
					   JLabel lblNewLabel_1 = new JLabel("");
					   lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
					   lblNewLabel_1.setBounds(10, 52, 1111, 62);
					   lblNewLabel_1.setText(q.getQuestion1());
				
					   frameViewQuestionInGame.getContentPane().add(lblNewLabel_1);
					   
					   

						

				  
				
	}
	
	
	public static int getCounterQuestionRed() {
		return counterQuestionRed;
	}

	public static void setCounterQuestionRed(int counterQuestionRed) {
		ViewQuestionInGame.counterQuestionRed = counterQuestionRed;
	}

	public static int getCounterQuestionBlack() {
		return counterQuestionBlack;
	}

	public static void setCounterQuestionBlack(int counterQuestionBlack) {
		ViewQuestionInGame.counterQuestionBlack = counterQuestionBlack;
	}

	public static int getCounterCurectAnswerBlack() {
		return counterCurectAnswerBlack;
	}

	public static void setCounterCurectAnswerBlack(int counterCurectAnswerBlack) {
		ViewQuestionInGame.counterCurectAnswerBlack = counterCurectAnswerBlack;
	}

	public static int getCounterCurrectAnswerRed() {
		return counterCurrectAnswerRed;
	}

	public static void setCounterCurrectAnswerRed(int counterCurrectAnswerRed) {
		ViewQuestionInGame.counterCurrectAnswerRed = counterCurrectAnswerRed;
	}
}
