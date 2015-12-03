package br.com.danielhabib.snake.rules;

import java.util.Stack;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class Snake {

	private Stack<Piece> pieces;

	public Snake(Stack<Piece> pieces) {
		this.pieces = pieces;
	}

	public Piece getTail() {
		return pieces.get(getTailIndex());
	}

	public Snake addTail(int x, int y) {
		pieces.push(new Piece(new Vector2(x, y), getTail().getNormDirection(), getHead().getTexture()));
		return this;
	}

	public Snake addTail() {
		Piece tail = getTail();
		Direction tailDirection = tail.getNormDirection();
		Vector2 point = tail.getPosition().cpy();
		Vector2 newPoint = point.sub(tail.getDirection());
		Piece newTail = new Piece(newPoint, tailDirection, tail.getTexture());
		pieces.push(newTail);
		return this;
	}

	public Snake move(Vector2 newPoint) {
		Vector2 headPoint = newPoint;
		Direction headDirection = pieces.get(0).getNormDirection();
		for (int i = 0; i < pieces.size(); i++) {
			Piece piece = pieces.get(i);
			Vector2 lastHeadPoint = piece.getPosition();
			Direction lastDirection = piece.getNormDirection();

			piece.move(headPoint).turn(headDirection);

			headPoint = lastHeadPoint;
			headDirection = lastDirection;
		}
		return this;
	}

	public Stack<Vector2> getNextPositions() {
		Stack<Vector2> nextPositions = new Stack<Vector2>();
		for (Piece piece : pieces) {
			nextPositions.add(piece.getNextPosition());
		}
		return nextPositions;
	}

	public Snake move() {
		return move(getNextPosition());
	}

	public Vector2 getNextPosition() {
		return getPosition().add(getDirection().getVector2());
	}

	public Snake removeTail() {
		pieces.pop();
		return this;
	}

	public Piece getHead() {
		return copyPieces().get(0);
	}

	private int getTailIndex() {
		return pieces.size() - 1;
	}

	public Vector2 getPosition() {
		return getHead().getPosition().cpy();
	}

	public Direction getDirection() {
		return getHead().getNormDirection();
	}

	@Override
	public String toString() {
		return pieces.toString();
	}

	public Snake turn(Direction direction) {
		getHead().turn(direction);
		return this;
	}

	public Snake revert() {
		Stack<Piece> newPieces = new Stack<Piece>();
		Stack<Piece> piecesCopy = copyPieces();
		Piece newHead = piecesCopy.pop();
		Piece newHeadPiece = new Piece(newHead.getPosition(), newHead.getNormDirection().invert(), getHead().getTexture());
		newPieces.push(newHeadPiece);
		Texture tailTexture = getTail().getTexture();
		while (!piecesCopy.isEmpty()) {
			Piece pop = piecesCopy.pop();
			Piece newPiece = new Piece(pop.getPosition(), pop.getNormDirection().invert(), tailTexture);
			newPieces.push(newPiece);
		}
		this.pieces = newPieces;
		return this;
	}

	public Stack<Piece> copyPieces() {
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

	public void draw(SpriteBatch batch) {
		for (Piece piece : pieces) {
			piece.draw(batch);
		}
	}
}
