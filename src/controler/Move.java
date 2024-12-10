package controler;


import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.plaf.metal.MetalBorders.TableHeaderBorder;

import model.PieceType;
import model.Pieces;
import model.Player;
import model.PlayerType;
import model.TileType;
import model.Tiles;
import views.NewGame;

public class Move {

	public final static int eatPieceScore = 100;
	public final static int greenTileScore = 50;
	public static Pieces piece ;
	public static Tiles tile;
	public static boolean flagForEventLis = false;
	public static Tiles preyTile;
	public static boolean blueFlag = false;
	public static boolean redFlag = false;
	public static boolean flagFirstStep = false;
	public static boolean flagSeq = false;
	public static int stuckRow;
	public static int stuckCol;

	
	
	
	public static boolean checkMoveInWall (Pieces jumper, Board board) {
		Tiles [][] theBoard = Board.getTheBoard();
		int curRow = jumper.getRow();
		int curCol = jumper.getCol();
		int dRow = board.getDestRow();
		int dCol = board.getDestCol();
		int r;
		Tiles tile = theBoard[dRow][dCol];
		int counterDownRight = 0;
		int counterDownLeft = 0;
		int counterUpRight = 0;
		int counterUpLeft = 0;
		int piecesInRoad = 0;
		if(jumper.getType() != PieceType.BLACK_KING ||jumper.getType() != PieceType.RED_KING  ) {
			return false;
		}
		while(counterDownRight<8 && !tile.isOccupied() ) { // Down Right
			curCol++;
			curRow++;

			if(curRow == 8 && curCol == 8 ) {
				curCol = curCol % 8;
				curRow = curRow % 8;
			} else if(curRow == 8 && curCol < 8) {
				curRow = curRow % 8;
			} else if(curRow < 8 && curCol == 8) {
				curCol = curCol % 8;
			}
			if(curCol+1 < 8 && curRow+1<8) {
				Tiles t1 = theBoard[curRow][curCol+1];
				if(curCol +1 == dCol && curRow +1 == dRow && piecesInRoad==0 && !t1.isOccupied()   ) {
					/// eat and move to the distTile 
					Tiles nextT = new Tiles(dRow, dCol,board);
					if(!nextT.isOccupied()){
						counterDownRight = 8 ;
						return true;
					}
				} else {
					// have a piece in your road 
					if(t1.isOccupied()) {
						piecesInRoad++;
						if(piecesInRoad == 1) {
							counterDownRight = 8 ;
							return false;
						} 
					}
				}
			} else {
				if(curRow+1 >= 8) {
					r=curRow;
					curRow = (curRow+1)%8;
					if(curCol+1 >= 8) {
						curCol = (curCol+1)%8;
						Tiles t1 = theBoard[curRow][curCol];
						if(curCol +1 == dCol && curRow +1 == dRow && piecesInRoad==0 && !t1.isOccupied()   ) {
							Tiles nextT = new Tiles(dRow, dCol,board);
							if(!nextT.isOccupied()) {
								counterDownRight = 8 ;
								return true;
							}
						}else {
							// have a piece in your road 
							if(t1.isOccupied()) {
								piecesInRoad++;
								if(piecesInRoad == 1) {
									counterDownRight = 8 ;
									return false;
								} 
							}
						}

					} else {
						Tiles t1 = theBoard[curRow][curCol+1];
						if(curCol +1 == dCol && curRow == dRow && piecesInRoad==0 && !t1.isOccupied()   ) {
							Tiles nextT = new Tiles(dRow, dCol,board);
							if(!nextT.isOccupied())
							{
								counterDownRight = 8 ; 
								return true;
							}
						}
						else {
							// have a piece in your road 
							if(t1.isOccupied()) {
								piecesInRoad++;
								if(piecesInRoad == 1) {
									counterDownRight = 8 ;
									return false;
								} 
							}
						}
					}
				} else 	if(curCol+1 >= 8) {
					curCol = (curCol+1)%8;
					Tiles t1 = theBoard[curRow+1][curCol];
					if(curCol == dCol && curRow +1 == dRow && piecesInRoad==0 && !t1.isOccupied()   ) {
						Tiles nextT = new Tiles(dRow, dCol,board);
						if(!nextT.isOccupied())
						{
							counterDownRight = 8 ;
							return true;
						}
					}else {
						// have a piece in your road 
						if(t1.isOccupied()) {
							piecesInRoad++;
							if(piecesInRoad == 1) {
								counterDownRight = 8 ;
								return false;
							} 
						}
					}
				}
			}
			if(curRow == dRow && curCol == dCol && piecesInRoad == 0) {
				return true;
			}
			counterDownRight ++;
		}

		while(counterDownLeft<8 && !tile.isOccupied() ) { // Down Left
			curCol--;
			curRow++;

			if(curRow == 8 && curCol < 0 ) {
				curCol = curCol-1 +8;
				curRow = curRow % 8;
			} else if(curRow == 8 && curCol >= 0) {

				curRow = curRow % 8;
			} else if(curRow < 8 && curCol <0) {

				curCol = curCol -1 +8;
			}
			if(curCol-1 >= 0 && curRow+1<8) {
				Tiles t1 = theBoard[curRow][curCol];
				if(curCol - 1 == dCol && curRow +1 == dRow && piecesInRoad==0 && !t1.isOccupied()   ) {
					/// eat and move to the distTile 
					Tiles nextT = new Tiles(dRow, dCol,board);
					if(!nextT.isOccupied()){
						counterDownLeft = 8 ;
						return true;
					}
				} else {
					// have a piece in your road 
					if(t1.isOccupied()) {
						piecesInRoad++;
						if(piecesInRoad == 1) {
							counterDownLeft = 8 ;
							return false;
						} 
					}
				}
			} else {
				if(curRow+1 >= 8) {
					r=curRow;
					curRow = (curRow+1)%8;
					if(curCol-1 < 0) {
						curCol = (curCol-1)+8;
						Tiles t1 = theBoard[curRow][curCol];
						if(curCol -1 == dCol && curRow +1 == dRow && piecesInRoad==0 && !t1.isOccupied()   ) {
							Tiles nextT = new Tiles(dRow, dCol,board);
							if(!nextT.isOccupied()) {
								counterDownLeft = 8 ;
								return true;
							}
						}else {
							// have a piece in your road 
							if(t1.isOccupied()) {
								piecesInRoad++;
								if(piecesInRoad == 1) {
									counterDownLeft = 8 ;
									return false;
								} 
							}
						}

					} else {
						Tiles t1 = theBoard[curRow][curCol-1];
						if(curCol -1 == dCol && curRow == dRow && piecesInRoad==0 && !t1.isOccupied()   ) {
							Tiles nextT = new Tiles(dRow, dCol,board);
							if(!nextT.isOccupied())
							{
								counterDownLeft = 8 ; 
								return true;
							}
						}
						else {
							// have a piece in your road 
							if(t1.isOccupied()) {
								piecesInRoad++;
								if(piecesInRoad == 1) {
									counterDownRight = 8 ;
									return false;
								} 
							}
						}
					}
				} else 	if(curCol-1 < 0) {

					curCol = (curCol-1)+8;
					Tiles t1 = theBoard[curRow+1][curCol];
					if(curCol == dCol && curRow +1 == dRow && piecesInRoad==0 && !t1.isOccupied()   ) {
						Tiles nextT = new Tiles(dRow, dCol,board);
						if(!nextT.isOccupied())
						{
							counterDownLeft = 8 ;
							return true;
						}
					}else {
						// have a piece in your road 
						if(t1.isOccupied()) {
							piecesInRoad++;
							if(piecesInRoad == 1) {
								counterDownLeft = 8 ;
								return false;
							} 
						}
					}
				}
			}
			if(curRow == dRow && curCol == dCol && piecesInRoad == 0) {
				return true;
			}
			counterDownLeft ++;
		}

		while(counterUpRight<8 && !tile.isOccupied() ) { // up Right
			curCol++;
			curRow--;

			if(curRow <0 && curCol == 8 ) {

				curCol = curCol % 8;
				curRow = curRow-1 + 8;
			} else if(curRow < 0 && curCol < 8) {

				curRow = curRow-1 + 8;
			} else if(curRow >= 0 && curCol == 8) {

				curCol = curCol % 8;
			}
			if(curCol+1 < 8 && curRow-1>=0) {
				Tiles t1 = theBoard[curRow][curCol];
				if(curCol +1 == dCol && curRow -1 == dRow && piecesInRoad==0 && !t1.isOccupied()   ) {
					/// eat and move to the distTile 
					Tiles nextT = new Tiles(dRow, dCol,board);
					if(!nextT.isOccupied()){
						counterUpRight = 8 ;
						return true;
					}
				} else {
					// have a piece in your road 
					if(t1.isOccupied()) {
						piecesInRoad++;
						if(piecesInRoad == 1) {
							counterUpRight = 8 ;
							return false;
						} 
					}
				}
			} else {
				if(curRow-1 <0) {
					r=curRow;
					curRow = (curRow-1)+8;
					if(curCol+1 >= 8) {
						curCol = (curCol+1)%8;
						Tiles t1 = theBoard[curRow][curCol];
						if(curCol +1 == dCol && curRow - 1 == dRow && piecesInRoad==0 && !t1.isOccupied()   ) {
							Tiles nextT = new Tiles(dRow, dCol,board);
							if(!nextT.isOccupied()) {
								counterUpRight = 8 ;
								return true;
							}
						}else {
							// have a piece in your road 
							if(t1.isOccupied()) {
								piecesInRoad++;
								if(piecesInRoad == 1) {
									counterUpRight = 8 ;
									return false;
								} 
							}
						}

					} else {
						Tiles t1 = theBoard[curRow][curCol+1];
						if(curCol +1 == dCol && curRow == dRow && piecesInRoad==0 && !t1.isOccupied()   ) {
							Tiles nextT = new Tiles(dRow, dCol,board);
							if(!nextT.isOccupied())
							{
								counterUpRight = 8 ; 
								return true;
							}
						}
						else {
							// have a piece in your road 
							if(t1.isOccupied()) {
								piecesInRoad++;
								if(piecesInRoad == 1) {
									counterUpRight = 8 ;
									return false;
								} 
							}
						}
					}
				} else 	if(curCol+1 >= 8) {
					curCol = (curCol+1)%8;
					Tiles t1 = theBoard[curRow-1][curCol];
					if(curCol == dCol && curRow -1 == dRow && piecesInRoad==0 && !t1.isOccupied()   ) {
						Tiles nextT = new Tiles(dRow, dCol,board);
						if(!nextT.isOccupied())
						{
							counterUpRight = 8 ;
							return true;
						}
					}else {
						// have a piece in your road 
						if(t1.isOccupied()) {
							piecesInRoad++;
							if(piecesInRoad == 1) {
								counterUpRight = 8 ;
								return false;
							} 
						}
					}
				}
			}
			if(curRow == dRow && curCol == dCol && piecesInRoad == 0) {
				return true;
			}
			counterUpRight ++;
		}

		while(counterUpLeft<8 && !tile.isOccupied() ) { // up Left
			curCol--;
			curRow--;

			if(curRow <0 && curCol < 0 ) {
				curCol = curCol-1 +8;
				curRow = curRow -1 + 8;
			} else if(curRow <0 && curCol >= 0) {
				curRow = curRow -1+8;
			} else if(curRow < 8 && curCol <0) {
				curCol = curCol -1 +8;
			}
			if(curCol-1 >= 0 && curRow>=0) {
				Tiles t1 = theBoard[curRow][curCol];
				if(curCol - 1 == dCol && curRow -1 == dRow && piecesInRoad==0 && !t1.isOccupied()   ) {
					/// eat and move to the distTile 
					Tiles nextT = new Tiles(dRow, dCol,board);
					if(!nextT.isOccupied()){
						counterUpLeft = 8 ;
						return true;
					}
				} else {
					// have a piece in your road 
					if(t1.isOccupied()) {
						piecesInRoad++;
						if(piecesInRoad == 1) {
							counterUpLeft = 8 ;
							return false;
						} 
					}
				}
			} else {
				if(curRow-1 <0) {
					r=curRow;
					curRow = (curRow-1)+8;
					if(curCol-1 < 0) {
						curCol = (curCol-1)+8;
						Tiles t1 = theBoard[curRow][curCol];
						if(curCol -1 == dCol && curRow -1 == dRow && piecesInRoad==0 && !t1.isOccupied()   ) {
							Tiles nextT = new Tiles(dRow, dCol,board);
							if(!nextT.isOccupied()) {
								counterUpLeft = 8 ;
								return true;
							}
						}else {
							// have a piece in your road 
							if(t1.isOccupied()) {
								piecesInRoad++;
								if(piecesInRoad == 1) {
									counterUpLeft = 8 ;
									return false;
								} 
							}
						}

					} else {
						Tiles t1 = theBoard[curRow][curCol];
						if(curCol -1 == dCol && curRow == dRow && piecesInRoad==0 && !t1.isOccupied()   ) {
							Tiles nextT = new Tiles(dRow, dCol,board);
							if(!nextT.isOccupied())
							{
								counterUpLeft = 8 ; 
								return true;
							}
						}
						else {
							// have a piece in your road 
							if(t1.isOccupied()) {
								piecesInRoad++;
								if(piecesInRoad == 1) {
									counterUpLeft = 8 ;
									return false;
								} 
							}
						}
					}
				} else 	if(curCol-1 < 0) {
					curCol = (curCol-1)+8;
					Tiles t1 = theBoard[curRow-1][curCol];
					if(curCol == dCol && curRow -1 == dRow && piecesInRoad==0 && !t1.isOccupied()   ) {
						Tiles nextT = new Tiles(dRow, dCol,board);
						if(!nextT.isOccupied())
						{
							counterUpLeft = 8 ;
							return true;
						}
					}else {
						// have a piece in your road 
						if(t1.isOccupied()) {
							piecesInRoad++;
							if(piecesInRoad == 1) {
								counterUpLeft = 8 ;
								return false;
							} 
						}
					}
				}
			}
			if(curRow == dRow && curCol == dCol && piecesInRoad == 0) {
				return true;
			}
			counterUpLeft ++;
		}

		return true;
	}

	public static boolean checkJump(Pieces jumper, Board board) { // Checks if a piece may jump .

		board.setCurrentRow(jumper.getRow());
		board.setCurrentCol(jumper.getCol());
		int currentRow = board.getCurrentRow();
		int currentCol = board.getCurrentCol();
		int destRow = board.getDestRow();
		int destCol = board.getDestCol();
		Tiles theBoard[][] = Board.getTheBoard();
		//		if(jumper.equals(Board.getLastPieceMoved()))
		//			eatSeqFlag=true;
		System.out.println("Jumper begins at " + currentRow + "," + currentCol);
		if (jumper.getType() == PieceType.BLACK || jumper.getType() == PieceType.RED) {
			int rowDistance = (destRow - jumper.getRow());
			int colDistance = (destCol - jumper.getCol());
			if((rowDistance == 1) || (rowDistance == -1)) {
				System.out.println("no prey in this move");
				return false;
			}
			// eaten normal piece 
			if(jumper.getType() == PieceType.RED && rowDistance == 2 && colDistance == 2) { // RED eat Right !!
				int preyRow = jumper.getRow() + 1;
				int preyCol = jumper.getCol() + 1;
				board.setPreyRow(preyRow);
				board.setPreyCol(preyCol);
				if (!theBoard[destRow][destCol].isOccupied()&&
						(theBoard[preyRow][preyCol].isOccupied())) {
					return true;
				} 
			} else if(jumper.getType() == PieceType.RED && rowDistance == 2 && colDistance == -2) { // RED eat Left !!
				int preyRow = jumper.getRow() + 1;
				int preyCol = jumper.getCol() - 1;
				board.setPreyRow(preyRow);
				board.setPreyCol(preyCol);
				if (!theBoard[destRow][destCol].isOccupied()&&
						(theBoard[preyRow][preyCol].isOccupied())) {
					return true;
				}
			} else if(jumper.getType() == PieceType.BLACK && rowDistance == -2 && colDistance == 2) { // BLACK eat Right !!
				int preyRow = jumper.getRow() - 1;
				int preyCol = jumper.getCol() + 1;
				board.setPreyRow(preyRow);
				board.setPreyCol(preyCol);
				if (!theBoard[destRow][destCol].isOccupied()&&
						(theBoard[preyRow][preyCol].isOccupied())) {
					return true;
				} 
			} else if(jumper.getType() == PieceType.BLACK && rowDistance == -2 && colDistance == -2) { // BLACK eat Left !!
				int preyRow = jumper.getRow() - 1;
				int preyCol = jumper.getCol() - 1;
				board.setPreyRow(preyRow);
				board.setPreyCol(preyCol);
				if (!theBoard[destRow][destCol].isOccupied()&&
						(theBoard[preyRow][preyCol].isOccupied())) {
					return true;
				} 
			}else {
				System.err.println("Cannot jump to " + destRow + "," + destCol);
				return false;
			}




		} else { /// ******king type *********
			int rowDistance1 = (destRow - jumper.getRow());
			int colDistance1 = (destCol - jumper.getCol());
			if(Math.abs(rowDistance1)!=1 && Math.abs(colDistance1)!=1 ) {
				if (rowDistance1 > 0 && colDistance1 > 0) { /// nazel yamen
					if (!theBoard[destRow][destCol].isOccupied()
							&& theBoard[destRow - 1][destCol - 1].isOccupied() 
							&& Math.abs(rowDistance1)==Math.abs(colDistance1) && theBoard[destRow-1][destCol-1].getPiece().getType() != jumper.getType()
							&& ((jumper.getType() == PieceType.BLACK_KING && theBoard[destRow-1][destCol-1].getPiece().getType()!= PieceType.BLACK )
									||(jumper.getType() == PieceType.RED_KING && theBoard[destRow-1][destCol-1].getPiece().getType()!= PieceType.RED ) )) {
						boolean flag = true;	
						int counter = 0 ;
						int r= jumper.getRow();
						int c = jumper.getCol();
						while(flag) {
							c++;
							r++; 
							if(r==destRow || c==destCol) {
								flag=false;
							}
							if(theBoard[r][c].isOccupied()) {
								counter++;
							}
							if(counter>1) {
								System.err.println("Cannot jump when we have more than one piece in the road.");
								return false;
							}
							if(r+1==destRow && c+1==destCol && theBoard[r][c].isOccupied()) {
								board.setPreyRow(r);
								board.setPreyCol(c);
							}
						}
						board.setPreyRow(destRow-1);
						board.setPreyCol(destCol-1);
						return true;
					} else if(!checkMoveInWall(jumper, board)) {
						System.err.println("Cannot jump to " + destRow + "," + destCol);
						return false;
					}else {
						return true;
					} 
				}
				if (rowDistance1 < 0 && colDistance1 > 0) { /// 6al3 yamen
					if (!theBoard[destRow][destCol].isOccupied()
							&& theBoard[destRow + 1][destCol - 1].isOccupied() 
							&& Math.abs(rowDistance1)==Math.abs(colDistance1)&& theBoard[destRow+1][destCol-1].getPiece().getType() != jumper.getType()
							&& ((jumper.getType() == PieceType.BLACK_KING &&  theBoard[destRow+1][destCol-1].getPiece().getType()!= PieceType.BLACK )
									||(jumper.getType() == PieceType.RED_KING && theBoard[destRow+1][destCol-1].getPiece().getType()!= PieceType.RED ) )) {

						boolean flag = true;	
						int counter = 0 ;
						int r= jumper.getRow();
						int c = jumper.getCol();
						while(flag) {
							c++;
							r--; 
							if(r==destRow || c==destCol) {
								flag=false;
							}
							if(theBoard[r][c].isOccupied()) {
								counter++;
							}
							if(counter>1) {
								System.err.println("Cannot jump when we have more than one piece in the road.");
								return false;
							}
							if(r-1==destRow && c+1==destCol && theBoard[r][c].isOccupied()) {
								board.setPreyRow(r);
								board.setPreyCol(c);
							}
						}
						return true;
					} else if(!checkMoveInWall(jumper, board)) {
						System.err.println("Cannot jump to " + destRow + "," + destCol);
						return false;
					}else {
						return true;
					} 
				}
				if (rowDistance1 > 0 && colDistance1 < 0) { /// nazel shmal
					if (!theBoard[destRow][destCol].isOccupied()
							&& theBoard[destRow - 1][destCol + 1].isOccupied() 
							&& Math.abs(rowDistance1)==Math.abs(colDistance1)&& theBoard[destRow-1][destCol+1].getPiece().getType() != jumper.getType()
							&& ((jumper.getType() == PieceType.BLACK_KING && theBoard[destRow-1][destCol+1].getPiece().getType()!= PieceType.BLACK )
									||(jumper.getType() == PieceType.RED_KING && theBoard[destRow-1][destCol+1].getPiece().getType()!= PieceType.RED ) )) {

						boolean flag = true;	
						int counter = 0 ;
						int r= jumper.getRow();
						int c = jumper.getCol();
						while(flag) {
							c--;
							r++; 
							if(r==destRow || c==destCol) {
								flag=false;
							}
							if(theBoard[r][c].isOccupied()) {
								counter++;
							}
							if(counter>1) {
								System.err.println("Cannot jump when we have more than one piece in the road.");
								return false;
							}
							if(r+1==destRow && c-1==destCol && theBoard[r][c].isOccupied()) {
								board.setPreyRow(r);
								board.setPreyCol(c);
							}
						}
						return true;
					}
				}
				if (rowDistance1 < 0 && colDistance1 < 0) { /// 6al3 shmal
					if (!theBoard[destRow][destCol].isOccupied()
							&& theBoard[destRow + 1][destCol + 1].isOccupied()
							&& Math.abs(rowDistance1)==Math.abs(colDistance1) && theBoard[destRow+1][destCol+1].getPiece().getType() != jumper.getType()
							&& ((jumper.getType() == PieceType.BLACK_KING && theBoard[destRow+1][destCol+1].getPiece().getType()!= PieceType.BLACK )
									||(jumper.getType() == PieceType.RED_KING && theBoard[destRow+1][destCol+1].getPiece().getType()!= PieceType.RED) )) {

						boolean flag = true;	
						int counter = 0 ;
						int r= jumper.getRow();
						int c = jumper.getCol();
						while(flag) {
							c--;
							r--; 
							if(r==destRow || c==destCol) {
								flag=false;
							}
							if(theBoard[r][c].isOccupied()) {
								counter++;
							}
							if(counter>1) {
								System.err.println("Cannot jump when we have more than one piece in the road.");
								return false;
							}
							if(r-1==destRow && c-1==destCol && theBoard[r][c].isOccupied()) {
								board.setPreyRow(r);
								board.setPreyCol(c);
							}
						}
						return true;
					}else if(!checkMoveInWall(jumper, board)) {
						System.err.println("Cannot jump to " + destRow + "," + destCol);
						return false;
					}else {
						return true;
					} 
				}
			}
		}

		return false;
	}


