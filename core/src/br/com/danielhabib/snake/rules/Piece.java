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
		pos.add(direction.getVector2());
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
		result = prime * result + ((pos == null) ? 0 : pos.hashCode());
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
		if (pos == null) {
			if (other.pos != null) {
				return false;
			}
		} else if (!pos.equals(other.pos)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "Piece [" + pos + ":" + direction + "]";
	}

	public Piece move(Vector2 finalPoint) {
		this.pos = finalPoint;
		return this;
	}

	public Piece turn(Direction newDirection) {
		this.direction = newDirection;
		return this;
	}

	private static float x = 0;
	@Override
	public void update() {
		sprite.setPosition(pos.x * Entity.SIZE, pos.y * Entity.SIZE);
		x += Math.PI / 400;
		// sprite.setRotation(direction.getRotation());
		// sprite.rotate((float) (12.5f * Math.sin(x)));
		sprite.rotate(20);
	}

}