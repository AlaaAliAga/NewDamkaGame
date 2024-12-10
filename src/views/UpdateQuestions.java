package views;

import javax.swing.JFrame;



import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JTextField;

import controler.Controller;
import model.question;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;

public class UpdateQuestions{

	public JFrame updateframe;
	private JTextField ans1;
	private JTextField ans2;
	private JTextField ans3;
	private JTextField ans4;
	private JTextField textCurrect_Answ;
	private JTextField textTeamName;
	private JTextField textLevel;
	private JTextField questionn;

	/**
	 * Create the application.
	 */
	public UpdateQuestions() {
		initialize();
	}

	/**
	 * Create the frame.
	 */
	@SuppressWarnings("unchecked")
	public void initialize() {
		updateframe = new JFrame();
		updateframe.setBackground(Color.WHITE);
	 	updateframe.setIconImage(Toolkit.getDefaultToolkit().getImage(AddQuestions.class.getResource("/pic/write.png")));
		updateframe.setTitle("addQuestion");
		updateframe.setResizable(false);
		updateframe.getContentPane().setBackground(Color.WHITE);
		updateframe.getContentPane().setLayout(null);

		JLabel lblQuestion = new JLabel("Question :");
		lblQuestion.setForeground(Color.WHITE);
		lblQuestion.setFont(new Font("Segoe Print", Font.BOLD | Font.ITALIC, 17));
		lblQuestion.setBounds(28, 37, 103, 28);
		updateframe.getContentPane().add(lblQuestion);

		JLabel lblAnswer = new JLabel("Answer 1 : ");
		lblAnswer.setForeground(Color.WHITE);
		lblAnswer.setFont(new Font("Segoe Print", Font.BOLD | Font.ITALIC, 17));
		lblAnswer.setBounds(28, 160, 122, 21);
		updateframe.getContentPane().add(lblAnswer);

		JLabel lblAnswer_1 = new JLabel("Answer 2 :");
		lblAnswer_1.setForeground(Color.WHITE);
		lblAnswer_1.setFont(new Font("Segoe Print", Font.BOLD | Font.ITALIC, 17));
		lblAnswer_1.setBounds(28, 212, 103, 21);
		updateframe.getContentPane().add(lblAnswer_1);

		JLabel lblAnswer_2 = new JLabel("Answer 3 :");
		lblAnswer_2.setForeground(Color.WHITE);
		lblAnswer_2.setFont(new Font("Segoe Print", Font.BOLD | Font.ITALIC, 17));
		lblAnswer_2.setBounds(28, 271, 103, 21);
		updateframe.getContentPane().add(lblAnswer_2);

		JLabel lblAnswer_3 = new JLabel("Answer 4 :");
		lblAnswer_3.setForeground(Color.WHITE);
		lblAnswer_3.setFont(new Font("Segoe Print", Font.BOLD | Font.ITALIC, 17));
		lblAnswer_3.setBounds(28, 323, 103, 21);
		updateframe.getContentPane().add(lblAnswer_3);

		JLabel lblCurrectAnswer = new JLabel("Currect answer :");
		lblCurrectAnswer.setForeground(Color.WHITE);
		lblCurrectAnswer.setFont(new Font("Segoe Print", Font.BOLD | Font.ITALIC, 17));
		lblCurrectAnswer.setBounds(28, 379, 147, 21);
		updateframe.getContentPane().add(lblCurrectAnswer);

		JLabel lblLevel = new JLabel("Difficulty level :");
		lblLevel.setForeground(Color.WHITE);
		lblLevel.setFont(new Font("Segoe Print", Font.BOLD | Font.ITALIC, 17));
		lblLevel.setBounds(28, 424, 147, 21);
		updateframe.getContentPane().add(lblLevel);

		ans1 = new JTextField();
		ans1.setFont(new Font("Segoe Print", Font.PLAIN, 16));
		ans1.setBounds(143, 152, 422, 36);
		updateframe.getContentPane().add(ans1);
		ans1.setColumns(10);

		ans2 = new JTextField();
		ans2.setFont(new Font("Segoe Print", Font.PLAIN, 16));
		ans2.setBounds(143, 204, 422, 36);
		updateframe.getContentPane().add(ans2);
		ans2.setColumns(10);

		ans3 = new JTextField();
		ans3.setFont(new Font("Segoe Print", Font.PLAIN, 16));
		ans3.setBounds(143, 263, 422, 36);
		updateframe.getContentPane().add(ans3);
		ans3.setColumns(10);

		ans4 = new JTextField();
		ans4.setFont(new Font("Segoe Print", Font.PLAIN, 16));
		ans4.setBounds(143, 315, 422, 36);
		updateframe.getContentPane().add(ans4);
		ans4.setColumns(10);

		updateframe.setBounds(100, 100, 735, 603);
		updateframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		
		textCurrect_Answ = new JTextField();
		textCurrect_Answ.setToolTipText("CurrectAnswer");
		textCurrect_Answ.setBounds(173, 378, 165, 28);
		updateframe.getContentPane().add(textCurrect_Answ);
		textCurrect_Answ.setColumns(10);
		
		textTeamName = new JTextField();
		textTeamName.setToolTipText("TeamName");
		textTeamName.setColumns(10);
		textTeamName.setBounds(173, 454, 165, 28);
		updateframe.getContentPane().add(textTeamName);
		
		textLevel = new JTextField();
		textLevel.setToolTipText("level");
		textLevel.setColumns(10);
		textLevel.setBounds(173, 417, 165, 28);
		updateframe.getContentPane().add(textLevel);

		
		JButton button = new JButton("");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				QuestionPart1 Q = new QuestionPart1();
				Q.Qframe.setVisible(true);
				updateframe.dispose();
			}
		});
		button.setIcon(new ImageIcon(AddQuestions.class.getResource("/pic/back.jpg")));
		button.setBounds(28, 527, 56, 29);
		updateframe.getContentPane().add(button);
		

		JButton btnAdd = new JButton("Update Question");
		btnAdd.setBackground(Color.WHITE);
		btnAdd.setFont(new Font("Segoe Print", Font.PLAIN, 16));


		btnAdd.setBounds(293, 514, 185, 42);
		updateframe.getContentPane().add(btnAdd);

	
