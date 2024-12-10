package model;

public class Pieces implements Cloneable {

	private PieceType type;
	private PlayerType side;
	private int row = 0;
	private int col = 0;
	private boolean potentialLocation;


	public Pieces(int row, int col, PieceType type) {
		if (type == PieceType.RED || type == PieceType.RED_KING) {
			side = PlayerType.RED;
		} else if (type == PieceType.BLACK || type == PieceType.BLACK_KING) {
			side = PlayerType.BLACK;
		}
		this.row = row;
		this.col = col;
		this.type = type;
	}

	public PieceType getType() {
		return this.type;
	}

	public void crowned() {
		if (type == PieceType.RED) {
			type = PieceType.RED_KING;
			System.out.println("Red crowned");
		} else if (type == PieceType.BLACK) {
			type = PieceType.BLACK_KING;
			System.out.println("Black crowned");
		}
	}

	public boolean canEatOrMove(Tiles[][] board) {
		return (eatOrMove(board));
	}

	private boolean eatOrMove(Tiles[][] board) {
		if(type == PieceType.RED && (leftRed(board )|| leftRedMove(board)||rightRedMove(board) || rightRed(board ))) {
			return true;
		} else 
			if(type == PieceType.BLACK&& (leftBlack(board )|| leftBlackMove(board) || rightBlack(board ) ||rightBlackMove(board) )) {
				return true;
			} else 
				if((type == PieceType.RED_KING || type == PieceType.BLACK_KING)) {
					return (kingEatRightINCol7(board)||kingEatLeftINCol7(board)||kingMoveLeftINCol7(board)||kingMoveRightINCol7(board)||kingEatLeftINRow0(board)||
							kingEatRightINRow0(board)||kingMoveRightINRow0(board)||kingMoveLeftINRow0(board)||kingMoveLastRow(board)|| kingEatLastRow(board)|| 
							kingMoveFirstCol(board) || kingEatFirstCol(board) ||rightRed(board ) || rightBlack(board ) 
							||rightBlackMove(board) || rightRedMove(board)||rightBlackMove(board) || rightRedMove(board)||leftRedMove(board) || leftBlackMove(board)
							||leftRed(board) || leftBlack(board));
				}else
					return false;
	}

	public boolean kingMoveLeftINRow0(Tiles[][]b) {
		if((type == PieceType.RED_KING || type == PieceType.BLACK_KING)&&row==0&&col>0&&col<8&&
				(!b[(row-1)%8+8][col-1].isOccupied()||!b[(row+1)][col-1].isOccupied()))
			return true;
		else {
			return false;
		}
	}
	public boolean kingMoveRightINRow0(Tiles[][]b) {
		if((type == PieceType.RED_KING || type == PieceType.BLACK_KING)&&row==0&&col>0&&col<8&&
				(!b[(row-1)%8+8][(col+1)%8].isOccupied()||!b[(row+1)][(col+1)%8].isOccupied()))
			return true;
		else {
			return false;
		}
	}
	public boolean kingEatRightINRow0(Tiles[][]b) {
		if((type == PieceType.RED_KING )&&row==0&&col>0&&col<8&&
				((b[(row-1)%8+8][(col+1)%8].isOccupied()&&!b[(row-2)%8+8][(col+2)%8].isOccupied() && (b[(row-1)%8+8][(col+1)%8].getPiece().getType() == PieceType.BLACK || b[(row-1)%8+8][(col+1)%8].getPiece().getType() == PieceType.BLACK_KING)) 
						||(b[(row+1)][(col+1)%8].isOccupied()&&!b[(row+2)][(col+2)%8].isOccupied() && (b[(row+1)][(col+1)%8].getPiece().getType() == PieceType.BLACK || b[(row+1)][(col+1)%8].getPiece().getType() == PieceType.BLACK_KING))))
			return true;
		if((type == PieceType.BLACK_KING)&&row==0&&col>0&&col<8&&
				((b[(row-1)%8+8][(col+1)%8].isOccupied()&&!b[(row-2)%8+8][(col+2)%8].isOccupied() && (b[(row-1)%8+8][(col+1)%8].getPiece().getType() == PieceType.RED || b[(row-1)%8+8][(col+1)%8].getPiece().getType() == PieceType.RED_KING)) 
						||(b[(row+1)][(col+1)%8].isOccupied()&&!b[(row+2)][(col+2)%8].isOccupied() && (b[(row+1)][(col+1)%8].getPiece().getType() == PieceType.RED || b[(row+1)][(col+1)%8].getPiece().getType() == PieceType.RED_KING))))
			return true;
		else {
			return false;
		}
	}