	public static void jumpPieces(Pieces jumper, Board board) { /// method that make the piece eaten ... 
		Pieces pp=jumper.copyPieces(jumper.getRow(),jumper.getCol(),jumper.getType());
		Tiles theBoard[][] = Board.getTheBoard();
		int preyRow = board.getPreyRow();
		int preyCol = board.getPreyCol();
		int destCol1= board.getDestCol();
		int destRow1 = board.getDestRow();
		int currentRow = board.getCurrentRow();
		int currentCol = board.getCurrentCol();
		Pieces prey = theBoard[preyRow][preyCol].getPiece();
		Tiles t = theBoard[currentRow][currentCol];
//		jumper = t.getPiece();
		if (checkJump(pp, board)) {
			if (prey.getType()!= null && (pp.getType() == prey.getType() ||
					(pp.getType() == PieceType.BLACK && prey.getType() == PieceType.BLACK_KING) ||  
					(pp.getType() == PieceType.RED && prey.getType() == PieceType.RED_KING)) )
			{
				System.err.println("Cannot eat same side piece");
				return;
			}
			int destRow = board.getDestRow();
			int destCol = board.getDestCol();
			if(theBoard[destRow][destCol].getType() == TileType.BLUE )
				blueFlag = true;
			if(theBoard[destRow][destCol].getType() == TileType.RED) {
				stuckRow = destRow;
				stuckCol = destCol;
				redFlag = true;
				
			}
			theBoard[destRow][destCol].addPiece(pp);
			theBoard[currentRow][currentCol].delete();
			pp.moved(destRow, destCol);
			Board.setLastPieceMoved(pp);
			checkingTheCrown(pp, destRow, destCol);
			board.setCurrentRow(destRow);
			board.setCurrentCol(currentCol);
			/// update score player when eatin a piece
			if(pp.getType()== PieceType.BLACK || pp.getType()== PieceType.BLACK_KING) {
				Board.setScoreBlackPlayer(Board.getScoreBlackPlayer() + eatPieceScore);
				NewGame.scoreP2.setText(String.valueOf(Board.getScoreBlackPlayer()));
			}else {
				Board.setScoreRedPlayer(Board.getScoreRedPlayer() + eatPieceScore);
				NewGame.scoreP1.setText(String.valueOf(Board.getScoreRedPlayer()));
			}
			if(theBoard[destRow][destCol].getType()==TileType.GREEN) {
				if(pp.getType()== PieceType.BLACK || pp.getType()== PieceType.BLACK_KING) {
					Board.setScoreBlackPlayer(Board.getScoreBlackPlayer() + greenTileScore);
					NewGame.scoreP2.setText(String.valueOf(Board.getScoreBlackPlayer()));
				}else {
					Board.setScoreRedPlayer(Board.getScoreRedPlayer() + greenTileScore);
					NewGame.scoreP1.setText(String.valueOf(Board.getScoreRedPlayer()));
				}
			}
			if(theBoard[destRow][destCol].getType() == TileType.YELLOW)
				NewGame.viewQuest();
		
			
			theBoard[prey.getRow()][prey.getCol()].delete();
			piece = Board.getLastPieceMoved();
			tile = theBoard[piece.getRow()][piece.getCol()];
			if(ifEatInSequence(piece, board) ) {
				int row = piece.getRow();
				int col = piece.getCol();
			
				eatInSequence(piece, board, row, col);
			} else {
				if(theBoard[destRow][destCol].getType() != TileType.RED && theBoard[destRow][destCol].getType() != TileType.YELLOW) {
					Controller.checkStatus();
					Board.switchTurns();
				}
				return ;
			}
		}
	}

	public static void eatInSequence (Pieces p , Board b, int row, int col) {
		Tiles theBoard[][] = Board.getTheBoard();
		flagForEventLis = true;
		int curRow = p.getRow();
		int curCol = p.getCol();
		
		int diffRow = b.getDestRow() - curRow ;
		int diffCol = b.getDestCol() - curCol;

		if(diffRow > 0 && diffCol >0) {
			if(theBoard[curRow+1][curCol+1].isOccupied()) {
				int preyRow = curRow+1;
				int preyCol = curCol+1;
				Pieces prey = theBoard[preyRow][preyCol].getPiece();
				if(theBoard[curRow+2][curCol+2].getType() == TileType.YELLOW ) {
					NewGame.viewQuest();
				}
				theBoard[curRow+2][curCol+2].addPiece(p);
				if(theBoard[curRow+2][curCol+2].getType() == TileType.GREEN)
				{if(prey.getType() == PieceType.BLACK ||prey.getType() == PieceType.BLACK_KING ) {
					Board.setScoreRedPlayer(Board.getScoreRedPlayer() + greenTileScore);
					NewGame.scoreP1.setText(String.valueOf(Board.getScoreRedPlayer()));
				}else {
					Board.setScoreBlackPlayer(Board.getScoreBlackPlayer() + greenTileScore);
					NewGame.scoreP2.setText(String.valueOf(Board.getScoreBlackPlayer()));	
				}

				}theBoard[curRow][curCol].delete();
				p.moved(curRow+2, curCol+2);
				Board.setLastPieceMoved(p);
				checkingTheCrown(p, curRow+2, curCol+2);
				theBoard[preyRow][preyCol].delete();
				if (prey.getType() == PieceType.RED || prey.getType() == PieceType.RED_KING) {
					//					b.getRed().pieceEaten(b);
					Board.setScoreBlackPlayer(Board.getScoreBlackPlayer() + eatPieceScore);  // update score RedPlayer when eaten
					NewGame.scoreP2.setText(String.valueOf(Board.getScoreBlackPlayer()));
				} else if (prey.getType() == PieceType.BLACK || prey.getType() == PieceType.BLACK_KING) {
					//					b.getBlack().pieceEaten(b);
					Board.setScoreRedPlayer(Board.getScoreRedPlayer() + eatPieceScore);  // update score BlackPlayer when eaten
					NewGame.scoreP1.setText(String.valueOf(Board.getScoreRedPlayer()));	
				}
				if(ifEatInSequence (p , b)) {
					int row1 = p.getRow();
					int col1 = p.getCol();
					flagSeq=true;
//					eatInSequence(p , b, row1, col1);
				} else {
					flagSeq=false;
					if(theBoard[curRow+2][curCol+2].getType() != TileType.RED && theBoard[curRow+2][curCol+2].getType() != TileType.YELLOW && !Move.flagSeq) {
						Controller.checkStatus();
						Board.switchTurns();}
				}
			}
		}

		if(diffRow > 0 && diffCol < 0) {
			if(theBoard[curRow+1][curCol-1].isOccupied()) {
				int preyRow = curRow+1;
				int preyCol = curCol-1;
				Pieces prey = theBoard[preyRow][preyCol].getPiece();
				if(theBoard[curRow+2][curCol-2].getType() == TileType.YELLOW) {
					NewGame.viewQuest();
				}
				theBoard[curRow+2][curCol-2].addPiece(p);
				theBoard[curRow][curCol].delete();
				if(theBoard[curRow+2][curCol-2].getType() == TileType.GREEN){
				{if(prey.getType() == PieceType.BLACK ||prey.getType() == PieceType.BLACK_KING ) {
					Board.setScoreRedPlayer(Board.getScoreRedPlayer() + greenTileScore);
					NewGame.scoreP1.setText(String.valueOf(Board.getScoreRedPlayer()));
				}
				else { 
					Board.setScoreBlackPlayer(Board.getScoreBlackPlayer() + greenTileScore);
					NewGame.scoreP2.setText(String.valueOf(Board.getScoreBlackPlayer()));
				}
				}}
		

				p.moved(curRow+2, curCol-2);
				Board.setLastPieceMoved(p);
				checkingTheCrown(p, curRow+2, curCol-2);
				if(theBoard[curRow+2][curCol-2].getType() == TileType.BLUE )
					blueFlag = true;
				if(theBoard[curRow+2][curCol-2].getType() == TileType.RED) {
					redFlag = true;
					
				}
				theBoard[preyRow][preyCol].delete();
				if (prey.getType() == PieceType.RED || prey.getType() == PieceType.RED_KING) {
					//					b.getRed().pieceEaten(b);
					Board.setScoreBlackPlayer(Board.getScoreBlackPlayer() + eatPieceScore);  // update score RedPlayer when eaten
					NewGame.scoreP2.setText(String.valueOf(Board.getScoreBlackPlayer()));
				} else if (prey.getType() == PieceType.BLACK || prey.getType() == PieceType.BLACK_KING) {
					//					b.getBlack().pieceEaten(b);
					Board.setScoreRedPlayer(Board.getScoreRedPlayer() + eatPieceScore);  // update score RedPlayer when eaten
					NewGame.scoreP1.setText(String.valueOf(Board.getScoreRedPlayer()));
				}
			
				if(ifEatInSequence (p , b)) {
					int row1 = p.getRow();
					int col1 = p.getCol();
//					eatInSequence(p , b, row1, col1);
					flagSeq=true;
				} else {
					flagSeq=false;
					if(theBoard[curRow+2][curCol-2].getType() != TileType.RED &&theBoard[curRow+2][curCol-2].getType() != TileType.YELLOW ) {
						Controller.checkStatus();
						Board.switchTurns();}
				}
			}
		}

		if(diffRow < 0 && diffCol >0) {
			if(theBoard[curRow-1][curCol+1].isOccupied()) {
				int preyRow = curRow-1;
				int preyCol = curCol+1;
				Pieces prey = theBoard[preyRow][preyCol].getPiece();
				if(theBoard[curRow-2][curCol+2].getType() == TileType.YELLOW) {
					NewGame.viewQuest();
				}
				theBoard[curRow-2][curCol+2].addPiece(p);
				theBoard[curRow][curCol].delete();
				if(theBoard[curRow-2][curCol+2].getType() == TileType.BLUE )
					blueFlag = true;
				if(theBoard[curRow-2][curCol+2].getType() == TileType.RED) {
					redFlag = true;
					
				}
				if(theBoard[curRow-2][curCol+2].getType() == TileType.GREEN)
				{if(prey.getType() == PieceType.BLACK ||prey.getType() == PieceType.BLACK_KING ) {
					Board.setScoreRedPlayer(Board.getScoreRedPlayer() + greenTileScore);
					NewGame.scoreP1.setText(String.valueOf(Board.getScoreRedPlayer()));
				}
				else {
					Board.setScoreBlackPlayer(Board.getScoreBlackPlayer() + greenTileScore);
					NewGame.scoreP2.setText(String.valueOf(Board.getScoreBlackPlayer()));	
				}}
			
				p.moved(curRow-2, curCol+2);
				Board.setLastPieceMoved(p);
				checkingTheCrown(p, curRow-2, curCol+2);
				theBoard[preyRow][preyCol].delete();
				if (prey.getType() == PieceType.RED || prey.getType() == PieceType.RED_KING) {
					//					b.getRed().pieceEaten(b);
					Board.setScoreBlackPlayer(Board.getScoreBlackPlayer() + eatPieceScore);  // update score RedPlayer when eaten
					NewGame.scoreP2.setText(String.valueOf(Board.getScoreBlackPlayer()));
				} else if (prey.getType() == PieceType.BLACK || prey.getType() == PieceType.BLACK_KING) {
					//					b.getBlack().pieceEaten(b);
					Board.setScoreRedPlayer(Board.getScoreRedPlayer() + eatPieceScore);  // update score RedPlayer when eaten
					NewGame.scoreP1.setText(String.valueOf(Board.getScoreRedPlayer()));
				}
				if(ifEatInSequence (p , b)) {
					int row1 = p.getRow();
					int col1 = p.getCol();
//					eatInSequence(p , b, row1, col1);
					flagSeq=true;
				} else {
					flagSeq=false;
				if(theBoard[curRow-2][curCol+2].getType() != TileType.RED && theBoard[curRow-2][curCol+2].getType() != TileType.YELLOW) {
					Controller.checkStatus();
					Board.switchTurns();}
				}
			}
		}

		if(diffRow < 0 && diffCol <0) {
			if(theBoard[curRow-1][curCol-1].isOccupied()) {
				int preyRow = curRow-1;
				int preyCol = curCol-1;
				Pieces prey = theBoard[preyRow][preyCol].getPiece();
			
				if(theBoard[curRow-2][curCol-2].getType() == TileType.BLUE )
					blueFlag = true;
				if(theBoard[curRow-2][curCol-2].getType() == TileType.RED) {
					redFlag = true;
					
				}
				if(theBoard[curRow-2][curCol-2].getType() == TileType.YELLOW) {
					NewGame.viewQuest();
				}
				theBoard[curRow-2][curCol-2].addPiece(p);
				theBoard[curRow][curCol].delete();
				if(theBoard[curRow-2][curCol-2].getType() == TileType.GREEN)
				{if(prey.getType() == PieceType.BLACK ||prey.getType() == PieceType.BLACK_KING ) {
					Board.setScoreRedPlayer(Board.getScoreRedPlayer() + greenTileScore);
					NewGame.scoreP1.setText(String.valueOf(Board.getScoreRedPlayer()));
				}
				else { 
					Board.setScoreBlackPlayer(Board.getScoreBlackPlayer() + greenTileScore);
					NewGame.scoreP2.setText(String.valueOf(Board.getScoreBlackPlayer()));
				}
				}
			
				p.moved(curRow-2, curCol-2);
				Board.setLastPieceMoved(p);
				checkingTheCrown(p, curRow-2, curCol-2);
				theBoard[preyRow][preyCol].delete();
				if (prey.getType() == PieceType.RED || prey.getType() == PieceType.RED_KING) {
					//					b.getRed().pieceEaten(b);
					Board.setScoreBlackPlayer(Board.getScoreBlackPlayer() + eatPieceScore);  // update score RedPlayer when eaten
					NewGame.scoreP2.setText(String.valueOf(Board.getScoreBlackPlayer()));
				} else if (prey.getType() == PieceType.BLACK || prey.getType() == PieceType.BLACK_KING) {
					//					b.getBlack().pieceEaten(b);
					Board.setScoreRedPlayer(Board.getScoreRedPlayer() + eatPieceScore);  // update score RedPlayer when eaten
					NewGame.scoreP1.setText(String.valueOf(Board.getScoreRedPlayer()));
				}
				if(ifEatInSequence (p , b)) {
					int row1 = p.getRow();
					int col1 = p.getCol();
//					eatInSequence(p , b, row1, col1);
					flagSeq=true;
				} else {
					flagSeq=false;
					if(theBoard[curRow-2][curCol-2].getType() != TileType.RED && theBoard[curRow-2][curCol-2].getType() != TileType.YELLOW) {
						Board.switchTurns();
						Controller.checkStatus();
						}

				}
			}
		}

	}

	public static boolean ifKingEatInSeq (Pieces p , Board b) {
		Tiles theBoard[][] = Board.getTheBoard();
		int curRow = p.getRow();
		int curCol = p.getCol();
		if((theBoard[(curRow-1+8)%8][(curCol-1+8)%8].isOccupied() && 
				!theBoard[(curRow-2+8)%8][(curCol-2+8)%8].isOccupied() 
				&& isKingType(p, (curRow-1+8)%8, (curCol-1+8)%8))
				||(theBoard[(curRow-1+8)%8][(curCol+1)%8].isOccupied() && 
						!theBoard[(curRow-2+8)%8][(curCol+2)%8].isOccupied() && 
						isKingType(p, (curRow-1+8)%8, (curCol+1)%8)) ||
				(theBoard[(curRow+1)%8][(curCol-1+8)%8].isOccupied() && 
						!theBoard[(curRow+2)%8][(curCol-2+8)%8].isOccupied() &&
						isKingType(p,(curRow+1)%8, (curCol-1+8)%8)) || 
				(theBoard[(curRow+1)%8][(curCol+1)%8].isOccupied() &&
						!theBoard[(curRow+2)%8][(curCol+2)%8].isOccupied() 
						&& isKingType(p, (curRow+1)%8, (curCol+1)%8))) {
			flagSeq = true;
			return true;
		}else {
			return false;}
	}
	public static boolean isKingType(Pieces p , int preyRow , int preyCol) {
		Tiles theBoard[][] = Board.getTheBoard();
		if((p.getType() == PieceType.BLACK_KING && (theBoard[preyRow][preyCol].getPiece().getType() == PieceType.RED 
				||theBoard[preyRow][preyCol].getPiece().getType() == PieceType.RED_KING ))
				||( p.getType() == PieceType.RED_KING && (theBoard[preyRow][preyCol].getPiece().getType() == PieceType.BLACK 
				||theBoard[preyRow][preyCol].getPiece().getType() == PieceType.BLACK_KING )) ) {
			return true;
		}return false;
	}

