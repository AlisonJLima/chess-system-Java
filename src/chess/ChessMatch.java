package chess;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import boardgame.Board;
import boardgame.Piece;
import boardgame.Position;
import chess.pieces.King;
import chess.pieces.Rook;

public class ChessMatch {
	// Classe que implementa as regras do jogo!

	private int turn;
	private Color currentPlayer;
	private Board board;
	private boolean check;

	private List<Piece> piecesOnTheBoard = new ArrayList<>();
	private List<Piece> capturedPieces = new ArrayList<>();

	public ChessMatch() {
		board = new Board(8, 8);
		turn = 1;
		currentPlayer = Color.WHITE;
		initialSetup();

	}

	public int getTurn() {
		return turn;
	}

	public Color getCurrentPlayer() {
		return currentPlayer;
	}

	public boolean getCheck() {
		return check;
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
	// Metodo para imprimir as posi�oes possiveis atraves da posi��o de origem

	public boolean[][] possibleMoves(ChessPosition sourcePosition) {
		Position position = sourcePosition.toPosition();
		validateSourcePosition(position);
		return board.piece(position).possibleMoves();

	}

	/*
	 * Metodo para movimentar pe�as , de uma posi��o de origem para uma posi��o de
	 * destino. - converte a ChessPosition para uma posi��o de Matriz - testa se tem
	 * pe�a na posi�ao de origem
	 */
	public ChessPiece performChessMove(ChessPosition sourcePosition, ChessPosition targetPosition) {
		Position source = sourcePosition.toPosition();
		Position target = targetPosition.toPosition();
		validateSourcePosition(source);
		validateTargetPosition(source, target);
		Piece capturedPiece = makeMove(source, target);

		if (testeCheck(currentPlayer)) {
			undoMove(source, target, capturedPiece);
			throw new ChessException("Voc� n�o pode se colocar em xeque");
		}
		// condi��o que testa se o adversario entrou me xeque
		check = (testeCheck(opponent(currentPlayer))) ? true : false;

		nextTurn();
		return (ChessPiece) capturedPiece;
	}

	// Metodo que valida a existencia de uma pe�a na posi�ao de origem
	private void validateSourcePosition(Position position) {
		if (!board.thereIsAPiece(position)) {
			throw new ChessException("Nao existe pe�a na posi��o de origem!");
		}

		if (currentPlayer != ((ChessPiece) board.piece(position)).getColor()) {
			throw new ChessException("A pe�a escolhida n�o � sua!");
		}

		if (!board.piece(position).isThereAnyPossibleMove()) {
			throw new ChessException("Nao existe movimentos possiveis para essa pe�a!");
		}
	}

	private void validateTargetPosition(Position source, Position target) {
		if (!board.piece(source).possibleMove(target)) {
			throw new ChessException("A pe�a na posi��o de origem nao pode se mover para a posi��o de destino!");
		}
	}

	/*
	 * Metodo responsavel pela logica da movimenta��o das pe�as - remove a pe�a da
	 * posi�ao de origem - remove a pe�a da posicao de destino (caso exista) -
	 * coloca a pe�a da posi��o de origem
	 */
	private Piece makeMove(Position source, Position target) {
		Piece p = board.removePiece(source);
		Piece capturedPiece = board.removePiece(target);
		board.placePiece(p, target);

		if (capturedPiece != null) {
			piecesOnTheBoard.remove(capturedPiece);
			capturedPieces.add(capturedPiece);
		}
		return capturedPiece;
	}

	// Metodo para desfazer movimento, em caso de captura, remove a pe�a da lista de
	// pe�as capturadas e devolve para a lista de pe�as no tabuleiro.
	private void undoMove(Position source, Position target, Piece capturedPiece) {
		Piece p = board.removePiece(target);
		board.placePiece(p, source);
		if (capturedPiece != null) {
			board.placePiece(capturedPiece, target);
			capturedPieces.remove(capturedPiece);
			piecesOnTheBoard.add(capturedPiece);
		}
	}

	// metodo para troca de turno
	private void nextTurn() {
		turn++;
		// condi��o ternaria para troca de jogador por turno
		currentPlayer = (currentPlayer == Color.WHITE) ? Color.BLACK : Color.WHITE;
	}

	// Se a cor for branca oponente = Preto, se nao, oponente = branco.
	private Color opponent(Color color) {
		return (color == color.WHITE) ? Color.BLACK : Color.WHITE;
	}

	// identificando o rei de cada Cor
	private ChessPiece king(Color color) {
		// filtro: todas as pe�as X do tabuleiro tal que a cor seja da cor passada como
		// argumento
		List<Piece> list = piecesOnTheBoard.stream().filter(x -> ((ChessPiece) x).getColor() == color)
				.collect(Collectors.toList());
		for (Piece p : list) {
			if (p instanceof King) {
				return (ChessPiece) p;
			}
		}
		throw new IllegalStateException("Nao existe rei da cor" + color + " no tabuleiro");
	}

	// metodo para testar se o rei est� em xeque
	private boolean testeCheck(Color color) {
		Position kingPosition = king(color).getChesssPosition().toPosition();
		List<Piece> opponentPieces = piecesOnTheBoard.stream()
				.filter(x -> ((ChessPiece) x).getColor() == opponent(color)).collect(Collectors.toList());
		for (Piece p : opponentPieces) {
			boolean[][] mat = p.possibleMoves(); // movimentos possiveis da pe�a adversaria P
			if (mat[kingPosition.getRow()][kingPosition.getColumn()]) {
				return true;
			}
		}
		return false;
	}

	// inserir pe�as no formato de posi��o de xadrez
	private void placeNewPiece(char column, int row, ChessPiece piece) {
		board.placePiece(piece, new ChessPosition(column, row).toPosition());
		piecesOnTheBoard.add(piece);
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