	public boolean kingEatLeftINRow0(Tiles[][]b) {
		if((type == PieceType.RED_KING )&&((row==0&&col>1&&col<8&&
				((b[(row-1)%8+8][col-1].isOccupied()&&!b[(row-2)%8+8][col-2].isOccupied() && (b[(row-1)%8+8][col-1].getPiece().getType() == PieceType.BLACK ||b[(row-1)%8+8][col-1].getPiece().getType() == PieceType.BLACK_KING))
						||(b[(row+1)][col-1].isOccupied()&&!b[(row+2)][col-2].isOccupied() && (b[(row+1)][col-1].getPiece().getType() == PieceType.BLACK || b[(row+1)][col-1].getPiece().getType() == PieceType.BLACK_KING) )))
				||row==0&&col==1&&( (b[(row+1)][col-1].isOccupied()&&!b[(row+2)][(col-2)%8+8].isOccupied() && (b[(row+1)][col-1].getPiece().getType() == PieceType.BLACK ||b[(row+1)][col-1].getPiece().getType() == PieceType.BLACK_KING ))
						||(b[(row-1)%8+8][col-1].isOccupied()&&!b[(row-2)%8+8][(col-2)%8+8].isOccupied() && (b[(row-1)%8+8][col-1].getPiece().getType() == PieceType.BLACK || b[(row-1)%8+8][col-1].getPiece().getType() == PieceType.BLACK_KING) ))))
			return true;
		if((type == PieceType.BLACK_KING)&&((row==0&&col>1&&col<8&&
				((b[(row-1)%8+8][col-1].isOccupied()&&!b[(row-2)%8+8][col-2].isOccupied() && (b[(row-1)%8+8][col-1].getPiece().getType() == PieceType.RED ||b[(row-1)%8+8][col-1].getPiece().getType() == PieceType.RED_KING))
						||(b[(row+1)][col-1].isOccupied()&&!b[(row+2)][col-2].isOccupied() && (b[(row+1)][col-1].getPiece().getType() == PieceType.RED || b[(row+1)][col-1].getPiece().getType() == PieceType.RED_KING) )))
				||row==0&&col==1&&( (b[(row+1)][col-1].isOccupied()&&!b[(row+2)][(col-2)%8+8].isOccupied() && (b[(row+1)][col-1].getPiece().getType() == PieceType.RED ||b[(row+1)][col-1].getPiece().getType() == PieceType.RED_KING ))
						||(b[(row-1)%8+8][col-1].isOccupied()&&!b[(row-2)%8+8][(col-2)%8+8].isOccupied() && (b[(row-1)%8+8][col-1].getPiece().getType() == PieceType.RED || b[(row-1)%8+8][col-1].getPiece().getType() == PieceType.RED_KING) ))))
			return true;
		else {
			return false;
		}
	}

	public boolean kingMoveRightINCol7(Tiles[][]b) {
		if((type == PieceType.RED_KING || type == PieceType.BLACK_KING)&&row>0&&row<7&&col==7&&
				(!b[row-1][(col+1)%8].isOccupied()||!b[(row+1)][(col+1)%8].isOccupied()))
			return true;
		else {
			return false;
		}
	}