	public static boolean ifEatInSequence (Pieces p , Board b) {
		Tiles theBoard[][] = Board.getTheBoard();
		int curRow = p.getRow();
		int curCol = p.getCol();
		if((curRow>1 && curCol>1 && theBoard[curRow-1][curCol-1].isOccupied() && !isSameType(p, b, curRow-1, curCol-1) && !theBoard[curRow-2][curCol-2].isOccupied() )||
				(curRow<6 && curCol<6 && theBoard[curRow+1][curCol+1].isOccupied() && !isSameType(p, b, curRow+1, curCol+1) && !theBoard[curRow+2][curCol+2].isOccupied())||
				(curRow>1 && curCol<6 && theBoard[curRow-1][curCol+1].isOccupied() && !isSameType(p, b, curRow-1, curCol+1) && !theBoard[curRow-2][curCol+2].isOccupied()) || 
				(curRow<6 && curCol>1 && theBoard[curRow+1][curCol-1].isOccupied() && !isSameType(p, b, curRow+1, curCol-1) && !theBoard[curRow+2][curCol-2].isOccupied())) {
			flagSeq = true;
			stuckRow = curRow;
			stuckCol = curCol;
			return true ;	
		}
		else return false;
	}

	public static boolean isSameType (Pieces p , Board b , int preyRow , int preyCol) {
		Tiles theBoard[][] = Board.getTheBoard();
		int curRow = p.getRow();
		int curCol = p.getCol();
		if((theBoard[preyRow][preyCol].getPiece().getType() == PieceType.BLACK || theBoard[preyRow][preyCol].getPiece().getType() == PieceType.BLACK_KING )&&
				(theBoard[curRow][curCol].getPiece().getType() == PieceType.BLACK || theBoard[curRow][curCol].getPiece().getType() == PieceType.BLACK_KING)) {
			return true;
		}
		if((theBoard[preyRow][preyCol].getPiece().getType() == PieceType.RED || theBoard[preyRow][preyCol].getPiece().getType() == PieceType.RED_KING )&&
				(theBoard[curRow][curCol].getPiece().getType() == PieceType.RED || theBoard[curRow][curCol].getPiece().getType() == PieceType.RED_KING)) {
			return true;
		}
		return false;
	}

	public static void checkingTheCrown(Pieces p, int destRow, int destCol) { // Checks if a piece has qualified to become a King
		if (p.getType() == PieceType.RED && destRow == 7) {
			p.crowned();
		} else if (p.getType() == PieceType.BLACK && destRow == 0) {
			p.crowned();
		}
	}

	public static void checkIfHavePiecesCanEatAndDelete (Tiles [][] theBoard,ArrayList<Pieces> array) { /// for the turn that player can eat and he did`t eat .
		/// random delete maybe magde want to change it .... 

		if(array.size() > 0) {
			Pieces p = Board.getRandomPiece(array);
			int r1 = p.getRow();
			int c1 = p.getCol();
			theBoard[r1][c1].delete();
		}
	}
	// prey         // dest
	public static void eatPieceWhenkingMove (Pieces piece,Tiles tile , Tiles nextTile , Board board) {
		Tiles [][] theBoard = Board.getTheBoard();
		int destRow = nextTile.getRow();
		int destCol = nextTile.getCol();
		int curRow = piece.getRow();
		int curCol = piece.getCol();
		int preyRow = tile.getRow();
		int preyCol = tile.getCol();

		if( ((piece.getType() == PieceType.RED_KING &&
				(tile.getPiece().getType() == PieceType.BLACK || tile.getPiece().getType() == PieceType.BLACK_KING )) 
				||(piece.getType() == PieceType.BLACK_KING && (tile.getPiece().getType() == PieceType.RED 
				|| tile.getPiece().getType() == PieceType.RED_KING ) ) )) {
			theBoard[piece.getRow()][piece.getCol()].delete();//Delete Cur
			theBoard[preyRow][preyCol].delete(); // Eat
			theBoard[destRow][destCol].addPiece(piece);// Jump
			if(theBoard[destRow][destCol].getType() == TileType.BLUE )
				blueFlag = true;
			if(theBoard[destRow][destCol].getType() == TileType.RED) {
				redFlag = true;
			}
			if(theBoard[destRow][destCol].getType() == TileType.YELLOW) {
				NewGame.viewQuest();
			}
			if(theBoard[destRow][destCol].getType() == TileType.GREEN)
			{if(piece.getType() == PieceType.BLACK ||piece.getType() == PieceType.BLACK_KING ) {
				Board.setScoreBlackPlayer(Board.getScoreBlackPlayer() + greenTileScore);
				NewGame.scoreP2.setText(String.valueOf(Board.getScoreBlackPlayer()));

			}else {
				Board.setScoreRedPlayer(Board.getScoreRedPlayer() + greenTileScore);
				NewGame.scoreP1.setText(String.valueOf(Board.getScoreRedPlayer()));
			}
			}
				if (piece.getType() == PieceType.RED ||
					piece.getType() == PieceType.RED_KING) {
					Board.setScoreRedPlayer(Board.getScoreRedPlayer()+eatPieceScore);
				} else if (piece.getType() == PieceType.BLACK ||
						piece.getType() == PieceType.BLACK_KING) {
					Board.setScoreBlackPlayer(Board.getScoreBlackPlayer()+eatPieceScore);
				} 
			piece.moved(destRow, destCol);
			Board.setLastPieceMoved(piece);//Set Last
		}

	}

	public static void addNewPiece (Board board) {
		Tiles [][] b = Board.getTheBoard();
		if(checkIfTheTilePossibleForBlue(Mouse.tile)) {
			if(Board.turnCounter%2==0) {
				Pieces p = new Pieces(Mouse.tile.getRow(), Mouse.tile.getCol(),PieceType.BLACK);
				b[Mouse.tile.getRow()][Mouse.tile.getCol()].addPiece(p);
				blueFlag=false;
				Board.switchTurns();
			}else {
				Pieces p = new Pieces(Mouse.tile.getRow(), Mouse.tile.getCol(),PieceType.RED);
				b[Mouse.tile.getRow()][Mouse.tile.getCol()].addPiece(p);
				blueFlag=false;
				Board.switchTurns();
			}
		}else {
			NewGame.displayDialogForExpInAddPiece() ;
		}

	}

	public static boolean checkIfTheTilePossibleForBlue(Tiles t) {
		Tiles [][]b =Board.getTheBoard();
		if (Board.turnCounter % 2 == 0) {
			if((!b[(t.getRow()+1)%8][(t.getCol()+1)%8].isOccupied()||(b[(t.getRow()+1)%8][(t.getCol()+1)%8].isOccupied()&&
					(b[(t.getRow()+1)%8][(t.getCol()+1)%8].getPiece().getType()==PieceType.BLACK||b[(t.getRow()+1)%8][(t.getCol()+1)%8].getPiece().getType()==PieceType.BLACK_KING)))
					&& (!b[(t.getRow()-1+8)%8][(t.getCol()-1+8)%8].isOccupied()||(b[(t.getRow()-1+8)%8][(t.getCol()-1+8)%8].isOccupied()&&
							(b[(t.getRow()-1+8)%8][(t.getCol()-1+8)%8].getPiece().getType()==PieceType.BLACK||b[(t.getRow()-1+8)%8][(t.getCol()-1+8)%8].getPiece().getType()==PieceType.BLACK_KING)))
					&& (!b[(t.getRow()+1)%8][(t.getCol()-1+8)%8].isOccupied()||(b[(t.getRow()+1)%8][(t.getCol()-1+8)%8].isOccupied()&&
							(b[(t.getRow()+1)%8][(t.getCol()-1+8)%8].getPiece().getType()==PieceType.BLACK||b[(t.getRow()+1)%8][(t.getCol()-1+8)%8].getPiece().getType()==PieceType.BLACK_KING)))
					&& (!b[(t.getRow()-1+8)%8][(t.getCol()+1)%8].isOccupied()||( b[(t.getRow()-1+8)%8][(t.getCol()+1)%8].isOccupied()&&
							(b[(t.getRow()-1+8)%8][(t.getCol()+1)%8].getPiece().getType()==PieceType.BLACK||b[(t.getRow()-1+8)%8][(t.getCol()+1)%8].getPiece().getType()==PieceType.BLACK_KING)) )
					) {
				return true;
			}
		}
		else if (Board.turnCounter % 2 == 1) {
			if((!b[(t.getRow()+1)%8][(t.getCol()+1)%8].isOccupied()||(b[(t.getRow()+1)%8][(t.getCol()+1)%8].isOccupied()&&
					(b[(t.getRow()+1)%8][(t.getCol()+1)%8].getPiece().getType()==PieceType.RED||b[(t.getRow()+1)%8][(t.getCol()+1)%8].getPiece().getType()==PieceType.RED_KING)))
					&& (!b[(t.getRow()-1+8)%8][(t.getCol()-1+8)%8].isOccupied()||(b[(t.getRow()-1+8)%8][(t.getCol()-1+8)%8].isOccupied()&&
							(b[(t.getRow()-1+8)%8][(t.getCol()-1+8)%8].getPiece().getType()==PieceType.RED||b[(t.getRow()-1+8)%8][(t.getCol()-1+8)%8].getPiece().getType()==PieceType.RED_KING)))
					&& (!b[(t.getRow()+1)%8][(t.getCol()-1+8)%8].isOccupied()||(b[(t.getRow()+1)%8][(t.getCol()-1+8)%8].isOccupied()&&
							(b[(t.getRow()+1)%8][(t.getCol()-1+8)%8].getPiece().getType()==PieceType.RED||b[(t.getRow()+1)%8][(t.getCol()-1+8)%8].getPiece().getType()==PieceType.RED_KING)))
					&& (!b[(t.getRow()-1+8)%8][(t.getCol()+1)%8].isOccupied()||( b[(t.getRow()-1+8)%8][(t.getCol()+1)%8].isOccupied()&&
							(b[(t.getRow()-1+8)%8][(t.getCol()+1)%8].getPiece().getType()==PieceType.RED||b[(t.getRow()-1+8)%8][(t.getCol()+1)%8].getPiece().getType()==PieceType.RED_KING)) )
					) {
				return true;
			}
		}

		return false;
	}

	// 4 methods for king moving . 
	public static void kingMoveUpRight(Pieces piece ,int dRow, int dCol, Board board) {
		Tiles [][] theBoard = Board.getTheBoard();
		int curRow = piece.getRow();
		int curCol = piece.getCol();
		Tiles tile = theBoard[dRow][dCol]; // Dest Tile
		int counterUpRight = 0;
		int piecesInRoad = 0;
		while(counterUpRight<8 && !tile.isOccupied() && !flagSeq ) { // Up Right
			curCol=(curCol+1)%8;
			curRow=(curRow-1+8)%8;
			Tiles t1 = theBoard[curRow][curCol];
			if(curCol+1 <8 && curRow-1 >=0) {
				if(curCol +1 == dCol && curRow -1 == dRow && piecesInRoad==0 && t1.isOccupied()) {
					/// eat and move to the distTile 
					if(!tile.isOccupied())
					{
						board.setPreyRow(t1.getRow());
						board.setPreyCol(t1.getCol());
						eatPieceWhenkingMove(piece ,t1, tile, board);
						if(!ifKingEatInSeq(piece, board)) {
							flagSeq = false;
							
							if(theBoard[dRow][dCol].getType() != TileType.RED && theBoard[dRow][dCol].getType() != TileType.BLUE && theBoard[dRow][dCol].getType() != TileType.YELLOW) {
								Board.switchTurns();
								Controller.checkStatus();
								}
						}else {
							stuckCol=piece.getCol();
							stuckRow=piece.getRow();
							flagSeq = true;
						}

						counterUpRight = 8 ; 
					}
				} else {
					// have a piece in your road 
					if(t1.isOccupied()) {
						piecesInRoad++;
						if(piecesInRoad == 1) {
							counterUpRight = 8 ;
							System.err.println("you have Pieces in your Road !!!");
						} 
					}
				}
			} else {
				if(curRow-1<0) {
					if(curCol+1>=8) {
						if((curCol+1)%8 == dCol && (curRow-1)+8 == dRow && piecesInRoad==0 && t1.isOccupied()   ) {
							if(!tile.isOccupied()) {
								board.setPreyRow(t1.getRow());
								board.setPreyCol(t1.getCol());
								eatPieceWhenkingMove(piece,t1,tile, board);
								if(!ifKingEatInSeq(piece, board)) {
									flagSeq = false;
									
									if(theBoard[dRow][dCol].getType() != TileType.RED &&theBoard[dRow][dCol].getType() != TileType.BLUE && theBoard[dRow][dCol].getType() != TileType.YELLOW) {
										Controller.checkStatus();
										Board.switchTurns();}
								}else {
									stuckCol=piece.getCol();
									stuckRow=piece.getRow();
									flagSeq = true;
								}

								counterUpRight = 8 ; 
							}
						}else {
							// have a piece in your road 
							if(t1.isOccupied()) {
								piecesInRoad++;
								if(piecesInRoad == 1) {
									counterUpRight = 8 ;
									System.err.println("you have Pieces in your Road !!!");
								} 
							}
						}

					} else {
						if(curCol+1 == dCol && curRow-1+8 == dRow && piecesInRoad==0 && t1.isOccupied()   ) {
							if(!tile.isOccupied())
							{
								board.setPreyRow(t1.getRow());
								board.setPreyCol(t1.getCol());
								eatPieceWhenkingMove(piece,t1, tile, board);
								if(!ifKingEatInSeq(piece, board)) {
									flagSeq = false;
									
									if(theBoard[dRow][dCol].getType() != TileType.RED &&theBoard[dRow][dCol].getType() != TileType.BLUE && theBoard[dRow][dCol].getType() != TileType.YELLOW) {
										Controller.checkStatus();
										Board.switchTurns();}
								}else {
									stuckCol=piece.getCol();
									stuckRow=piece.getRow();
									flagSeq = true;
								}

								counterUpRight = 8 ; 
							}
						}else {
							// have a piece in your road 
							if(t1.isOccupied()) {
								piecesInRoad++;
								if(piecesInRoad == 1) {
									counterUpRight = 8 ;
									System.err.println("you have Pieces in your Road !!!");
								} 
							}
						}
					}
				} else 	if(curCol+1>=8) {
					if((curCol+1)%8 == dCol && (curRow -1+8)%8 == dRow && piecesInRoad==0 && t1.isOccupied()   ) {
						if(!tile.isOccupied()){
							board.setPreyRow(t1.getRow());
							board.setPreyCol(t1.getCol());
							eatPieceWhenkingMove(piece,t1, tile, board);
							if(!ifKingEatInSeq(piece, board)) {
								flagSeq = false;
								
								if(theBoard[dRow][dCol].getType() != TileType.RED && theBoard[dRow][dCol].getType() != TileType.BLUE &&theBoard[dRow][dCol].getType() != TileType.YELLOW) {
									Controller.checkStatus();
									Board.switchTurns();}
							}else {
								stuckCol=piece.getCol();
								stuckRow=piece.getRow();
								flagSeq = true;
							}

							counterUpRight = 8 ; 
						}
					}else {
						// have a piece in your road 
						if(t1.isOccupied()) {
							piecesInRoad++;
							if(piecesInRoad == 1) {
								counterUpRight = 8 ;
								System.err.println("you have Pieces in your Road !!!");
							} 
						}
					}
				}
			}
			if(curRow == dRow && curCol == dCol && piecesInRoad == 0 && !tile.isOccupied()) {// Move In Wall
				if(theBoard[dRow][dCol].getType() == TileType.YELLOW) {
					NewGame.viewQuest();
				}
				theBoard[piece.getRow()][piece.getCol()].delete();
				theBoard[dRow][dCol].addPiece(piece);
				Board.setLastPieceMoved(piece);
				if(theBoard[dRow][dCol].getType() == TileType.GREEN)
				{if(piece.getType() == PieceType.BLACK ||piece.getType() == PieceType.BLACK_KING ) {
					Board.setScoreBlackPlayer(Board.getScoreBlackPlayer() + greenTileScore);
					NewGame.scoreP2.setText(String.valueOf(Board.getScoreBlackPlayer()));	
				}
				else {
					Board.setScoreRedPlayer(Board.getScoreRedPlayer() + greenTileScore);
					NewGame.scoreP1.setText(String.valueOf(Board.getScoreRedPlayer()));
				}
				}
				if(theBoard[dRow][dCol].getType() == TileType.BLUE )
					blueFlag = true;
				if(theBoard[dRow][dCol].getType() == TileType.RED) {
					redFlag = true;
				}
				
				if(theBoard[dRow][dCol].getType() != TileType.RED &&theBoard[dRow][dCol].getType() != TileType.BLUE && theBoard[dRow][dCol].getType() != TileType.YELLOW) {
					Controller.checkStatus();
					Board.switchTurns();}
				piece.moved(dRow, dCol);
				counterUpRight=8;
			}
			counterUpRight ++;
		}// End UP Right
		if(flagSeq) {// eat in Seq UP Right - , + 
			if(theBoard[(dRow+1)%8][(dCol-1+8)%8].isOccupied() && !theBoard[dRow][dCol].isOccupied() && theBoard[curRow][curCol].isOccupied() &&
					((piece.getType() == PieceType.BLACK_KING &&(theBoard[(dRow+1)%8][(dCol-1+8)%8].getPiece().getType()==PieceType.RED||
					theBoard[(dRow+1)%8][(dCol-1+8)%8].getPiece().getType()==PieceType.RED_KING))
							||(piece.getType() == PieceType.RED_KING &&(theBoard[(dRow+1)%8][(dCol-1+8)%8].getPiece().getType()==PieceType.BLACK
							||theBoard[(dRow+1)%8][(dCol-1+8)%8].getPiece().getType()==PieceType.BLACK_KING))) ) {
				if(theBoard[dRow][dCol].getType() == TileType.YELLOW) {
					NewGame.viewQuest();
				}
				theBoard[(dRow+1)%8][(dCol-1+8)%8].delete();
				theBoard[curRow][curCol].delete();
				theBoard[dRow][dCol].addPiece(piece);
				piece.setRow(dRow);
				piece.setCol(dCol);

				if(theBoard[dRow][dCol].getType() == TileType.YELLOW) {
					NewGame.viewQuest();
				}
				
				if(theBoard[dRow][dCol].getType() == TileType.GREEN)
				{if(piece.getType() == PieceType.BLACK ||piece.getType() == PieceType.BLACK_KING ) {
					Board.setScoreBlackPlayer(Board.getScoreBlackPlayer() + greenTileScore);
					NewGame.scoreP2.setText(String.valueOf(Board.getScoreBlackPlayer()));	
				}
				else {
					Board.setScoreRedPlayer(Board.getScoreRedPlayer() + greenTileScore);
					NewGame.scoreP1.setText(String.valueOf(Board.getScoreRedPlayer()));
				}
				}
		
			}
			if (theBoard[dRow][dCol].getType() == TileType.BLUE)
				blueFlag=true;
				if (piece.getType() == PieceType.RED || piece.getType() == PieceType.RED_KING) {
					Board.setScoreRedPlayer(Board.getScoreRedPlayer() + eatPieceScore);  // update score RedPlayer when eaten
					NewGame.scoreP1.setText(String.valueOf(Board.getScoreRedPlayer()));

				} else if (piece.getType() == PieceType.BLACK || piece.getType() == PieceType.BLACK_KING) {
					Board.setScoreBlackPlayer(Board.getScoreBlackPlayer() + eatPieceScore);  // update score RedPlayer when eaten
					NewGame.scoreP2.setText(String.valueOf(Board.getScoreBlackPlayer()));			
				}
				if(!ifKingEatInSeq(theBoard[dRow][dCol].getPiece(), board)) {
					flagSeq = false;
					if(theBoard[dRow][dCol].getType() == TileType.RED) {
						stuckRow = dRow;
						stuckCol = dCol;
						redFlag = true;
					}else {
					if(theBoard[dRow][dCol].getType() != TileType.RED &&theBoard[dRow][dCol].getType() != TileType.BLUE && theBoard[dRow][dCol].getType() != TileType.YELLOW) {
						Controller.checkStatus();
						Board.switchTurns();}	
					}
			
			}
		}
	}

