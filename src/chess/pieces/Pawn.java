package chess.pieces;

import boardgame.Board;
import boardgame.Position;
import chess.ChessPiece;
import chess.Color;

public class Pawn extends ChessPiece {

	public Pawn(Board board, Color color) {
		super(board, color);
	}

	@Override
	public boolean[][] possibleMoves() {
		boolean[][] mat = new boolean[getBoard().getRows()][getBoard().getColumns()];

		Position p = new Position(0, 0);

		// Regra de Peao branco; anda para cima ( row - 1 ) se existir e estiver vazia
		if (getColor() == Color.WHITE) {
			p.setValues(position.getRow() - 1, position.getColumn());
			if (getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) {
				mat[p.getRow()][p.getColumn()] = true;
			}
			// regra de movimento especial do peão
				p.setValues(position.getRow() - 2, position.getColumn());
				Position p2 = new Position(position.getRow() - 1, position.getColumn());
				if (getBoard().positionExists(p) && !getBoard().thereIsAPiece(p) && getBoard().positionExists(p2)
						&& !getBoard().thereIsAPiece(p2) && getmoveCount() == 0) {
					mat[p.getRow()][p.getColumn()] = true;
				}
				// teste diagonal
				p.setValues(position.getRow() - 1, position.getColumn() - 1);
				if (getBoard().positionExists(p) && isThereOpponentPiece(p)) {
					mat[p.getRow()][p.getColumn()] = true;
				}
				// teste diagonal²
				p.setValues(position.getRow() - 1, position.getColumn() + 1);
				if (getBoard().positionExists(p) && isThereOpponentPiece(p)) {
					mat[p.getRow()][p.getColumn()] = true;
				}
			} 
			else {
					p.setValues(position.getRow() + 1, position.getColumn());
					if (getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) {
						mat[p.getRow()][p.getColumn()] = true;
				}
				// regra de movimento especial do peão
				p.setValues(position.getRow() + 2, position.getColumn());
				Position p2 = new Position(position.getRow() + 1, position.getColumn());

				if (getBoard().positionExists(p) && !getBoard().thereIsAPiece(p) && getBoard().positionExists(p2)
						&& !getBoard().thereIsAPiece(p2) && getmoveCount() == 0) {
						mat[p.getRow()][p.getColumn()] = true;
					}
					// teste diagonal
					p.setValues(position.getRow() + 1, position.getColumn() - 1);
					if (getBoard().positionExists(p) && isThereOpponentPiece(p)) {
						mat[p.getRow()][p.getColumn()] = true;
					}
					// teste diagonal²
					p.setValues(position.getRow() + 1, position.getColumn() + 1);
					if (getBoard().positionExists(p) && isThereOpponentPiece(p)) {
						mat[p.getRow()][p.getColumn()] = true;
					}
				}
				return mat;
			}
			@Override
			public String toString() {
				return "P";
		}
	}