	public boolean kingMoveLeftINCol7(Tiles[][]b) {
		if((type == PieceType.RED_KING || type == PieceType.BLACK_KING)&&row>0&&row<7&&col==7&&
				(!b[row-1][col-1].isOccupied()||!b[row+1][col-1].isOccupied()))
			return true;
		else {
			return false;
		}
	}

	public boolean kingEatLeftINCol7(Tiles[][]b) {
		if((type == PieceType.RED_KING )&&row>0&&row<7&&col==7&&
				((b[row-1][col-1].isOccupied()&&!b[row-2][col-2].isOccupied() && (b[row-1][col-1].getPiece().getType() == PieceType.BLACK ||b[row-1][col-1].getPiece().getType() == PieceType.BLACK_KING)) ||
						(b[(row+1)%8][col-1].isOccupied()&&!b[(row+2)%8][col-2].isOccupied() && (b[(row+1)%8][col-1].getPiece().getType() == PieceType.BLACK ||b[(row+1)%8][col-1].getPiece().getType() == PieceType.BLACK_KING))))
			return true;
		if((type == PieceType.BLACK_KING )&&row>0&&row<7&&col==7&&
				((b[row-1][col-1].isOccupied()&&!b[row-2][col-2].isOccupied() && (b[row-1][col-1].getPiece().getType() == PieceType.RED ||b[row-1][col-1].getPiece().getType() == PieceType.RED_KING)) ||
						(b[(row+1)%8][col-1].isOccupied()&&!b[(row+2)%8][col-2].isOccupied() && (b[(row+1)%8][col-1].getPiece().getType() == PieceType.RED ||b[(row+1)%8][col-1].getPiece().getType() == PieceType.RED_KING))))
			return true;
		else {
			return false;
		}
	}

	public boolean kingEatRightINCol7(Tiles[][]b) {
		if((type == PieceType.RED_KING &&(row>1&&row<7&&col==7&&
				((b[row-1][(col+1)%8].isOccupied() && !b[row-2][(col+2)%8].isOccupied() && 
						((b[row-1][(col+1)%8].getPiece().getType()==PieceType.BLACK || b[row-1][(col+1)%8].getPiece().getType()==PieceType.BLACK_KING ) ) )
						||(b[(row+1)%8][(col+1)%8].isOccupied()&&!b[(row+2)%8][(col+2)%8].isOccupied() && 
								(b[(row+1)%8][(col+1)%8].getPiece().getType() == PieceType.BLACK || b[(row+1)%8][(col+1)%8].getPiece().getType() == PieceType.BLACK_KING))))
				|| ( type == PieceType.BLACK_KING ) && (row>1 && row<7 && col==7 &&
				((b[row-1][(col+1)%8].isOccupied() && !b[row-2][(col+2)%8].isOccupied() && (b[row-1][(col+1)%8].getPiece().getType() == PieceType.RED_KING || b[row-1][(col+1)%8].getPiece().getType() == PieceType.RED ))
						||(b[(row+1)%8][(col+1)%8].isOccupied()&&!b[(row+2)%8][(col+2)%8].isOccupied() && (b[(row+1)%8][(col+1)%8].getPiece().getType() == PieceType.RED || b[(row+1)%8][(col+1)%8].getPiece().getType() == PieceType.RED_KING))))))
			return true;
		else {
			return false;
		}
	}

	private boolean kingMoveLastRow(Tiles[][] b) {
		if((type == PieceType.RED_KING || type == PieceType.BLACK_KING) && row==7 && col>1 && (!b[(row+1)%8][col-1].isOccupied() || 
				!b[(row+1)%8][col+1].isOccupied() ||!b[row-1][col-1].isOccupied() || !b[row-1][col+1].isOccupied()  ))
			return true;
		if((type == PieceType.RED_KING || type == PieceType.BLACK_KING) && row==7 && col==0 && (!b[(row+1)%8][(col-1)%8+8].isOccupied() || 
				!b[(row+1)%8][col+1].isOccupied()|| !b[(row-1)][(col-1)%8+8].isOccupied() || !b[(row-1)][col+1].isOccupied()))
			return true;
		return false;
	}

