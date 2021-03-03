package boardgame;

public class Piece {
		
	protected Position position;
	private Board board;
	
	//Pe�a rec�m criada, recebe position = null.
	// pois ainda nao est� inserida no tabuleiro
	
	public Piece(Board board) {
		this.board = board;
		position = null;
	}

	protected Board getBoard() {
		return board;
	}
	
	

}
