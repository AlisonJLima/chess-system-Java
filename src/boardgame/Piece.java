package boardgame;

public abstract class Piece {
		
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
	
	
	public abstract boolean [][] possibleMoves();
		
	//Hook Method - um metodo concreto utiliza um metodo abstrato.
	public boolean possibleMove(Position position) {
		return possibleMoves()[position.getRow()][position.getColumn()];
	}
	//Metodo que verifica se existe algum movimento possivel para a pe�a
	// Variavel mat recebe a matriz de movimentos e verifica se tem alguma posi��o que seja verdadeira.
	
	public boolean isThereAnyPossibleMove() {
		boolean [][] mat = possibleMoves();
		for(int i=0; i<mat.length; i++) {
			for (int j=0; j<mat.length; j++) {
				if(mat[i][j]) {
					return true;
				}
			}
		}
		return false;
		
	}

}
