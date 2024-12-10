package views;

import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import controler.Controller;
import tables.QuestionTable;
import tables.ScoreTable;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class GameHistory {

	public JFrame gameHistoryframe;
	private JTable table;
	private JLabel lblNewLabel;


	/**
	 * Create the application.
	 */
	public GameHistory() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	@SuppressWarnings("serial")
	private void initialize() {
		gameHistoryframe = new JFrame();
		gameHistoryframe.getContentPane().setBackground(Color.WHITE);
		gameHistoryframe.setBounds(100, 100, 1444, 753);
		gameHistoryframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		gameHistoryframe.setIconImage(Toolkit.getDefaultToolkit().getImage(GameHistory.class.getResource("/pic/zebra.png")));
		gameHistoryframe.setFont(new Font("Segoe Print", Font.PLAIN, 12));
		gameHistoryframe.setTitle("GameHistory");
		gameHistoryframe.getContentPane().setLayout(null);



		
	       
		ScoreTable score =  new ScoreTable(Controller.historyGameArray ());
		
		score.fireTableDataChanged();
		  gameHistoryframe.getContentPane().setLayout(null);

      // System.out.println(Controller.getPlayers());

		  table= new JTable(score)
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
			    scrollPane.setBounds(310, 0, 1110, 395);
			    scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
			    gameHistoryframe.getContentPane().add(scrollPane);
			 table.getTableHeader().setFont(new Font("Segoe Print", Font.ITALIC, 18));
			 table.getTableHeader().setBackground(Color.white);
			table.setRowHeight(60);
			TableColumnModel columnModel = table.getColumnModel();
			columnModel.getColumn(0).setWidth(150);
				table.setFont(new Font("Segoe Print", Font.PLAIN, 10));
			       scrollPane.setViewportView(table);
			      
			       
			    //   gameHistoryframe.getContentPane().add(scrollPane);
			       
			       JButton btnNewButton = new JButton("go back");
			       btnNewButton.setBounds(10, 675, 46, 31);
			       btnNewButton.addActionListener(new ActionListener() {
			       	public void actionPerformed(ActionEvent e) {
			       		HomePageForAll home = new HomePageForAll();
			       		home.HomePageForAll.setVisible(true);
			       		gameHistoryframe.dispose();
			       	}
			       });
					btnNewButton.setIcon(new ImageIcon(GameHistory.class.getResource("/pic/back.jpg")));
			       gameHistoryframe.getContentPane().add(btnNewButton);
			       
			       JLabel lblNewLabel_1 = new JLabel("");
			       lblNewLabel_1.setIcon(new ImageIcon(GameHistory.class.getResource("/pic/ZZebra.png")));
			       lblNewLabel_1.setBounds(-43, 0, 763, 814);
			       gameHistoryframe.getContentPane().add(lblNewLabel_1);
			       
			      
		
	}
}