	public static void kingMoveDownRight(Pieces piece ,int dRow, int dCol, Board board) {
		//update the current col and row 
		Tiles [][] theBoard = Board.getTheBoard();
		int curRow = piece.getRow();
		int curCol = piece.getCol();
		Tiles tile = theBoard[dRow][dCol]; // Dest Tile
		int counterDownRight = 0;
		curRow = piece.getRow();
		curCol = piece.getCol();
		int piecesInRoad = 0 ;
		while(counterDownRight<8 && !tile.isOccupied() && !flagSeq) { // Down Right

			curCol= (curCol+1)%8;
			curRow = (curRow+1)%8;
			Tiles t1 = theBoard[curRow][curCol];
			if(curCol+1 < 8 && curRow+1<8) {// No Bounds Exception
				if(curCol +1 == dCol && curRow +1 == dRow && piecesInRoad==0 && t1.isOccupied()   ) {
					/// eat and move to the distTile 
					if(!tile.isOccupied())
					{
						board.setPreyRow(t1.getRow());
						board.setPreyCol(t1.getCol());
						eatPieceWhenkingMove(piece ,t1, tile, board);// Eat Method
						if(!ifKingEatInSeq(piece, board)) {
							flagSeq = false;
							
							if(theBoard[dRow][dCol].getType() != TileType.RED && theBoard[dRow][dCol].getType() != TileType.BLUE && theBoard[dRow][dCol].getType() != TileType.YELLOW) {
								Controller.checkStatus();
								Board.switchTurns();}
						}else {
							stuckCol=piece.getCol();
							stuckRow=piece.getRow();
							flagSeq = true;
						}
						counterDownRight = 8 ; 
					}
				} else {
					// have a piece in your road 
					if(t1.isOccupied() ) {
						piecesInRoad++;
						if(piecesInRoad == 1) {
							counterDownRight = 8 ;
							System.err.println("you have Pieces in your Road !!!");
						} 
					}
				}
			}else {
				if(curRow+1 >= 8) {
					if(curCol+1 >= 8) {
						if((curCol +1)%8 == dCol && (curRow +1)%8 == dRow && piecesInRoad==0 && t1.isOccupied()) {
							if(!tile.isOccupied()) {
								board.setPreyRow(t1.getRow());
								board.setPreyCol(t1.getCol());
								eatPieceWhenkingMove(piece,t1, tile, board);
								if(!ifKingEatInSeq(piece, board)) {
									flagSeq = false;
									
									if(theBoard[dRow][dCol].getType() != TileType.RED && theBoard[dRow][dCol].getType() != TileType.BLUE && theBoard[dRow][dCol].getType() != TileType.YELLOW) {
										Controller.checkStatus();
										Board.switchTurns();}
								}else {
									stuckCol=piece.getCol();
									stuckRow=piece.getRow();
									flagSeq = true;
								}
								counterDownRight = 8 ; 
							}
						}else {
							// have a piece in your road 
							if(t1.isOccupied()) {
								piecesInRoad++;
								if(piecesInRoad == 1) {
									counterDownRight = 8 ;
									System.err.println("you have Pieces in your Road !!!");
								} 
							}
						}
					} else {
						if(curCol+1 == dCol && (curRow+1)%8 == dRow && piecesInRoad==0 && t1.isOccupied()   ) {
							if(!tile.isOccupied())
							{
								board.setPreyRow(t1.getRow());
								board.setPreyCol(t1.getCol());
								eatPieceWhenkingMove(piece,t1,tile, board);
								if(!ifKingEatInSeq(piece, board)) {
									flagSeq = false;
								
									if(theBoard[dRow][dCol].getType() != TileType.RED &&theBoard[dRow][dCol].getType() != TileType.BLUE &&  theBoard[dRow][dCol].getType() != TileType.YELLOW) {
										Controller.checkStatus();
										Board.switchTurns();}
								}else {
									stuckCol=piece.getCol();
									stuckRow=piece.getRow();
									flagSeq = true;
								}
								counterDownRight = 8 ; 
							}
						}else {
							// have a piece in your road 
							if(t1.isOccupied()) {
								piecesInRoad++;
								if(piecesInRoad == 1) {
									counterDownRight = 8 ;
									System.err.println("you have Pieces in your Road !!!");
								} 
							}
						}
					}
				} else 	if(curCol+1 >= 8) {
					if((curCol+1)%8 == dCol && curRow +1 == dRow && piecesInRoad==0 && t1.isOccupied()   ) {
						if(!tile.isOccupied())
						{
							board.setPreyRow(t1.getRow());
							board.setPreyCol(t1.getCol());
							eatPieceWhenkingMove(piece,t1,tile, board);
							if(!ifKingEatInSeq(piece, board)) {
								flagSeq = false;
								
								if(theBoard[dRow][dCol].getType() != TileType.RED &&theBoard[dRow][dCol].getType() != TileType.BLUE &&  theBoard[dRow][dCol].getType() != TileType.YELLOW) {
									Controller.checkStatus();
									Board.switchTurns();}
							}else {
								stuckCol=piece.getCol();
								stuckRow=piece.getRow();
								flagSeq = true;
							}
							counterDownRight = 8 ; 
						}
					}else {
						// have a piece in your road 
						if(t1.isOccupied()) {
							piecesInRoad++;
							if(piecesInRoad == 1) {
								counterDownRight = 8 ;
								System.err.println("you have Pieces in your Road !!!");
							} 
						}
					}
				}
			}
			if(curRow == dRow && curCol == dCol && !tile.isOccupied() && piecesInRoad == 0) { // Move IN Wall
				theBoard[piece.getRow()][piece.getCol()].delete();
				theBoard[dRow][dCol].addPiece(piece);
				Board.setLastPieceMoved(piece);
				if(theBoard[dRow][dCol].getType() == TileType.GREEN)
				{if(piece.getType() == PieceType.BLACK ||piece.getType() == PieceType.BLACK_KING ) {
					Board.setScoreBlackPlayer(Board.getScoreBlackPlayer() + greenTileScore);
					NewGame.scoreP2.setText(String.valueOf(Board.getScoreBlackPlayer()));	
				}
				else { 
					Board.setScoreRedPlayer(Board.getScoreRedPlayer() + greenTileScore);
					NewGame.scoreP1.setText(String.valueOf(Board.getScoreRedPlayer()));
				}
				}
				if(theBoard[dRow][dCol].getType() == TileType.YELLOW)
					NewGame.viewQuest();
				if(theBoard[dRow][dCol].getType() == TileType.BLUE )
					blueFlag = true;
				if(theBoard[dRow][dCol].getType() == TileType.RED) {
					redFlag = true;
				}
			
				if(theBoard[dRow][dCol].getType() != TileType.RED &&theBoard[dRow][dCol].getType() != TileType.BLUE &&  theBoard[dRow][dCol].getType() != TileType.YELLOW) {
					Controller.checkStatus();
					Board.switchTurns();}
				piece.moved(dRow, dCol);
				counterDownRight = 8 ;
			}
			counterDownRight ++;
		} /// end down right
		if(flagSeq) {// eat in seq in down Right + , +
			if(theBoard[(dRow-1+8)%8][(dCol-1+8)%8].isOccupied() && !theBoard[dRow][dCol].isOccupied() && //theBoard[curRow][curCol].isOccupied() &&
					( (piece.getType() == PieceType.BLACK_KING &&(theBoard[(dRow-1+8)%8][(dCol-1+8)%8].getPiece().getType()==PieceType.RED||
					theBoard[(dRow-1+8)%8][(dCol-1+8)%8].getPiece().getType()==PieceType.RED_KING))||(piece.getType() == PieceType.RED_KING &&
					(theBoard[(dRow-1+8)%8][(dCol-1+8)%8].getPiece().getType()==PieceType.BLACK||theBoard[(dRow-1+8)%8][(dCol-1+8)%8].getPiece().getType()==PieceType.BLACK_KING))) ) {
				theBoard[(dRow-1+8)%8][(dCol-1+8)%8].delete();
				theBoard[curRow][curCol].delete();
				theBoard[dRow][dCol].addPiece(piece);
				piece.setRow(dRow);
				piece.setCol(dCol);

				if(theBoard[dRow][dCol].getType() == TileType.YELLOW) {
					NewGame.viewQuest();
				}
				
				if(theBoard[dRow][dCol].getType() == TileType.GREEN)
				{if(piece.getType() == PieceType.BLACK ||piece.getType() == PieceType.BLACK_KING ) {
					Board.setScoreBlackPlayer(Board.getScoreBlackPlayer() + greenTileScore);
					NewGame.scoreP2.setText(String.valueOf(Board.getScoreBlackPlayer()));	
				}
				else {
					Board.setScoreRedPlayer(Board.getScoreRedPlayer() + greenTileScore);
					NewGame.scoreP1.setText(String.valueOf(Board.getScoreRedPlayer()));
				}
				}
				if (piece.getType() == PieceType.RED || piece.getType() == PieceType.RED_KING) {
					Board.setScoreRedPlayer(Board.getScoreRedPlayer() + eatPieceScore);  // update score RedPlayer when eaten
					NewGame.scoreP1.setText(String.valueOf(Board.getScoreRedPlayer()));

				} else if (piece.getType() == PieceType.BLACK || piece.getType() == PieceType.BLACK_KING) {
					Board.setScoreBlackPlayer(Board.getScoreBlackPlayer() + eatPieceScore);  // update score RedPlayer when eaten
					NewGame.scoreP2.setText(String.valueOf(Board.getScoreBlackPlayer()));			
				}

				if (theBoard[dRow][dCol].getType() == TileType.BLUE) {
					blueFlag=true;
			}
				if(!ifKingEatInSeq(theBoard[dRow][dCol].getPiece(), board)) {
					flagSeq = false;
					if(theBoard[dRow][dCol].getType() == TileType.RED) {
						stuckRow = dRow;
						stuckCol = dCol;
						redFlag = true;
					}else {
						
						if(theBoard[dRow][dCol].getType() != TileType.RED &&theBoard[dRow][dCol].getType() != TileType.BLUE &&  theBoard[dRow][dCol].getType() != TileType.YELLOW) {
							Controller.checkStatus();
							Board.switchTurns();}
					}
				}

			}
		}


	}

	public static void kingMoveDownLeft(Pieces piece ,int dRow, int dCol, Board board) {
		Tiles [][] theBoard = Board.getTheBoard();
		int curRow = piece.getRow();
		int curCol = piece.getCol();
		Tiles tile = theBoard[dRow][dCol]; // Dest Tile
		int counterDownLeft = 0;
		curRow = piece.getRow();
		curCol = piece.getCol();
		int piecesInRoad = 0 ;
		while(counterDownLeft<8 && !tile.isOccupied() && !flagSeq ) { // Down Left
			curCol=(curCol-1+8)%8;
			curRow=(curRow+1)%8;
			Tiles t1 = theBoard[curRow][curCol];
			if(curCol-1 >=0 && curRow+1<8) {
				if(curCol -1 == dCol && curRow +1 == dRow && piecesInRoad==0 && t1.isOccupied()   ) {
					/// eat and move to the distTile 
					if(!tile.isOccupied())
					{
						board.setPreyRow(t1.getRow());
						board.setPreyCol(t1.getCol());
						eatPieceWhenkingMove(piece ,t1,tile, board);
						if(!ifKingEatInSeq(piece, board)) {
							flagSeq = false;
							
							if(theBoard[dRow][dCol].getType() != TileType.RED &&theBoard[dRow][dCol].getType() != TileType.BLUE &&  theBoard[dRow][dCol].getType() != TileType.YELLOW) {
								Controller.checkStatus();
								Board.switchTurns();}
						}else {
							stuckCol=piece.getCol();
							stuckRow=piece.getRow();
							flagSeq = true;
						}
						counterDownLeft = 8 ; 
					}
				} else {
					// have a piece in your road 
					if(t1.isOccupied()) {
						piecesInRoad++;
						if(piecesInRoad == 1) {
							counterDownLeft = 8 ;
							System.err.println("you have Pieces in your Road !!!");
						} 
					}
				}
			} else {
				if(curRow+1 >= 8) {
					if(curCol-1 <0) {
						if((curCol-1+8)%8 == dCol && (curRow +1)%8 == dRow && piecesInRoad==0 && t1.isOccupied()   ) {
							if(!tile.isOccupied()) {
								board.setPreyRow(t1.getRow());
								board.setPreyCol(t1.getCol());
								eatPieceWhenkingMove(piece,t1, tile, board);//Eat
								if(!ifKingEatInSeq(piece, board)) {
									flagSeq = false;
								
									if(theBoard[dCol][dCol].getType() != TileType.RED &&theBoard[dRow][dCol].getType() != TileType.BLUE &&  theBoard[dCol][dCol].getType() != TileType.YELLOW) {
										Controller.checkStatus();
										Board.switchTurns();}
								}else {
									stuckCol=piece.getCol();
									stuckRow=piece.getRow();
									flagSeq = true;
								}
								counterDownLeft = 8 ; 
							}
						}else {// have a piece in your road 
							if(t1.isOccupied()) {
								piecesInRoad++;
								if(piecesInRoad == 1) {
									counterDownLeft = 8 ;
									System.err.println("you have Pieces in your Road !!!");
								} 
							}
						}
					} else {
						if(curCol-1 == dCol && (curRow+1)%8 == dRow && piecesInRoad==0 && t1.isOccupied()) {
							if(!tile.isOccupied())
							{
								board.setPreyRow(t1.getRow());
								board.setPreyCol(t1.getCol());
								eatPieceWhenkingMove(piece,t1,tile, board);
								if(!ifKingEatInSeq(piece, board)) {
									flagSeq = false;
									
									if(theBoard[dRow][dCol].getType() != TileType.RED &&theBoard[dRow][dCol].getType() != TileType.BLUE &&  theBoard[dRow][dCol].getType() != TileType.YELLOW) {
										Controller.checkStatus();
										Board.switchTurns();}
								}else {
									stuckCol=piece.getCol();
									stuckRow=piece.getRow();
									flagSeq = true;
								}
								counterDownLeft = 8 ; 
							}
						}else {
							// have a piece in your road 
							if(t1.isOccupied()) {
								piecesInRoad++;
								if(piecesInRoad == 1) {
									counterDownLeft = 8 ;
									System.err.println("you have Pieces in your Road !!!");
								} 
							}
						}
					}
				} else 	if(curCol-1 <0) {
					if((curCol-1+8)%8 == dCol && curRow +1 == dRow && piecesInRoad==0 && t1.isOccupied()) {
						if(!tile.isOccupied())
						{
							board.setPreyRow(t1.getRow());
							board.setPreyCol(t1.getCol());
							eatPieceWhenkingMove(piece,t1,tile, board);
							if(!ifKingEatInSeq(piece, board)) {
								flagSeq = false;
								
								if(theBoard[dRow][dCol].getType() != TileType.RED &&theBoard[dRow][dCol].getType() != TileType.BLUE &&  theBoard[dRow][dCol].getType() != TileType.YELLOW) {
									Controller.checkStatus();
									Board.switchTurns();}
							}else {
								stuckCol=piece.getCol();
								stuckRow=piece.getRow();
								flagSeq = true;
							}
							Board.switchTurns();
							counterDownLeft = 8 ; 
						}
					}else {
						// have a piece in your road 
						if(t1.isOccupied()) {
							piecesInRoad++;
							if(piecesInRoad == 1) {
								counterDownLeft = 8 ;
								System.err.println("you have Pieces in your Road !!!");
							} 
						}
					}
				}
			}
			if(curRow == dRow && curCol == dCol && piecesInRoad == 0 && !tile.isOccupied()) {//move
				theBoard[piece.getRow()][piece.getCol()].delete();
				theBoard[dRow][dCol].addPiece(piece);
				Board.setLastPieceMoved(piece);
				if(theBoard[dRow][dCol].getType() == TileType.GREEN)
				{if(piece.getType() == PieceType.BLACK ||piece.getType() == PieceType.BLACK_KING ) {
					Board.setScoreBlackPlayer(Board.getScoreBlackPlayer() + greenTileScore);
					NewGame.scoreP2.setText(String.valueOf(Board.getScoreBlackPlayer()));
				}
				else {
					Board.setScoreRedPlayer(Board.getScoreRedPlayer() + greenTileScore);
					NewGame.scoreP1.setText(String.valueOf(Board.getScoreRedPlayer()));
					
				}}
			if(theBoard[dRow][dCol].getType() == TileType.YELLOW)
				NewGame.viewQuest();
				if(theBoard[dRow][dCol].getType() == TileType.BLUE )
					blueFlag = true;
				if(theBoard[dRow][dCol].getType() == TileType.RED) {
					redFlag = true;
				}
				if(theBoard[dRow][dCol].getType() != TileType.RED &&theBoard[dRow][dCol].getType() != TileType.BLUE &&  theBoard[dRow][dCol].getType() != TileType.YELLOW) {
					Controller.checkStatus();
					Board.switchTurns();}
				piece.moved(dRow, dCol);
				counterDownLeft=8;
			}
			counterDownLeft ++;
		}// End DownLeft
		if(flagSeq) {// eat in seq in Down Left + , -
			if(theBoard[(dRow-1+8)%8][(dCol+1)%8].isOccupied() && !theBoard[dRow][dCol].isOccupied() && //theBoard[curRow][curCol].isOccupied() &&
					( (piece.getType() == PieceType.BLACK_KING &&(theBoard[(dRow-1+8)%8][(dCol+1)%8].getPiece().getType()==PieceType.RED||
					theBoard[(dRow-1+8)%8][(dCol+1)%8].getPiece().getType()==PieceType.RED_KING))||(piece.getType() == PieceType.RED_KING &&
					(theBoard[(dRow-1+8)%8][(dCol+1)%8].getPiece().getType()==PieceType.BLACK||theBoard[(dRow-1+8)%8][(dCol+1)%8].getPiece().getType()==PieceType.BLACK_KING))) ) {
				theBoard[(dRow-1+8)%8][(dCol+1)%8].delete();
				theBoard[curRow][curCol].delete();
				theBoard[dRow][dCol].addPiece(piece);
				piece.setRow(dRow);
				piece.setCol(dCol);

				if(theBoard[dRow][dCol].getType() == TileType.YELLOW) {
					NewGame.viewQuest();
				}
				
				if(theBoard[dRow][dCol].getType() == TileType.GREEN)
				{if(piece.getType() == PieceType.BLACK ||piece.getType() == PieceType.BLACK_KING ) {
					Board.setScoreBlackPlayer(Board.getScoreBlackPlayer() + greenTileScore);
					NewGame.scoreP2.setText(String.valueOf(Board.getScoreBlackPlayer()));
				}
				else {
					Board.setScoreRedPlayer(Board.getScoreRedPlayer() + greenTileScore);
					NewGame.scoreP1.setText(String.valueOf(Board.getScoreRedPlayer()));	
				}
				}
				if (piece.getType() == PieceType.RED || piece.getType() == PieceType.RED_KING) {
					Board.setScoreRedPlayer(Board.getScoreRedPlayer() + eatPieceScore);  // update score RedPlayer when eaten
					NewGame.scoreP1.setText(String.valueOf(Board.getScoreRedPlayer()));								

				} else if (piece.getType() == PieceType.BLACK || piece.getType() == PieceType.BLACK_KING) {
					Board.setScoreBlackPlayer(Board.getScoreBlackPlayer() + eatPieceScore);  // update score RedPlayer when eaten
					NewGame.scoreP2.setText(String.valueOf(Board.getScoreBlackPlayer()));
				}
				if(theBoard[dRow][dCol].getType() == TileType.BLUE )
					blueFlag = true;
				if(!ifKingEatInSeq(theBoard[dRow][dCol].getPiece(), board)) {
					flagSeq = false;
					if(theBoard[dRow][dCol].getType() == TileType.RED) {
						stuckRow = dRow;
						stuckCol = dCol;
						redFlag = true;
					}else {
						
						if(theBoard[dRow][dCol].getType() != TileType.RED &&theBoard[dRow][dCol].getType() != TileType.BLUE &&  theBoard[dRow][dCol].getType() != TileType.YELLOW) {
							Controller.checkStatus();
							Board.switchTurns();}
					}
				}
			}
		}

	}

