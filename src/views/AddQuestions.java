package views;



import javax.swing.JFrame;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import controler.Controller;
import model.question;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.Toolkit;

public class AddQuestions  {

	public JFrame Addframe;
	private JTextField queTxt;
	private JTextField ans1;
	private JTextField ans2;
	private JTextField ans3;
	private JTextField ans4;
	private JTextField textLevel;
	private JTextField textCurrectAnswer;
	private JTextField textTeam;


	/**
	 * Create the application.
	 */
	public AddQuestions() {
		initialize();
	}




	/**
	 * Initialize the contents of the frame.
	 */
	public void initialize() {
		
		Addframe = new JFrame();
		Addframe.setBackground(Color.WHITE);
		Addframe.setIconImage(Toolkit.getDefaultToolkit().getImage(AddQuestions.class.getResource("/pic/write.png")));
		Addframe.setTitle("addQuestion");
		Addframe.setResizable(false);
		Addframe.getContentPane().setBackground(Color.WHITE);
		Addframe.getContentPane().setLayout(null);

		JLabel lblQuestion = new JLabel("Question :");
		lblQuestion.setForeground(Color.WHITE);
		lblQuestion.setFont(new Font("Segoe Print", Font.BOLD | Font.ITALIC, 17));
		lblQuestion.setBounds(28, 37, 103, 28);
		Addframe.getContentPane().add(lblQuestion);

		JLabel lblAnswer = new JLabel("Answer 1 : ");
		lblAnswer.setForeground(Color.WHITE);
		lblAnswer.setFont(new Font("Segoe Print", Font.BOLD | Font.ITALIC, 16));
		lblAnswer.setBounds(28, 93, 122, 21);
		Addframe.getContentPane().add(lblAnswer);

		JLabel lblAnswer_1 = new JLabel("Answer 2 :");
		lblAnswer_1.setForeground(Color.WHITE);
		lblAnswer_1.setFont(new Font("Segoe Print", Font.BOLD | Font.ITALIC, 17));
		lblAnswer_1.setBounds(28, 145, 103, 21);
		Addframe.getContentPane().add(lblAnswer_1);

		JLabel lblAnswer_2 = new JLabel("Answer 3 :");
		lblAnswer_2.setForeground(Color.WHITE);
		lblAnswer_2.setFont(new Font("Segoe Print", Font.BOLD | Font.ITALIC, 17));
		lblAnswer_2.setBounds(28, 204, 103, 21);
		Addframe.getContentPane().add(lblAnswer_2);

		JLabel lblAnswer_3 = new JLabel("Answer 4 :");
		lblAnswer_3.setForeground(Color.WHITE);
		lblAnswer_3.setFont(new Font("Segoe Print", Font.BOLD | Font.ITALIC, 17));
		lblAnswer_3.setBounds(28, 256, 103, 21);
		Addframe.getContentPane().add(lblAnswer_3);

		JLabel lblCurrectAnswer = new JLabel("Currect answer :");
		lblCurrectAnswer.setForeground(Color.WHITE);
		lblCurrectAnswer.setFont(new Font("Segoe Print", Font.BOLD | Font.ITALIC, 17));
		lblCurrectAnswer.setBounds(28, 312, 147, 21);
		Addframe.getContentPane().add(lblCurrectAnswer);

		JLabel lblLevel = new JLabel("Difficulty level :");
		lblLevel.setForeground(Color.WHITE);
		lblLevel.setFont(new Font("Segoe Print", Font.BOLD | Font.ITALIC, 17));
		lblLevel.setBounds(28, 347, 147, 21);
		Addframe.getContentPane().add(lblLevel);

		queTxt = new JTextField();
		queTxt.setFont(new Font("Segoe Print", Font.PLAIN, 16));
		queTxt.setBounds(143, 29, 417, 36);
		Addframe.getContentPane().add(queTxt);
		queTxt.setColumns(10);

		ans1 = new JTextField();
		ans1.setFont(new Font("Segoe Print", Font.PLAIN, 16));
		ans1.setBounds(143, 85, 417, 36);
		Addframe.getContentPane().add(ans1);
		ans1.setColumns(10);

		ans2 = new JTextField();
		ans2.setFont(new Font("Segoe Print", Font.PLAIN, 16));
		ans2.setBounds(143, 137, 417, 36);
		Addframe.getContentPane().add(ans2);
		ans2.setColumns(10);

		ans3 = new JTextField();
		ans3.setFont(new Font("Segoe Print", Font.PLAIN, 16));
		ans3.setBounds(143, 196, 417, 36);
		Addframe.getContentPane().add(ans3);
		ans3.setColumns(10);

		ans4 = new JTextField();
		ans4.setFont(new Font("Segoe Print", Font.PLAIN, 16));
		ans4.setBounds(143, 248, 417, 36);
		Addframe.getContentPane().add(ans4);
		ans4.setColumns(10);

		Addframe.setBounds(100, 100, 735, 523);
		Addframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		textLevel = new JTextField();
		textLevel.setToolTipText("Level");
		textLevel.setBounds(188, 347, 122, 22);
		Addframe.getContentPane().add(textLevel);
		textLevel.setColumns(10);
		
		textCurrectAnswer = new JTextField();
		textCurrectAnswer.setToolTipText("currectAnswer");
		textCurrectAnswer.setBounds(185, 312, 122, 22);
		Addframe.getContentPane().add(textCurrectAnswer);
		textCurrectAnswer.setColumns(10);
		
		JLabel lblTeam = new JLabel("Team :");
		lblTeam.setForeground(Color.WHITE);
		lblTeam.setFont(new Font("Segoe Print", Font.BOLD | Font.ITALIC, 17));
		lblTeam.setBounds(57, 378, 147, 21);
		Addframe.getContentPane().add(lblTeam);
		
		textTeam = new JTextField();
		textTeam.setToolTipText("team");
		textTeam.setColumns(10);
		textTeam.setBounds(188, 379, 122, 22);
		Addframe.getContentPane().add(textTeam);


		JButton btnAdd = new JButton("Add Question");
		btnAdd.setBackground(Color.WHITE);
		btnAdd.setFont(new Font("Segoe Print", Font.PLAIN, 16));
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				String question = queTxt.getText();
				String answer1 = ans1.getText();
				String answer2 = ans2.getText();
				String answer3 = ans3.getText();
				String answer4 = ans4.getText();
				String CurrectAnswer = textCurrectAnswer.getText();
				String level = textLevel.getText();
				String team = textTeam.getText();
				
				if(question==null || answer1==null || answer2==null ||answer3==null ||answer4==null 
						|| CurrectAnswer==null || level==null || team==null) {
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

				if(Controller.AddToFile(question,arr,CurrectAnswer,level,team)) {
					JOptionPane.showMessageDialog(null, "The question Added successfully");
					QuestionPart1 QQQ = new QuestionPart1();
					QQQ.Qframe.setVisible(true);
					Addframe.dispose();

					return;

				}
	      	 
				JOptionPane.showMessageDialog(null, "Failed to add the question");
				QuestionPart1 QQQ = new QuestionPart1();
				QQQ.Qframe.setVisible(true);
				Addframe.dispose();

				return;

               
			}
			
		   
		}); 
	
		btnAdd.setBounds(297, 434, 159, 42);
		Addframe.getContentPane().add(btnAdd);
	
		
		JButton button = new JButton("");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				QuestionPart1 Q = new QuestionPart1();
				Q.Qframe.setVisible(true);
				Addframe.dispose();
			}
		});
		button.setIcon(new ImageIcon(AddQuestions.class.getResource("/pic/back.jpg")));
		button.setBounds(57, 434, 56, 29);
		Addframe.getContentPane().add(button);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(0, 0, 721, 486);
		lblNewLabel.setIcon(new ImageIcon(AddQuestions.class.getResource("/pic/newGame.jpg")));
		Addframe.getContentPane().add(lblNewLabel);
		

	}
}