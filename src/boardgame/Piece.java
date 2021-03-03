package boardgame;

public class Piece {
		
	protected Position position;
	private Board board;
	
	//Peça recém criada, recebe position = null.
	// pois ainda nao está inserida no tabuleiro
	
	public Piece(Board board) {
		this.board = board;
		position = null;
	}

	protected Board getBoard() {
		return board;
	}
	
	

}
