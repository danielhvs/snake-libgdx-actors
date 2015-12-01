package br.com.danielhabib.snake.rules;

import com.badlogic.gdx.math.Vector2;

public enum Direction {
	UP(new Vector2(0, -1)), DOWN(new Vector2(0, 1)), LEFT(new Vector2(-1, 0)), RIGHT(new Vector2(1, 0)), NOP(new Vector2(0, 0));
	private Vector2 direction;

	private Direction(Vector2 direction) {
		this.direction = direction;
	}

	public Vector2 getDirection() {
		return direction;
	}

	public Direction invert() {
		return Direction.valueOf(direction.cpy().rotate(180));
	}

	public static Direction valueOf(Vector2 point) {
		for (Direction direction : Direction.values()) {
			if (direction.getDirection().equals(point)) {
				return direction;
			}
		}
		return null;
	}
}
