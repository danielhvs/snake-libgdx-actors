package br.com.danielhabib.snake.rules;

import java.util.List;
import java.util.Stack;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;

import br.com.danielhabib.snake.game.Tail;

public class Snake extends Actor {

	private List<Piece> pieces;
	private Texture pieceTexture;
	private Vector2 direction;
	private float speed;

	public Snake(List<Piece> pieces, Texture pieceTexture, Vector2 direction) {
		this.pieces = pieces;
		this.pieceTexture = pieceTexture;
		this.direction = direction;
		this.speed = 5f;
	}

	public Piece getTail() {
		return pieces.get(getTailIndex());
	}

	public Snake addTail() {
		Piece oldTail = getTail();
		Piece newTail = new Tail(new Vector2(0, 0), getTextureOf(oldTail));
		setNewPositionAndRotation(oldTail, newTail);
		oldTail.setTexture(pieceTexture);
		pieces.add(newTail);
		newTail.debug();
		getStage().addActor(newTail);
		return this;
	}

	public Snake move(float delta) {
		Piece snakeHead = pieces.get(0);
		int snakeLength = pieces.size() - 1;
		Vector2 newPosition = getNextPosition(delta);
		snakeHead.setPosition(newPosition.x, newPosition.y);
		for (int i = 1; i <= snakeLength; i++) {
			Piece partBefore = pieces.get(i - 1);
			Piece thisPart = pieces.get(i);
			setNewPositionAndRotation(partBefore, thisPart);
		}
		return this;
	}

	private void setNewPositionAndRotation(Piece pieceBefore, Piece thisPiece) {
		float deltaX = pieceBefore.getX() - thisPiece.getX();
		float deltaY = pieceBefore.getY() - thisPiece.getY();
		float angle = (float) Math.atan2(deltaY, deltaX);

		thisPiece.setX(pieceBefore.getX() - (float) Math.cos(angle) * pieceBefore.getWidth());
		thisPiece.setY(pieceBefore.getY() - (float) Math.sin(angle) * pieceBefore.getHeight());
		thisPiece.setRotation((float) Math.toDegrees(angle));
	}

	public Stack<Vector2> getNextPositions() {
		// Stack<Vector2> nextPositions = new Stack<Vector2>();
		// for (int i = 1; i < pieces.size(); i++) {
		// nextPositions.push(pieces.get(i).getNextPosition());
		// }
		// nextPositions.push(getHead().getNextPosition());
		// return nextPositions;
		return new Stack<Vector2>();
	}

	public Vector2 getNextPosition(float delta) {
		return getPosition().add(getDirection().scl(speed * delta));
	}

	public Snake removeTail() {
		int size = pieces.size();
		if (size <= 2) {
			return this;
		} else {
			Piece piece = pieces.get(getTailIndex() - 1);
			getTail().setPosition(piece.getPosition().x, piece.getPosition().y);
			getTail().setRotation(piece.getRotation());
			piece.remove();
			pieces.remove(piece);
		}
		return this;
	}

	public Piece getHead() {
		return pieces.get(0);
	}

	private int getTailIndex() {
		return pieces.size() - 1;
	}

	public Vector2 getPosition() {
		return getHead().getPosition().cpy();
	}

	public Vector2 getDirection() {
		return direction.cpy();
	}

	@Override
	public String toString() {
		return pieces.toString() + " DIRECTION: " + direction;
	}

	public Snake turn(Vector2 direction) {
		this.direction = direction;
		getHead().setRotation(direction.angle());
		return this;
	}

	public Snake revert() {
		Vector2 headPosition = getHead().getPosition();
		Vector2 tailPosition = getTail().getPosition();
		getHead().setPosition(tailPosition.x, tailPosition.y);
		getTail().setPosition(headPosition.x, headPosition.y);
		direction.rotate(180);
		getHead().rotateBy(180);
		return this;
	}

	private Texture getTextureOf(Piece piece) {
		return piece.getSprite().getTexture();
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

	public Snake move(Vector2 newPoint) {
		// moves instantly.
		Piece snakeHead = pieces.get(0);
		int snakeLength = pieces.size() - 1;
		Vector2 newPosition = newPoint;
		snakeHead.setPosition(newPosition.x, newPosition.y);
		for (int i = 1; i <= snakeLength; i++) {
			Piece partBefore = pieces.get(i - 1);
			Piece thisPart = pieces.get(i);
			float deltaX = partBefore.getX() - thisPart.getX();
			float deltaY = partBefore.getY() - thisPart.getY();
			float angle = (float) Math.atan2(deltaY, deltaX);

			// + sign here.
			thisPart.setX(partBefore.getX() + (float) Math.cos(angle) * thisPart.getWidth());
			thisPart.setY(partBefore.getY() + (float) Math.sin(angle) * thisPart.getHeight());
			thisPart.setRotation((float) Math.toDegrees(angle));
		}
		return this;
	}

	public void incSpeed(float offset) {
		this.speed += offset;
	}

	public void decSpeed(float offset) {
		this.speed -= offset;
	}

	public float getSpeed() {
		return speed;
	}

	public Rectangle getBounds() {
		return getHead().getBounds();
	}


}