	private boolean kingEatLastRow(Tiles[][] b) {
		if((type == PieceType.RED_KING) && row==7 && col>1 && ((b[(row+1)%8][col-1].isOccupied() && !b[(row+2)%8][col-2].isOccupied() && (b[(row+1)%8][col-1].getPiece().getType() == PieceType.BLACK || b[(row+1)%8][col-1].getPiece().getType() == PieceType.BLACK_KING)) || 
				(b[(row+1)%8][(col+1)%8].isOccupied() && !b[(row+2)%8][(col+2)%8].isOccupied() && (b[(row+1)%8][(col+1)%8].getPiece().getType() == PieceType.BLACK || b[(row+1)%8][(col+1)%8].getPiece().getType() == PieceType.BLACK_KING)) 
				||(b[row-1][col-1].isOccupied() && !b[row-2][col-2].isOccupied() &&(b[row-1][col-1].getPiece().getType() == PieceType.BLACK || b[row-1][col-1].getPiece().getType() == PieceType.BLACK_KING)) 
				|| (b[row-1][(col+1)%8].isOccupied() && !b[row-2][(col+2)%8].isOccupied() && (b[row-1][(col+1)%8].getPiece().getType() == PieceType.BLACK || b[row-1][(col+1)%8].getPiece().getType() == PieceType.BLACK_KING) ) ))
			return true;
		if((type == PieceType.RED_KING) && row==7 && col==0 && (
				(b[(row+1)%8][(col-1)%8+8].isOccupied() && !b[(row+2)%8][(col-2)%8+8].isOccupied() && (b[(row+1)%8][(col-1)%8+8].getPiece().getType()==PieceType.BLACK || b[(row+1)%8][(col-1)%8+8].getPiece().getType()==PieceType.BLACK_KING)) || 
				(b[(row+1)%8][col+1].isOccupied() && !b[(row+2)%8][col+2].isOccupied() && (b[(row+1)%8][col+1].getPiece().getType() == PieceType.BLACK || b[(row+1)%8][col+1].getPiece().getType() == PieceType.BLACK_KING))||
				(b[(row-1)][(col-1)%8+8].isOccupied() && !b[(row-2)][(col-2)%8+8].isOccupied() && (b[(row-1)][(col-1)%8+8].getPiece().getType() == PieceType.BLACK || b[(row-1)][(col-1)%8+8].getPiece().getType() == PieceType.BLACK_KING)) ||
				(b[(row-1)][col+1].isOccupied() && !b[(row-2)][col+2].isOccupied() && (b[(row-1)][col+1].getPiece().getType() == PieceType.BLACK || b[(row-1)][col+1].getPiece().getType() == PieceType.BLACK_KING ))))
			return true;
		if((type == PieceType.BLACK_KING) && row==7 && col>1 && ((b[(row+1)%8][col-1].isOccupied() && !b[(row+2)%8][col-2].isOccupied() && (b[(row+1)%8][col-1].getPiece().getType() == PieceType.RED || b[(row+1)%8][col-1].getPiece().getType() == PieceType.RED_KING)) || 
				(b[(row+1)%8][(col+1)%8].isOccupied() && !b[(row+2)%8][(col+2)%8].isOccupied() && (b[(row+1)%8][(col+1)%8].getPiece().getType() == PieceType.RED || b[(row+1)%8][(col+1)%8].getPiece().getType() == PieceType.RED_KING)) 
				||(b[row-1][col-1].isOccupied() && !b[row-2][col-2].isOccupied() &&(b[row-1][col-1].getPiece().getType() == PieceType.RED || b[row-1][col-1].getPiece().getType() == PieceType.RED_KING)) 
				|| (b[row-1][(col+1)%8].isOccupied() && !b[row-2][(col+2)%8].isOccupied() && (b[row-1][(col+1)%8].getPiece().getType() == PieceType.RED || b[row-1][(col+1)%8].getPiece().getType() == PieceType.RED_KING) ) ))
			return true;
		if((type == PieceType.BLACK_KING) && row==7 && col==0 && (
				(b[(row+1)%8][(col-1)%8+8].isOccupied() && !b[(row+2)%8][(col-2)%8+8].isOccupied() && (b[(row+1)%8][(col-1)%8+8].getPiece().getType()==PieceType.RED || b[(row+1)%8][(col-1)%8+8].getPiece().getType()==PieceType.RED_KING)) || 
				(b[(row+1)%8][col+1].isOccupied() && !b[(row+2)%8][col+2].isOccupied() && (b[(row+1)%8][col+1].getPiece().getType() == PieceType.RED || b[(row+1)%8][col+1].getPiece().getType() == PieceType.RED_KING))||
				(b[(row-1)][(col-1)%8+8].isOccupied() && !b[(row-2)][(col-2)%8+8].isOccupied() && (b[(row-1)][(col-1)%8+8].getPiece().getType() == PieceType.RED || b[(row-1)][(col-1)%8+8].getPiece().getType() == PieceType.RED_KING)) ||
				(b[(row-1)][col+1].isOccupied() && !b[(row-2)][col+2].isOccupied() && (b[(row-1)][col+1].getPiece().getType() == PieceType.RED || b[(row-1)][col+1].getPiece().getType() == PieceType.RED_KING ))))
			return true;
		return false;
	}

