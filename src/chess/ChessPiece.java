package chess;

import boardgame.Board;
import boardgame.Piece;
import boardgame.Position;

public abstract class ChessPiece extends Piece {
	
	private Color color;
	//private int moveCount;
	
	public ChessPiece(Board board, Color color) {
		super(board);
		this.color = color;	
	}

	public Color getColor() {
		return color;
	}
	
	public ChessPosition getChesssPosition() {
		return ChessPosition.fromPosition(position);
	}
	//Metodo que testa se existe uma peça na posição e se é uma peça do oponente
	protected boolean isThereOpponentPiece(Position position) {
		ChessPiece p = (ChessPiece) getBoard().piece(position);
		return p != null  && p.getColor() != color;
		
	}
}
