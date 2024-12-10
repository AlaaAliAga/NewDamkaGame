package model;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.Timer;

import controler.Board;
import controler.Move;

public class Tiles extends JPanel {
	final private int WIDTH = 75;
	final private int HEIGHT = 75;
	private Pieces piece;
	private Board board;
	private boolean PieceAdded = false;
	private int row;
	private int col;
	private boolean select;
	private TileType type;
	private boolean selectBlueTile = false;



	public Tiles(int row, int col, Board b) {
		this.row = row;
		this.col = col;
		this.board = b;
	}



	public Board getBoard() {
		return this.board;
	}

	public void coord() {
		System.out.println("This is tile (" + row + "," + col + ").");
	}

	public int getRow() {
		return row;
	}

	public int getCol() {
		return col;
	}

	public void addPiece(Pieces p) {
		piece = p;
		PieceAdded = true;
	}

	public boolean isOccupied() {
		if (piece != null) {
			return true;
		} else
			return false;
	}


	public void setPiece(Pieces piece) {
		this.piece = piece;
	}

	public boolean isYellow (Tiles t) {
		if(t.type == TileType.YELLOW)
			return true ;
		return false;
	}
	public boolean isBlue (Tiles t) {
		if(t.type == TileType.BLUE)
			return true ;
		return false;
	}
	public boolean isGreen (Tiles t) {
		if(t.type == TileType.GREEN)
			return true ;
		return false;
	}
	public boolean isRed (Tiles t) {
		if(t.type == TileType.RED)
			return true ;
		return false;
	}
	public boolean isOrange (Tiles t) {
		if(t.type == TileType.ORANGE)
			return true ;
		return false;
	}
	public void selected(boolean bool,Board board) {
		this.select = bool;
		if (this.select == false) { // If the destination is a non-piece holding tile, it will set the destination coordinates
			Move.movePieces(row, col, board); // Gives board destination coordinates
		}
		
		//		this.select = false;		//Refreshes the function
	}

	public Pieces getPiece() {
		return this.piece;
	}

	public void delete() {
		this.piece = null;
	}


	public void paintComponent(Graphics g) { // Paints tiles. If piece is
		// present, paints a circle within the tile.
		Graphics2D g2 = (Graphics2D) g;

		if ((row + col) % 2 == 0) {
			g2.setColor(Color.WHITE);
		} else {
			g2.setColor(Color.GRAY);
			Tiles t = Board.getTile(row, col);
			if(isYellow(t))
				g2.setColor(Color.YELLOW);
			if(isRed(t))
				g2.setColor(Color.RED);
			if(isBlue(t))
				g2.setColor(Color.BLUE);
			if(isGreen(t)) 
				g2.setColor(Color.GREEN);
			if(isOrange(t)) 
				g2.setColor(Color.ORANGE);
		}
		g2.fillRect(0, 0, WIDTH, HEIGHT);
		if (piece != null) {
			if (piece.getType() == PieceType.BLACK
					|| piece.getType() == PieceType.BLACK_KING) {
				g2.setColor(Color.BLACK);
				if (piece.getType() == PieceType.BLACK_KING) {
					Color c = new Color (139, 69, 19);
					g2.setColor(c);					//Black kings become Dark gray
				}
			} else if (piece.getType() == PieceType.RED
					|| piece.getType() == PieceType.RED_KING) {
				g2.setColor(Color.WHITE);
				if (piece.getType() == PieceType.RED_KING) {
					g2.setColor(Color.LIGHT_GRAY);						//Red kings become light Gray
				}
			}
			g2.fillOval(8, 5, 55, 55); // Creates "checker" look
		}
		repaint();		//Continuously repaint to make sure the pieces appear
	}

	
	
	
	public TileType getType() {
		return type;
	}

	public void setType(TileType type) {
		this.type = type;
	}

}