	public static void kingMoveUpLeft (Pieces piece ,int dRow, int dCol, Board board) {
		Tiles [][] theBoard = Board.getTheBoard();
		int curRow = piece.getRow();
		int curCol = piece.getCol();
		Tiles tile = theBoard[dRow][dCol]; // Dest Tile
		int counterUpLeft = 0;

		curRow = piece.getRow();
		curCol = piece.getCol();
		int piecesInRoad = 0 ;
		while(counterUpLeft<8 && !tile.isOccupied() && !flagSeq ) { // Up Left
			curCol=(curCol-1+8)%8;
			curRow=(curRow-1+8)%8;
			Tiles t1 = theBoard[curRow][curCol];
			if(curCol-1 >=0 && curRow-1>=0) {
				if(curCol -1 == dCol && curRow -1 == dRow && piecesInRoad==0 && t1.isOccupied()   ) {
					/// eat and move to the distTile 
					if(!tile.isOccupied())
					{
						board.setPreyRow(t1.getRow());
						board.setPreyCol(t1.getCol());
						eatPieceWhenkingMove(piece ,t1, tile, board);
						if(!ifKingEatInSeq(piece, board)) {
							flagSeq = false;
							
							if(theBoard[dRow][dCol].getType() != TileType.RED &&theBoard[dRow][dCol].getType() != TileType.BLUE && theBoard[dRow][dCol].getType() != TileType.YELLOW) {
								Controller.checkStatus();
								Board.switchTurns();}
						}else {
							stuckCol=piece.getCol();
							stuckRow=piece.getRow();
							flagSeq = true;
						}
						counterUpLeft = 8 ; 
					}
				} else {
					// have a piece in your road 
					if(t1.isOccupied()) {
						piecesInRoad++;
						if(piecesInRoad == 1) {
							counterUpLeft = 8 ;
							System.err.println("you have Pieces in your Road !!!");
						} 
					}
				}
			} else {
				if(curRow-1<0) {
					if(curCol-1 <0) {
						if((curCol -1+8)%8== dCol && (curRow -1+8)%8 == dRow && piecesInRoad==0 && t1.isOccupied()) {
							if(!tile.isOccupied()) {
								board.setPreyRow(t1.getRow());
								board.setPreyCol(t1.getCol());
								eatPieceWhenkingMove(piece,t1,tile, board);
								if(!ifKingEatInSeq(piece, board)) {
									flagSeq = false;
								
									if(theBoard[dRow][dCol].getType() != TileType.RED &&theBoard[dRow][dCol].getType() != TileType.BLUE && theBoard[dRow][dCol].getType() != TileType.YELLOW) {
										Controller.checkStatus();
										Board.switchTurns();}
								}else {
									stuckCol=piece.getCol();
									stuckRow=piece.getRow();
									flagSeq = true;
								}
								counterUpLeft = 8 ; 
							}
						}else {
							// have a piece in your road 
							if(t1.isOccupied()) {
								piecesInRoad++;
								if(piecesInRoad == 1) {
									counterUpLeft = 8 ;
									System.err.println("you have Pieces in your Road !!!");
								} 
							}
						}

					} else {
						if(curCol -1 == dCol && (curRow-1+8)%8 == dRow && piecesInRoad==0 && t1.isOccupied()) {
							if(!tile.isOccupied())
							{
								board.setPreyRow(t1.getRow());
								board.setPreyCol(t1.getCol());
								eatPieceWhenkingMove(piece,t1, tile, board);
								if(!ifKingEatInSeq(piece, board)) {
									flagSeq = false;
									
									if(theBoard[dRow][dCol].getType() != TileType.RED &&theBoard[dRow][dCol].getType() != TileType.BLUE && theBoard[dRow][dCol].getType() != TileType.YELLOW) {
										Controller.checkStatus();
										Board.switchTurns();}
								}else {
									stuckCol=piece.getCol();
									stuckRow=piece.getRow();
									flagSeq = true;
								}
								counterUpLeft = 8 ; 
							}
						}else {
							// have a piece in your road 
							if(t1.isOccupied()) {
								piecesInRoad++;
								if(piecesInRoad == 1) {
									counterUpLeft = 8 ;
									System.err.println("you have Pieces in your Road !!!");
								} 
							}
						}
					}
				} else 	if(curCol-1 <0) {
					if((curCol-1+8)%8 == dCol && curRow -1 == dRow && piecesInRoad==0 && t1.isOccupied()   ) {
						if(!tile.isOccupied())
						{
							board.setPreyRow(t1.getRow());
							board.setPreyCol(t1.getCol());
							eatPieceWhenkingMove(piece,t1, tile, board);
							if(!ifKingEatInSeq(piece, board)) {
								flagSeq = false;
								
								if(theBoard[dRow][dCol].getType() != TileType.RED && theBoard[dRow][dCol].getType() != TileType.BLUE && theBoard[dRow][dCol].getType() != TileType.YELLOW) {
									Controller.checkStatus();
									Board.switchTurns();}
							}else {
								stuckCol=piece.getCol();
								stuckRow=piece.getRow();
								flagSeq = true;
							}
							counterUpLeft = 8 ; 
						}
					}else {
						// have a piece in your road 
						if(t1.isOccupied()) {
							piecesInRoad++;
							if(piecesInRoad == 1) {
								counterUpLeft = 8 ;
								System.err.println("you have Pieces in your Road !!!");
							} 
						}
					}
				}
			}
			if(curRow == dRow && curCol == dCol&& !tile.isOccupied() && piecesInRoad == 0) {// Move In Wall
				theBoard[piece.getRow()][piece.getCol()].delete();
				theBoard[dRow][dCol].addPiece(piece);
				Board.setLastPieceMoved(piece);
				if(theBoard[dRow][dCol].getType() == TileType.GREEN)
				{if(piece.getType() == PieceType.BLACK ||piece.getType() == PieceType.BLACK_KING ) {
					Board.setScoreBlackPlayer(Board.getScoreBlackPlayer() + greenTileScore);
					NewGame.scoreP2.setText(String.valueOf(Board.getScoreBlackPlayer()));					
				}
				else {
					Board.setScoreRedPlayer(Board.getScoreRedPlayer() + greenTileScore);
					NewGame.scoreP1.setText(String.valueOf(Board.getScoreRedPlayer()));
				}
				}
				if(theBoard[dRow][dCol].getType() ==TileType.YELLOW )
					NewGame.viewQuest();
					
				if(theBoard[dRow][dCol].getType() == TileType.BLUE )
					blueFlag = true;
				if(theBoard[dRow][dCol].getType() == TileType.RED) {
					redFlag = true;
				}
				if(theBoard[dRow][dCol].getType() != TileType.RED &&theBoard[dRow][dCol].getType() != TileType.BLUE && theBoard[dRow][dCol].getType() != TileType.YELLOW) {
					Controller.checkStatus();
					Board.switchTurns();}
				piece.moved(dRow, dCol);
				counterUpLeft=8;
			}
			counterUpLeft ++;
		} 	// End UP Left
		if(flagSeq) {// eat in seq in UP Left
			if(theBoard[(dRow+1)%8][(dCol+1)%8].isOccupied() && !theBoard[dRow][dCol].isOccupied() &&// theBoard[curRow][curCol].isOccupied() &&
					( (piece.getType() == PieceType.BLACK_KING &&(theBoard[(dRow+1)%8][(dCol+1)%8].getPiece().getType()==PieceType.RED||
					theBoard[(dRow+1)%8][(dCol+1)%8].getPiece().getType()==PieceType.RED_KING))||(piece.getType() == PieceType.RED_KING &&
					(theBoard[(dRow+1)%8][(dCol+1)%8].getPiece().getType()==PieceType.BLACK||theBoard[(dRow+1)%8][(dCol+1)%8].getPiece().getType()==PieceType.BLACK_KING))) ) {
				theBoard[(dRow+1)%8][(dCol+1)%8].delete();
				theBoard[curRow][curCol].delete();
				theBoard[dRow][dCol].addPiece(piece);
				piece.setRow(dRow);
				piece.setCol(dCol);
				if(theBoard[dRow][dCol].getType() == TileType.YELLOW) {
					NewGame.viewQuest();
				}
				if(theBoard[dRow][dCol].getType() == TileType.BLUE )
					blueFlag = true;
				if(theBoard[dRow][dCol].getType() == TileType.GREEN)
				{if(piece.getType() == PieceType.BLACK ||piece.getType() == PieceType.BLACK_KING ) {
					Board.setScoreBlackPlayer(Board.getScoreBlackPlayer() + greenTileScore);
					NewGame.scoreP2.setText(String.valueOf(Board.getScoreBlackPlayer()));
				}
				else {
					Board.setScoreRedPlayer(Board.getScoreRedPlayer() + greenTileScore);
					NewGame.scoreP1.setText(String.valueOf(Board.getScoreRedPlayer()));	
			
				}
				}
				if (piece.getType() == PieceType.RED || piece.getType() == PieceType.RED_KING) {
					Board.setScoreRedPlayer(Board.getScoreRedPlayer() + eatPieceScore);  // update score RedPlayer when eaten
					NewGame.scoreP1.setText(String.valueOf(Board.getScoreRedPlayer()));			
					
				} else if (piece.getType() == PieceType.BLACK || piece.getType() == PieceType.BLACK_KING) {
					Board.setScoreBlackPlayer(Board.getScoreBlackPlayer() + eatPieceScore);  // update score RedPlayer when eaten
					NewGame.scoreP2.setText(String.valueOf(Board.getScoreBlackPlayer()));
				}

				if(!ifKingEatInSeq(theBoard[dRow][dCol].getPiece(), board)) {
					flagSeq = false;
					if(theBoard[dRow][dCol].getType() == TileType.RED) {
						stuckRow = dRow;
						stuckCol = dCol;
						redFlag = true;
					}else {
						if(theBoard[dRow][dCol].getType() != TileType.RED &&theBoard[dRow][dCol].getType() != TileType.BLUE && theBoard[dRow][dCol].getType() != TileType.YELLOW) {
							Controller.checkStatus();
							Board.switchTurns();}
					}
				}
			}
		}



	}


