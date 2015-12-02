package br.com.danielhabib.snake.rules;

import com.badlogic.gdx.math.Vector2;

public class Piece {

	private Vector2 point;
	private Direction direction;

	public Piece(Vector2 point, Direction direction) {
		this.point = point;
		this.direction = direction;
	}

	public Vector2 getPoint() {
		return point.cpy();
	}

	public Direction getDirection() {
		return direction;
	}

	public Vector2 getVector2() {
		return direction.getVector2().cpy();
	}

	public Piece move() {
		// FIXME: speed
		Vector2 newPoint = point.cpy().add(direction.getVector2());
		return new Piece(newPoint, direction);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((direction == null) ? 0 : direction.hashCode());
		result = prime * result + ((point == null) ? 0 : point.hashCode());
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
		if (point == null) {
			if (other.point != null) {
				return false;
			}
		} else if (!point.equals(other.point)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "Piece [" + point + ":" + direction + "]";
	}

	public Piece move(Vector2 finalPoint) {
		return new Piece(finalPoint, direction);
	}

	public Piece turn(Direction newDirection) {
		return new Piece(point.cpy(), newDirection);
	}

}
