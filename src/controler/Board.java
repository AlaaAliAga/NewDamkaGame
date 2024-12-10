	package controler;
	
	import views.NewGame;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
	import java.util.Random;
	import java.util.TreeSet;

import javax.swing.Timer;

import model.PieceType;
import model.Pieces;
import model.Player;
import model.PlayerType;
import model.TileType;
import model.Tiles;
	
	
	
	
	public class Board {
	
	
		// Numerous private variables with different characteristics
		final private static int COL = 8;
		final private static int ROW = 8;
		private static Tiles theBoard[][] = new Tiles[COL][ROW]; // The container for the
		// checkerboard
		private Tiles[][] tile = new Tiles[COL][ROW]; // Container for the tiles
	
		private ArrayList<Pieces> redPieces = new ArrayList<>(); // Holds all the
		// pieces.
		private ArrayList<Pieces> blackPieces = new ArrayList<>(); // Used in
		// construction
		// of the board
	
		private int redCounter = 0;
		private int blackCounter = 0;
		private static int destRow = 0;
		private static int destCol = 0;
		private int currentRow = 0;
		private int currentCol = 0;
		private int preyRow = 0; // Coordinates of piece being eaten
		private int preyCol = 0;
		public static int turnCounter = 0;
		private static int scoreRedPlayer = 0 ;
		private static int scoreBlackPlayer = 0 ;
		private static Tiles t1;
		private static Pieces lastPieceMoved ; // Proposed for a path-finding function...
		private static Player red; // Players of the game
		private static Player black;
		private ArrayList<Pieces> nextPiece = new ArrayList<>();
		private static String loser; // Prints losing side
		public static int second ;/// seconds Counter
		public static int gameSecond; 





		public static Timer timer;
		public static Timer timerGame;
	





		public Board() {
			NewGame frontend = new NewGame(this);
		
			makePieces();
			black = new Player(PlayerType.BLACK);
			red = new Player(PlayerType.RED);
		}

	
		
		
		public static void switchTurns() { // Once a move is made, switch/increment turns
			Tiles [][] b = Board.getTheBoard();
			Tiles destTile = getTile(destRow, destCol);
			if(destTile.getType() == TileType.BLUE ) {
				System.out.println("select Tile to add a new piece");
			}else if(b[Board.getLastPieceMoved().getRow()][Board.getLastPieceMoved().getCol()].getType() == TileType.RED){
				System.out.println("Do Another Step in the same piece");
			}else if(Move.flagSeq){
				System.out.println("you can play another time");
			}else
			{if (turnCounter % 2 == 1) {
				setScoreRedPlayer(getScoreRedPlayer()+(60-second));
				NewGame.scoreP1.setText(String.valueOf(Board.getScoreRedPlayer()));
			} else {
				setScoreBlackPlayer(getScoreBlackPlayer()+(60-second));
				NewGame.scoreP2.setText(String.valueOf(Board.getScoreBlackPlayer()));
			}
			timer.stop();	
			second=0;
			Controller.resetTilesColor ();
			turnCounter++;
			timer1();
			timer.start();
			colorYellowTile();
			colorRedTile();
			colorBlueTile();
			}
		}
		public static void timer1() {
			timer=new Timer(1000, (ActionListener) new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					second++;
					if(Board.getSecond()/60<10 &&Board.getSecond()%60<10 ) {
						NewGame.timeS.setText("0"+String.valueOf(getSecond()/60 + ":0" + getSecond()%60));	
					}else if(Board.getSecond()/60>=10 &&Board.getSecond()%60>=10 ) {
						NewGame.timeS.setText(String.valueOf(getSecond()/60 + ":" + getSecond()%60));	
					}else if(Board.getSecond()/60>=10 &&Board.getSecond()%60<10 ) {
						NewGame.timeS.setText(String.valueOf(getSecond()/60 + ":0" + getSecond()%60));		
					}else if(Board.getSecond()/60<10 &&Board.getSecond()%60>=10 ) {
						NewGame.timeS.setText("0"+String.valueOf(getSecond()/60 + ":" + getSecond()%60));
					}
					
					
					
					if(second==30) {
						greenTimer();

					}
					if(second==90) {
						orangeTimer();
					}
				
				}

			});
		}
		
		public static void timerGame() {
			timerGame=new Timer(1000, (ActionListener) new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					gameSecond++;
					if(getGameSecond()%60<10 && getGameSecond()/60<10)
						NewGame.GameTime.setText(String.valueOf("0"+getGameSecond()/60 + ":0" + getGameSecond()%60));
					else if(getGameSecond()%60<10 && getGameSecond()/60>10)
						NewGame.GameTime.setText(String.valueOf(getGameSecond()/60 + ":0" + getGameSecond()%60));
					else if(getGameSecond()%60>10 && getGameSecond()/60<10)
						NewGame.GameTime.setText(String.valueOf("0"+getGameSecond()/60 + ":" + getGameSecond()%60));
					else if(getGameSecond()%60>10 && getGameSecond()/60>10)
						NewGame.GameTime.setText(String.valueOf(getGameSecond()/60 + ":" + getGameSecond()%60));
				}

			});
		}
		
		
		public static void orangeTimer () {
			ArrayList<Tiles> orangeTiles = new ArrayList<>();
			orangeTiles =  playerTilesAvailable () ;
			for (Tiles t : orangeTiles) {
				t.setType(TileType.ORANGE);
			}
		}
		public static void greenTimer () {
			ArrayList<Tiles> greenTiles = new ArrayList<>();
			greenTiles =  Board.playerTilesAvailable () ;
			if(greenTiles.size()!=0) {
				Tiles t = Board.getRandomTile(greenTiles);
				t.setType(TileType.GREEN);
			}
				
		}

		
		
		public void clearPotentialMoves() {
			nextPiece.clear();
		}
	
	
		public PlayerType turn() { // Makes sure turns are alternating. Black goes
			// first.
			if (turnCounter % 2 == 1) {
				return PlayerType.RED;
			} else {
				return PlayerType.BLACK;
			}
		}
	
		public void getRootRowCol(int row, int col) { // Passes in clicked
			// piece/tile coordinates
			currentRow = row;
			currentCol = col;
		}
	
		public void makePieces() { // Creation and storage of Pieces
			for (int i = 0; i < ROW; i++) {
				for (int j = 0; j < COL; j++) {
					if ((i + j) % 2 == 1) {
						if (i < 3) { // Creates red pieces up to row 3
							redPieces.add(new Pieces(i, j, PieceType.RED)); // (row,
							// column,
							// type)
							theBoard[i][j].addPiece(redPieces.get(redCounter));
							redCounter++;
						} else if (i > 4 && i < 8) { // Creates black pieces up from
							// row 5
							blackPieces.add(new Pieces(i, j, PieceType.BLACK));
							theBoard[i][j].addPiece(blackPieces.get(blackCounter));
							blackCounter++;
						}
					}
				}
			}
		}
		

		
	
		public static Tiles getTile(int xCoord, int yCoord) {
			if ((xCoord >= 0 && xCoord <= 7) && (yCoord >= 0 && yCoord <= 7)) {
				return theBoard[xCoord][yCoord];
			} else
				return null;
		}
	

	
		public static Tiles getRandomTile(ArrayList<Tiles> list) 
		{ 
			Random rand = new Random();
			if(!list.isEmpty())
				return list.get(rand.nextInt(list.size())); 
			else {
				System.err.println("didnt have any tiles");
				return null;	
			}
		}
		public static Pieces getRandomPiece(ArrayList<Pieces> list) 
		{ 
			Random rand = new Random(); 
			if(!list.isEmpty())
				return list.get(rand.nextInt(list.size())); 
			else {
				System.err.println("didnt have any pieces");
				return null;	
			}
		}
	
		public static void colorYellowTile() {
			ArrayList<Tiles> availableTiles = playerTilesAvailable();
			ArrayList<Tiles> yellowTiles = new ArrayList<>();
			int size = availableTiles.size();
			System.out.println(size);
			if(size>2) {
				while(yellowTiles.size()<3) {
					t1 = getRandomTile(availableTiles);
					if(!yellowTiles.contains(t1)) {
						t1.setType(TileType.YELLOW);
						yellowTiles.add(t1);
					}
				}
			} else {
				for(Tiles t : availableTiles ){
					t.setType(TileType.YELLOW);
			}	
			}
		}
	





		public static ArrayList<Pieces> PiecesCanEat () {
			Tiles[][] b = getTheBoard();
			ArrayList<Pieces> redPieces = new ArrayList<>();
			ArrayList<Pieces> blackPieces = new ArrayList<>();
			ArrayList<Pieces> redPiecesCanEat = new ArrayList<>();
			ArrayList<Pieces> blackPiecesCanEat = new ArrayList<>();
			for(int i = 0; i < b.length; i++) {
				for(int j = 0; j < b[0].length; j++) {
					if(b[i][j].isOccupied() && ((i+j) % 2 == 1) // gray tiles 
							&& (b[i][j].getPiece().getType() == PieceType.RED || b[i][j].getPiece().getType() == PieceType.RED_KING)) {
						redPieces.add(b[i][j].getPiece());
					} else if(b[i][j].isOccupied()) {
						blackPieces.add(b[i][j].getPiece());
					}
	
				}
			} 
			if (turnCounter % 2 == 1) { // red turn
				for(Pieces p : redPieces) {
					int row = p.getRow();
					int col = p.getCol();
					if(p.getType() == PieceType.RED) {
						if(col+2<8 && row+2<8  && b[row+1][col+1].isOccupied()  && !b[row+2][col+2].isOccupied() && // red Right
								(b[row+1][col+1].getPiece().getType() == PieceType.BLACK || b[row+1][col+1].getPiece().getType() == PieceType.BLACK_KING)) {
							redPiecesCanEat.add(p);
						}
						if(row+2<8 && col-2>=0 &&b[row+1][col-1].isOccupied()  && !b[row+2][col-2].isOccupied() && // red Left
								(b[(row+1)][(col-1)].getPiece().getType() == PieceType.BLACK || b[row+1][col-1].getPiece().getType() == PieceType.BLACK_KING) ) {
							redPiecesCanEat.add(p);
						}
					}else if(p.getType() == PieceType.RED_KING){ // RED KING
						int r = p.getRow();
						int c = p.getCol();
						if(row-2>=0 &&col+2<8 && row+2<8 && col-2>=0 &&(((b[(r+1)][(c-1)].isOccupied()&& (b[(r+1)][(c-1)].getPiece().getType() == PieceType.BLACK || b[(r+1)][(c-1)].getPiece().getType() == PieceType.BLACK_KING ) && !b[(r+2)][(c-2)].isOccupied()) ||
								((b[(r-1)][(c+1)].isOccupied() &&(b[(r-1)][(c+1)].getPiece().getType() == PieceType.BLACK || b[(r-1)][(c+1)].getPiece().getType() == PieceType.BLACK_KING )) && !b[(r-2)][(c+2)].isOccupied()) ||
								(b[(r+1)][(c+1)].isOccupied()&&(b[(r+1)][(c+1)].getPiece().getType() == PieceType.BLACK || b[(r+1)][(c+1)].getPiece().getType() == PieceType.BLACK_KING ) && !b[(r+2)][(c+2)].isOccupied()) ||
								(b[(r-1)][(c-1)].isOccupied()&& (b[(r-1)][(c-1)].getPiece().getType() == PieceType.BLACK || b[(r-1)][(c-1)].getPiece().getType() == PieceType.BLACK_KING ) && !b[(r-2)][(c-2)].isOccupied())))) {
							redPiecesCanEat.add(p);
						}	
						if(row==1 && col<6 && col>1 &&((b[r+1][c+1].isOccupied() && !b[r+2][c+2].isOccupied() && (b[(r+1)][(c+1)].getPiece().getType() == PieceType.BLACK || b[(r+1)][(c+1)].getPiece().getType() == PieceType.BLACK_KING ) ) ||
								(b[(r+1)][(c-1)].isOccupied()&& (b[(r+1)][(c-1)].getPiece().getType() == PieceType.BLACK || b[(r+1)][(c-1)].getPiece().getType() == PieceType.BLACK_KING ) && !b[(r+2)][(c-2)].isOccupied()) || 
								(b[0][c+1].isOccupied() && (b[0][(c+1)].getPiece().getType() == PieceType.BLACK || b[0][(c+1)].getPiece().getType() == PieceType.BLACK_KING ) && !b[7][c+2].isOccupied())|| 
								(b[0][c-1].isOccupied() && (b[0][(c-1)].getPiece().getType() == PieceType.BLACK || b[0][(c-1)].getPiece().getType() == PieceType.BLACK_KING )&& !b[7][c-2].isOccupied()) ))
						{
							redPiecesCanEat.add(p);
						}
						if(row==1 && col==0 && ((b[r+1][c+1].isOccupied() && !b[r+2][c+2].isOccupied() &&(b[(r+1)][(c+1)].getPiece().getType() == PieceType.BLACK || b[(r+1)][(c+1)].getPiece().getType() == PieceType.BLACK_KING ) )|| 
								(b[r-1][c+1].isOccupied() && !b[7][c+2].isOccupied() && (b[r-1][c+1].getPiece().getType() == PieceType.BLACK ||b[r-1][c+1].getPiece().getType() == PieceType.BLACK_KING ) ) ||
								(b[r-1][7].isOccupied() && !b[7][6].isOccupied() && (b[r-1][7].getPiece().getType() == PieceType.BLACK ||b[r-1][7].getPiece().getType() == PieceType.BLACK_KING ))||
								(b[r+1][c+1].isOccupied() && !b[r+2][6].isOccupied() && (b[r+1][7].getPiece().getType() == PieceType.BLACK ||b[r+1][7].getPiece().getType() == PieceType.BLACK_KING )))) {
							redPiecesCanEat.add(p);
						}
						if(row==1 && col==6 && ((b[r+1][c+1].isOccupied() && !b[r+2][0].isOccupied() && (b[r+1][7].getPiece().getType() == PieceType.BLACK ||b[r+1][7].getPiece().getType() == PieceType.BLACK_KING ))||
								(b[0][7].isOccupied() && !b[7][0].isOccupied() &&(b[0][7].getPiece().getType() == PieceType.BLACK ||b[0][7].getPiece().getType() == PieceType.BLACK_KING))||
								(b[r-1][c-1].isOccupied() && !b[7][c-2].isOccupied() && (b[7][c-2].getPiece().getType() == PieceType.BLACK ||b[7][c-2].getPiece().getType() == PieceType.BLACK_KING) ) ||
								(b[(r+1)][(c-1)].isOccupied()&& !b[(r+2)][(c-2)].isOccupied() && (b[(r+1)][(c-1)].getPiece().getType() == PieceType.BLACK || b[(r+1)][(c-1)].getPiece().getType() == PieceType.BLACK_KING )))) {
							redPiecesCanEat.add(p);
						}
						if(row==0 && col<6 && col>1 && ((b[row+1][col+1].isOccupied() && !b[row+2][col+2].isOccupied() && (b[(r+1)][(c+1)].getPiece().getType() == PieceType.BLACK || b[(r+1)][(c+1)].getPiece().getType() == PieceType.BLACK_KING ) ) ||
								(b[(r+1)][(c-1)].isOccupied()&&  !b[(r+2)][(c-2)].isOccupied() && (b[(r+1)][(c-1)].getPiece().getType() == PieceType.BLACK || b[(r+1)][(c-1)].getPiece().getType() == PieceType.BLACK_KING ) )|| 
								(b[(r-1)%8+8][c+1].isOccupied() && !b[(r-2)%8+8][c+2].isOccupied() && ( b[(r-1)%8+8][c+1].getPiece().getType()== PieceType.BLACK || b[(r-1)%8+8][c+1].getPiece().getType()== PieceType.BLACK_KING ))||
								(b[(r-1)%8+8][c-1].isOccupied() && !b[(r-2)%8+8][c-2].isOccupied()) && (b[(r-1)%8+8][c-1].getPiece().getType() == PieceType.BLACK || b[(r-1)%8+8][c-1].getPiece().getType() == PieceType.BLACK_KING) )) {
							redPiecesCanEat.add(p);
						}
						if(row==0 && col==7 && ( ( b[7][0].isOccupied() && !b[6][1].isOccupied() && (b[7][0].getPiece().getType() == PieceType.BLACK || b[7][0].getPiece().getType() == PieceType.BLACK_KING )  ) ||
								(b[r+1][6].isOccupied() && !b[r+2][5].isOccupied() && (b[r+1][6].getPiece().getType() == PieceType.BLACK || b[r+1][6].getPiece().getType() == PieceType.BLACK_KING)) || 
								(b[r+1][0].isOccupied() && !b[r+2][1].isOccupied() && (b[r+1][0].getPiece().getType() == PieceType.BLACK || b[r+1][0].getPiece().getType() == PieceType.BLACK_KING)) ||
								(b[7][c-1].isOccupied() && !b[6][c-2].isOccupied() && (b[7][c-1].getPiece().getType() == PieceType.BLACK || b[7][c-1].getPiece().getType() == PieceType.BLACK_KING)))) {
							redPiecesCanEat.add(p);
						}
						if(row==0 && col==1 && ( ( b[r+1][c+1].isOccupied() && !b[r+2][c+2].isOccupied() && ( b[r+1][c+1].getPiece().getType() == PieceType.BLACK || b[r+1][c+1].getPiece().getType() == PieceType.BLACK_KING) ) ||  
								(b[1][0].isOccupied() && !b[2][7].isOccupied()) && (b[1][0].getPiece().getType() == PieceType.BLACK || b[1][0].getPiece().getType() == PieceType.BLACK_KING) ||
								(b[7][2].isOccupied() && !b[6][3].isOccupied() && (b[7][2].getPiece().getType() == PieceType.BLACK||b[7][2].getPiece().getType() == PieceType.BLACK_KING)) || 
								(b[7][0].isOccupied() && !b[6][7].isOccupied() && (b[7][0].getPiece().getType() == PieceType.BLACK || b[7][0].getPiece().getType() == PieceType.BLACK_KING)))) {
							redPiecesCanEat.add(p);
						}
						if(col == 1 && row> 1 && row<7 && (( b[r+1][c+1].isOccupied() && !b[(r+2)%8][c+2].isOccupied() && (b[(r+1)][(c+1)].getPiece().getType() == PieceType.BLACK || b[(r+1)][(c+1)].getPiece().getType() == PieceType.BLACK_KING ) )||
								(b[(r-1)][(c+1)].isOccupied() &&  !b[(r-2)][(c+2)].isOccupied() && (b[(r-1)][(c+1)].getPiece().getType() == PieceType.BLACK || b[(r-1)][(c+1)].getPiece().getType() == PieceType.BLACK_KING ))|| 
								(b[r-1][c-1].isOccupied() && !b[r-2][(c-2)%8+8].isOccupied() && (b[r-1][c-1].getPiece().getType() == PieceType.BLACK ||b[r-1][c-1].getPiece().getType() == PieceType.BLACK_KING))|| 
								(b[r-1][c+1].isOccupied() && !b[r-2][(c-2)%8+8].isOccupied() && (b[r-1][c+1].getPiece().getType() == PieceType.BLACK || b[r-1][c+1].getPiece().getType() == PieceType.BLACK_KING)))) {
							redPiecesCanEat.add(p);
						}
						if(col == 0 && row> 1 && row<7 &&( ( b[r-1][7].isOccupied() && !b[r-2][6].isOccupied() && (b[r-1][7].getPiece().getType() == PieceType.BLACK || b[r-1][7].getPiece().getType() == PieceType.BLACK_KING ) ) ||
								(b[r+1][7].isOccupied() && !b[r+2][6].isOccupied() && (b[r+1][7].getPiece().getType() == PieceType.BLACK || b[r+1][7].getPiece().getType() == PieceType.BLACK_KING )) || 
								(b[r+1][c+1].isOccupied() && !b[r+2][c+2].isOccupied() && (b[(r+1)][(c+1)].getPiece().getType() == PieceType.BLACK || b[(r+1)][(c+1)].getPiece().getType() == PieceType.BLACK_KING ))||
								(b[(r-1)][(c+1)].isOccupied()&& !b[(r-2)][(c+2)].isOccupied() &&(b[(r-1)][(c+1)].getPiece().getType() == PieceType.BLACK || b[(r-1)][(c+1)].getPiece().getType() == PieceType.BLACK_KING )))) {
							redPiecesCanEat.add(p);
						}
						if(row==6&&col<6&&col>1&&((b[row-1][col+1].isOccupied()&&!b[row-2][col+2].isOccupied()&&(b[row-1][col+1].getPiece().getType()==PieceType.BLACK||b[row-1][col+1].getPiece().getType()==PieceType.BLACK_KING))||
								(b[row-1][col-1].isOccupied()&&!b[row-2][col-2].isOccupied()&&(b[row-1][col-1].getPiece().getType()==PieceType.BLACK||b[row-1][col-1].getPiece().getType()==PieceType.BLACK_KING))||
								(b[row+1][col+1].isOccupied()&&!b[0][col+2].isOccupied()&&(b[row+1][col+1].getPiece().getType()==PieceType.BLACK||b[row+1][col+1].getPiece().getType()==PieceType.BLACK_KING))||
								(b[row+1][col-1].isOccupied()&&!b[0][col-2].isOccupied()&&(b[row+1][col-1].getPiece().getType()==PieceType.BLACK||b[row+1][col-1].getPiece().getType()==PieceType.BLACK_KING)))) {
								redPiecesCanEat.add(p);
						}
						if(row==6&&col==1&&((b[row-1][col+1].isOccupied()&&!b[row-2][col+2].isOccupied()&&(b[row-1][col+1].getPiece().getType()==PieceType.BLACK||b[row-1][col+1].getPiece().getType()==PieceType.BLACK_KING))||
								(b[7][0].isOccupied()&&!b[0][7].isOccupied()&&(b[7][0].getPiece().getType()==PieceType.BLACK||b[7][0].getPiece().getType()==PieceType.BLACK_KING))||
								(b[7][2].isOccupied()&&!b[0][3].isOccupied()&&(b[7][2].getPiece().getType()==PieceType.BLACK||b[7][2].getPiece().getType()==PieceType.BLACK_KING))||
								(b[5][0].isOccupied()&&!b[4][7].isOccupied()&&(b[5][0].getPiece().getType()==PieceType.BLACK||b[5][0].getPiece().getType()==PieceType.BLACK_KING)))) {
								redPiecesCanEat.add(p);
						}
						if(row==6&&col==7&&((b[row-1][col-1].isOccupied()&&!b[row-2][col-2].isOccupied()&&(b[row-1][col-1].getPiece().getType()==PieceType.BLACK||b[row-1][col-1].getPiece().getType()==PieceType.BLACK_KING))
								||(b[7][6].isOccupied()&&!b[1][4].isOccupied()&&(b[7][6].getPiece().getType()==PieceType.BLACK||b[7][6].getPiece().getType()==PieceType.BLACK_KING))||
								(b[5][0].isOccupied()&&!b[4][1].isOccupied()&&(b[5][0].getPiece().getType()==PieceType.BLACK||b[5][0].getPiece().getType()==PieceType.BLACK_KING))||
								(b[7][0].isOccupied()&&!b[0][1].isOccupied()&&(b[7][0].getPiece().getType()==PieceType.BLACK||b[7][0].getPiece().getType()==PieceType.BLACK_KING)))) {
								redPiecesCanEat.add(p);
						}
						
						if(row==7&&col>1&&col<6&&((b[row-1][col-1].isOccupied()&&!b[row-2][col-2].isOccupied()&&(b[row-1][col-1].getPiece().getType()==PieceType.BLACK||b[row-1][col-1].getPiece().getType()==PieceType.BLACK_KING))||
								(b[row-1][col+1].isOccupied()&&!b[row-2][col+2].isOccupied()&&(b[row-1][col+1].getPiece().getType()==PieceType.BLACK||b[row-1][col+1].getPiece().getType()==PieceType.BLACK_KING))||
								(b[(row+1)%8][col-1].isOccupied()&&!b[(row+2)%8][col-2].isOccupied()&&(b[(row+1)%8][col-1].getPiece().getType()==PieceType.BLACK||b[(row+1)%8][col-1].getPiece().getType()==PieceType.BLACK_KING))||
								(b[(row+1)%8][col+1].isOccupied()&&!b[(row+2)%8][col+2].isOccupied()&&(b[(row+1)%8][col+1].getPiece().getType()==PieceType.BLACK||b[(row+1)%8][col+1].getPiece().getType()==PieceType.BLACK_KING)))){
								redPiecesCanEat.add(p);
								}
						if(row==7&&col==0&&((b[row-1][col+1].isOccupied()&&!b[row-2][col+2].isOccupied()&&(b[row-1][col+1].getPiece().getType()==PieceType.BLACK||b[row-1][col+1].getPiece().getType()==PieceType.BLACK_KING))||
								(b[(row+1)%8][col+1].isOccupied()&&!b[(row+2)%8][col+2].isOccupied()&&(b[(row+1)%8][col+1].getPiece().getType()==PieceType.BLACK||b[(row+1)%8][col+1].getPiece().getType()==PieceType.BLACK_KING))||
								(b[row-1][(col-1)%8+8].isOccupied()&&!b[row-2][(col-2)%8+8].isOccupied()&&(b[row-1][(col-1)%8+8].getPiece().getType()==PieceType.BLACK||b[row-1][(col-1)%8+8].getPiece().getType()==PieceType.BLACK_KING))||
								(b[(row+1)%8][(col-1)%8+8].isOccupied()&&!b[(row+2)%8][(col-2)%8+8].isOccupied()&&(b[(row+1)%8][(col-1)%8+8].getPiece().getType()==PieceType.BLACK||b[(row+1)%8][(col-1)%8+8].getPiece().getType()==PieceType.BLACK_KING)))) {
								redPiecesCanEat.add(p);
						}
						if(row==7&&col==6&&((b[row-1][col-1].isOccupied()&&!b[row-2][col-2].isOccupied()&&(b[row-1][col-1].getPiece().getType()==PieceType.BLACK||b[row-1][col-1].getPiece().getType()==PieceType.BLACK_KING))||
								(b[row-1][col+1].isOccupied()&&!b[row-2][(col+2)%8].isOccupied()&&(b[row-1][col+1].getPiece().getType()==PieceType.BLACK||b[row-1][col+1].getPiece().getType()==PieceType.BLACK_KING))||
								(b[(row+1)%8][col-1].isOccupied()&&!b[(row+2)%8][col-2].isOccupied()&&(b[(row+1)%8][col-1].getPiece().getType()==PieceType.BLACK||b[(row+1)%8][col-1].getPiece().getType()==PieceType.BLACK_KING))||
								(b[(row+1)%8][col+1].isOccupied()&&!b[(row+2)%8][(col+2)%8].isOccupied()&&(b[(row+1)%8][col+1].getPiece().getType()==PieceType.BLACK||b[(row+1)%8][col+1].getPiece().getType()==PieceType.BLACK_KING)))) {
								redPiecesCanEat.add(p);
							
						}
						if(row<6&&row>1&&col==6&&((b[row-1][col-1].isOccupied()&&!b[row-2][col-2].isOccupied()&&(b[row-1][col-1].getPiece().getType()==PieceType.BLACK||b[row-1][col-1].getPiece().getType()==PieceType.BLACK_KING))||
								(b[row+1][col-1].isOccupied()&&!b[row+2][col-2].isOccupied()&&(b[row+1][col-1].getPiece().getType()==PieceType.BLACK||b[row+1][col-1].getPiece().getType()==PieceType.BLACK_KING))||
								(b[row-1][col+1].isOccupied()&&!b[row-2][(col+2)%8].isOccupied()&&(b[row-1][col+1].getPiece().getType()==PieceType.BLACK||b[row-1][col+1].getPiece().getType()==PieceType.BLACK_KING))||
								(b[row+1][col+1].isOccupied()&&!b[row+2][(col+2)%8].isOccupied()&&(b[row+1][col+1].getPiece().getType()==PieceType.BLACK||b[row+1][col+1].getPiece().getType()==PieceType.BLACK_KING)))) {
								redPiecesCanEat.add(p);
						}
						if(row<6&&row>1&&col==7&&((b[row-1][col-1].isOccupied()&&!b[row-2][col-2].isOccupied()&&(b[row-1][col-1].getPiece().getType()==PieceType.BLACK||b[row-1][col-1].getPiece().getType()==PieceType.BLACK_KING))||
								(b[row+1][col-1].isOccupied()&&!b[row+2][col-2].isOccupied()&&(b[row+1][col-1].getPiece().getType()==PieceType.BLACK||b[row+1][col-1].getPiece().getType()==PieceType.BLACK_KING))||
								(b[row+1][(col+1)%8].isOccupied()&&!b[row+2][(col+2)%8].isOccupied()&&(b[row+1][(col+1)%8].getPiece().getType()==PieceType.BLACK||b[row+1][(col+1)%8].getPiece().getType()==PieceType.BLACK_KING))||
								(b[row-1][(col+1)%8].isOccupied()&&!b[row-2][(col+2)%8].isOccupied()&&(b[row-1][(col+1)%8].getPiece().getType()==PieceType.BLACK||b[row-1][(col+1)%8].getPiece().getType()==PieceType.BLACK_KING)))) {
								redPiecesCanEat.add(p);
						}
	
					}
				}return redPiecesCanEat;
			}else {//black turn
				for(Pieces p : blackPieces) {
					int row = p.getRow();
					int col = p.getCol();
					if(p.getType() == PieceType.BLACK) {
						if(row-2>=0 &&col+2<8 && b[row-1][col+1].isOccupied()  && !b[row-2][col+2].isOccupied() && // black Right
								(b[row-1][col+1].getPiece().getType() == PieceType.RED || b[row-1][col+1].getPiece().getType() == PieceType.RED_KING)) {
							blackPiecesCanEat.add(p);
						}
						if(row-2>=0 && col-2>=0 && b[row-1][col-1].isOccupied()  && !b[row-2][col-2].isOccupied() && // red Left
								(b[row-1][col-1].getPiece().getType() == PieceType.RED || b[row-1][col-1].getPiece().getType() == PieceType.RED_KING) ) {
							blackPiecesCanEat.add(p);
						}
					}else if(p.getType() == PieceType.BLACK_KING){ // BLACK KING
						int r = p.getRow();
						int c = p.getCol();
						if(row-2>=0 &&col+2<8 && row+2<8 && col-2>=0 &&(((b[(r+1)][(c-1)].isOccupied()&& (b[(r+1)][(c-1)].getPiece().getType() == PieceType.RED || b[(r+1)][(c-1)].getPiece().getType() == PieceType.RED_KING ) && !b[(r+2)][(c-2)].isOccupied()) ||
								((b[(r-1)][(c+1)].isOccupied() &&(b[(r-1)][(c+1)].getPiece().getType() == PieceType.RED || b[(r-1)][(c+1)].getPiece().getType() == PieceType.RED_KING )) && !b[(r-2)][(c+2)].isOccupied()) ||
								(b[(r+1)][(c+1)].isOccupied()&&(b[(r+1)][(c+1)].getPiece().getType() == PieceType.RED || b[(r+1)][(c+1)].getPiece().getType() == PieceType.RED_KING ) && !b[(r+2)][(c+2)].isOccupied()) ||
								(b[(r-1)][(c-1)].isOccupied()&& (b[(r-1)][(c-1)].getPiece().getType() == PieceType.RED || b[(r-1)][(c-1)].getPiece().getType() == PieceType.RED_KING ) && !b[(r-2)][(c-2)].isOccupied())))) {
							 blackPiecesCanEat.add(p);
						}	
						if(row==1 && col<6 && col>1 &&((b[r+1][c+1].isOccupied() && !b[r+2][c+2].isOccupied() && (b[(r+1)][(c+1)].getPiece().getType() == PieceType.RED || b[(r+1)][(c+1)].getPiece().getType() == PieceType.RED_KING ) ) ||
								(b[(r+1)][(c-1)].isOccupied()&& (b[(r+1)][(c-1)].getPiece().getType() == PieceType.RED || b[(r+1)][(c-1)].getPiece().getType() == PieceType.RED_KING ) && !b[(r+2)][(c-2)].isOccupied()) || 
								(b[0][c+1].isOccupied() && (b[0][(c+1)].getPiece().getType() == PieceType.RED || b[0][(c+1)].getPiece().getType() == PieceType.RED_KING ) && !b[7][c+2].isOccupied())|| 
								(b[0][c-1].isOccupied() && (b[0][(c-1)].getPiece().getType() == PieceType.RED || b[0][(c-1)].getPiece().getType() == PieceType.RED_KING )&& !b[7][c-2].isOccupied()) ))
						{
							 blackPiecesCanEat.add(p);
						}
						if(row==1 && col==0 && ((b[r+1][c+1].isOccupied() && !b[r+2][c+2].isOccupied() &&(b[(r+1)][(c+1)].getPiece().getType() == PieceType.RED || b[(r+1)][(c+1)].getPiece().getType() == PieceType.RED_KING ) )|| 
								(b[r-1][c+1].isOccupied() && !b[7][c+2].isOccupied() && (b[r-1][c+1].getPiece().getType() == PieceType.RED ||b[r-1][c+1].getPiece().getType() == PieceType.RED_KING ) ) ||
								(b[r-1][7].isOccupied() && !b[7][6].isOccupied() && (b[r-1][7].getPiece().getType() == PieceType.RED ||b[r-1][7].getPiece().getType() == PieceType.RED_KING ))||
								(b[r+1][c+1].isOccupied() && !b[r+2][6].isOccupied() && (b[r+1][7].getPiece().getType() == PieceType.RED ||b[r+1][7].getPiece().getType() == PieceType.RED_KING )))) {
							 blackPiecesCanEat.add(p);
						}
						if(row==1 && col==6 && ((b[r+1][c+1].isOccupied() && !b[r+2][0].isOccupied() && (b[r+1][7].getPiece().getType() == PieceType.RED ||b[r+1][7].getPiece().getType() == PieceType.RED_KING ))||
								(b[0][7].isOccupied() && !b[7][0].isOccupied() &&(b[0][7].getPiece().getType() == PieceType.RED ||b[0][7].getPiece().getType() == PieceType.RED_KING))||
								(b[r-1][c-1].isOccupied() && !b[7][c-2].isOccupied() && (b[7][c-2].getPiece().getType() == PieceType.RED ||b[7][c-2].getPiece().getType() == PieceType.RED_KING) ) ||
								(b[(r+1)][(c-1)].isOccupied()&& !b[(r+2)][(c-2)].isOccupied() && (b[(r+1)][(c-1)].getPiece().getType() == PieceType.RED || b[(r+1)][(c-1)].getPiece().getType() == PieceType.RED_KING )))) {
							 blackPiecesCanEat.add(p);
						}
						if(row==0 && col<6 && col>1 && ((b[row+1][col+1].isOccupied() && !b[row+2][col+2].isOccupied() && (b[(r+1)][(c+1)].getPiece().getType() == PieceType.RED || b[(r+1)][(c+1)].getPiece().getType() == PieceType.RED_KING ) ) ||
								(b[(r+1)][(c-1)].isOccupied()&&  !b[(r+2)][(c-2)].isOccupied() && (b[(r+1)][(c-1)].getPiece().getType() == PieceType.RED || b[(r+1)][(c-1)].getPiece().getType() == PieceType.RED_KING ) )|| 
								(b[(r-1)%8+8][c+1].isOccupied() && !b[(r-2)%8+8][c+2].isOccupied() && ( b[(r-1)%8+8][c+1].getPiece().getType()== PieceType.RED || b[(r-1)%8+8][c+1].getPiece().getType()== PieceType.RED_KING ))||
								(b[(r-1)%8+8][c-1].isOccupied() && !b[(r-2)%8+8][c-2].isOccupied()) && (b[(r-1)%8+8][c-1].getPiece().getType() == PieceType.RED || b[(r-1)%8+8][c-1].getPiece().getType() == PieceType.RED_KING) )) {
							 blackPiecesCanEat.add(p);
						}
						if(row==0 && col==7 && ( ( b[7][0].isOccupied() && !b[6][1].isOccupied() && (b[7][0].getPiece().getType() == PieceType.RED || b[7][0].getPiece().getType() == PieceType.RED_KING )  ) ||
								(b[r+1][6].isOccupied() && !b[r+2][5].isOccupied() && (b[r+1][6].getPiece().getType() == PieceType.RED || b[r+1][6].getPiece().getType() == PieceType.RED_KING)) || 
								(b[r+1][0].isOccupied() && !b[r+2][1].isOccupied() && (b[r+1][0].getPiece().getType() == PieceType.RED || b[r+1][0].getPiece().getType() == PieceType.RED_KING)) ||
								(b[7][c-1].isOccupied() && !b[6][c-2].isOccupied() && (b[7][c-1].getPiece().getType() == PieceType.RED || b[7][c-1].getPiece().getType() == PieceType.RED_KING)))) {
							 blackPiecesCanEat.add(p);
						}
						if(row==0 && col==1 && ( ( b[r+1][c+1].isOccupied() && !b[r+2][c+2].isOccupied() && ( b[r+1][c+1].getPiece().getType() == PieceType.RED || b[r+1][c+1].getPiece().getType() == PieceType.RED_KING) ) ||  
								(b[1][0].isOccupied() && !b[2][7].isOccupied()) && (b[1][0].getPiece().getType() == PieceType.RED || b[1][0].getPiece().getType() == PieceType.RED_KING) ||
								(b[7][2].isOccupied() && !b[6][3].isOccupied() && (b[7][2].getPiece().getType() == PieceType.RED||b[7][2].getPiece().getType() == PieceType.RED_KING)) || 
								(b[7][0].isOccupied() && !b[6][7].isOccupied() && (b[7][0].getPiece().getType() == PieceType.RED || b[7][0].getPiece().getType() == PieceType.RED_KING)))) {
							 blackPiecesCanEat.add(p);
						}
						if(col == 1 && row> 1 && row<7 && (( b[r+1][c+1].isOccupied() && !b[r+2][c+2].isOccupied() && (b[(r+1)][(c+1)].getPiece().getType() == PieceType.RED || b[(r+1)][(c+1)].getPiece().getType() == PieceType.RED_KING ) )||
								(b[(r-1)][(c+1)].isOccupied() &&  !b[(r-2)][(c+2)].isOccupied() && (b[(r-1)][(c+1)].getPiece().getType() == PieceType.RED || b[(r-1)][(c+1)].getPiece().getType() == PieceType.RED_KING ))|| 
								(b[r-1][c-1].isOccupied() && !b[r-2][(c-2)%8+8].isOccupied() && (b[r-1][c-1].getPiece().getType() == PieceType.RED ||b[r-1][c-1].getPiece().getType() == PieceType.RED_KING))|| 
								(b[r-1][c+1].isOccupied() && !b[r-2][(c-2)%8+8].isOccupied() && (b[r-1][c+1].getPiece().getType() == PieceType.RED || b[r-1][c+1].getPiece().getType() == PieceType.RED_KING)))) {
							 blackPiecesCanEat.add(p);
						}
						if(col == 0 && row> 1 && row<7 &&( ( b[r-1][7].isOccupied() && !b[r-2][6].isOccupied() && (b[r-1][7].getPiece().getType() == PieceType.RED || b[r-1][7].getPiece().getType() == PieceType.RED_KING ) ) ||
								(b[r+1][c+1].isOccupied() && !b[r+2][c+2].isOccupied() && (b[(r+1)][(c+1)].getPiece().getType() == PieceType.RED || b[(r+1)][(c+1)].getPiece().getType() == PieceType.RED_KING ))||
								(b[(r-1)][(c+1)].isOccupied()&& !b[(r-2)][(c+2)].isOccupied() &&(b[(r-1)][(c+1)].getPiece().getType() == PieceType.RED || b[(r-1)][(c+1)].getPiece().getType() == PieceType.RED_KING )))) {
							 blackPiecesCanEat.add(p);
						}
						if(row==6&&col<6&&col>1&&((b[row-1][col+1].isOccupied()&&!b[row-2][col+2].isOccupied()&&(b[row-1][col+1].getPiece().getType()==PieceType.RED||b[row-1][col+1].getPiece().getType()==PieceType.RED_KING))||
								(b[row-1][col-1].isOccupied()&&!b[row-2][col-2].isOccupied()&&(b[row-1][col-1].getPiece().getType()==PieceType.RED||b[row-1][col-1].getPiece().getType()==PieceType.RED_KING))||
								(b[row+1][col+1].isOccupied()&&!b[0][col+2].isOccupied()&&(b[row+1][col+1].getPiece().getType()==PieceType.RED||b[row+1][col+1].getPiece().getType()==PieceType.RED_KING))||
								(b[row+1][col-1].isOccupied()&&!b[0][col-2].isOccupied()&&(b[row+1][col-1].getPiece().getType()==PieceType.RED||b[row+1][col-1].getPiece().getType()==PieceType.RED_KING)))) {
							 blackPiecesCanEat.add(p);
						}
						if(row==6&&col==1&&((b[row-1][col+1].isOccupied()&&!b[row-2][col+2].isOccupied()&&(b[row-1][col+1].getPiece().getType()==PieceType.RED||b[row-1][col+1].getPiece().getType()==PieceType.RED_KING))||
								(b[7][0].isOccupied()&&!b[0][7].isOccupied()&&(b[7][0].getPiece().getType()==PieceType.RED||b[7][0].getPiece().getType()==PieceType.RED_KING))||
								(b[7][2].isOccupied()&&!b[0][3].isOccupied()&&(b[7][2].getPiece().getType()==PieceType.RED||b[7][2].getPiece().getType()==PieceType.RED_KING))||
								(b[5][0].isOccupied()&&!b[4][7].isOccupied()&&(b[5][0].getPiece().getType()==PieceType.RED||b[5][0].getPiece().getType()==PieceType.RED_KING)))) {
							 blackPiecesCanEat.add(p);
						}
						if(row==6&&col==7&&((b[row-1][col-1].isOccupied()&&!b[row-2][col-2].isOccupied()&&(b[row-1][col-1].getPiece().getType()==PieceType.RED||b[row-1][col-1].getPiece().getType()==PieceType.RED_KING))
								||(b[7][6].isOccupied()&&!b[1][4].isOccupied()&&(b[7][6].getPiece().getType()==PieceType.RED||b[7][6].getPiece().getType()==PieceType.RED_KING))||
								(b[5][0].isOccupied()&&!b[4][1].isOccupied()&&(b[5][0].getPiece().getType()==PieceType.RED||b[5][0].getPiece().getType()==PieceType.RED_KING))||
								(b[7][0].isOccupied()&&!b[0][1].isOccupied()&&(b[7][0].getPiece().getType()==PieceType.RED||b[7][0].getPiece().getType()==PieceType.RED_KING)))) {
							 blackPiecesCanEat.add(p);
						}
						
						if(row==7&&col>1&&col<6&&((b[row-1][col-1].isOccupied()&&!b[row-2][col-2].isOccupied()&&(b[row-1][col-1].getPiece().getType()==PieceType.RED||b[row-1][col-1].getPiece().getType()==PieceType.RED_KING))||
								(b[row-1][col+1].isOccupied()&&!b[row-2][col+2].isOccupied()&&(b[row-1][col+1].getPiece().getType()==PieceType.RED||b[row-1][col+1].getPiece().getType()==PieceType.RED_KING))||
								(b[(row+1)%8][col-1].isOccupied()&&!b[(row+2)%8][col-2].isOccupied()&&(b[(row+1)%8][col-1].getPiece().getType()==PieceType.RED||b[(row+1)%8][col-1].getPiece().getType()==PieceType.RED_KING))||
								(b[(row+1)%8][col+1].isOccupied()&&!b[(row+2)%8][col+2].isOccupied()&&(b[(row+1)%8][col+1].getPiece().getType()==PieceType.RED||b[(row+1)%8][col+1].getPiece().getType()==PieceType.RED_KING)))){
							 blackPiecesCanEat.add(p);
								}
						if(row==7&&col==0&&((b[row-1][col+1].isOccupied()&&!b[row-2][col+2].isOccupied()&&(b[row-1][col+1].getPiece().getType()==PieceType.RED||b[row-1][col+1].getPiece().getType()==PieceType.RED_KING))||
								(b[(row+1)%8][col+1].isOccupied()&&!b[(row+2)%8][col+2].isOccupied()&&(b[(row+1)%8][col+1].getPiece().getType()==PieceType.RED||b[(row+1)%8][col+1].getPiece().getType()==PieceType.RED_KING))||
								(b[row-1][(col-1)%8+8].isOccupied()&&!b[row-2][(col-2)%8+8].isOccupied()&&(b[(row+1)%8][col+1].getPiece().getType()==PieceType.RED||b[(row+1)%8][col+1].getPiece().getType()==PieceType.RED_KING))||
								(b[(row+1)%8][(col-1)%8+8].isOccupied()&&!b[(row+2)%8][(col-2)%8+8].isOccupied()&&(b[(row+1)%8][(col-1)%8+8].getPiece().getType()==PieceType.RED||b[(row+1)%8][(col-1)%8+8].getPiece().getType()==PieceType.RED_KING)))) {
							 blackPiecesCanEat.add(p);
						}
						if(row==7&&col==6&&((b[row-1][col-1].isOccupied()&&!b[row-2][col-2].isOccupied()&&(b[row-1][col-1].getPiece().getType()==PieceType.RED||b[row-1][col-1].getPiece().getType()==PieceType.RED_KING))||
								(b[row-1][col+1].isOccupied()&&!b[row-2][(col+2)%8].isOccupied()&&(b[row-1][col+1].getPiece().getType()==PieceType.RED||b[row-1][col+1].getPiece().getType()==PieceType.RED_KING))||
								(b[(row+1)%8][col-1].isOccupied()&&!b[(row+2)%8][col-2].isOccupied()&&(b[(row+1)%8][col-1].getPiece().getType()==PieceType.RED||b[(row+1)%8][col-1].getPiece().getType()==PieceType.RED_KING))||
								(b[(row+1)%8][col+1].isOccupied()&&!b[row+2][(col+2)%8].isOccupied()&&(b[(row+1)%8][col+1].getPiece().getType()==PieceType.RED||b[(row+1)%8][col+1].getPiece().getType()==PieceType.RED_KING)))) {
							 blackPiecesCanEat.add(p);
							
						}
						if(row<6&&row>1&&col==6&&((b[row-1][col-1].isOccupied()&&!b[row-2][col-2].isOccupied()&&(b[row-1][col-1].getPiece().getType()==PieceType.RED||b[row-1][col-1].getPiece().getType()==PieceType.RED_KING))||
								(b[row+1][col-1].isOccupied()&&!b[row+2][col-2].isOccupied()&&(b[row+1][col-1].getPiece().getType()==PieceType.RED||b[row+1][col-1].getPiece().getType()==PieceType.RED_KING))||
								(b[row-1][col+1].isOccupied()&&!b[row-2][(col+2)%8].isOccupied()&&(b[row-1][col+1].getPiece().getType()==PieceType.RED||b[row-1][col+1].getPiece().getType()==PieceType.RED_KING))||
								(b[row+1][col+1].isOccupied()&&!b[row+2][(col+2)%8].isOccupied()&&(b[row+1][col+1].getPiece().getType()==PieceType.RED||b[row+1][col+1].getPiece().getType()==PieceType.RED_KING)))) {
							 blackPiecesCanEat.add(p);
						}
						if(row<6 && row>1 && col==7 &&((b[row-1][col-1].isOccupied()&&!b[row-2][col-2].isOccupied()&&(b[row-1][col-1].getPiece().getType()==PieceType.RED||b[row-1][col-1].getPiece().getType()==PieceType.RED_KING))||
								(b[row+1][col-1].isOccupied()&&!b[row+2][col-2].isOccupied()&&(b[row+1][col-1].getPiece().getType()==PieceType.RED||b[row+1][col-1].getPiece().getType()==PieceType.RED_KING))||
								(b[row+1][(col+1)%8].isOccupied()&&!b[row+2][(col+2)%8].isOccupied()&&(b[row+1][(col+1)%8].getPiece().getType()==PieceType.RED||b[row+1][(col+1)%8].getPiece().getType()==PieceType.RED_KING))||
								(b[row-1][(col+1)%8].isOccupied()&&!b[row-2][(col+2)%8].isOccupied()&&(b[row-1][(col+1)%8].getPiece().getType()==PieceType.RED||b[row-1][(col+1)%8].getPiece().getType()==PieceType.RED_KING)))) {
							 blackPiecesCanEat.add(p);
						}
					}
				}
				return blackPiecesCanEat;
			} 
		}
	
	
		public static void colorRedTile() {
			ArrayList<Pieces> piecesCanEatTurn = PiecesCanEat();
			ArrayList<Tiles> array = playerTilesAvailableForRed();
				if(piecesCanEatTurn.size()==0 && array.size()>0) {
					Tiles t = getRandomTile(array);
					t.setType(TileType.RED);
				}
			}
	

	
	
		public static void colorBlueTile() { /// we must do in the move if we stand in this tile what will happened 
			Tiles[][] b = getTheBoard();
			ArrayList<Pieces> redPieces = new ArrayList<>();
			ArrayList<Pieces> blackPieces = new ArrayList<>();
			ArrayList<Tiles> nullTiles =  new ArrayList<>();
			int redQueenCounter =0;
			int redPieceCounter=0;
			int blackQueenCounter=0;
			int blackPieceCounter=0;
			for(int i = 0; i < b.length; i++) {
				for(int j = 0; j < b[0].length; j++) {
					if(b[i][j].isOccupied() && ((i+j) % 2 == 1) // gray tiles 
							&& (b[i][j].getPiece().getType() == PieceType.RED || b[i][j].getPiece().getType() == PieceType.RED_KING)) {
						if(b[i][j].getPiece().getType() == PieceType.RED)
							redPieceCounter++;
						else
							redQueenCounter++;
						redPieces.add(new Pieces(i, j, b[i][j].getPiece().getType()));
					} else if(b[i][j].isOccupied()&&  ((i+j) % 2 == 1)) {
						if(b[i][j].getPiece().getType() == PieceType.BLACK)
							blackPieceCounter++;
						else
							blackQueenCounter++;
						blackPieces.add(new Pieces(i, j, b[i][j].getPiece().getType()));
					} else if (!b[i][j].isOccupied() &&  ((i+j) % 2 == 1)) {
						nullTiles.add(b[i][j]);
					}
	
				}
			}
			if (turnCounter % 2 == 1 ) {
				if (redPieces.size()==3) { // red turn
					if(redQueenCounter == 1 && redPieceCounter == 2 ) {
						Tiles t = getRandomTile(nullTiles);
						t.setType(TileType.BLUE);			
				}
			} 
			}else { // black turn
				if(turnCounter % 2 == 0 )
					if(blackPieces.size()==3) {
						if(blackQueenCounter == 1 && blackPieceCounter == 2 ) {
							Tiles t = getRandomTile(nullTiles);
							t.setType(TileType.BLUE);		
					}
				}
			}
		}
	
		public static ArrayList<Tiles> playerTilesAvailable () { /// returns an Array that have all the tiles that available
			Tiles[][] b = getTheBoard();
			ArrayList<Pieces> redPieces = new ArrayList<>();
			ArrayList<Pieces> blackPieces = new ArrayList<>();
			ArrayList<Tiles> redPlayerTiles = new ArrayList<>();
			ArrayList<Tiles> blackPlayerTiles = new ArrayList<>();
			Pieces p ;
			for(int i = 0; i < b.length; i++) {
				for(int j = 0; j < b[0].length; j++) {
					p = b[i][j].getPiece();
					if(b[i][j].isOccupied() && 
							( b[i][j].getPiece().getType() == PieceType.RED || b[i][j].getPiece().getType() == PieceType.RED_KING)) {
						redPieces.add(new Pieces(i, j, b[i][j].getPiece().getType()));
					} else if(b[i][j].isOccupied() && p.canEatOrMove(Board.getTheBoard())) {
						blackPieces.add(new Pieces(i, j, b[i][j].getPiece().getType()));
					}
				}
			}
	
			for(Pieces p1 : redPieces) {
				if(p1.getRow()+1<8 && p1.getCol()+1<8 && !b[p1.getRow()+1][p1.getCol()+1].isOccupied() && p1.getType() == PieceType.RED) {
					redPlayerTiles.add(b[p1.getRow()+1][p1.getCol()+1]);
				}
				if(p1.getRow()+1<8 && p1.getCol()-1>=0 && !b[p1.getRow()+1][p1.getCol()-1].isOccupied() && p1.getType() == PieceType.RED) {
					redPlayerTiles.add(b[p1.getRow()+1][p1.getCol()-1]);
				}
				if(p1.getRow()+2<8 && p1.getCol()+2<8 && !b[p1.getRow()+2][p1.getCol()+2].isOccupied() && b[p1.getRow()+1][p1.getCol()+1].isOccupied() && p1.getType()==PieceType.RED 
						&& (b[p1.getRow()+1][p1.getCol()+1].getPiece().getType()==PieceType.BLACK || b[p1.getRow()+1][p1.getCol()+1].getPiece().getType()==PieceType.BLACK_KING)) {
					redPlayerTiles.add(b[p1.getRow()+2][p1.getCol()+2]);
				}
				if(p1.getRow()+2<8 && p1.getCol()-2>=0 && !b[p1.getRow()+2][p1.getCol()-2].isOccupied() && b[p1.getRow()+1][p1.getCol()-1].isOccupied() && p1.getType()==PieceType.RED 
						&& (b[p1.getRow()+1][p1.getCol()-1].getPiece().getType()==PieceType.BLACK || b[p1.getRow()+1][p1.getCol()-1].getPiece().getType()==PieceType.BLACK_KING)) {
					redPlayerTiles.add(b[p1.getRow()+2][p1.getCol()-2]);
				}
				if(p1.getType() == PieceType.RED_KING ) {
					if(!b[(p1.getRow()+1)%8][(p1.getCol()+1)%8].isOccupied()) { /// + , +
						redPlayerTiles.add(b[(p1.getRow()+1)%8][(p1.getCol()+1)%8]);
					}
					if(p1.getRow()-1 == -1 && p1.getCol()-1>=0 && !b[(p1.getRow()-1)%8+8][(p1.getCol()-1)].isOccupied()) { /// - , -
						redPlayerTiles.add(b[(p1.getRow()-1)%8+8][(p1.getCol()-1)]);
					}
					if(p1.getRow()-1>=0 && p1.getCol()-1==-1 &&!b[(p1.getRow()-1)][(p1.getCol()-1)%8+8].isOccupied()) {/// - , -
						redPlayerTiles.add(b[(p1.getRow()-1)][(p1.getCol()-1)%8+8]);
					}
					if(p1.getRow()-1>=0 && p1.getCol()-1>=0 && !b[(p1.getRow()-1)][(p1.getCol()-1)].isOccupied()) {/// - , -
						redPlayerTiles.add(b[(p1.getRow()-1)][(p1.getCol()-1)]);
					}
					if(p1.getRow()-1>=0 && !b[(p1.getRow()-1)][(p1.getCol()+1)%8].isOccupied() ) { // - , +
						redPlayerTiles.add(b[(p1.getRow()-1)][(p1.getCol()+1)%8]);
					}
					if(p1.getRow()-1==-1 && !b[(p1.getRow()-1)%8+8][(p1.getCol()+1)%8].isOccupied()) {// - , +
						redPlayerTiles.add(b[(p1.getRow()-1)%8+8][(p1.getCol()+1)%8]);
					}
					if(p1.getCol()-1>=0 && !b[(p1.getRow()+1)%8][(p1.getCol()-1)].isOccupied() ) {/// + , -
						redPlayerTiles.add(b[(p1.getRow()+1)%8][(p1.getCol()-1)]);	
					}
					if(p1.getCol()-1==-1 && !b[(p1.getRow()+1)%8][(p1.getCol()-1)%8+8].isOccupied() ) {/// + , -
						redPlayerTiles.add(b[(p1.getRow()+1)%8][(p1.getCol()-1)%8+8]);	
					}
					if(!b[(p1.getRow()+2)%8][(p1.getCol()+2)%8].isOccupied() && b[(p1.getRow()+1)%8][(p1.getCol()+1)%8].isOccupied() 
							&& (b[(p1.getRow()+1)%8][(p1.getCol()+1)%8].getPiece().getType()==PieceType.BLACK || b[(p1.getRow()+1)%8][(p1.getCol()+1)%8].getPiece().getType()==PieceType.BLACK_KING)) {
						redPlayerTiles.add(b[(p1.getRow()+2)%8][(p1.getCol()+2)%8]);
					}
					if(!b[(p1.getRow()+2)%8][(p1.getCol()-2+8)%8].isOccupied() && b[(p1.getRow()+1)%8][(p1.getCol()-1+8)%8].isOccupied() 
							&& (b[(p1.getRow()+1)%8][(p1.getCol()-1+8)%8].getPiece().getType()==PieceType.BLACK || b[(p1.getRow()+1)%8][(p1.getCol()-1+8)%8].getPiece().getType()==PieceType.BLACK_KING)) {
						redPlayerTiles.add(b[(p1.getRow()+2)%8][(p1.getCol()-2+8)%8]);
					}
					if(!b[(p1.getRow()+8-2)%8][(p1.getCol()+8-2)%8].isOccupied() && b[(p1.getRow()+8-1)%8][(p1.getCol()+8-1)%8].isOccupied() 
							&& (b[(p1.getRow()+8-1)%8][(p1.getCol()+8-1)%8].getPiece().getType()==PieceType.BLACK || b[(p1.getRow()+8-1)%8][(p1.getCol()+8-1)%8].getPiece().getType()==PieceType.BLACK_KING)) {
						redPlayerTiles.add(b[(p1.getRow()+8-2)%8][(p1.getCol()+8-2)%8]);
					}
					if(!b[(p1.getRow()+8-2)%8][(p1.getCol()+2)%8].isOccupied() && b[(p1.getRow()+8-1)%8][(p1.getCol()+1)%8].isOccupied() 
							&& (b[(p1.getRow()+8-1)%8][(p1.getCol()+1)%8].getPiece().getType()==PieceType.BLACK || b[(p1.getRow()-1+8)%8][(p1.getCol()+1)%8].getPiece().getType()==PieceType.BLACK_KING)) {
						redPlayerTiles.add(b[(p1.getRow()-2+8)%8][(p1.getCol()+2)%8]);
					}
				}
			}
			for(Pieces p1 : blackPieces) {
				if(p1.getRow()-1>=0 && p1.getCol()-1>=0 && !b[p1.getRow()-1][p1.getCol()-1].isOccupied() && p1.getType() == PieceType.BLACK) {
					blackPlayerTiles.add(b[p1.getRow()-1][p1.getCol()-1]);
				}
				if(p1.getRow()-1>=0 && p1.getCol()+1<8 && !b[p1.getRow()-1][p1.getCol()+1].isOccupied() && p1.getType() == PieceType.BLACK) {
					blackPlayerTiles.add(b[p1.getRow()-1][p1.getCol()+1]);
				}
				if(p1.getRow()-2>=0 && p1.getCol()-2>=0 && b[p1.getRow()-1][p1.getCol()-1].isOccupied() && !b[p1.getRow()-2][p1.getCol()-2].isOccupied() && p1.getType() == PieceType.BLACK 
						&& (b[p1.getRow()-1][p1.getCol()-1].getPiece().getType() == PieceType.RED ||b[p1.getRow()-1][p1.getCol()-1].getPiece().getType() == PieceType.RED_KING )) {	
					blackPlayerTiles.add(b[p1.getRow()-2][p1.getCol()-2]);
				}
				if(p1.getRow()-2>=0 && p1.getCol()+2<8 && b[p1.getRow()-1][p1.getCol()+1].isOccupied() && !b[p1.getRow()-2][p1.getCol()+2].isOccupied() && p1.getType() == PieceType.BLACK 
						&& (b[p1.getRow()-1][p1.getCol()+1].getPiece().getType() == PieceType.RED ||b[p1.getRow()-1][p1.getCol()+1].getPiece().getType() == PieceType.RED_KING )) {	
					blackPlayerTiles.add(b[p1.getRow()-2][p1.getCol()+2]);
				}
				if(p1.getRow()-1>=0 && p1.getCol()+1<8 && !b[p1.getRow()-1][p1.getCol()+1].isOccupied() && p1.getType() == PieceType.BLACK) {
					blackPlayerTiles.add(b[p1.getRow()-1][p1.getCol()+1]);
				}
				if(p1.getType() == PieceType.BLACK_KING ) {
					if(!b[(p1.getRow()+1)%8][(p1.getCol()+1)%8].isOccupied()) { /// + , +
						blackPlayerTiles.add(b[(p1.getRow()+1)%8][(p1.getCol()+1)%8]);
					}
					if(p1.getRow()-1 == -1 && p1.getCol()-1>=0 && !b[(p1.getRow()-1)%8+8][(p1.getCol()-1)].isOccupied()) { /// - , -
						blackPlayerTiles.add(b[(p1.getRow()-1)%8+8][(p1.getCol()-1)]);
					}
					if(p1.getRow()-1>=0 && p1.getCol()-1==-1 &&!b[(p1.getRow()-1)][(p1.getCol()-1)%8+8].isOccupied()) {/// - , -
						blackPlayerTiles.add(b[(p1.getRow()-1)][(p1.getCol()-1)%8+8]);
					}
					if(p1.getRow()-1>=0 && p1.getCol()-1>=0 && !b[(p1.getRow()-1)][(p1.getCol()-1)].isOccupied()) {/// - , -
						blackPlayerTiles.add(b[(p1.getRow()-1)][(p1.getCol()-1)]);
					}
					if(p1.getRow()-1>=0 && !b[(p1.getRow()-1)][(p1.getCol()+1)%8].isOccupied() ) { // - , +
						blackPlayerTiles.add(b[(p1.getRow()-1)][(p1.getCol()+1)%8]);
					}
					if(p1.getRow()-1==-1 && !b[(p1.getRow()-1)%8+8][(p1.getCol()+1)%8].isOccupied()) {// - , +
						blackPlayerTiles.add(b[(p1.getRow()-1)%8+8][(p1.getCol()+1)%8]);
					}
					if(p1.getCol()-1>=0 && !b[(p1.getRow()+1)%8][(p1.getCol()-1)].isOccupied() ) {/// + , -
						blackPlayerTiles.add(b[(p1.getRow()+1)%8][(p1.getCol()-1)]);	
					}
					if(p1.getCol()-1==-1 && !b[(p1.getRow()+1)%8][(p1.getCol()-1)%8+8].isOccupied() ) {/// + , -
						blackPlayerTiles.add(b[(p1.getRow()+1)%8][(p1.getCol()-1)%8+8]);	
					}
					if(!b[(p1.getRow()+2)%8][(p1.getCol()+2)%8].isOccupied() && b[(p1.getRow()+1)%8][(p1.getCol()+1)%8].isOccupied() 
							&& (b[(p1.getRow()+1)%8][(p1.getCol()+1)%8].getPiece().getType()==PieceType.RED || b[(p1.getRow()+1)%8][(p1.getCol()+1)%8].getPiece().getType()==PieceType.RED_KING)) {
						blackPlayerTiles.add(b[(p1.getRow()+2)%8][(p1.getCol()+2)%8]);
					}
					if(!b[(p1.getRow()+2)%8][(p1.getCol()-2+8)%8].isOccupied() && b[(p1.getRow()+1)%8][(p1.getCol()-1+8)%8].isOccupied() 
							&& (b[(p1.getRow()+1)%8][(p1.getCol()-1+8)%8].getPiece().getType()==PieceType.RED || b[(p1.getRow()+1)%8][(p1.getCol()-1+8)%8].getPiece().getType()==PieceType.RED_KING)) {
						blackPlayerTiles.add(b[(p1.getRow()+2)%8][(p1.getCol()-2+8)%8]);
					}
					if(!b[(p1.getRow()+8-2)%8][(p1.getCol()+8-2)%8].isOccupied() && b[(p1.getRow()+8-1)%8][(p1.getCol()+8-1)%8].isOccupied() 
							&& (b[(p1.getRow()+8-1)%8][(p1.getCol()+8-1)%8].getPiece().getType()==PieceType.RED || b[(p1.getRow()+8-1)%8][(p1.getCol()+8-1)%8].getPiece().getType()==PieceType.RED_KING)) {
						blackPlayerTiles.add(b[(p1.getRow()+8-2)%8][(p1.getCol()+8-2)%8]);
					}
					if(!b[(p1.getRow()+8-2)%8][(p1.getCol()+2)%8].isOccupied() && b[(p1.getRow()+8-1)%8][(p1.getCol()+1)%8].isOccupied() 
							&& (b[(p1.getRow()+8-1)%8][(p1.getCol()+1)%8].getPiece().getType()==PieceType.RED || b[(p1.getRow()-1+8)%8][(p1.getCol()+1)%8].getPiece().getType()==PieceType.RED_KING)) {
						blackPlayerTiles.add(b[(p1.getRow()-2+8)%8][(p1.getCol()+2)%8]);
					}
				}
			}
	
			if (turnCounter % 2 == 1) {
				return redPlayerTiles;
			} else {
				return blackPlayerTiles;
			}
	
		}
	
		
		
		
		public static ArrayList<Tiles> playerTilesAvailableForRed () { /// returns an Array that have all the tiles that available
			Tiles[][] b = getTheBoard();
			ArrayList<Pieces> redPieces = new ArrayList<>();
			ArrayList<Pieces> blackPieces = new ArrayList<>();
			ArrayList<Tiles> redPlayerTiles = new ArrayList<>();
			ArrayList<Tiles> blackPlayerTiles = new ArrayList<>();
			Pieces p ;
			for(int i = 0; i < b.length; i++) {
				for(int j = 0; j < b[0].length; j++) {
					p = b[i][j].getPiece();
					if(b[i][j].isOccupied() && 
							( b[i][j].getPiece().getType() == PieceType.RED || b[i][j].getPiece().getType() == PieceType.RED_KING)) {
						redPieces.add(new Pieces(i, j, b[i][j].getPiece().getType()));
					} else if(b[i][j].isOccupied() && p.canEatOrMove(Board.getTheBoard())) {
						blackPieces.add(new Pieces(i, j, b[i][j].getPiece().getType()));
					}
				}
			}
	
			for(Pieces p1 : redPieces) {
				if(p1.getRow()+1<8 && p1.getCol()+1<8 && p1.getRow()+2<8 &&p1.getCol()+2<8 && p1.getRow()+3<8 && p1.getCol()+3<8 && !b[p1.getRow()+1][p1.getCol()+1].isOccupied() &&(!b[p1.getRow()+2][p1.getCol()+2].isOccupied()||
						b[p1.getRow()+2][p1.getCol()+2].isOccupied() &&!b[p1.getRow()+3][p1.getCol()+3].isOccupied()&&(b[p1.getRow()+2][p1.getCol()+2].getPiece().getType()==PieceType.BLACK||b[p1.getRow()+2][p1.getCol()+2].getPiece().getType()==PieceType.BLACK_KING))
						&& p1.getType() == PieceType.RED) {
					redPlayerTiles.add(b[p1.getRow()+1][p1.getCol()+1]);
				}
				if(p1.getRow()+1<8 && p1.getCol()-1>=0 && p1.getRow()+2<8 &&p1.getCol()-2>=0 && p1.getRow()+3<8 && p1.getCol()-3>=0 && !b[p1.getRow()+1][p1.getCol()-1].isOccupied() &&(!b[p1.getRow()+2][p1.getCol()-2].isOccupied()||
						b[p1.getRow()+2][p1.getCol()-2].isOccupied()&& !b[p1.getRow()+3][p1.getCol()-3].isOccupied() && (b[p1.getRow()+2][p1.getCol()-2].getPiece().getType()==PieceType.BLACK||b[p1.getRow()+2][p1.getCol()-2].getPiece().getType()==PieceType.BLACK_KING)  )
						&& p1.getType() == PieceType.RED) {
					redPlayerTiles.add(b[p1.getRow()+1][p1.getCol()-1]);
				}
				if(p1.getType() == PieceType.RED_KING ) {
					if(!b[(p1.getRow()+1)%8][(p1.getCol()+1)%8].isOccupied()) { /// + , +
						redPlayerTiles.add(b[(p1.getRow()+1)%8][(p1.getCol()+1)%8]);
					}
					if(p1.getRow()-1 == -1 && p1.getCol()-1>=0 && !b[(p1.getRow()-1)%8+8][(p1.getCol()-1)].isOccupied()) { /// - , -
						redPlayerTiles.add(b[(p1.getRow()-1)%8+8][(p1.getCol()-1)]);
					}
					if(p1.getRow()-1>=0 && p1.getCol()-1==-1 &&!b[(p1.getRow()-1)][(p1.getCol()-1)%8+8].isOccupied()) {/// - , -
						redPlayerTiles.add(b[(p1.getRow()-1)][(p1.getCol()-1)%8+8]);
					}
					if(p1.getRow()-1>=0 && p1.getCol()-1>=0 && !b[(p1.getRow()-1)][(p1.getCol()-1)].isOccupied()) {/// - , -
						redPlayerTiles.add(b[(p1.getRow()-1)][(p1.getCol()-1)]);
					}
					if(p1.getRow()-1>=0 && !b[(p1.getRow()-1)][(p1.getCol()+1)%8].isOccupied() ) { // - , +
						redPlayerTiles.add(b[(p1.getRow()-1)][(p1.getCol()+1)%8]);
					}
					if(p1.getRow()-1==-1 && !b[(p1.getRow()-1)%8+8][(p1.getCol()+1)%8].isOccupied()) {// - , +
						redPlayerTiles.add(b[(p1.getRow()-1)%8+8][(p1.getCol()+1)%8]);
					}
					if(p1.getCol()-1>=0 && !b[(p1.getRow()+1)%8][(p1.getCol()-1)].isOccupied() ) {/// + , -
						redPlayerTiles.add(b[(p1.getRow()+1)%8][(p1.getCol()-1)]);	
					}
					if(p1.getCol()-1==-1 && !b[(p1.getRow()+1)%8][(p1.getCol()-1)%8+8].isOccupied() ) {/// + , -
						redPlayerTiles.add(b[(p1.getRow()+1)%8][(p1.getCol()-1)%8+8]);	
					}
				}
			}
			for(Pieces p1 : blackPieces) {
				if(p1.getRow()-1>=0 && p1.getCol()-1>=0 && p1.getRow()-2>=0 &&p1.getCol()-2>=0 && p1.getRow()-3>=0 && p1.getCol()-3>=0 && !b[p1.getRow()-1][p1.getCol()-1].isOccupied() &&(!b[p1.getRow()-2][p1.getCol()-2].isOccupied()||
						b[p1.getRow()-2][p1.getCol()-2].isOccupied()&& !b[p1.getRow()-3][p1.getCol()-3].isOccupied() && (b[p1.getRow()-2][p1.getCol()-2].getPiece().getType()==PieceType.RED||b[p1.getRow()-2][p1.getCol()-2].getPiece().getType()==PieceType.RED_KING)  )
						&& p1.getType() == PieceType.BLACK) {
					blackPlayerTiles.add(b[p1.getRow()-1][p1.getCol()-1]);
				}
				if(p1.getRow()-1>=0 && p1.getCol()+1<8 && p1.getRow()-2>=0 &&p1.getCol()+2<8 && p1.getRow()-3>=0 && p1.getCol()+3<8 && !b[p1.getRow()-1][p1.getCol()+1].isOccupied() &&(!b[p1.getRow()-2][p1.getCol()+2].isOccupied()||
						b[p1.getRow()-2][p1.getCol()+2].isOccupied()&& !b[p1.getRow()-3][p1.getCol()+3].isOccupied() && (b[p1.getRow()-2][p1.getCol()+2].getPiece().getType()==PieceType.RED||b[p1.getRow()-2][p1.getCol()+2].getPiece().getType()==PieceType.RED_KING)  )
						&& p1.getType() == PieceType.BLACK) {
					blackPlayerTiles.add(b[p1.getRow()-1][p1.getCol()+1]);
				}
				if(p1.getType() == PieceType.BLACK_KING ) {
					if(!b[(p1.getRow()+1)%8][(p1.getCol()+1)%8].isOccupied()) { /// + , +
						blackPlayerTiles.add(b[(p1.getRow()+1)%8][(p1.getCol()+1)%8]);
					}
					if(p1.getRow()-1 == -1 && p1.getCol()-1>=0 && !b[(p1.getRow()-1)%8+8][(p1.getCol()-1)].isOccupied()) { /// - , -
						blackPlayerTiles.add(b[(p1.getRow()-1)%8+8][(p1.getCol()-1)]);
					}
					if(p1.getRow()-1>=0 && p1.getCol()-1==-1 &&!b[(p1.getRow()-1)][(p1.getCol()-1)%8+8].isOccupied()) {/// - , -
						blackPlayerTiles.add(b[(p1.getRow()-1)][(p1.getCol()-1)%8+8]);
					}
					if(p1.getRow()-1>=0 && p1.getCol()-1>=0 && !b[(p1.getRow()-1)][(p1.getCol()-1)].isOccupied()) {/// - , -
						blackPlayerTiles.add(b[(p1.getRow()-1)][(p1.getCol()-1)]);
					}
					if(p1.getRow()-1>=0 && !b[(p1.getRow()-1)][(p1.getCol()+1)%8].isOccupied() ) { // - , +
						blackPlayerTiles.add(b[(p1.getRow()-1)][(p1.getCol()+1)%8]);
					}
					if(p1.getRow()-1==-1 && !b[(p1.getRow()-1)%8+8][(p1.getCol()+1)%8].isOccupied()) {// - , +
						blackPlayerTiles.add(b[(p1.getRow()-1)%8+8][(p1.getCol()+1)%8]);
					}
					if(p1.getCol()-1>=0 && !b[(p1.getRow()+1)%8][(p1.getCol()-1)].isOccupied() ) {/// + , -
						blackPlayerTiles.add(b[(p1.getRow()+1)%8][(p1.getCol()-1)]);	
					}
					if(p1.getCol()-1==-1 && !b[(p1.getRow()+1)%8][(p1.getCol()-1)%8+8].isOccupied() ) {/// + , -
						blackPlayerTiles.add(b[(p1.getRow()+1)%8][(p1.getCol()-1)%8+8]);	
					}
				}
			}
	
			if (turnCounter % 2 == 1) {
				return redPlayerTiles;
			} else {
				return blackPlayerTiles;
			}
	
		}
		
		
		
		
		public int returnTurns() {
			return turnCounter;
		}
	
		public static Tiles[][] getTheBoard() {
			return theBoard;
		}
	
		public void setTheBoard(Tiles[][] theBoard) {
			Board.theBoard = theBoard;
		}
		public Tiles[][] getTheBoardF() {
			return theBoard;
		}
	
		public Tiles[][] getTile() {
			return tile;
		}
	
		public void setTile(Tiles[][] tile) {
			this.tile = tile;
		}
	
		public  ArrayList<Pieces> getRedPieces() {
			return redPieces;
		}
	
		public void setRedPieces(ArrayList<Pieces> redPieces) {
			this.redPieces = redPieces;
		}
	
		public ArrayList<Pieces> getBlackPieces() {
			return blackPieces;
		}
	
		public void setBlackPieces(ArrayList<Pieces> blackPieces) {
			this.blackPieces = blackPieces;
		}
	
		public int getRedCounter() {
			return redCounter;
		}
	
		public void setRedCounter(int redCounter) {
			this.redCounter = redCounter;
		}
	
		public int getBlackCounter() {
			return blackCounter;
		}
	
		public void setBlackCounter(int blackCounter) {
			this.blackCounter = blackCounter;
		}
	
		public int getDestRow() {
			return destRow;
		}
	
		public void setDestRow(int destRow) {
			Board.destRow = destRow;
		}
	
		public int getDestCol() {
			return destCol;
		}
	
		public void setDestCol(int destCol) {
			Board.destCol = destCol;
		}
	
		public int getCurrentRow() {
			return currentRow;
		}
	
		public void setCurrentRow(int currentRow) {
			this.currentRow = currentRow;
		}
	
		public int getCurrentCol() {
			return currentCol;
		}
	
		public void setCurrentCol(int currentCol) {
			this.currentCol = currentCol;
		}
	
		public int getPreyRow() {
			return preyRow;
		}
	
		public void setPreyRow(int preyRow) {
			this.preyRow = preyRow;
		}
	
		public int getPreyCol() {
			return preyCol;
		}
	
		public void setPreyCol(int preyCol) {
			this.preyCol = preyCol;
		}
	
		public static int getTurnCounter() {
			return turnCounter;
		}
	
		public static  void setTurnCounter(int turnCounter) {
			Board.turnCounter = turnCounter;
		}
		
		public  void setTurnCounter1(int turnCounter) {
			Board.turnCounter = turnCounter;
		}

	
		public static int getScoreRedPlayer() {
			return scoreRedPlayer;
		}
	
		public static void setScoreRedPlayer(int scoreRedPlayer) {
			Board.scoreRedPlayer = scoreRedPlayer;
		}
	
		public static int getScoreBlackPlayer() {
			return scoreBlackPlayer;
		}
	
		public static void setScoreBlackPlayer(int scorePlayer2) {
			Board.scoreBlackPlayer = scorePlayer2;
		}
	

	
		public static Pieces getLastPieceMoved() {
			return lastPieceMoved;
		}
	
		public static void setLastPieceMoved(Pieces lastPieceMoved) {
			Board.lastPieceMoved = lastPieceMoved;
		}
	
		public static Player getRed() {
			return red;
		}
	
		public void setRed(Player red) {
			Board.red = red;
		}
	
		public static Player getBlack() {
			return black;
		}
	
		public void setBlack(Player black) {
			Board.black = black;
		}
	
		public ArrayList<Pieces> getNextPiece() {
			return nextPiece;
		}
	
		public void setNextPiece(ArrayList<Pieces> nextPiece) {
			this.nextPiece = nextPiece;
		}
	
		public String getLoser() {
			return loser;
		}
	
		public static void setLoser(String loser) {
			Board.loser = loser;
		}
	
		public int getCOL() {
			return COL;
		}
	
		public int getROW() {
			return ROW;
		}

		public static Tiles getT1() {
			return t1;
		}

		public static void setT1(Tiles t1) {
			Board.t1 = t1;
		}

		public static int getSecond() {
			return second;
		}

		public static void setSecond(int second) {
			Board.second = second;
		}
		
		public static void board() {
			// TODO Auto-generated method stub
			Board b = new Board();
		}
		public static Timer getTimer() {
			return timer;
		}

		public static void setTimer(Timer timer) {
			Board.timer = timer;
		}

		
	
		public static int getGameSecond() {
			return gameSecond;
		}


		public static Timer getTimerGame() {
			return timerGame;
		}




		public static void setTimerGame(Timer timerGame) {
			Board.timerGame = timerGame;
		}


		public static void setGameSecond(int gameSecond) {
			Board.gameSecond = gameSecond;
		}

	}