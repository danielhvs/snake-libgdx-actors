package br.com.danielhabib.snake.rules;

import java.util.HashMap;
import java.util.Map;

import com.badlogic.gdx.math.Vector2;

public enum Direction {
	UP(new Vector2(0, -1)), DOWN(new Vector2(0, 1)), LEFT(new Vector2(-1, 0)), RIGHT(new Vector2(1, 0)), NOP(new Vector2(0, 0));
	private Vector2 direction;

	private Direction(Vector2 direction) {
		this.direction = direction;
	}

	public Vector2 getVector2() {
		return direction;
	}

	private static Map<Direction, Direction> invertedDirections = new HashMap<Direction, Direction>();
	static {
		invertedDirections.put(UP, DOWN);
		invertedDirections.put(DOWN, UP);
		invertedDirections.put(LEFT, RIGHT);
		invertedDirections.put(RIGHT, LEFT);
	}

	public Direction invert() {
		return invertedDirections.get(this);
	}
}
