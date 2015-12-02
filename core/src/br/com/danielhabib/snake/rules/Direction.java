package br.com.danielhabib.snake.rules;

import java.util.HashMap;
import java.util.Map;

import com.badlogic.gdx.math.Vector2;

public enum Direction {
	UP(new Vector2(0, -1), 0.0f), DOWN(new Vector2(0, 1), 180.0f), LEFT(new Vector2(-1, 0), 270.0f), RIGHT(new Vector2(1, 0), 90.0f);
	private Vector2 direction;
	private float rotation;

	private Direction(Vector2 direction, float rotation) {
		this.direction = direction;
		this.rotation = rotation;
	}

	public float getRotation() {
		return rotation;
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