	private boolean kingMoveFirstCol (Tiles [][]b) {
		if((type == PieceType.RED_KING || type == PieceType.BLACK_KING) && col==0 && row<7 && (!b[row+1][col+1].isOccupied() || !b[row+1][(col-1)%8+8].isOccupied() || 
				!b[(row-1)][col+1].isOccupied() || !b[(row-1)][(col-1)%8+8].isOccupied() ))
			return true;
		return false; /// 7,0 is checked in kingMoveLastRow 
	}

	private boolean kingEatFirstCol (Tiles [][]b) {
		if((type == PieceType.BLACK_KING) && col==0 && row<7 && 
				((b[row+1][col+1].isOccupied()&&!b[row+2][col+2].isOccupied() && (b[row+1][col+1].getPiece().getType() == PieceType.RED ||b[row+1][col+1].getPiece().getType() == PieceType.RED_KING ) )
						||(b[row+1][(col-1)%8+8].isOccupied() && !b[(row+2)%8][(col-2)%8+8].isOccupied() && (b[row+1][(col-1)%8+8].getPiece().getType() == PieceType.RED || b[row+1][(col-1)%8+8].getPiece().getType() == PieceType.RED_KING  ) )
						||(b[(row-1)][col+1].isOccupied() && !b[(row-2+8)%8][col+2].isOccupied() && (b[(row-1)][col+1].getPiece().getType() == PieceType.RED ||b[(row-1)][col+1].getPiece().getType() == PieceType.RED_KING)) 
						||(b[(row-1)][(col-1)%8].isOccupied() && !b[(row-2+8)%8][(col-2+8)%8].isOccupied()) && (b[(row-1)][(col-1)%8].getPiece().getType() == PieceType.RED || b[(row-1)][(col-1)%8].getPiece().getType() == PieceType.RED_KING)))
			return true;
		if((type == PieceType.RED_KING) && col==0 && row<7 && 
				((b[row+1][col+1].isOccupied()&&!b[row+2][col+2].isOccupied() && (b[row+1][col+1].getPiece().getType() == PieceType.BLACK ||b[row+1][col+1].getPiece().getType() == PieceType.BLACK_KING ) )
						||(b[row+1][(col-1)%8+8].isOccupied() && !b[(row+2)%8][(col-2)%8+8].isOccupied() && (b[row+1][(col-1)%8+8].getPiece().getType() == PieceType.BLACK || b[row+1][(col-1)%8+8].getPiece().getType() == PieceType.BLACK_KING  ) )
						||(b[(row-1)][col+1].isOccupied() && !b[(row-2+8)%8][col+2].isOccupied() && (b[(row-1)][col+1].getPiece().getType() == PieceType.BLACK ||b[(row-1)][col+1].getPiece().getType() == PieceType.BLACK_KING)) 
						||(b[(row-1)][(col-1)%8].isOccupied() && !b[(row-2+8)%8][(col-2+8)%8].isOccupied()) && (b[(row-1)][(col-1)%8].getPiece().getType() == PieceType.BLACK || b[(row-1)][(col-1)%8].getPiece().getType() == PieceType.BLACK_KING)))
			return true;
		return false; /// 7,0 is checked in kingMoveLastRow 
	}


