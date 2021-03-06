package boardgame;

public class Board {

	private int rows;
	private int columns;
	private Piece[][] pieces;

	public Board(int rows, int columns) {
		//defensiva
		if (rows < 1 || columns < 1) {
			throw new BoardException("Erro ao criar o Tabuleiro: Deve ter pelo menos 1 linha e uma coluna");
		}
		this.rows = rows;
		this.columns = columns;
		pieces = new Piece[rows][columns];
	}

	public int getRows() {
		return rows;
	}

	public int getColumns() {
		return columns;
	}

	public Piece piece(int row, int column) {
		// defensiva
		if (!positionExists(row, column)) {
			throw new BoardException("Posi��o inexistente no tabuleiro!");
		}
		return pieces[row][column];
	}

	public Piece piece(Position position) {
		// defensiva
		if (!positionExists(position)) {
			throw new BoardException("Posi��o inexistente no tabuleiro!");
		}
		return pieces[position.getRow()][position.getColumn()];
	}

	// Metodo para adicionar pe�as
	// Atribui a pe�a para a posi��o, na matriz de pieces

	public void placePiece(Piece piece, Position position) {
		
		if (thereIsAPiece(position)) {
			throw new BoardException("Ja existe uma pe�a na posi��o " + position);
		}
		pieces[position.getRow()][position.getColumn()] = piece;
		piece.position = position;

	}


	//metodo auxiliar para testar se uma posi��o � valida
	private boolean positionExists(int row, int column) {
		return row >= 0 && row < rows && column >= 0 && column < columns;
	}
	//metodo para testar se a posi��o � valida
	public boolean positionExists(Position position) {
		return positionExists(position.getRow(), position.getColumn());
}
	public boolean thereIsAPiece(Position position) {
		if (!positionExists(position)) {
			throw new BoardException("Posi��o inexistente no tabuleiro!");
		}
		return piece(position) != null;
	}
}
