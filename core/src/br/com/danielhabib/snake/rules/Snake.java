package br.com.danielhabib.snake.rules;

import java.util.List;
import java.util.Stack;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

import br.com.danielhabib.snake.game.Head;
import br.com.danielhabib.snake.game.Tail;

public class Snake implements SnakeDrawable {

	private List<Piece> pieces;
	private Texture pieceTexture;

	public Snake(List<Piece> pieces, Texture pieceTexture) {
		this.pieces = pieces;
		this.pieceTexture = pieceTexture;
	}

	public Piece getTail() {
		return pieces.get(getTailIndex());
	}

	public Snake addTail(int x, int y) {
		pieces.add(new Piece(new Vector2(x, y), getTail().getNormDirection(), getTextureOf(getHead())));
		return this;
	}

	public Snake addTail() {
		Piece tail = getTail();
		Direction tailDirection = tail.getNormDirection();
		Vector2 point = tail.getPosition().cpy();
		Vector2 newPoint = point.sub(tail.getDirection());
		Piece newTail = new Tail(newPoint, tailDirection, tail.getSprite().getTexture());
		Piece newPiece = new Piece(tail.getPosition(), tailDirection, pieceTexture);

		pieces.remove(getTailIndex());
		pieces.add(newPiece);
		pieces.add(newTail);
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
		for (int i = 1; i < pieces.size(); i++) {
			nextPositions.push(pieces.get(i).getNextPosition());
		}
		nextPositions.push(getHead().getNextPosition());
		return nextPositions;
	}

	public Snake move() {
		return move(getNextPosition());
	}

	public Vector2 getNextPosition() {
		return getPosition().add(getDirection().getVector2());
	}

	public Snake removeTail() {
		int size = pieces.size();
		if (size == 1) {//only head
			pieces.remove(0);
		} else if (size == 2) {// head and tail
			pieces.remove(getTailIndex());
		} else {
			Piece piece = pieces.get(getTailIndex() - 1);
			getTail().move(piece.getPosition());
			getTail().turn(piece.getNormDirection());
			pieces.remove(piece);
		}
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
		Piece newHeadPiece = new Head(newHead.getPosition(), newHead.getNormDirection().invert(), getTextureOf(getHead()));
		newPieces.push(newHeadPiece);
		Texture tailTexture = getTextureOf(getTail());
		while (piecesCopy.size() > 1) {
			Piece pop = piecesCopy.pop();
			Piece newPiece = new Piece(pop.getPosition(), pop.getNormDirection().invert(), pieceTexture);
			newPieces.push(newPiece);
		}
		if (piecesCopy.size() == 1) {
			Piece pop = piecesCopy.pop();
			Piece newPiece = new Tail(pop.getPosition(), pop.getNormDirection().invert(), tailTexture);
			newPieces.push(newPiece);
		}
		this.pieces = newPieces;
		return this;
	}

	private Texture getTextureOf(Piece piece) {
		return piece.getSprite().getTexture();
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

	@Override
	public void update() {
		for (Piece piece : pieces) {
			piece.update();
		}
	}

	@Override
	public void render(SpriteBatch batch) {
		for (Piece piece : pieces) {
			piece.render(batch);
		}
	}

	@Override
	public void dispose() {
		for (Piece piece : pieces) {
			piece.dispose();
		}
	}
}
