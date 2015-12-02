package br.com.danielhabib.snake.rules;

import java.util.ArrayList;
import java.util.List;
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
		Vector2 tailDirection = tail.getDirection();
		Vector2 point = tail.getPoint().cpy();
		Vector2 newPoint = point.sub(tail.getDirection());
		Piece newTail = new Piece(newPoint, tailDirection);
		pieces.push(newTail);
		return new Snake(pieces);
	}

	public Snake move(Vector2 Vector2) {
		Stack<Piece> newPieces = new Stack<Piece>();
		List<Piece> list = new ArrayList<Piece>(getPieces());
		Vector2 headVector2 = Vector2;
		Vector2 headDirection = list.get(0).getDirection();
		for (int i = 0; i < list.size(); i++) {
			Piece moved = list.get(i).move(headVector2);
			Piece turned = moved.turn(headDirection);
			newPieces.push(turned);
			headVector2 = list.get(i).getPoint();
			headDirection = list.get(i).getDirection();
		}
		return new Snake(newPieces);
	}

	public Snake move() {
		return move(getPosition().add(getDirection()));
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

	public Vector2 getDirection() {
		return getHead().getDirection().cpy();
	}

	@Override
	public String toString() {
		return pieces.toString();
	}

	public Snake turn(Vector2 direction) {
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
			Piece newPiece = new Piece(pop.getPoint(), pop.getDirection().rotate(180));
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
