package views;

import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import controler.Controller;

import model.question;
import javax.swing.JTextField;
import java.awt.SystemColor;

public class DeleteQuestions {

	public JFrame removeframe;
	private JTextField textCurrentAnswer;
	private JTextField textLevel;
	private JTextField textTeam;
	private JTextField answer11;
	private JTextField answer22;
	private JTextField answer33;
	private JTextField answer44;


	/**
	 * Create the application.
	 */
	public DeleteQuestions() {
		initialize();
	}


	@SuppressWarnings("unchecked")
	public void initialize() {
		removeframe = new JFrame();
		removeframe.setBackground(Color.WHITE);
		removeframe.setIconImage(Toolkit.getDefaultToolkit().getImage(AddQuestions.class.getResource("/pic/write.png")));
		removeframe.setTitle("deleteQuestion");
//		removeframe.setSize(735, 539);
		removeframe.setResizable(false);
		removeframe.getContentPane().setBackground(Color.WHITE);
		removeframe.getContentPane().setLayout(null);

		JLabel lblQuestion = new JLabel("Question :");
		lblQuestion.setForeground(Color.WHITE);
		lblQuestion.setFont(new Font("Segoe Print", Font.BOLD | Font.ITALIC, 17));
		lblQuestion.setBounds(28, 37, 103, 28);
		removeframe.getContentPane().add(lblQuestion);

		JLabel lblAnswer = new JLabel("Answer 1 : ");
		lblAnswer.setForeground(Color.WHITE);
		lblAnswer.setFont(new Font("Segoe Print", Font.BOLD | Font.ITALIC, 17));
		lblAnswer.setBounds(28, 93, 122, 21);
		removeframe.getContentPane().add(lblAnswer);

		JLabel lblAnswer_1 = new JLabel("Answer 2 :");
		lblAnswer_1.setForeground(Color.WHITE);
		lblAnswer_1.setFont(new Font("Segoe Print", Font.BOLD | Font.ITALIC, 17));
		lblAnswer_1.setBounds(28, 145, 103, 21);
		removeframe.getContentPane().add(lblAnswer_1);

		JLabel lblAnswer_2 = new JLabel("Answer 3 :");
		lblAnswer_2.setForeground(Color.WHITE);
		lblAnswer_2.setFont(new Font("Segoe Print", Font.BOLD | Font.ITALIC, 17));
		lblAnswer_2.setBounds(28, 204, 103, 21);
		removeframe.getContentPane().add(lblAnswer_2);

		JLabel lblAnswer_3 = new JLabel("Answer 4 :");
		lblAnswer_3.setForeground(Color.WHITE);
		lblAnswer_3.setFont(new Font("Segoe Print", Font.BOLD | Font.ITALIC, 17));
		lblAnswer_3.setBounds(28, 256, 103, 21);
		removeframe.getContentPane().add(lblAnswer_3);

		JLabel lblCurrectAnswer = new JLabel("Currect answer :");
		lblCurrectAnswer.setForeground(Color.WHITE);
		lblCurrectAnswer.setFont(new Font("Segoe Print", Font.BOLD | Font.ITALIC, 17));
		lblCurrectAnswer.setBounds(28, 312, 147, 21);
		removeframe.getContentPane().add(lblCurrectAnswer);

		JLabel labelll = new JLabel("Difficulty level :");
		labelll.setForeground(Color.WHITE);
		labelll.setFont(new Font("Segoe Print", Font.BOLD | Font.ITALIC, 17));
		labelll.setBounds(28, 357, 147, 21);
		removeframe.getContentPane().add(labelll);


		removeframe.setBounds(100, 100, 735, 531);
		removeframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);



