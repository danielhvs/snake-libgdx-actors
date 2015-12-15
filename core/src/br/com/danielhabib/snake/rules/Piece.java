package br.com.danielhabib.snake.rules;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

public class Piece extends Entity {
	protected Direction direction;

	public Piece(Vector2 pos, Direction direction, Texture texture) {
		super(texture, pos);
		this.direction = direction;
	}

	public Direction getNormDirection() {
		return direction;
	}

	public Vector2 getDirection() {
		return direction.getVector2().cpy();
	}

	public Piece move() {
		Vector2 newLocation = getPosition().add(direction.getVector2());
		setX(newLocation.x);
		setY(newLocation.y);
		return this;
	}

	public Vector2 getNextPosition() {
		return getPosition().add(getDirection());
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((direction == null) ? 0 : direction.hashCode());
		result = prime * result + ((getPosition() == null) ? 0 : getPosition().hashCode());
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
		Piece other = (Piece) obj;
		if (direction != other.direction) {
			return false;
		}
		if (getPosition() == null) {
			if (other.getPosition() != null) {
				return false;
			}
		} else if (!getPosition().equals(other.getPosition())) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "Piece [" + getPosition() + ":" + direction + "]";
	}

	public Piece move(Vector2 finalPoint) {
		setX(finalPoint.x);
		setY(finalPoint.y);
		return this;
	}

	public Piece turn(Direction newDirection) {
		this.direction = newDirection;
		return this;
	}

	private float degrees = 0f;

	@Override
	public void updateAct() {
		degrees += 10f;
		setRotation(degrees);
	}

}