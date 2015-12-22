package br.com.danielhabib.snake.rules;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.utils.Array;

public class Snake extends Actor {

	private Array<Piece> pieces;
	private Texture pieceTexture;
	private Vector2 velocity;
	private boolean isDead;

	public Snake(Array<Piece> pieces, Texture pieceTexture, Vector2 velocity) {
		this.pieces = pieces;
		this.pieceTexture = pieceTexture;
		this.velocity = velocity;
	}

	public Piece getTail() {
		return pieces.get(getTailIndex());
	}

	public Snake addTail() {
		Piece oldTail = getTail();
		Piece newTail = new Piece(new Vector2(0, 0), getTextureOf(oldTail));
		setNewPositionAndRotation(oldTail, newTail);
		oldTail.setTexture(pieceTexture);
		pieces.add(newTail);
		getStage().addActor(newTail);
		return this;
	}

	public Snake move(float delta) {
		Piece snakeHead = pieces.get(0);
		int snakeLength = pieces.size - 1;
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

	public Vector2 getNextPosition(float delta) {
		return getPosition().add(getVelocity().scl(delta));
	}

	public Snake removeTail() {
		int size = pieces.size;
		if (size <= 2) {
			return this;
		} else {
			Piece piece = pieces.get(getTailIndex() - 1);
			piece.setTexture(getTextureOf(getTail()));
			getTail().remove();
			pieces.removeValue(getTail(), true);
		}
		return this;
	}

	public Piece getHead() {
		return pieces.get(0);
	}

	private int getTailIndex() {
		return pieces.size - 1;
	}

	public Vector2 getPosition() {
		return getHead().getPosition().cpy();
	}

	public Vector2 getVelocity() {
		return velocity.cpy();
	}

	@Override
	public String toString() {
		return pieces.toString() + " VELOCITY: " + velocity;
	}

	public Snake turn(float angle) {
		velocity.rotate(angle);
		getHead().setRotation(velocity.angle());
		return this;
	}

	public Snake revert() {
		Texture headTexture = getTextureOf(getHead());
		getHead().setTexture(getTextureOf(getTail()));
		getTail().setTexture(headTexture);

		getHead().rotateBy(180);
		getTail().rotateBy(180);
		velocity.setAngle(getTail().getRotation());

		pieces.reverse();
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
		int snakeLength = pieces.size - 1;
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

	/**
	 * @param percent
	 *            should be, for instance, 1.1f to increase and 0.9f to
	 *            decrease.
	 */
	public void incSpeed(float percent) {
		velocity.scl(percent);
	}

	public Rectangle getBounds() {
		return getHead().getBounds();
	}

	public boolean isEatingItSelf() {
		Piece head = getHead();
		// Disconsiders the first piece.
		for (int i = 2; i < pieces.size; i++) {
			Rectangle headBounds = head.getBounds();
			Rectangle pieceBounds = pieces.get(i).getBounds();
			if (headBounds.contains(pieceBounds) || headBounds.overlaps(pieceBounds)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean hasActions() {
		for (Piece piece : pieces) {
			if (piece.hasActions()) {
				return true;
			}
		}
		return false;
	}

	public void die() {
		int sign = 1;
		for (Piece piece : pieces) {
			sign *= -1;
			piece.addAction(Actions.rotateBy(sign * 720f, 0.5f));
			piece.addAction(Actions.fadeOut(1f));
		}
		died();
	}

	private void died() {
		this.isDead = true;
	}

	public boolean isDead() {
		return isDead;
	}

}
