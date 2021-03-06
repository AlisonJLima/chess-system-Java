package chess;

import boardgame.Board;
import boardgame.Piece;
import boardgame.Position;
import chess.pieces.King;
import chess.pieces.Rook;

public class ChessMatch {
	// Classe que implementa as regras do jogo!

	private Board board;

	public ChessMatch() {
		board = new Board(8, 8);
		initialSetup();
	}

	public ChessPiece[][] getPieces() {
		ChessPiece[][] mat = new ChessPiece[board.getRows()][board.getColumns()];
		for (int i = 0; i < board.getRows(); i++) {
			for (int j = 0; j < board.getColumns(); j++) {
				mat[i][j] = (ChessPiece) board.piece(i, j);

			}
		}
		return mat;
	}
	/* Metodo para movimentar pe�as , de uma posi��o de origem para uma posi��o de destino.
	 * - converte a ChessPosition para uma posi��o de Matriz
	 * - testa se tem pe�a na posi�ao de origem
	 */
	public ChessPiece performChessMove(ChessPosition sourcePosition, ChessPosition targetPosition) {
		Position source = sourcePosition.toPosition();
		Position target = targetPosition.toPosition();
		validateSourcePosition(source);
		Piece capturedPiece = makeMove(source, target);
return (ChessPiece)capturedPiece;		
	}
	
	// Metodo que valida a existencia de uma pe�a na posi�ao de origem
	private void validateSourcePosition(Position position) {
		if(!board.thereIsAPiece(position)){
			throw new ChessException("Nao existe pe�a na posi��o de origem!");
		}
		if(!board.piece(position).isThereAnyPossibleMove()) {
			throw new ChessException("Nao existe movimentos possiveis para essa pe�a!");
		}
	}	
		/*	Metodo responsavel pela logica da movimenta��o das pe�as
		 * 	- remove a pe�a da posi�ao de origem
		 *  - remove a pe�a da posicao de destino (caso exista)
		 *  - coloca a pe�a da posi��o de origem
		*/
		private Piece makeMove(Position source, Position target) {
			Piece p = board.removePiece(source);
			Piece capturedPiece = board.removePiece(target);
			board.placePiece(p, target);
			return capturedPiece;
		}


	// inserir pe�as no formato de posi��o de xadrez
	private void placeNewPiece(char column, int row, ChessPiece piece) {
		board.placePiece(piece, new ChessPosition(column, row).toPosition());
	}

	private void initialSetup() {
		placeNewPiece('c', 1, new Rook(board, Color.WHITE));
		placeNewPiece('c', 2, new Rook(board, Color.WHITE));
		placeNewPiece('d', 2, new Rook(board, Color.WHITE));
		placeNewPiece('e', 2, new Rook(board, Color.WHITE));
		placeNewPiece('e', 1, new Rook(board, Color.WHITE));
		placeNewPiece('d', 1, new King(board, Color.WHITE));

		placeNewPiece('c', 7, new Rook(board, Color.BLACK));
		placeNewPiece('c', 8, new Rook(board, Color.BLACK));
		placeNewPiece('d', 7, new Rook(board, Color.BLACK));
		placeNewPiece('e', 7, new Rook(board, Color.BLACK));
		placeNewPiece('e', 8, new Rook(board, Color.BLACK));
		placeNewPiece('d', 8, new King(board, Color.BLACK));

	}
}