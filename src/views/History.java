package views;

import java.awt.EventQueue;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;

import controler.Controller;

import java.awt.Color;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTable;
import javax.swing.JScrollBar;
import java.awt.ScrollPane;
import java.awt.Scrollbar;

public class History {

	private JFrame frame;
	public ArrayList<String []> array = Controller.historyGameArray();
	
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					History window = new History();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}


	/**
	 * Create the application.
	 */
	public History() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 802, 540);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		

		
		

		for(int i = 0 ; i< array.size();i++) {
		JLabel lbl = new JLabel("");
		lbl.setBackground(Color.RED);
		lbl.setBounds(117, (153*i)+43, 615, 142);
		frame.getContentPane().add(lbl);
		
		JLabel lblNewLabel = new JLabel("Game Number");
		lblNewLabel.setBounds(246,(153*i)+ 56, 94, 20);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Date and Time");
		lblNewLabel_1.setBounds(399,(153*i)+ 56, 94, 17);
		frame.getContentPane().add(lblNewLabel_1);
		
		String s = String.valueOf(i+1);
		JLabel gameNum = new JLabel(s);
		gameNum.setBounds(334, (153*i)+58, 46, 17);
		frame.getContentPane().add(gameNum);
		
		JLabel gameDate = new JLabel((String) null);
		gameDate.setBounds(503,(153*i)+ 56, 119, 17);
		frame.getContentPane().add(gameDate);
		
		JLabel lblNewLabel_4 = new JLabel("Player Name : ");
		lblNewLabel_4.setBounds(162,(153*i)+ 118, 94, 20);
		frame.getContentPane().add(lblNewLabel_4);
		
		JLabel lblNewLabel_4_1 = new JLabel("Player Name : ");
		lblNewLabel_4_1.setBounds(162,(153*i)+ 87, 94, 20);
		frame.getContentPane().add(lblNewLabel_4_1);
		
		JLabel player1 = new JLabel((String) null);
		player1.setBounds(266, (153*i)+87, 100, 20);
		frame.getContentPane().add(player1);
		
		JLabel player2 = new JLabel((String) null);
		player2.setBounds(266,(153*i)+ 121, 100, 20);
		frame.getContentPane().add(player2);
		
		JLabel lblNewLabel_6 = new JLabel("Score :");
		lblNewLabel_6.setBounds(389, (153*i)+89, 54, 17);
		frame.getContentPane().add(lblNewLabel_6);
		
		JLabel lblNewLabel_6_1 = new JLabel("Score :");
		lblNewLabel_6_1.setBounds(389, (153*i)+120, 54, 17);
		frame.getContentPane().add(lblNewLabel_6_1);
		
		JLabel score1 = new JLabel((String) null);
		score1.setBounds(440, (153*i)+87, 88, 17);
		frame.getContentPane().add(score1);
		
		JLabel score2 = new JLabel((String) null);
		score2.setBounds(440, (153*i)+121, 88, 17);
		frame.getContentPane().add(score2);
		
		JLabel lblNewLabel_8 = new JLabel("knowledge percent :");
		lblNewLabel_8.setBounds(538,(153*i)+ 87, 135, 17);
		frame.getContentPane().add(lblNewLabel_8);
		
		JLabel knowledge1 = new JLabel((String) null);
		knowledge1.setBounds(678,(153*i)+ 87, 54, 17);
		frame.getContentPane().add(knowledge1);
		
		JLabel lblNewLabel_8_1 = new JLabel("knowledge percent :");
		lblNewLabel_8_1.setBounds(538, (153*i)+118, 147, 17);
		frame.getContentPane().add(lblNewLabel_8_1);
		
		JLabel knowledge2 = new JLabel((String) null);
		knowledge2.setBounds(678, (153*i)+118, 54, 17);
		frame.getContentPane().add(knowledge2);
		
		JLabel lblNewLabel_2 = new JLabel("Winner Name : ");
		lblNewLabel_2.setBounds(357, (153*i)+144, 94, 20);
		frame.getContentPane().add(lblNewLabel_2);
		
		JLabel player1_1 = new JLabel((String) null);
		player1_1.setBounds(453, (153*i)+144, 100, 20);
		frame.getContentPane().add(player1_1);
		
	
		}

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setBounds(0, 0, 786, 501);
		frame.getContentPane().add(scrollPane);
		
	
	}
}
