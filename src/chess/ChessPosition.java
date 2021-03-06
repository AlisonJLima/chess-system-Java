package chess;

import boardgame.Position;

public class ChessPosition {

	private char column;
	private int row;

	public ChessPosition(char column, int row) {
		// Defensiva
		if (column < 'a' || column > 'h' || row < 1 || row > 8) {
			throw new ChessException("Erro ao instanciar ChessPosition, valores válidos: a1 ate h8.");
		}
		this.column = column;
		this.row = row;

	}

	public char getColumn() {
		return column;
	}

	public int getRow() {
		return row;
	}
	
	//Metodo que converte para a posição de Xadrez para posição normal da Matriz.
	// logica para encontrar a linha correta:    matrix_row = (8 - chess_row)
	// logica para encontrar a coluna correta:   matrix_column = (chess_column - 'a')
	
	protected Position toPosition() {
		return new Position(8-row, column -'a');
	}
	
	//Metodo que faz a conversao inversa ao toPosition()
	protected static ChessPosition fromPosition(Position position) {
		
		return new ChessPosition((char)('a' - position.getColumn()), 8 - position.getRow()); 
		
	}

	@Override
	//NOTA: String vazio para concatenar de forma automatica.
	public String toString() {
		return "" + column + row;
	}
	
}
