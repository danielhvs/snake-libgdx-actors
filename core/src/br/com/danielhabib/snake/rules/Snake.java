package br.com.danielhabib.snake.rules;

import java.util.Stack;

import com.badlogic.gdx.math.Vector2;

public class Snake {

	private Stack<Piece> pieces;

	public Snake() {
	}

	public Snake(Stack<Piece> pieces) {
		this.pieces = pieces;
	}

	public Piece getTail() {
		return pieces.get(getTailIndex());
	}

	public Snake addTail(int x, int y) {
		pieces.push(new Piece(new Vector2(x, y), getTail().getDirection()));
		return new Snake(pieces);
	}

	public Snake addTail() {
		Piece tail = getTail();
		Direction tailDirection = tail.getDirection();
		Vector2 point = tail.getPoint().cpy();
		Vector2 newPoint = point.sub(tail.getVector2());
		Piece newTail = new Piece(newPoint, tailDirection);
		pieces.push(newTail);
		return new Snake(pieces);
	}

	public Snake move(Vector2 newPoint) {
		Stack<Piece> newPieces = new Stack<Piece>();
		Vector2 headPoint = newPoint;
		Stack<Piece> stack = getPieces();
		Direction headDirection = stack.get(0).getDirection();
		for (int i = 0; i < stack.size(); i++) {
			Piece piece = stack.get(i);
			Piece newPiece = piece.move(headPoint).turn(headDirection);
			newPieces.push(newPiece);
			headPoint = piece.getPoint();
			headDirection = piece.getDirection();
		}
		return new Snake(newPieces);
	}

	public Snake move() {
		// FIXME: speed
		return move(getPosition().add(getDirection().getVector2()));
	}

	public Snake removeTail() {
		pieces.pop();
		return new Snake(getPieces());
	}

	public Piece getHead() {
		return getPieces().get(0);
	}

	private int getTailIndex() {
		return pieces.size() - 1;
	}

	public Vector2 getPosition() {
		return getHead().getPoint().cpy();
	}

	public Direction getDirection() {
		return getHead().getDirection();
	}

	@Override
	public String toString() {
		return pieces.toString();
	}

	public Snake turn(Direction direction) {
		Piece newHead = getHead().turn(direction);
		int headIndex = 0;
		Stack<Piece> newPieces = getPieces();
		newPieces.remove(headIndex);
		newPieces.insertElementAt(newHead, headIndex);
		return new Snake(newPieces);
	}

	public Snake revert() {
		Stack<Piece> newPieces = new Stack<Piece>();
		Stack<Piece> piecesCopy = getPieces();
		while (!piecesCopy.isEmpty()) {
			Piece pop = piecesCopy.pop();
			Piece newPiece = new Piece(pop.getPoint(), pop.getDirection().invert());
			newPieces.push(newPiece);
		}
		return new Snake(newPieces);
	}

	public Stack<Piece> getPieces() {
		Stack<Piece> copy = new Stack<Piece>();
		copy.addAll(pieces);
		return copy;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((pieces == null) ? 0 : pieces.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		Snake other = (Snake) obj;
		if (pieces == null) {
			if (other.pieces != null) {
				return false;
			}
		} else if (!pieces.equals(other.pieces)) {
			return false;
		}
		return true;
	}

}