		JButton button = new JButton("");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				QuestionPart1 Q = new QuestionPart1();
				Q.Qframe.setVisible(true);
				removeframe.dispose();
			}
		});
		button.setIcon(new ImageIcon(AddQuestions.class.getResource("/pic/back.jpg")));
		button.setBounds(31, 442, 56, 29);
		removeframe.getContentPane().add(button);

		@SuppressWarnings("rawtypes")
		JComboBox quesCombo = new JComboBox();
		quesCombo.setBackground(Color.WHITE);
		quesCombo.setBounds(143, 41, 422, 28);
		//Controller.getInstance();
		ArrayList<question> array = new ArrayList<>();
		array.addAll(Controller.getQuestionss());
		array.addAll(Controller.getRemovedQuestion());
		for(question q : array) {
			System.out.println(q+"hhhhhhhhhhh" );
			quesCombo.addItem(q.getQuestion1());}
		

		textCurrentAnswer = new JTextField();
		textCurrentAnswer.setToolTipText("CurrectAnswer");
		textCurrentAnswer.setBounds(185, 311, 109, 28);
		removeframe.getContentPane().add(textCurrentAnswer);
		textCurrentAnswer.setColumns(10);
		
		textLevel = new JTextField();
		textLevel.setToolTipText("Level");
		textLevel.setColumns(10);
		textLevel.setBounds(185, 350, 109, 28);
		removeframe.getContentPane().add(textLevel);
		
		textTeam = new JTextField();
		textTeam.setToolTipText("Team");
		textTeam.setColumns(10);
		textTeam.setBounds(185, 391, 109, 28);
		removeframe.getContentPane().add(textTeam);
		
		
		answer11 = new JTextField();
		answer11.setColumns(10);
		answer11.setBounds(134, 84, 431, 36);
		removeframe.getContentPane().add(answer11);
		
		answer22 = new JTextField();
		answer22.setColumns(10);
		answer22.setBounds(134, 145, 431, 36);
		removeframe.getContentPane().add(answer22);
		
		answer33 = new JTextField();
		answer33.setColumns(10);
		answer33.setBounds(134, 204, 431, 36);
		removeframe.getContentPane().add(answer33);
		
		answer44 = new JTextField();
		answer44.setColumns(10);
		answer44.setBounds(134, 260, 431, 36);
		removeframe.getContentPane().add(answer44);

		
		question question = Controller.getSpecificQuestion((String)quesCombo.getSelectedItem());
		System.out.println(question);
		answer11.setText(question.getAnswers().get(0));
		answer22.setText(question.getAnswers().get(1));
		answer33.setText(question.getAnswers().get(2));
		answer44.setText(question.getAnswers().get(3));
		textCurrentAnswer.setText(question.getCorrectAns());
		textLevel.setText(question.getLevel());
		textTeam.setText(question.getTeam());
		
		removeframe.getContentPane().add(quesCombo);
		
		JLabel lblTeam = new JLabel("Team :");
		lblTeam.setForeground(Color.WHITE);
		lblTeam.setFont(new Font("Segoe Print", Font.BOLD | Font.ITALIC, 17));
		lblTeam.setBounds(55, 392, 147, 21);
		removeframe.getContentPane().add(lblTeam);
		
		JLabel ans4_1 = new JLabel();
		ans4_1.setText((String) null);
		ans4_1.setFont(new Font("Segoe Print", Font.PLAIN, 16));
		ans4_1.setBounds(278, 297, 422, 36);
		removeframe.getContentPane().add(ans4_1);
		

		quesCombo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				question question = Controller.getSpecificQuestion(quesCombo.getSelectedItem().toString());
				answer11.setText(question.getAnswers().get(0));
				answer22.setText(question.getAnswers().get(1));
				answer33.setText(question.getAnswers().get(2));
				answer44.setText(question.getAnswers().get(3));
				textCurrentAnswer.setText(question.getCorrectAns());
				textLevel.setText(question.getLevel());
				textTeam.setText(question.getTeam());
				

			}

		});


		JButton btnremove = new JButton("Remove Question");
		btnremove.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String question = quesCombo.getSelectedItem().toString();
				String answer1 = answer11.getText();
				String answer2 = answer22.getText();
				String answer3 = answer33.getText();
				String answer4 = answer44.getText();
				String curreectAnsw = textCurrentAnswer.getText();
				String level = textLevel.getText();
				String team = textTeam.getText();				

				question q = new question();

				ArrayList<String> arr = new ArrayList<String>();
				arr.add(answer1);
				arr.add(answer2);
				arr.add(answer3);
				arr.add(answer4);

				q.setAnswers(arr);
				q = new question(question,arr,curreectAnsw,level,team);
//				removeData
//				RemoveQuestion
				
				
				if(Controller.RemoveQuestion(q)) {
					quesCombo.removeItem(quesCombo.getSelectedItem());
					JOptionPane.showMessageDialog(null, "The question removed successfully");
					QuestionPart1 QQQ = new QuestionPart1();
					QQQ.Qframe.setVisible(true);
					removeframe.dispose();
					return;
				}

	      	 
				JOptionPane.showMessageDialog(null, "Failed to remove the question");	
				QuestionPart1 QQQ = new QuestionPart1();
				QQQ.Qframe.setVisible(true);
				removeframe.dispose();
				return;
				
			}
		});
		btnremove.setBackground(Color.WHITE);
		btnremove.setFont(new Font("Segoe Print", Font.PLAIN, 16));
		btnremove.setBounds(288, 442, 185, 42);
		removeframe.getContentPane().add(btnremove);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(0, 0, 721, 494);
		lblNewLabel.setIcon(new ImageIcon(DeleteQuestions.class.getResource("/pic/newGame.jpg")));
		removeframe.getContentPane().add(lblNewLabel);
		
	
	}
}