	public static void openDialog(Pieces piece ,int dRow, int dCol, Board board)
	{
		
		JDialog dialog = new JDialog();
		dialog.setSize(1400,400);
	    dialog.setPreferredSize(new Dimension(450,120));
	    dialog.setLocationRelativeTo(null);
		JButton button = new JButton("Up Left");
		JButton button1 = new JButton("Up Right");
		JButton button2 = new JButton("Down Left");
		JButton button3 = new JButton("Down Right");
		JLabel label =new JLabel("Choose A Moving Step\n");
		button.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				kingMoveUpLeft(piece, dRow, dCol, board);
				dialog.dispose();
			}
		});

		button1.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				kingMoveUpRight(piece, dRow, dCol, board);
				dialog.dispose();
			}
		});

		button2.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				kingMoveDownLeft(piece, dRow, dCol, board);

				dialog.dispose();
			}
		});

		button3.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				kingMoveDownRight(piece, dRow, dCol, board);
				dialog.dispose();
			}
		});
		JPanel panel = new JPanel();
		panel.add(label);
		panel.add(button);
		panel.add(button1);
		panel.add(button2);
		panel.add(button3);
		JButton[] buttons = { button,button1,button2,button3 };
		JOptionPane optionPane = new JOptionPane(panel,
				JOptionPane.YES_NO_CANCEL_OPTION,
				JOptionPane.PLAIN_MESSAGE,
				null, buttons, null);
		dialog.getContentPane().add(optionPane);
		dialog.setSize(300,300);
		//    dialog.setLocationRelativeTo(f);
		dialog.pack();
		dialog.setVisible(true);
	}

	public static boolean curPieceCanEatAndDedntEat (Pieces p, Board board) {
		Tiles theBoard[][] = Board.getTheBoard();
		int currentRow = p.getRow();
		int currentCol = p.getCol();
		if((currentCol-2>=0 && currentRow-2>=0 && p.getType() == PieceType.BLACK && 
				theBoard[currentRow-1][currentCol-1].isOccupied() &&!theBoard[currentRow-2][currentCol-2].isOccupied() 
				&&(theBoard[currentRow-1][currentCol-1].getPiece().getType()== PieceType.RED ||
				theBoard[currentRow-1][currentCol-1].getPiece().getType()== PieceType.RED_KING) ) ||  ( currentRow-2>=0 && currentCol+2<8 &&
				p.getType() == PieceType.BLACK && theBoard[currentRow-1][currentCol+1].isOccupied() &&!theBoard[currentRow-2][currentCol+2].isOccupied() 
				&&(theBoard[currentRow-1][currentCol+1].getPiece().getType()== PieceType.RED ||
				theBoard[currentRow-1][currentCol+1].getPiece().getType()== PieceType.RED_KING)  )) {
			return true;
		}else if(	( currentRow+2<8 && currentCol+2<8 &&
				p.getType() == PieceType.RED && theBoard[currentRow+1][currentCol+1].isOccupied() &&!theBoard[currentRow+2][currentCol+2].isOccupied() 
				&&(theBoard[currentRow+1][currentCol+1].getPiece().getType()== PieceType.BLACK ||
				theBoard[currentRow+1][currentCol+1].getPiece().getType()== PieceType.BLACK_KING)  ) || ( currentRow+2<8 && currentCol-2>=0 &&
				p.getType() == PieceType.RED && theBoard[currentRow+1][currentCol-1].isOccupied() &&!theBoard[currentRow+2][currentCol-2].isOccupied() 
				&&(theBoard[currentRow+1][currentCol-1].getPiece().getType()== PieceType.BLACK ||
				theBoard[currentRow+1][currentCol-1].getPiece().getType()== PieceType.BLACK_KING))) {
			return true;
		}else if(( p.getType()==PieceType.RED_KING &&theBoard[(currentRow+1)%8][(currentCol+1)%8].isOccupied() &&!theBoard[(currentRow+2)%8][(currentCol+2)%8].isOccupied() && 
				(theBoard[(currentRow+1)%8][(currentCol+1)%8].getPiece().getType()==PieceType.BLACK||theBoard[(currentRow+1)%8][(currentCol+1)%8].getPiece().getType()==PieceType.BLACK_KING))|| 
				( p.getType()==PieceType.RED_KING &&theBoard[(currentRow-1+8)%8][(currentCol+1)%8].isOccupied() &&!theBoard[(currentRow-2+8)%8][(currentCol+2)%8].isOccupied() && 
				(theBoard[(currentRow-1+8)%8][(currentCol+1)%8].getPiece().getType()==PieceType.BLACK||theBoard[(currentRow-1+8)%8][(currentCol+1+8)%8].getPiece().getType()==PieceType.BLACK_KING)) ||
				( p.getType()==PieceType.RED_KING &&theBoard[(currentRow+1)%8][(currentCol-1+8)%8].isOccupied() &&!theBoard[(currentRow+2)%8][(currentCol-2+8)%8].isOccupied() && 
				(theBoard[(currentRow+1)%8][(currentCol-1+8)%8].getPiece().getType()==PieceType.BLACK||theBoard[(currentRow+1)%8][(currentCol-1+8)%8].getPiece().getType()==PieceType.BLACK_KING))||
				( p.getType()==PieceType.RED_KING &&theBoard[(currentRow-1+8)%8][(currentCol-1+8)%8].isOccupied() &&!theBoard[(currentRow-2+8)%8][(currentCol-2+8)%8].isOccupied() && 
				(theBoard[(currentRow-1+8)%8][(currentCol-1+8)%8].getPiece().getType()==PieceType.BLACK||theBoard[(currentRow-1+8)%8][(currentCol-1+8)%8].getPiece().getType()==PieceType.BLACK_KING))) {
			return true;
		}else if(( p.getType()==PieceType.BLACK_KING &&theBoard[(currentRow+1)%8][(currentCol+1)%8].isOccupied() &&!theBoard[(currentRow+2)%8][(currentCol+2)%8].isOccupied() && 
				(theBoard[(currentRow+1)%8][(currentCol+1)%8].getPiece().getType()==PieceType.RED||theBoard[(currentRow+1)%8][(currentCol+1)%8].getPiece().getType()==PieceType.RED_KING))|| 
				( p.getType()==PieceType.BLACK_KING &&theBoard[(currentRow-1+8)%8][(currentCol+1)%8].isOccupied() &&!theBoard[(currentRow-2+8)%8][(currentCol+2)%8].isOccupied() && 
				(theBoard[(currentRow-1+8)%8][(currentCol+1)%8].getPiece().getType()==PieceType.RED||theBoard[(currentRow-1+8)%8][(currentCol+1)%8].getPiece().getType()==PieceType.RED_KING)) ||
				( p.getType()==PieceType.BLACK_KING &&theBoard[(currentRow+1)%8][(currentCol-1+8)%8].isOccupied() &&!theBoard[(currentRow+2)%8][(currentCol-2+8)%8].isOccupied() && 
				(theBoard[(currentRow+1)%8][(currentCol-1+8)%8].getPiece().getType()==PieceType.RED||theBoard[(currentRow+1)%8][(currentCol-1+8)%8].getPiece().getType()==PieceType.RED_KING))||
				( p.getType()==PieceType.BLACK_KING &&theBoard[(currentRow-1+8)%8][(currentCol-1+8)%8].isOccupied() &&!theBoard[(currentRow-2+8)%8][(currentCol-2+8)%8].isOccupied() && 
				(theBoard[(currentRow-1+8)%8][(currentCol-1+8)%8].getPiece().getType()==PieceType.RED||theBoard[(currentRow-1+8)%8][(currentCol-1+8)%8].getPiece().getType()==PieceType.RED_KING))) {
			return true;
		}
		return false;
	}


	public static void movePieces(int dRow, int dCol, Board board) { /// method about move without eat ... 
		Tiles theBoard[][] = Board.getTheBoard();
		board.setDestRow(dRow);
		board.setDestCol(dCol);
		int currentRow = board.getCurrentRow();
		int currentCol = board.getCurrentCol();
		int destRow = board.getDestRow();
		int destCol = board.getDestCol();

		System.out.println(currentRow + "," + currentCol
				+ " would like to go to " + destRow + "," + destCol);
		if ((destRow + destCol) % 2 == 1) { // Gray tiles
			if(blueFlag) {
				addNewPiece(board);
				if(checkIfTheTilePossibleForBlue(Mouse.tile)) {
					blueFlag = false;}
			} else if((theBoard[currentRow][currentCol].isOccupied())) {
				ArrayList<Pieces> arrayPiece = Board.PiecesCanEat();
				Pieces root = theBoard[currentRow][currentCol].getPiece();

				if ((root.getType() == PieceType.BLACK || root.getType() == PieceType.RED)) {
					if ((root.getType() == PieceType.RED && (destRow > currentRow))
							|| (root.getType() == PieceType.BLACK && (destRow < currentRow))) {
						if ((Math.abs(destRow - currentRow) == 1)
								&& (Math.abs(destCol - currentCol) == 1)) {

							if (theBoard[destRow][destCol].isOccupied() == false) {

								if(curPieceCanEatAndDedntEat(root, board) && !redFlag) {
									if(theBoard[destRow][destCol].getType() == TileType.YELLOW) {
										NewGame.viewQuest();
									}
									theBoard[currentRow][currentCol].delete();
									root.moved(destRow, destCol);
									System.out.println("this piece was can eat,and we Delete it Because whin U must eat U must do IT ");
								}else {
									checkIfHavePiecesCanEatAndDelete(theBoard,arrayPiece);
									if(theBoard[destRow][destCol].getType() == TileType.YELLOW) {
										NewGame.viewQuest();
									}
									theBoard[destRow][destCol].addPiece(root);
									theBoard[currentRow][currentCol].delete();
									root.moved(destRow, destCol);
									Board.setLastPieceMoved(root);
								}

								if(theBoard[destRow][destCol].getType() == TileType.GREEN ) { /// check green Tile and if green give extra score to the player
									if(Board.getTurnCounter()%2 == 1) { // red 
										Board.setScoreRedPlayer(Board.getScoreRedPlayer() + greenTileScore);
										NewGame.scoreP1.setText(String.valueOf(Board.getScoreRedPlayer()));			
									}else { // Black
										Board.setScoreBlackPlayer(Board.getScoreBlackPlayer() + greenTileScore);
										NewGame.scoreP2.setText(String.valueOf(Board.getScoreBlackPlayer()));			

									}
								}else if(theBoard[destRow][destCol].getType() == TileType.BLUE )
									blueFlag = true;
								if(theBoard[destRow][destCol].getType() == TileType.RED) {
									stuckRow = destRow;
									stuckCol = destCol;
									redFlag = true;
									
								}
								System.out.println("Root piece moved to "
										+ destRow + "," + destCol);
								checkingTheCrown(root, destRow, destCol);
								
								if(theBoard[destRow][destCol].getType() != TileType.RED && theBoard[destRow][destCol].getType() != TileType.YELLOW) {
									Controller.checkStatus();
									Board.switchTurns();}
							}
						}  else 
							if (checkJump(root, board)) {
								jumpPieces(root, board);
								//board.switchTurns();
							}

					}else if(ifEatInSequence(Board.getLastPieceMoved(), board)) {
						int row = Board.getLastPieceMoved().getRow();
						int col = Board.getLastPieceMoved().getCol();
						eatInSequence(Board.getLastPieceMoved(), board , row , col);
					}
					else {
						System.err.println("Normal pieces can't move backwards");
						return;
					}
				} else {
					if (checkJump(root, board) && checkKingMove(board, root)) {
					
						jumpPieces(root, board);
						//						board.switchTurns();
					}
					
				}
			} else if( Board.getTurnCounter() != 0) { 
				if(theBoard[currentRow][currentCol].getType() != TileType.BLUE && !theBoard[currentRow][currentCol].isOccupied()){
					System.err.println("Current Tile is null !!!!");
				}}else if( Board.getTurnCounter() == 0) {
					System.err.println("Current Tile is null !!!!");
				}

		} else {
			System.err.println("Cannot move onto white tile bounds");
			return;
		}
	}


	public boolean checkIfAddPieceSuitableForBlueRoles (int row , int col , Board board , PlayerType type) {
		Tiles[][] b = Board.getTheBoard();
		if(type == PlayerType.RED) {

			// all tiles in the middle ... 
			if(row+2<8 && row-2>-1 && col+2<8 && col-2>-1 && checkRighDown(row, col, board, type) && checkRighUp(row, col, board, type) &&
					checkLeftDown(row, col, board, type) && checkLeftUp(row, col, board, type)) {
				return true;
			} 

			// if tiles[1][0] selected 
			if(row == 1 && col== 0 && checkRighDown(row, col, board, type) && b[0][1].isOccupied()
					&& (b[0][1].getPiece().getType() == PieceType.RED || b[0][1].getPiece().getType() == PieceType.RED_KING) ){
				return true;
			}
			if(row == 1 && col== 0 && checkRighDown(row, col, board, type) && !b[0][1].isOccupied() ){
				return true;
			}

			// if tiles[0][1] selected 
			if(row == 0 && col== 1 && checkRighDown(row, col, board, type) && b[1][0].isOccupied()
					&& (b[1][0].getPiece().getType() == PieceType.RED || b[1][0].getPiece().getType() == PieceType.RED_KING) ){
				return true;
			}
			if(row ==0 && col== 1 && checkRighDown(row, col, board, type) && !b[1][0].isOccupied() ){
				return true;
			}
			// if tiles[7][6] selected 
			if(row == 7 && col== 6 && checkLeftUp(row, col, board, type) && b[6][7].isOccupied()
					&& (b[6][7].getPiece().getType() == PieceType.RED || b[6][7].getPiece().getType() == PieceType.RED_KING) ){
				return true;
			}
			if(row == 7 && col==6  && checkLeftUp(row, col, board, type) && !b[6][7].isOccupied() ){
				return true;
			}

			// if tiles[6][7]selected 
			if(row == 6 && col== 7 && checkLeftUp(row, col, board, type) && b[7][6].isOccupied()
					&& (b[7][6].getPiece().getType() == PieceType.RED || b[7][6].getPiece().getType() == PieceType.RED_KING) ){
				return true;
			}
			if(row ==6 && col== 7 && checkLeftUp(row, col, board, type) && !b[7][6].isOccupied() ){
				return true;
			}

			if(row ==6 && col== 7 && checkLeftUp(row, col, board, type) && !b[7][6].isOccupied() ){
				return true;
			}

			// if tiles[6][1]selected 
			if(row == 6 && col== 1 && b[7][2].isOccupied() && b[5][0].isOccupied() && b[7][0].isOccupied()&& 
					(b[7][2].getPiece().getType() == PieceType.RED || b[7][2].getPiece().getType() == PieceType.RED_KING ) && 
					(b[5][0].getPiece().getType() == PieceType.RED || b[5][0].getPiece().getType() == PieceType.RED_KING) && 
					(b[7][0].getPiece().getType() == PieceType.RED || b[7][0].getPiece().getType() == PieceType.RED_KING)
					&& checkRighUp(row, col, board, type)){
				return true;
			}
			if(row == 6 && col== 1 && !b[7][2].isOccupied() && b[5][0].isOccupied() && b[7][0].isOccupied()&& 
					(b[5][0].getPiece().getType() == PieceType.RED || b[5][0].getPiece().getType() == PieceType.RED_KING) && 
					(b[7][0].getPiece().getType() == PieceType.RED || b[7][0].getPiece().getType() == PieceType.RED_KING)
					&& checkRighUp(row, col, board, type)){
				return true;
			}
			if(row == 6 && col== 1 && b[7][2].isOccupied() && !b[5][0].isOccupied() && b[7][0].isOccupied()&& 
					(b[7][2].getPiece().getType() == PieceType.RED || b[7][2].getPiece().getType() == PieceType.RED_KING ) && 
					(b[7][0].getPiece().getType() == PieceType.RED || b[7][0].getPiece().getType() == PieceType.RED_KING)
					&& checkRighUp(row, col, board, type)){
				return true;
			}
			if(row == 6 && col== 1 && b[7][2].isOccupied() && b[5][0].isOccupied() && !b[7][0].isOccupied()&& 
					(b[7][2].getPiece().getType() == PieceType.RED || b[7][2].getPiece().getType() == PieceType.RED_KING ) && 
					(b[5][0].getPiece().getType() == PieceType.RED || b[5][0].getPiece().getType() == PieceType.RED_KING)
					&& checkRighUp(row, col, board, type)){
				return true;
			}
			if(row == 6 && col== 1 && !b[7][2].isOccupied() && !b[5][0].isOccupied() && !b[7][0].isOccupied()
					&& checkRighUp(row, col, board, type)){
				return true;
			}
			if(row == 6 && col== 1 && !b[7][2].isOccupied() && !b[5][0].isOccupied() && b[7][0].isOccupied()&& 
					(b[7][0].getPiece().getType() == PieceType.RED || b[7][0].getPiece().getType() == PieceType.RED_KING)
					&& checkRighUp(row, col, board, type)){
				return true;
			}
			if(row == 6 && col== 1 && !b[7][2].isOccupied() && b[5][0].isOccupied() && !b[7][0].isOccupied()&& 
					(b[5][0].getPiece().getType() == PieceType.RED || b[5][0].getPiece().getType() == PieceType.RED_KING)						    && checkRighUp(row, col, board, type)){
				return true;
			}
			if(row == 6 && col== 1 && b[7][2].isOccupied() && !b[5][0].isOccupied() && !b[7][0].isOccupied()&& 
					(b[7][2].getPiece().getType() == PieceType.RED || b[7][2].getPiece().getType() == PieceType.RED_KING ) && 
					checkRighUp(row, col, board, type)){
				return true;
			}
			// if tiles[1][6]selected 
			if(row == 1 && col== 6 && b[2][7].isOccupied() && b[0][5].isOccupied() && b[0][7].isOccupied()&& 
					(b[2][7].getPiece().getType() == PieceType.RED || b[2][7].getPiece().getType() == PieceType.RED_KING ) && 
					(b[0][5].getPiece().getType() == PieceType.RED || b[0][5].getPiece().getType() == PieceType.RED_KING) && 
					(b[0][7].getPiece().getType() == PieceType.RED || b[0][7].getPiece().getType() == PieceType.RED_KING)
					&& checkRighUp(row, col, board, type)){
				return true;
			}
			if(row == 1 && col== 6 && !b[2][7].isOccupied() && b[0][5].isOccupied() && b[0][7].isOccupied()&& 
					(b[0][5].getPiece().getType() == PieceType.RED || b[0][5].getPiece().getType() == PieceType.RED_KING) && 
					(b[0][7].getPiece().getType() == PieceType.RED || b[0][7].getPiece().getType() == PieceType.RED_KING)
					&& checkRighUp(row, col, board, type)){
				return true;
			}
			if(row == 1 && col==6 && b[2][7].isOccupied() && !b[0][5].isOccupied() && b[0][7].isOccupied()&& 
					(b[2][7].getPiece().getType() == PieceType.RED || b[2][7].getPiece().getType() == PieceType.RED_KING ) && 
					(b[0][7].getPiece().getType() == PieceType.RED || b[0][7].getPiece().getType() == PieceType.RED_KING)
					&& checkRighUp(row, col, board, type)){
				return true;
			}
			if(row == 1 && col==6 && b[2][7].isOccupied() && b[0][5].isOccupied() && !b[0][7].isOccupied()&& 
					(b[2][7].getPiece().getType() == PieceType.RED || b[2][7].getPiece().getType() == PieceType.RED_KING ) && 
					(b[0][5].getPiece().getType() == PieceType.RED || b[0][5].getPiece().getType() == PieceType.RED_KING)
					&& checkRighUp(row, col, board, type)){
				return true;
			}
			if(row == 1 && col== 6 && !b[2][7].isOccupied() && !b[0][5].isOccupied() && !b[0][7].isOccupied()
					&& checkRighUp(row, col, board, type)){
				return true;
			}
			if(row == 1 && col== 6 && !b[2][7].isOccupied() && !b[0][5].isOccupied() && b[0][7].isOccupied()&& 
					(b[0][7].getPiece().getType() == PieceType.RED || b[0][7].getPiece().getType() == PieceType.RED_KING)
					&& checkRighUp(row, col, board, type)){
				return true;
			}
			if(row == 1 && col== 6 && !b[2][7].isOccupied() && b[0][5].isOccupied() && !b[0][7].isOccupied()&& 
					(b[0][5].getPiece().getType() == PieceType.RED || b[0][5].getPiece().getType() == PieceType.RED_KING)						    && checkRighUp(row, col, board, type)){
				return true;
			}
			if(row == 1 && col== 6 && b[2][7].isOccupied() && !b[0][5].isOccupied() && !b[0][7].isOccupied()&& 
					(b[2][7].getPiece().getType() == PieceType.RED || b[2][7].getPiece().getType() == PieceType.RED_KING ) && 
					checkRighUp(row, col, board, type)){
				return true;
			}



			/// top down left tile .... mkreh ketsooon
			if(row == 7 && col == 0 && checkRighUp(row, col, board, type) ) {
				return true;
			}
			// top up right tile .... mkreh ketson 
			if(row == 0 && col == 7 && checkLeftDown(row, col, board, type)) {
				return true;
			}

			// first row tiles select that not a mekra ketsoon  
			if(row == 0 && col>1 && col< 7 && checkLeftDown(row, col, board, type) && checkRighDown(row, col, board, type)) {
				return true;
			}

			// last row tiles selected that noa an mekra ketson 
			if(row == 7 && col>1 && col< 6 && checkLeftUp(row, col, board, type) && checkRighUp(row, col, board, type)) {
				return true;
			}

			// first col tiles selected that not a mekra ketson 
			if(col == 0 && row>1 && row< 7 && checkRighUp(row, col, board, type) && checkRighDown(row, col, board, type)) {
				return true;
			}
			// last col tiles selected that not a mekra ketson 
			if(col == 7 && row>1 && row< 6 && checkLeftUp(row, col, board, type) && checkLeftDown(row, col, board, type)) {
				return true;
			}
			// Second row tiles select that not a mekra ketsoon  
			if(row == 1 && col>1 && col< 7 && checkLeftDown(row, col, board, type) && checkRighDown(row, col, board, type) 
					&&  b[row-1][col-1].isOccupied() &&  b[row-1][col+1].isOccupied() && 
					(b[row-1][col-1].getPiece().getType() == PieceType.RED || b[row-1][col-1].getPiece().getType() == PieceType.RED_KING) 
					&&(b[row-1][col+1].getPiece().getType() == PieceType.RED || b[row-1][col+1].getPiece().getType() == PieceType.RED_KING) ) {
				return true;
			}
			if(row == 1 && col>1 && col< 7 && checkLeftDown(row, col, board, type) && checkRighDown(row, col, board, type) 
					&&  b[row-1][col-1].isOccupied() &&  !b[row-1][col+1].isOccupied() && 
					(b[row-1][col-1].getPiece().getType() == PieceType.RED || b[row-1][col-1].getPiece().getType() == PieceType.RED_KING) 
					) {
				return true;
			}
			if(row == 1 && col>1 && col< 7 && checkLeftDown(row, col, board, type) && checkRighDown(row, col, board, type) 
					&&  !b[row-1][col-1].isOccupied() &&  b[row-1][col+1].isOccupied()
					&&(b[row-1][col+1].getPiece().getType() == PieceType.RED || b[row-1][col+1].getPiece().getType() == PieceType.RED_KING) ) {
				return true;
			}
			if(row == 1 && col>1 && col< 7 && checkLeftDown(row, col, board, type) && checkRighDown(row, col, board, type) 
					&&  !b[row-1][col-1].isOccupied() &&  !b[row-1][col+1].isOccupied() ) {
				return true;
			}

			// second last row tiles selected that noa an mekra ketson 
			if(row == 6 && col>1 && col< 7 && checkLeftUp(row, col, board, type) && checkRighUp(row, col, board, type) && 
					b[row+1][col-1].isOccupied() &&  b[row+1][col+1].isOccupied() && 
					(b[row+1][col-1].getPiece().getType() == PieceType.RED || b[row+1][col-1].getPiece().getType() == PieceType.RED_KING) 
					&&(b[row+1][col+1].getPiece().getType() == PieceType.RED || b[row+1][col+1].getPiece().getType() == PieceType.RED_KING)) {
				return true;
			}
			if(row == 6 && col>1 && col< 7 && checkLeftUp(row, col, board, type) && checkRighUp(row, col, board, type) && 
					!b[row+1][col-1].isOccupied() &&  b[row+1][col+1].isOccupied() 
					&&(b[row+1][col+1].getPiece().getType() == PieceType.RED || b[row+1][col+1].getPiece().getType() == PieceType.RED_KING)) {
				return true;
			}
			if(row == 6 && col>1 && col< 7 && checkLeftUp(row, col, board, type) && checkRighUp(row, col, board, type) && 
					b[row+1][col-1].isOccupied() &&  !b[row+1][col+1].isOccupied() && 
					(b[row+1][col-1].getPiece().getType() == PieceType.RED || b[row+1][col-1].getPiece().getType() == PieceType.RED_KING)) {
				return true;
			}
			if(row == 6 && col>1 && col< 7 && checkLeftUp(row, col, board, type) && checkRighUp(row, col, board, type) && 
					!b[row+1][col-1].isOccupied() &&  !b[row+1][col+1].isOccupied()) {
				return true;
			}

			// second col tiles selected that not a mekra ketson 
			if(col == 1 && row>1 && row< 6 && checkRighUp(row, col, board, type) && checkRighDown(row, col, board, type) && 
					b[row+1][col-1].isOccupied() &&  b[row-1][col-1].isOccupied() && 
					(b[row+1][col-1].getPiece().getType() == PieceType.RED || b[row+1][col-1].getPiece().getType() == PieceType.RED_KING) 
					&&(b[row-1][col-1].getPiece().getType() == PieceType.RED || b[row-1][col-1].getPiece().getType() == PieceType.RED_KING)) {
				return true;
			}
			if(col == 1 && row>1 && row< 6 && checkRighUp(row, col, board, type) && checkRighDown(row, col, board, type) && 
					!b[row+1][col-1].isOccupied() &&  b[row-1][col-1].isOccupied() && 
					(b[row-1][col-1].getPiece().getType() == PieceType.RED || b[row-1][col-1].getPiece().getType() == PieceType.RED_KING)) {
				return true;
			}
			if(col == 1 && row>1 && row< 6 && checkRighUp(row, col, board, type) && checkRighDown(row, col, board, type) && 
					b[row+1][col-1].isOccupied() &&  !b[row-1][col-1].isOccupied() && 
					(b[row+1][col-1].getPiece().getType() == PieceType.RED || b[row+1][col-1].getPiece().getType() == PieceType.RED_KING)) {
				return true;
			}
			if(col == 1 && row>1 && row< 6 && checkRighUp(row, col, board, type) && checkRighDown(row, col, board, type) && 
					!b[row+1][col-1].isOccupied() &&  !b[row-1][col-1].isOccupied() ) {
				return true;
			}
			// last col tiles selected that not a mekra ketson 
			if(col == 6 && row>1 && row< 6 && checkLeftUp(row, col, board, type) && checkLeftDown(row, col, board, type) &&
					b[row-1][col+1].isOccupied() &&  b[row+1][col+1].isOccupied() && 
					(b[row-1][col+1].getPiece().getType() == PieceType.RED || b[row-1][col+1].getPiece().getType() == PieceType.RED_KING) 
					&&(b[row+1][col+1].getPiece().getType() == PieceType.RED || b[row+1][col+1].getPiece().getType() == PieceType.RED_KING)) {
				return true;
			}
			if(col == 6 && row>1 && row< 6 && checkLeftUp(row, col, board, type) && checkLeftDown(row, col, board, type) &&
					!b[row-1][col+1].isOccupied() &&  b[row+1][col+1].isOccupied()
					&&(b[row+1][col+1].getPiece().getType() == PieceType.RED || b[row+1][col+1].getPiece().getType() == PieceType.RED_KING)) {
				return true;
			}
			if(col == 6 && row>1 && row< 6 && checkLeftUp(row, col, board, type) && checkLeftDown(row, col, board, type) &&
					b[row-1][col+1].isOccupied() &&  !b[row+1][col+1].isOccupied() && 
					(b[row-1][col+1].getPiece().getType() == PieceType.RED || b[row-1][col+1].getPiece().getType() == PieceType.RED_KING)) {
				return true;
			}
			if(col == 6 && row>1 && row< 6 && checkLeftUp(row, col, board, type) && checkLeftDown(row, col, board, type) &&
					!b[row-1][col+1].isOccupied() &&  !b[row+1][col+1].isOccupied()) {
				return true;
			}
		} else {
			// all tiles in the midel .... 
			if(row+2<8 && row-2>-1 && col+2<8 && col-2>-1 && checkRighDown(row, col, board, type) && checkRighUp(row, col, board, type) &&
					checkLeftDown(row, col, board, type) && checkLeftUp(row, col, board, type)) {
				return true;
			} 

			// if tiles[1][0] selected 
			if(row == 1 && col== 0 && checkRighDown(row, col, board, type) && b[0][1].isOccupied()
					&& (b[0][1].getPiece().getType() == PieceType.BLACK || b[0][1].getPiece().getType() == PieceType.BLACK_KING) ){
				return true;
			}
			if(row == 1 && col== 0 && checkRighDown(row, col, board, type) && !b[0][1].isOccupied() ){
				return true;
			}

			// if tiles[0][1] selected 
			if(row == 0 && col== 1 && checkRighDown(row, col, board, type) && b[1][0].isOccupied()
					&& (b[1][0].getPiece().getType() == PieceType.BLACK || b[1][0].getPiece().getType() == PieceType.BLACK_KING) ){
				return true;
			}
			if(row ==0 && col== 1 && checkRighDown(row, col, board, type) && !b[1][0].isOccupied() ){
				return true;
			}
			// if tiles[7][6] selected 
			if(row == 7 && col== 6 && checkLeftUp(row, col, board, type) && b[6][7].isOccupied()
					&& (b[6][7].getPiece().getType() == PieceType.BLACK || b[6][7].getPiece().getType() == PieceType.BLACK_KING) ){
				return true;
			}
			if(row == 7 && col==6  && checkLeftUp(row, col, board, type) && !b[6][7].isOccupied() ){
				return true;
			}

			// if tiles[6][7]selected 
			if(row == 6 && col== 7 && checkLeftUp(row, col, board, type) && b[7][6].isOccupied()
					&& (b[7][6].getPiece().getType() == PieceType.BLACK || b[7][6].getPiece().getType() == PieceType.BLACK_KING) ){
				return true;
			}
			if(row ==6 && col== 7 && checkLeftUp(row, col, board, type) && !b[7][6].isOccupied() ){
				return true;
			}

			// if tiles[6][1]selected 
			if(row == 6 && col== 1 && b[7][2].isOccupied() && b[5][0].isOccupied() && b[7][0].isOccupied()&& 
					(b[7][2].getPiece().getType() == PieceType.BLACK || b[7][2].getPiece().getType() == PieceType.BLACK_KING ) && 
					(b[5][0].getPiece().getType() == PieceType.BLACK || b[5][0].getPiece().getType() == PieceType.BLACK_KING) && 
					(b[7][0].getPiece().getType() == PieceType.BLACK || b[7][0].getPiece().getType() == PieceType.BLACK_KING)
					&& checkRighUp(row, col, board, type)){
				return true;
			}
			if(row == 6 && col== 1 && !b[7][2].isOccupied() && b[5][0].isOccupied() && b[7][0].isOccupied()&& 
					(b[5][0].getPiece().getType() == PieceType.BLACK || b[5][0].getPiece().getType() == PieceType.BLACK_KING) && 
					(b[7][0].getPiece().getType() == PieceType.BLACK || b[7][0].getPiece().getType() == PieceType.BLACK_KING)
					&& checkRighUp(row, col, board, type)){
				return true;
			}
			if(row == 6 && col== 1 && b[7][2].isOccupied() && !b[5][0].isOccupied() && b[7][0].isOccupied()&& 
					(b[7][2].getPiece().getType() == PieceType.BLACK || b[7][2].getPiece().getType() == PieceType.BLACK_KING ) && 
					(b[7][0].getPiece().getType() == PieceType.BLACK || b[7][0].getPiece().getType() == PieceType.BLACK_KING)
					&& checkRighUp(row, col, board, type)){
				return true;
			}
			if(row == 6 && col== 1 && b[7][2].isOccupied() && b[5][0].isOccupied() && !b[7][0].isOccupied()&& 
					(b[7][2].getPiece().getType() == PieceType.BLACK || b[7][2].getPiece().getType() == PieceType.BLACK_KING ) && 
					(b[5][0].getPiece().getType() == PieceType.BLACK || b[5][0].getPiece().getType() == PieceType.BLACK_KING)
					&& checkRighUp(row, col, board, type)){
				return true;
			}
			if(row == 6 && col== 1 && !b[7][2].isOccupied() && !b[5][0].isOccupied() && !b[7][0].isOccupied()
					&& checkRighUp(row, col, board, type)){
				return true;
			}
			if(row == 6 && col== 1 && !b[7][2].isOccupied() && !b[5][0].isOccupied() && b[7][0].isOccupied()&& 
					(b[7][0].getPiece().getType() == PieceType.BLACK || b[7][0].getPiece().getType() == PieceType.BLACK_KING)
					&& checkRighUp(row, col, board, type)){
				return true;
			}
			if(row == 6 && col== 1 && !b[7][2].isOccupied() && b[5][0].isOccupied() && !b[7][0].isOccupied()&& 
					(b[5][0].getPiece().getType() == PieceType.BLACK || b[5][0].getPiece().getType() == PieceType.BLACK_KING)						    && checkRighUp(row, col, board, type)){
				return true;
			}
			if(row == 6 && col== 1 && b[7][2].isOccupied() && !b[5][0].isOccupied() && !b[7][0].isOccupied()&& 
					(b[7][2].getPiece().getType() == PieceType.BLACK || b[7][2].getPiece().getType() == PieceType.BLACK_KING ) && 
					checkRighUp(row, col, board, type)){
				return true;
			}
			// if tiles[1][6]selected 
			if(row == 1 && col== 6 && b[2][7].isOccupied() && b[0][5].isOccupied() && b[0][7].isOccupied()&& 
					(b[2][7].getPiece().getType() == PieceType.BLACK || b[2][7].getPiece().getType() == PieceType.BLACK_KING ) && 
					(b[0][5].getPiece().getType() == PieceType.BLACK || b[0][5].getPiece().getType() == PieceType.BLACK_KING) && 
					(b[0][7].getPiece().getType() == PieceType.BLACK || b[0][7].getPiece().getType() == PieceType.BLACK_KING)
					&& checkRighUp(row, col, board, type)){
				return true;
			}
			if(row == 1 && col== 6 && !b[2][7].isOccupied() && b[0][5].isOccupied() && b[0][7].isOccupied()&& 
					(b[0][5].getPiece().getType() == PieceType.BLACK || b[0][5].getPiece().getType() == PieceType.BLACK_KING) && 
					(b[0][7].getPiece().getType() == PieceType.BLACK || b[0][7].getPiece().getType() == PieceType.BLACK_KING)
					&& checkRighUp(row, col, board, type)){
				return true;
			}
			if(row == 1 && col==6 && b[2][7].isOccupied() && !b[0][5].isOccupied() && b[0][7].isOccupied()&& 
					(b[2][7].getPiece().getType() == PieceType.BLACK || b[2][7].getPiece().getType() == PieceType.BLACK_KING ) && 
					(b[0][7].getPiece().getType() == PieceType.BLACK || b[0][7].getPiece().getType() == PieceType.BLACK_KING)
					&& checkRighUp(row, col, board, type)){
				return true;
			}
			if(row == 1 && col==6 && b[2][7].isOccupied() && b[0][5].isOccupied() && !b[0][7].isOccupied()&& 
					(b[2][7].getPiece().getType() == PieceType.BLACK || b[2][7].getPiece().getType() == PieceType.BLACK_KING ) && 
					(b[0][5].getPiece().getType() == PieceType.BLACK || b[0][5].getPiece().getType() == PieceType.BLACK_KING)
					&& checkRighUp(row, col, board, type)){
				return true;
			}
			if(row == 1 && col== 6 && !b[2][7].isOccupied() && !b[0][5].isOccupied() && !b[0][7].isOccupied()
					&& checkRighUp(row, col, board, type)){
				return true;
			}
			if(row == 1 && col== 6 && !b[2][7].isOccupied() && !b[0][5].isOccupied() && b[0][7].isOccupied()&& 
					(b[0][7].getPiece().getType() == PieceType.BLACK || b[0][7].getPiece().getType() == PieceType.BLACK_KING)
					&& checkRighUp(row, col, board, type)){
				return true;
			}
			if(row == 1 && col== 6 && !b[2][7].isOccupied() && b[0][5].isOccupied() && !b[0][7].isOccupied()&& 
					(b[0][5].getPiece().getType() == PieceType.BLACK || b[0][5].getPiece().getType() == PieceType.BLACK_KING) && checkRighUp(row, col, board, type)){
				return true;
			}
			if(row == 1 && col== 6 && b[2][7].isOccupied() && !b[0][5].isOccupied() && !b[0][7].isOccupied()&& 
					(b[2][7].getPiece().getType() == PieceType.BLACK || b[2][7].getPiece().getType() == PieceType.BLACK_KING ) && 
					checkRighUp(row, col, board, type)){
				return true;
			}

			/// top down left tile .... mkreh ketsooon
			if(row == 7 && col == 0 && checkRighUp(row, col, board, type) ) {
				return true;
			}
			// top up right tile .... mkreh ketson 
			if(row == 0 && col == 7 && checkLeftDown(row, col, board, type)) {
				return true;
			}

			// first row tiles select that not a mekra ketsoon  
			if(row == 0 && col>1 && col< 7 && checkLeftDown(row, col, board, type) && checkRighDown(row, col, board, type)) {
				return true;
			}

			// last row tiles selected that noa an mekra ketson 
			if(row == 7 && col>1 && col< 6 && checkLeftUp(row, col, board, type) && checkRighUp(row, col, board, type)) {
				return true;
			}

			// first col tiles selected that not a mekra ketson 
			if(col == 0 && row>1 && row< 7 && checkRighUp(row, col, board, type) && checkRighDown(row, col, board, type)) {
				return true;
			}
			// last col tiles selected that not a mekra ketson 
			if(col == 7 && row>1 && row< 6 && checkLeftUp(row, col, board, type) && checkLeftDown(row, col, board, type)) {
				return true;
			}
			// Second row tiles select that not a mekra ketsoon  
			if(row == 1 && col>1 && col< 7 && checkLeftDown(row, col, board, type) && checkRighDown(row, col, board, type) 
					&&  b[row-1][col-1].isOccupied() &&  b[row-1][col+1].isOccupied() && 
					(b[row-1][col-1].getPiece().getType() == PieceType.BLACK || b[row-1][col-1].getPiece().getType() == PieceType.BLACK_KING) 
					&&(b[row-1][col+1].getPiece().getType() == PieceType.BLACK || b[row-1][col+1].getPiece().getType() == PieceType.BLACK_KING) ) {
				return true;
			}
			if(row == 1 && col>1 && col< 7 && checkLeftDown(row, col, board, type) && checkRighDown(row, col, board, type) 
					&&  b[row-1][col-1].isOccupied() &&  !b[row-1][col+1].isOccupied() && 
					(b[row-1][col-1].getPiece().getType() == PieceType.BLACK || b[row-1][col-1].getPiece().getType() == PieceType.BLACK_KING) 
					) {
				return true;
			}
			if(row == 1 && col>1 && col< 7 && checkLeftDown(row, col, board, type) && checkRighDown(row, col, board, type) 
					&&  !b[row-1][col-1].isOccupied() &&  b[row-1][col+1].isOccupied()
					&&(b[row-1][col+1].getPiece().getType() == PieceType.BLACK || b[row-1][col+1].getPiece().getType() == PieceType.BLACK_KING) ) {
				return true;
			}
			if(row == 1 && col>1 && col< 7 && checkLeftDown(row, col, board, type) && checkRighDown(row, col, board, type) 
					&&  !b[row-1][col-1].isOccupied() &&  !b[row-1][col+1].isOccupied() ) {
				return true;
			}

			// second last row tiles selected that noa an mekra ketson 
			if(row == 6 && col>1 && col< 7 && checkLeftUp(row, col, board, type) && checkRighUp(row, col, board, type) && 
					b[row+1][col-1].isOccupied() &&  b[row+1][col+1].isOccupied() && 
					(b[row+1][col-1].getPiece().getType() == PieceType.BLACK || b[row+1][col-1].getPiece().getType() == PieceType.BLACK_KING) 
					&&(b[row+1][col+1].getPiece().getType() == PieceType.BLACK || b[row+1][col+1].getPiece().getType() == PieceType.BLACK_KING)) {
				return true;
			}
			if(row == 6 && col>1 && col< 7 && checkLeftUp(row, col, board, type) && checkRighUp(row, col, board, type) && 
					!b[row+1][col-1].isOccupied() &&  b[row+1][col+1].isOccupied() 
					&&(b[row+1][col+1].getPiece().getType() == PieceType.BLACK || b[row+1][col+1].getPiece().getType() == PieceType.BLACK_KING)) {
				return true;
			}
			if(row == 6 && col>1 && col< 7 && checkLeftUp(row, col, board, type) && checkRighUp(row, col, board, type) && 
					b[row+1][col-1].isOccupied() &&  !b[row+1][col+1].isOccupied() && 
					(b[row+1][col-1].getPiece().getType() == PieceType.BLACK || b[row+1][col-1].getPiece().getType() == PieceType.BLACK_KING)) {
				return true;
			}
			if(row == 6 && col>1 && col< 7 && checkLeftUp(row, col, board, type) && checkRighUp(row, col, board, type) && 
					!b[row+1][col-1].isOccupied() &&  !b[row+1][col+1].isOccupied()) {
				return true;
			}

			// second col tiles selected that not a mekra ketson 
			if(col == 1 && row>1 && row< 6 && checkRighUp(row, col, board, type) && checkRighDown(row, col, board, type) && 
					b[row+1][col-1].isOccupied() &&  b[row-1][col-1].isOccupied() && 
					(b[row+1][col-1].getPiece().getType() == PieceType.BLACK || b[row+1][col-1].getPiece().getType() == PieceType.BLACK_KING) 
					&&(b[row-1][col-1].getPiece().getType() == PieceType.BLACK || b[row-1][col-1].getPiece().getType() == PieceType.BLACK_KING)) {
				return true;
			}
			if(col == 1 && row>1 && row< 6 && checkRighUp(row, col, board, type) && checkRighDown(row, col, board, type) && 
					!b[row+1][col-1].isOccupied() &&  b[row-1][col-1].isOccupied() && 
					(b[row-1][col-1].getPiece().getType() == PieceType.BLACK || b[row-1][col-1].getPiece().getType() == PieceType.BLACK_KING)) {
				return true;
			}
			if(col == 1 && row>1 && row< 6 && checkRighUp(row, col, board, type) && checkRighDown(row, col, board, type) && 
					b[row+1][col-1].isOccupied() &&  !b[row-1][col-1].isOccupied() && 
					(b[row+1][col-1].getPiece().getType() == PieceType.BLACK || b[row+1][col-1].getPiece().getType() == PieceType.BLACK_KING)) {
				return true;
			}
			if(col == 1 && row>1 && row< 6 && checkRighUp(row, col, board, type) && checkRighDown(row, col, board, type) && 
					!b[row+1][col-1].isOccupied() &&  !b[row-1][col-1].isOccupied() ) {
				return true;
			}
			// last col tiles selected that not a mekra ketson 
			if(col == 6 && row>1 && row< 6 && checkLeftUp(row, col, board, type) && checkLeftDown(row, col, board, type) &&
					b[row-1][col+1].isOccupied() &&  b[row+1][col+1].isOccupied() && 
					(b[row-1][col+1].getPiece().getType() == PieceType.BLACK || b[row-1][col+1].getPiece().getType() == PieceType.BLACK_KING) 
					&&(b[row+1][col+1].getPiece().getType() == PieceType.BLACK || b[row+1][col+1].getPiece().getType() == PieceType.BLACK_KING)) {
				return true;
			}
			if(col == 6 && row>1 && row< 6 && checkLeftUp(row, col, board, type) && checkLeftDown(row, col, board, type) &&
					!b[row-1][col+1].isOccupied() &&  b[row+1][col+1].isOccupied()
					&&(b[row+1][col+1].getPiece().getType() == PieceType.BLACK || b[row+1][col+1].getPiece().getType() == PieceType.BLACK_KING)) {
				return true;
			}
			if(col == 6 && row>1 && row< 6 && checkLeftUp(row, col, board, type) && checkLeftDown(row, col, board, type) &&
					b[row-1][col+1].isOccupied() &&  !b[row+1][col+1].isOccupied() && 
					(b[row-1][col+1].getPiece().getType() == PieceType.BLACK || b[row-1][col+1].getPiece().getType() == PieceType.BLACK_KING)) {
				return true;
			}
			if(col == 6 && row>1 && row< 6 && checkLeftUp(row, col, board, type) && checkLeftDown(row, col, board, type) &&
					!b[row-1][col+1].isOccupied() &&  !b[row+1][col+1].isOccupied()) {
				return true;
			}

		}
		return false;

	}

	public boolean checkRighDown (int row , int col , Board board , PlayerType type) {

		Tiles[][] b = Board.getTheBoard();
		if(type == PlayerType.RED) {
			if(!b[row][col].isOccupied() && !b[row+1][col+1].isOccupied() && !b[row+2][col+2].isOccupied() ) {
				return true ; 
			}
			if(!b[row][col].isOccupied() && !b[row+1][col+1].isOccupied() && b[row+2][col+2].isOccupied()
					&& (b[row+2][col+2].getPiece().getType() == PieceType.RED || b[row+2][col+2].getPiece().getType() == PieceType.RED_KING )) {
				return true ; 
			}
			if(!b[row][col].isOccupied() && !b[row+2][col+2].isOccupied() && b[row+1][col+1].isOccupied()
					&& (b[row+1][col+1].getPiece().getType() == PieceType.RED || b[row+1][col+1].getPiece().getType() == PieceType.RED_KING )) {
				return true ; 
			}
			if(!b[row][col].isOccupied() && b[row+1][col+1].isOccupied() && b[row+2][col+2].isOccupied()
					&& (b[row+1][col+1].getPiece().getType() == PieceType.RED || b[row+1][col+1].getPiece().getType() == PieceType.RED_KING ) 
					&& (b[row+2][col+2].getPiece().getType() == PieceType.RED || b[row+2][col+2].getPiece().getType() == PieceType.RED_KING )) {
				return true ; 
			}
			return false ; 
		} else {
			if(!b[row][col].isOccupied() && !b[row+1][col+1].isOccupied() && !b[row+2][col+2].isOccupied() ) {
				return true ; 
			}
			if(!b[row][col].isOccupied() && !b[row+1][col+1].isOccupied() && b[row+2][col+2].isOccupied()
					&& (b[row+2][col+2].getPiece().getType() == PieceType.BLACK || b[row+2][col+2].getPiece().getType() == PieceType.BLACK_KING )) {
				return true ; 
			}
			if(!b[row][col].isOccupied() && !b[row+2][col+2].isOccupied() && b[row+1][col+1].isOccupied()
					&& (b[row+1][col+1].getPiece().getType() == PieceType.BLACK || b[row+1][col+1].getPiece().getType() == PieceType.BLACK_KING )) {
				return true ; 
			}
			if(!b[row][col].isOccupied() && b[row+1][col+1].isOccupied() && b[row+2][col+2].isOccupied()
					&& (b[row+1][col+1].getPiece().getType() == PieceType.BLACK || b[row+1][col+1].getPiece().getType() == PieceType.BLACK_KING ) 
					&& (b[row+2][col+2].getPiece().getType() == PieceType.BLACK || b[row+2][col+2].getPiece().getType() == PieceType.BLACK_KING )) {
				return true ; 
			}
			return false ; 
		} 
	}

	public boolean checkLeftDown (int row , int col , Board board , PlayerType type) {

		Tiles[][] b = Board.getTheBoard();
		if(type == PlayerType.RED) {
			if(!b[row][col].isOccupied() && !b[row+1][col-1].isOccupied() && !b[row+2][col-2].isOccupied() ) {
				return true ; 
			}
			if(!b[row][col].isOccupied() && !b[row+1][col-1].isOccupied() && b[row+2][col-2].isOccupied()
					&& (b[row+2][col-2].getPiece().getType() == PieceType.RED || b[row+2][col-2].getPiece().getType() == PieceType.RED_KING )) {
				return true ; 
			}
			if(!b[row][col].isOccupied() && !b[row+2][col-2].isOccupied() && b[row+1][col-1].isOccupied()
					&& (b[row+1][col-1].getPiece().getType() == PieceType.RED || b[row+1][col-1].getPiece().getType() == PieceType.RED_KING )) {
				return true ; 
			}
			if(!b[row][col].isOccupied() && b[row+1][col-1].isOccupied() && b[row+2][col-2].isOccupied()
					&& (b[row+1][col-1].getPiece().getType() == PieceType.RED || b[row+1][col-1].getPiece().getType() == PieceType.RED_KING ) 
					&& (b[row+2][col-2].getPiece().getType() == PieceType.RED || b[row+2][col-2].getPiece().getType() == PieceType.RED_KING )) {
				return true ; 
			}
			return false ; 
		} else {
			if(!b[row][col].isOccupied() && !b[row+1][col-1].isOccupied() && !b[row+2][col-2].isOccupied() ) {
				return true ; 
			}
			if(!b[row][col].isOccupied() && !b[row+1][col-1].isOccupied() && b[row+2][col-2].isOccupied()
					&& (b[row+2][col-2].getPiece().getType() == PieceType.BLACK || b[row+2][col-2].getPiece().getType() == PieceType.BLACK_KING )) {
				return true ; 
			}
			if(!b[row][col].isOccupied() && !b[row+2][col-2].isOccupied() && b[row+1][col-1].isOccupied()
					&& (b[row+1][col-1].getPiece().getType() == PieceType.BLACK || b[row+1][col-1].getPiece().getType() == PieceType.BLACK_KING )) {
				return true ; 
			}
			if(!b[row][col].isOccupied() && b[row+1][col-1].isOccupied() && b[row+2][col-2].isOccupied()
					&& (b[row+1][col-1].getPiece().getType() == PieceType.BLACK || b[row+1][col-1].getPiece().getType() == PieceType.BLACK_KING ) 
					&& (b[row+2][col-2].getPiece().getType() == PieceType.BLACK || b[row+2][col-2].getPiece().getType() == PieceType.BLACK_KING )) {
				return true ; 
			}
			return false ; 
		} 
	}

	public boolean checkRighUp (int row , int col , Board board , PlayerType type) {

		Tiles[][] b = Board.getTheBoard();
		if(type == PlayerType.RED) {
			if(!b[row][col].isOccupied() && !b[row-1][col+1].isOccupied() && !b[row-2][col+2].isOccupied() ) {
				return true ; 
			}
			if(!b[row][col].isOccupied() && !b[row-1][col+1].isOccupied() && b[row-2][col+2].isOccupied()
					&& (b[row-2][col+2].getPiece().getType() == PieceType.RED || b[row-2][col+2].getPiece().getType() == PieceType.RED_KING )) {
				return true ; 
			}
			if(!b[row][col].isOccupied() && !b[row-2][col+2].isOccupied() && b[row-1][col+1].isOccupied()
					&& (b[row-1][col+1].getPiece().getType() == PieceType.RED || b[row-1][col+1].getPiece().getType() == PieceType.RED_KING )) {
				return true ; 
			}
			if(!b[row][col].isOccupied() && b[row-1][col+1].isOccupied() && b[row-2][col+2].isOccupied()
					&& (b[row-1][col+1].getPiece().getType() == PieceType.RED || b[row-1][col+1].getPiece().getType() == PieceType.RED_KING ) 
					&& (b[row-2][col+2].getPiece().getType() == PieceType.RED || b[row-2][col+2].getPiece().getType() == PieceType.RED_KING )) {
				return true ; 
			}
			return false ; 
		} else {
			if(!b[row][col].isOccupied() && !b[row-1][col+1].isOccupied() && !b[row-2][col+2].isOccupied() ) {
				return true ; 
			}
			if(!b[row][col].isOccupied() && !b[row-1][col+1].isOccupied() && b[row-2][col+2].isOccupied()
					&& (b[row-2][col+2].getPiece().getType() == PieceType.BLACK || b[row-2][col+2].getPiece().getType() == PieceType.BLACK_KING )) {
				return true ; 
			}
			if(!b[row][col].isOccupied() && !b[row-2][col+2].isOccupied() && b[row-1][col+1].isOccupied()
					&& (b[row-1][col+1].getPiece().getType() == PieceType.BLACK || b[row-1][col+1].getPiece().getType() == PieceType.BLACK_KING )) {
				return true ; 
			}
			if(!b[row][col].isOccupied() && b[row-1][col+1].isOccupied() && b[row-2][col+2].isOccupied()
					&& (b[row-1][col+1].getPiece().getType() == PieceType.BLACK || b[row-1][col+1].getPiece().getType() == PieceType.BLACK_KING ) 
					&& (b[row-2][col+2].getPiece().getType() == PieceType.BLACK || b[row-2][col+2].getPiece().getType() == PieceType.BLACK_KING )) {
				return true ; 
			}
			return false ; 
		} 
	}

	public boolean checkLeftUp (int row , int col , Board board , PlayerType type) {

		Tiles[][] b = Board.getTheBoard();
		if(type == PlayerType.RED) {
			if(!b[row][col].isOccupied() && !b[row-1][col-1].isOccupied() && !b[row-2][col-2].isOccupied() ) {
				return true ; 
			}
			if(!b[row][col].isOccupied() && !b[row-1][col-1].isOccupied() && b[row-2][col-2].isOccupied()
					&& (b[row-2][col-2].getPiece().getType() == PieceType.RED || b[row-2][col-2].getPiece().getType() == PieceType.RED_KING )) {
				return true ; 
			}
			if(!b[row][col].isOccupied() && !b[row-2][col-2].isOccupied() && b[row-1][col-1].isOccupied()
					&& (b[row-1][col-1].getPiece().getType() == PieceType.RED || b[row-1][col-1].getPiece().getType() == PieceType.RED_KING )) {
				return true ; 
			}
			if(!b[row][col].isOccupied() && b[row-1][col+1].isOccupied() && b[row-2][col+2].isOccupied()
					&& (b[row-1][col-1].getPiece().getType() == PieceType.RED || b[row-1][col-1].getPiece().getType() == PieceType.RED_KING ) 
					&& (b[row-2][col-2].getPiece().getType() == PieceType.RED || b[row-2][col-2].getPiece().getType() == PieceType.RED_KING )) {
				return true ; 
			}
			return false ; 
		} else {
			if(!b[row][col].isOccupied() && !b[row-1][col-1].isOccupied() && !b[row-2][col-2].isOccupied() ) {
				return true ; 
			}
			if(!b[row][col].isOccupied() && !b[row-1][col-1].isOccupied() && b[row-2][col-2].isOccupied()
					&& (b[row-2][col-2].getPiece().getType() == PieceType.BLACK || b[row-2][col-2].getPiece().getType() == PieceType.BLACK_KING )) {
				return true ; 
			}
			if(!b[row][col].isOccupied() && !b[row-2][col-2].isOccupied() && b[row-1][col-1].isOccupied()
					&& (b[row-1][col-1].getPiece().getType() == PieceType.BLACK || b[row-1][col-1].getPiece().getType() == PieceType.BLACK_KING )) {
				return true ; 
			}
			if(!b[row][col].isOccupied() && b[row-1][col-1].isOccupied() && b[row-2][col-2].isOccupied()
					&& (b[row-1][col-1].getPiece().getType() == PieceType.BLACK || b[row-1][col-1].getPiece().getType() == PieceType.BLACK_KING ) 
					&& (b[row-2][col-2].getPiece().getType() == PieceType.BLACK || b[row-2][col-2].getPiece().getType() == PieceType.BLACK_KING )) {
				return true ; 
			}
			return false ; 
		} 
	}


	public static boolean jumpAvailable(Pieces jumper, Board board) { // Checks numerous potential destinations
		Tiles theBoard[][] = Board.getTheBoard();
		int switchCase = 0, RowMovement = 0, jumperRow = 0, jumperCol = 0;

		if (jumper.getType() == PieceType.RED) { /// Red pieces may only move South
			switchCase = 1;
			RowMovement = 2; 
		} else if (jumper.getType() == PieceType.BLACK) { // Black pieces may only move North
			switchCase = 2;
			RowMovement = -2;
		} else if (jumper.getType() == PieceType.RED_KING // Kings move in all 4 directions
				|| jumper.getType() == PieceType.BLACK_KING) {
			switchCase = 3;
		}


		jumperRow = jumper.getRow();
		jumperCol = jumper.getCol();

		switch (switchCase) {
		case 1: { // Red pieces
			if ((jumperRow > -1 && jumperRow < 8)
					&& (jumperCol > -1 && jumperCol < 8)) { // Checks if within board bounds
				if ((jumperRow + RowMovement) <= 7) { // Checks if row is <= 7
					if (jumperCol < 6 &&  jumperCol > 1) { // If the selected piece is not near any edges
						if (!theBoard[jumperRow + RowMovement][jumperCol + 2].isOccupied()
								&& theBoard[jumperRow + 1][jumperCol + 1].isOccupied()) {
							return true;
						}
						if (!theBoard[jumperRow + RowMovement][jumperCol - 2] // Check left location
								.isOccupied()
								&& theBoard[jumperRow + 1][jumperCol - 1]
										.isOccupied()) {
							return true;
						}
						return false;
					}

					if (jumperCol >= 6) { // if jumper is close to 7 cols, check movement toward 0 col
						if (!theBoard[jumperRow + RowMovement][jumperCol - 2]
								.isOccupied()
								&& theBoard[jumperRow + 1][jumperCol - 1]
										.isOccupied()) {
							return true;
						}
					}
					if (jumperCol <= 1) {
						if (!theBoard[jumperRow + RowMovement][jumperCol + 2]
								.isOccupied()
								&& theBoard[jumperRow + 1][jumperCol + 1]
										.isOccupied()) {
							return true;
						}
					}
					return false;
				} else
					return false;
			}
		}
		break;
		case 2: { // Black pieces
			if ((jumperRow > -1 && jumperRow < 8)
					&& (jumperCol > -1 && jumperCol < 8)) { // Checks if within
				// board bounds
				if ((jumperRow + RowMovement) > -1) { // if row within bounds

					if ( jumperCol < 6 && jumperCol > 1) { // if column less than 6 and
						// greater than 1. check both
						// sides

						if (!theBoard[jumperRow + RowMovement][jumperCol + 2]
								.isOccupied()
								&& theBoard[jumperRow - 1][jumperCol + 1]
										.isOccupied()) {
							return true;
						}
						if (!theBoard[jumperRow + RowMovement][jumperCol - 2]
								.isOccupied()
								&& theBoard[jumperRow - 1][jumperCol - 1]
										.isOccupied()) {
							return true;
						}
						return false;
					}
					if (jumperCol >= 6) { // if jumper is close to 7 cols, check
						// movement toward 0 col
						if (!theBoard[jumperRow + RowMovement][jumperCol - 2]
								.isOccupied()
								&& theBoard[jumperRow - 1][jumperCol - 1]
										.isOccupied()) {
							return true;
						}
					}
					if (jumperCol <= 1) {
						if (!theBoard[jumperRow + RowMovement][jumperCol + 2]
								.isOccupied()
								&& theBoard[jumperRow - 1][jumperCol + 1]
										.isOccupied()) {
							return true;
						}
					}
					return false;
				} else
					return false;
			}
		}
		break;
		case 3: {
			int counter =0;
			int x=jumperRow ,y=jumperCol;
			int diffCol =  board.getDestCol() - jumperCol ;
			int diffRow =  board.getDestRow() - jumperRow ;
			if(diffRow>0 && diffCol>0) {

				// nazel yamen .... 
				while(counter<=7) {
					counter++;		
					if(x<5 && y<5) {

						if (!theBoard[x+2][y+2].isOccupied()
								&& theBoard[x+1][y+1].isOccupied()) {
							return true;	
						}	
						x++;
						y++;
					}else if(x==6 && y<=5) {

						if (!theBoard[0][y+1].isOccupied()
								&& theBoard[x+1][y+1].isOccupied()) {
							x=0;
							counter = 0;
							return true;	
						}
						counter=8;	
					} else if(x<=5 && y==6) {

						if (!theBoard[x+2][0].isOccupied()
								&& theBoard[x+1][y+1].isOccupied()) {
							y=0;
							counter = 0;
							return true;
						}
						counter=8;
					}
					else if(x==7 && y<=5) {

						if (!theBoard[1][y+2].isOccupied()
								&& theBoard[0][y+1].isOccupied()) {
							x=1;
							counter = 0;
							return true;	
						}
						counter=8;	
					} else if(x<=5 && y==7) {

						if (!theBoard[x+2][1].isOccupied()
								&& theBoard[x+1][0].isOccupied()) {
							y=1;
							counter = 0;
							return true;
						}
						counter=8;
					}
					else if(x==6 && y==7) {

						if (!theBoard[0][1].isOccupied()
								&& theBoard[7][0].isOccupied()) {
							x=0;
							y=1;
							counter = 0;
							return true;	
						}
						counter=8;	
					} else if(x==7 && y==6) {

						if (!theBoard[1][0].isOccupied()
								&& theBoard[0][7].isOccupied()) {
							y=0;
							x=1;
							counter = 0;
							return true;
						}
						counter=8;
					}
				}

			}

			if(diffCol < 0 && diffRow < 0) {

				//// 6al3 shmal
				int c = 0;
				while(c<=7) {
					c++;
					if(x>1 && x<8 && y>1 && y<8) {

						if (!theBoard[x-2][y-2].isOccupied()
								&& theBoard[x-1][y-1].isOccupied()) {
							c = 0;
							return true;	
						}}else if(x==1 &&  y>1 && y<8) {

							if (!theBoard[7][y-1].isOccupied()
									&& theBoard[x-1][y-1].isOccupied()) {
								x=7;
								c = 0;
								return true;	
							}
							c=8;	
						} else if(x==0 &&  y>1 && y<8) {

							if (!theBoard[6][y-2].isOccupied()
									&& theBoard[7][y-1].isOccupied()) {
								x=6;
								c = 0;
								return true;
							}
							c=8;
						}
						else if(x>1 && x<8 && y==0) {

							if (!theBoard[x-2][6].isOccupied()
									&& theBoard[x-1][7].isOccupied()) {
								y=6;
								c = 0;
								return true;	
							}
							c=8;	
						} else if(x>1 && x<8 && y==1) {

							if (!theBoard[x-2][7].isOccupied()
									&& theBoard[x-1][0].isOccupied()) {
								y=7;
								c = 0;
								return true;
							}
							c=8;
						}
						else if(x==1 && y==0) {

							if (!theBoard[7][6].isOccupied()
									&& theBoard[0][7].isOccupied()) {
								x=7;
								y=6;
								c = 0;
								return true;	
							}
							c=8;	
						} else if(x==0 && y==1) {

							if (!theBoard[6][7].isOccupied()
									&& theBoard[0][7].isOccupied()) {
								y=7;
								x=6;
								c = 0;
								return true;
							}
							c=8;
						}
				}	
			}

			if(diffRow <0 && diffCol>0) {

				///// 6al3 yamen 
				int c1 = 0;
				while(c1<=7) {
					c1++;
					if(x>1 && x<8 && y>=0 && y<6) {

						if (!theBoard[x-2][y+2].isOccupied()
								&& theBoard[x-1][y+1].isOccupied()) {
							c1 = 0;

							return true;	
						}}else if(x==1 &&  y>=0 && y<6) {

							if (!theBoard[7][y+1].isOccupied()
									&& theBoard[x-1][y+1].isOccupied()) {
								x=7;
								c1 = 0;
								return true;	
							}
							c1=8;	
						} else if(x==0 &&  y>=0 && y<6) {

							if (!theBoard[6][y+2].isOccupied()
									&& theBoard[7][y+1].isOccupied()) {
								x=6;
								c1 = 0;
								return true;
							}
							c1=8;
						}
						else if(x>1 && x<8 && y==6) {

							if (!theBoard[x-2][0].isOccupied()
									&& theBoard[x-1][7].isOccupied()) {
								y=0;
								c1 = 0;
								return true;	
							}
							c1=8;	
						} else if(x==0 && y==7) {

							if (!theBoard[0][1].isOccupied()
									&& theBoard[1][0].isOccupied()) {
								y=1;
								c1 = 0;
								return true;
							}
							c1=8;
						}else if(x==2 && y==7) {

							if (!theBoard[6][1].isOccupied()
									&& theBoard[7][0].isOccupied()) {
								y=1;
								c1 = 0;
								return true;
							}
							c1=8;
						}


				}
			}

			if(diffRow>0 && diffCol<0) {
				///// nazl shmal
				int c2 = 0;
				while(c2<=7) {
					c2++;
					if(x>=0 && x<6 && y>1 && y<8) {
						if (!theBoard[x+2][y-2].isOccupied()
								&& theBoard[x+1][y-1].isOccupied()) {
							c2 = 0;
							return true;	
						}}else if(x>=0 &&  x<6 && y==1) {
							if (!theBoard[x+2][7].isOccupied()
									&& theBoard[x+1][y-1].isOccupied()) {
								c2 = 0;
								y=7;
								return true;	
							}
							c2=8;	
						} else if(x>=0 &&  x<6 && y==0) {
							if (!theBoard[x+2][6].isOccupied()
									&& theBoard[x+1][7].isOccupied()) {
								y=6;
								c2 = 0;
								return true;
							}
							c2=8;
						}
						else if(x==6 && y>1 && y<8) {
							if (!theBoard[0][y-2].isOccupied()
									&& theBoard[x+1][y-1].isOccupied()) {
								x=0;
								c2 = 0;
								return true;	
							}
							c2=8;	
						} else if(x==7 && y==0) {

							if (!theBoard[1][6].isOccupied()
									&& theBoard[0][7].isOccupied()) {
								y=6;
								c2 = 0;
								return true;
							}
							c2=8;
						} else if(x==6 && y==0) {

							if (!theBoard[7][6].isOccupied()
									&& theBoard[7][7].isOccupied()) {
								y=6;
								c2 = 0;
								return true;
							}
							c2=8;
						}

				}
			}
		}
		break;
		default:
			System.err.println("Default case");
			break;
		}
		return false;
	}
	public static boolean checkKingMove(Board board , Pieces jumper) {
		Tiles theBoard[][] = Board.getTheBoard();
		int destR = board.getDestRow();
		int destC = board.getDestCol();
		int jRow = jumper.getRow();
		int jCol = jumper.getCol();
		if(jRow>destR && jCol>destC) {
			for(int i = 2 ; i<Math.abs(destR-jRow)-1; i++) {
				if(theBoard[destR+i][destC+i].isOccupied()) {
					return false;
				}
			}
		}
		if(jRow<destR && jCol>destC) {
			for(int i = 2 ; i<Math.abs(destR-jRow)-1; i++) {
				if(theBoard[destR-i][destC+i].isOccupied()) {
					return false;
				}
			}
		}
		if(jRow>destR && jCol<destC) {
			for(int i = 2 ; i<Math.abs(destR-jRow)-1; i++) {
				if(theBoard[destR+i][destC-i].isOccupied()) {
					return false;
				}
			}
		}
		if(jRow<destR && jCol<destC) {
			for(int i = 2 ; i<Math.abs(destR-jRow)-1; i++) {
				if(theBoard[destR-i][destC-i].isOccupied()) {
					return false;
				}
			}
		}
		return true ;
	}


	public static int getStuckRow() {
		return stuckRow;
	}

	public static void setStuckRow(int stuckRow) {
		Move.stuckRow = stuckRow;
	}

	public static int getStuckCol() {
		return stuckCol;
	}

	public static void setStuckCol(int stuckCol) {
		Move.stuckCol = stuckCol;
	}

}