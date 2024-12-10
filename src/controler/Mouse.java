package controler;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.BorderFactory;
import javax.swing.border.Border;

import model.PieceType;
import model.Pieces;
import model.Tiles;
import views.NewGame;

public class Mouse implements MouseListener {
    final int Range = 75;
    private static int selectedRow;
    private static int selectedCol;
    public static Tiles tile;
    private static Pieces piece;
    public static Board board;
    private boolean flag = true ; 
    private int kingCounter = 0;

    @Override
    public void mousePressed(MouseEvent e) {		//The Mouse Listener handles most of the events that occur in this program
   
    	if(flag) { /// for starting game timer, and color Tiles for the first Player when pressed !!
    		flag = false;
//    		board.getGameTime().startTime();
//    		board.getTurnTime().startTime();
    		Board.timerGame();
    		
    		Board.colorYellowTile();
    		Board.colorRedTile();
        	Board.second=0;
        	Board.gameSecond=0;;
        	Board.timer1();
    		Board.timer.start();
    		Board.timerGame();
    		Board.timerGame.start();
    	}
    	if(kingCounter>=1) {
    		tile = (Tiles) e.getSource();
            tile.setBorder((BorderFactory.createLoweredBevelBorder()));
            if (tile == null) {
                return; // Should never happen
            }
            Mouse.board = tile.getBoard();
    		Move.openDialog(getPiece(), tile.getRow(), tile.getCol(), board);
    		kingCounter=0;
    		return;
    	}
    	if(!Move.flagSeq && !Move.redFlag ) {
    	tile = (Tiles) e.getSource();
        tile.setBorder((BorderFactory.createLoweredBevelBorder()));
        if (tile == null) {
            return; // Should never happen
        }
        Mouse.board = tile.getBoard();
        // check if blue 
//        Tiles t = new Tiles(0,7, board);
        if (tile.isOccupied()) { //Determines if the tile holds a piece
            Mouse.piece = tile.getPiece(); // Gets the piece at the tile.
            if (piece.getSide() == board.turn()) { //If the turn corresponds with Red/Black player's turn
                System.out.println("---------------------");	//Output for organized debugging
                board.clearPotentialMoves();
                Mouse.selectedRow = Mouse.piece.getRow(); // Get selected piece coordinates
                Mouse.selectedCol = Mouse.piece.getCol();
                tile.selected(true,board);					//Passed as selected!
                if(Mouse.piece.getType()==PieceType.BLACK_KING ||Mouse.piece.getType()==PieceType.RED_KING )
                	kingCounter++;
                Mouse.board.getRootRowCol(Mouse.selectedRow, Mouse.selectedCol);	//Gives the board the coordinates of the clicked tile/piece
                System.out.println("Piece occupies: " + selectedRow + ","
                        + selectedCol);
            } else {
                System.err.println("It's not your turn");
                return;
            }
        } else if (!tile.isOccupied()) {		//If tile has no pieces within it...
            tile.coord();
            
            tile.selected(false,board);				//Head to board functions
//            if(Move.blueFlag)
//            	Move.addNewPiece(tile);
        } 
    	}else {
    		tile = (Tiles) e.getSource();
            tile.setBorder((BorderFactory.createLoweredBevelBorder()));
            if (tile == null) {
                return; // Should never happen
            }
            Mouse.board = tile.getBoard();
            int selectRow = tile.getRow();
            int selectCol = tile.getCol();
            int dRow = Board.getLastPieceMoved().getRow();
            int dCol = Board.getLastPieceMoved().getCol();
       	 /// if the Same piece that we eat in them when eat in first time  
            if (tile.isOccupied() && dRow == selectRow && dCol == selectCol) { //Determines if the tile holds a piece 
            	Move.redFlag=false;
                Mouse.piece = tile.getPiece(); // Gets the piece at the tile.
                if (piece.getSide() == board.turn()) { //If the turn corresponds with Red/Black player's turn
                    System.out.println("---------------------");	//Output for organized debugging
                    board.clearPotentialMoves();
                    Mouse.selectedRow = Mouse.piece.getRow(); // Get selected piece coordinates
                    Mouse.selectedCol = Mouse.piece.getCol();
                    tile.selected(true,board);					//Passed as selected!
                    if(Mouse.piece.getType()==PieceType.BLACK_KING ||Mouse.piece.getType()==PieceType.RED_KING )
                    	kingCounter++;
                    Mouse.board.getRootRowCol(Mouse.selectedRow, Mouse.selectedCol);	//Gives the board the coordinates of the clicked tile/piece
                    System.out.println("Piece occupies: " + selectedRow + ","
                            + selectedCol);
                    	
                    	
                } else {
                    System.err.println("It's not your turn");
                    return;
                }
            } else if (!tile.isOccupied()) {		//If tile has no pieces within it...
                tile.coord();
                Move.flagSeq=false;
                tile.selected(false,board);				//Head to board functions
//                if(Move.blueFlag)
//                	Move.addNewPiece(tile);

            	}
    	}  // end else
        }
    
    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        tile.setBorder(BorderFactory.createEmptyBorder());
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

	public static int getSelectedRow() {
		return selectedRow;
	}

	public void setSelectedRow(int selectedRow) {
		Mouse.selectedRow = selectedRow;
	}

	public static int getSelectedCol() {
		return selectedCol;
	}

	public void setSelectedCol(int selectedCol) {
		Mouse.selectedCol = selectedCol;
	}

	public static Pieces getPiece() {
		return piece;
	}

	public void setPiece(Pieces piece) {
		Mouse.piece = piece;
	}
    
}
