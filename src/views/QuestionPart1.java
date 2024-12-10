package views;

import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import controler.Controller;
import model.question;
import tables.QuestionTable;

public class QuestionPart1 {

	public JFrame Qframe;
	private JTable table;


	/**
	 * Create the application.
	 */
	public QuestionPart1() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	@SuppressWarnings("serial")
	private void initialize() {
		Qframe = new JFrame();
		Qframe.setResizable(false);
		Qframe.getContentPane().setBackground(Color.WHITE);
		Qframe.setFont(new Font("Segoe Print", Font.PLAIN, 12));
		Qframe.setTitle("Question");
		Qframe.setIconImage(Toolkit.getDefaultToolkit().getImage(QuestionPart1.class.getResource("/pic/write.png")));
		Qframe.setBounds(100, 100, 1209, 684);
		Qframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Qframe.getContentPane().setLayout(null);
		
		JButton btnAddQuestion = new JButton("Add Question");
		btnAddQuestion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				AddQuestions addQ = new AddQuestions();
				addQ.Addframe.setVisible(true);
				Qframe.dispose();
			}
		});
		btnAddQuestion.setFont(new Font("Segoe Print", Font.PLAIN, 16));
		btnAddQuestion.setBackground(Color.WHITE);
		btnAddQuestion.setBounds(339, 506, 192, 50);
		Qframe.getContentPane().add(btnAddQuestion);
		
		JButton btnRemovQuestion = new JButton("Remove Question");
		btnRemovQuestion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			DeleteQuestions removeQ = new DeleteQuestions();
			removeQ.removeframe.setVisible(true);
			Qframe.dispose();
			}
		});
		btnRemovQuestion.setFont(new Font("Segoe Print", Font.PLAIN, 16));
		btnRemovQuestion.setBackground(Color.WHITE);
		btnRemovQuestion.setBounds(189, 404, 192, 50);
		Qframe.getContentPane().add(btnRemovQuestion);
		
		JButton btnUpdateQuestion = new JButton("Update Question");
		btnUpdateQuestion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				UpdateQuestions updateQ = new UpdateQuestions();
				updateQ.updateframe.setVisible(true);
				Qframe.dispose();
			}
		});
		btnUpdateQuestion.setFont(new Font("Segoe Print", Font.PLAIN, 16));
		btnUpdateQuestion.setBackground(Color.WHITE);
		btnUpdateQuestion.setBounds(494, 404, 192, 50);
		Qframe.getContentPane().add(btnUpdateQuestion);
		
		
		
		/// go back
		JButton button = new JButton("");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				HomePageForAll f = new HomePageForAll();
				f.HomePageForAll.setVisible(true);
				Qframe.dispose();	
			}
		});
		button.setIcon(new ImageIcon(QuestionPart1.class.getResource("/pic/back.jpg")));
		button.setBounds(15, 608, 60, 29);
		Qframe.getContentPane().add(button);
		
		
		ArrayList<question> array = new ArrayList<>();
		array.addAll(Controller.getQuestionss());
		array.addAll(Controller.getRemovedQuestion());
		
		QuestionTable question1 =  new QuestionTable(array);
		
		question1.fireTableDataChanged();

		  
		  table= new JTable(question1)
		  {
			    @Override
			       public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
			    	
			    	
			    	//java.lang.IndexOutOfBoundsException: Index 3 out of bounds for length 3
			           Component component = super.prepareRenderer(renderer, row, column);
			           int rendererWidth = component.getPreferredSize().width;
			           TableColumn tableColumn = getColumnModel().getColumn(column);
			           tableColumn.setPreferredWidth(Math.max(rendererWidth + getIntercellSpacing().width, tableColumn.getPreferredWidth()));
			           return component;
			        }
			    };
			    table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
			    JScrollPane scrollPane = new JScrollPane(table);
			    scrollPane.setBounds(15, 16, 1126, 316);
			    scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
			    Qframe.getContentPane().add(scrollPane);
			 table.getTableHeader().setFont(new Font("Segoe Print", Font.ITALIC, 18));
			 table.getTableHeader().setBackground(Color.white);
			table.setRowHeight(50);
			TableColumnModel columnModel = table.getColumnModel();
			columnModel.getColumn(0).setWidth(150);
				table.setFont(new Font("Segoe Print", Font.PLAIN, 10));
			       scrollPane.setViewportView(table);
			      
			       Qframe.getContentPane().add(scrollPane);
			       
			       JLabel lblNewLabel = new JLabel("");
			       lblNewLabel.setBounds(683, 335, 502, 312);
			       lblNewLabel.setIcon(new ImageIcon(QuestionPart1.class.getResource("/pic/cuteZebra.png")));
			       Qframe.getContentPane().add(lblNewLabel);
	}
}
