package model;

import controler.Board;

public class Player {
	private String name ;
	private int id ; 
	
	private int pieces = 0;
	private PlayerType side;

	
	private String firstName ;
	private String lastName ;
	private String nickName ;

public Player(String firstNamee , String lastName,String nickName ) {
	this.firstName=firstNamee ;
	this.lastName=lastName;
	this.nickName=nickName;
}
public Player(String nickName ) {
//	this.firstName=firstNamee ;
//	this.lastName=lastName;
	this.nickName=nickName;
}
	public String getFirstName() {
	return firstName;
}

public void setFirstName(String firstName) {
	this.firstName = firstName;
}

public String getLastName() {
	return lastName;
}

public void setLastName(String lastName) {
	this.lastName = lastName;
}

public String getNickName() {
	return nickName;
}

public void setNickName(String nickName) {
	this.nickName = nickName;
}
	

	public void pieceEaten(Board board) {
		pieces--;
		if(side == PlayerType.RED){
			System.out.println("Red piece eaten");
			
		}else if (side == PlayerType.BLACK){
			System.out.println("Black piece eaten");
		}

		
	}

	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}


	public Player(PlayerType type) {
		side = type;
		pieces = 12;
	}

	public int piecesLeft() {
		return pieces;
	}


}