////// go back
//		JButton button = new JButton("");
//		button.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent arg0) {
//
//				QuestionPart1 Q = new QuestionPart1();
//				Q.Qframe.setVisible(true);
//				updateframe.dispose();
//			}
//		});
//		button.setIcon(new ImageIcon(AddQuestions.class.getResource("/pic/back.jpg")));
//		button.setBounds(10, 581, 56, 29);
//		updateframe.getContentPane().add(button);
//		

		@SuppressWarnings("rawtypes")
		JComboBox quesCombo = new JComboBox();
		quesCombo.setBackground(Color.WHITE);
		quesCombo.setBounds(143, 41, 422, 28);
		//Controller.getInstance();
		ArrayList<question> array = new ArrayList<>();
		array.addAll(Controller.getQuestionss());
		array.addAll(Controller.getRemovedQuestion());
		for(question q : array) {
			quesCombo.addItem(q.getQuestion1());

		}
		questionn = new JTextField();
		questionn.setFont(new Font("Segoe Print", Font.PLAIN, 16));
		questionn.setBackground(Color.WHITE);
		questionn.setBounds(143, 91, 422, 36);
		updateframe.getContentPane().add(questionn);
		questionn.setColumns(10);
		
		question question = Controller.getSpecificQuestion(quesCombo.getSelectedItem().toString());
		questionn.setText(question.getQuestion1());
		ans1.setText(question.getAnswers().get(0));
		ans2.setText(question.getAnswers().get(1));
		ans3.setText(question.getAnswers().get(2));
		ans4.setText(question.getAnswers().get(3));
		textCurrect_Answ.setText(question.getCorrectAns());
		textLevel.setText(question.getLevel());
		textTeamName.setText(question.getTeam());
		

		updateframe.getContentPane().add(quesCombo);
		
		
		
		
		JLabel lblTeam = new JLabel("Team :");
		lblTeam.setForeground(Color.WHITE);
		lblTeam.setToolTipText("TeamName");
		lblTeam.setFont(new Font("Segoe Print", Font.BOLD | Font.ITALIC, 17));
		lblTeam.setBounds(51, 461, 147, 21);
		updateframe.getContentPane().add(lblTeam);
		
		JLabel lblNewLabel_1 = new JLabel("New Question :");
		lblNewLabel_1.setBackground(Color.WHITE);
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setFont(new Font("Segoe Print", Font.BOLD | Font.ITALIC, 16));
		lblNewLabel_1.setBounds(28, 102, 122, 25);
		updateframe.getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(0, 0, 721, 581);
		lblNewLabel.setIcon(new ImageIcon(UpdateQuestions.class.getResource("/pic/newGame.jpg")));
		updateframe.getContentPane().add(lblNewLabel);
		
		
		
		
		quesCombo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//Controller.getInstance();
				question question = Controller.getSpecificQuestion(quesCombo.getSelectedItem().toString());
				questionn.setText(question.getQuestion1());
				ans1.setText(question.getAnswers().get(0));
				ans2.setText(question.getAnswers().get(1));
				ans3.setText(question.getAnswers().get(2));
				ans4.setText(question.getAnswers().get(3));
				textCurrect_Answ.setText(question.getCorrectAns());
				textLevel.setText(question.getLevel());
				textTeamName.setText(question.getTeam());
				
			}

		});


		btnAdd.addActionListener(new ActionListener() {
			@SuppressWarnings({ "static-access", "static-access" })
			public void actionPerformed(ActionEvent arg0) {

								String question = quesCombo.getSelectedItem().toString();
								String questionUpdate = questionn.getText();
								System.out.println("\n"+questionUpdate+"\n");
								String answer1 = ans1.getText();
								String answer2 = ans2.getText();
								String answer3 = ans3.getText();
								String answer4 = ans4.getText();
								String currectAns = textCurrect_Answ.getText();
								String level = textLevel.getText();
								String team = textTeamName.getText();
										
				
								if(question==null || questionUpdate== null|| answer1==null || answer2==null ||answer3==null ||answer4==null
										|| currectAns == null || level == null || team == null) {
									JOptionPane.showMessageDialog(null, "Please enter all the fields");
									return;
								}
								
								question q = new question();
					
								ArrayList<String> arr = new ArrayList<String>();
								arr.add(answer1);
								arr.add(answer2);
								arr.add(answer3);
								arr.add(answer4);
				
								q.setAnswers(arr);
								

								if(Controller.updateQuestion(question,questionUpdate,arr,currectAns,level,team)) {
									JOptionPane.showMessageDialog(null, "The question updated successfully");
									QuestionPart1 QQQ = new QuestionPart1();
									QQQ.Qframe.setVisible(true);
									updateframe.dispose();
									return;
								}
									
					      	 
								JOptionPane.showMessageDialog(null, "Failed to update the question");
								QuestionPart1 QQQ = new QuestionPart1();
								QQQ.Qframe.setVisible(true);
								updateframe.dispose();
								return;
							}
		}); 
		

	}

}