	private boolean rightRed(Tiles[][] board) {
		if (row+2<8 && col+2<8 && board[row+1][col+1].isOccupied() && !board[row+2][col+2].isOccupied() && (board[row+1][col+1].getPiece().getType() == PieceType.BLACK 
				|| board[row+1][col+1].getPiece().getType() == PieceType.BLACK_KING)) {
			return true;
		}
		return false;
	}

	private boolean rightBlack(Tiles[][] board) {
		if(row-2>=0 && col+2<8 && board[row-1][col+1].isOccupied() && !board[row-2][col+2].isOccupied() && (board[row-1][col+1].getPiece().getType() != PieceType.RED 
				|| board[row-1][col+1].getPiece().getType() != PieceType.RED_KING )) {
			return true;
		}
		return false;
	}

	private boolean rightBlackMove (Tiles[][] board ) {
		if(row-1>=0 && col+1<8 && !board[row-1][col+1].isOccupied()) {
			return true;
		}
		return false;
	}

	private boolean rightRedMove (Tiles[][] board ) {
		if(row+1<8 && col+1<8 && !board[row+1][col+1].isOccupied()) {
			return true;
		}
		return false;
	}

	private boolean leftRed(Tiles[][] board) {
		if (row+2 <8 && col-2 >= 0 &&board[row+1][col-1].isOccupied() &&!board[row+2][col-2].isOccupied() && (board[row+1][col-1].getPiece().getType() == PieceType.BLACK
				|| board[row+1][col-1].getPiece().getType() == PieceType.BLACK_KING )) {
			return true;
		}
		return false;
	}

	private boolean leftRedMove (Tiles[][] board ) {
		if(row+1<8&& col-1>=0 &&!board[row+1][col-1].isOccupied()) {
			return true;
		}
		return false;
	}

	private boolean leftBlack(Tiles[][] board) {
		if (row-2 >= 0 && col-2 >= 0 && board[row-1][col-1].isOccupied() && !board[row-2][col-2].isOccupied() &&
				(board[row-1][col-1].getPiece().getType() == PieceType.RED 
				|| board[row-1][col-1].getPiece().getType() == PieceType.RED_KING  )) {
			return true;
		}
		return false;
	}
	private boolean leftBlackMove (Tiles[][] board ) {
		if(row-1>=0 && col-1>=0 && !board[row-1][col-1].isOccupied()) {
			return true;
		}
		return false;
	}


	public int getRow() {
		return row;
	}

	public void setRow(int row) {
		this.row = row;
	}

	public int getCol() {
		return col;
	}

	public void setCol(int col) {
		this.col = col;
	}



	public void setType(PieceType type) {
		this.type = type;
	}

	public PlayerType getSide() {
		return this.side;
	}


	public void talk(){
		System.out.println("Piece is at " + row + "," + col);
	}

	public void moved(int row, int col) { // Changes coordinates after being
		// moved
		System.out.println("Moved to " + row + "," + col);
		this.row = row;
		this.col = col;
	}

	public Pieces copyPieces(int row, int col, PieceType type) {
		Pieces copy=null;
		try {
			copy=(Pieces) super.clone();
			copy.setRow(row);
			copy.setCol(col);
			copy.setType(type);
		} catch (CloneNotSupportedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return copy;
	}

	
	
	
	public boolean potentialMove() {
		potentialLocation = true;
		return potentialLocation;
	}

}