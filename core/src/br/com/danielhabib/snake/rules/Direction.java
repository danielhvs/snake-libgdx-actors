package br.com.danielhabib.snake.rules;

import com.badlogic.gdx.math.Vector2;

public class Direction {
	public static final Vector2 UP = new Vector2(0, Entity.SIZE);
	public static final Vector2 DOWN = new Vector2(0, -Entity.SIZE);
	public static final Vector2 LEFT = new Vector2(-Entity.SIZE, 0);
	public static final Vector2 RIGHT = new Vector2(Entity.SIZE, 0);
}
